package com.istic.m2.vv.processor.impl;

import com.istic.m2.vv.processor.Runner;
import com.istic.m2.vv.processor.adder.Adder;
import com.istic.m2.vv.processor.analyzer.Analyzer;
import com.istic.m2.vv.processor.collector.Collector;
import org.apache.log4j.Level;
import spoon.Launcher;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Runner implementation city
 * @author Erwan IQUEL - Adrien LEBLANC
 * @version 1.0
 */
public class RunnerImpl implements Runner {
    private String srcFolder;
    private String testFolder;
    private String methodName;
    private int numberOfAssertions;

    private Analyzer analyzer;
    private Collector collector;
    private Adder adder;

    private Launcher launcher;

    /**
     * Constructor
     * @param srcFolder src folder of maven project
     * @param testFolder test folder of maven project
     * @param methodName method where to generate assertions
     * @param numberOfAssertions number of assertions to generate
     * @param analyzer analyzer
     * @param collector collector
     * @param adder assertion adder
     */
    public RunnerImpl(String srcFolder, String testFolder, String methodName, int numberOfAssertions, Analyzer analyzer, Collector collector, Adder adder) {
        this.srcFolder = srcFolder;
        this.testFolder = testFolder;
        this.methodName = methodName;
        this.numberOfAssertions = numberOfAssertions;
        this.analyzer = analyzer;
        this.collector = collector;
        this.adder = adder;
        this.launcher = new Launcher();
    }

    @Override
    public void run() {
        this.launcher.getEnvironment().setAutoImports(true);
        this.launcher.getEnvironment().setLevel(Level.ALL.toString());
        this.launcher.addInputResource(this.srcFolder);
        this.launcher.addInputResource(this.testFolder);
        this.launcher.addInputResource("src/main/java/com/istic/m2/vv/processor/util/Logger.java");
        this.launcher.getEnvironment().setSourceClasspath(getPathToJunit());
        this.launcher.buildModel();

        List<CtType<?>> ctClasses = this.launcher.getFactory().Class().getAll();

        for(CtType<?> ctClass : ctClasses) {
            Map<CtMethod, List<CtLocalVariable>> localVariables = this.analyzer.analyze(ctClass);

            if(!localVariables.isEmpty()) {
                for(CtMethod<?> ctMethod : localVariables.keySet()) {
                    this.collector.collect();
                }

                for(CtMethod<?> ctMethod : localVariables.keySet()) {
                    this.adder.add();
                }
            }
        }
    }

    @Override
    public void setLauncher(Launcher launcher) {
        this.launcher = launcher;
    }

    @Override
    public Launcher getLauncher() {
        return this.launcher;
    }

    private static String[] getPathToJunit() {
        Process p = null;
        try {
            if (System.getProperty("os.name").startsWith("Windows"))
                p = Runtime.getRuntime().exec("cmd /C mvn dependency:build-classpath -Dmdep.outputFile=.cp");
            else
                p = Runtime.getRuntime().exec("mvn dependency:build-classpath -Dmdep.outputFile=.cp");
            Process finalP = p;
            new Thread() {
                @Override
                public void run() {
                    while (finalP.isAlive()) {
                        try {
                            System.out.print((char) finalP.getInputStream().read());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            p.waitFor();
            BufferedReader buffer = new BufferedReader(new FileReader(".cp"));
            final String classpath = buffer.lines().collect(Collectors.joining(System.getProperty("path.separator")));
            buffer.close();
            return classpath.split(System.getProperty("path.separator"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
