package com.istic.m2.vv.processor.impl;

import com.istic.m2.vv.processor.Runner;
import com.istic.m2.vv.processor.adder.Adder;
import com.istic.m2.vv.processor.analyzer.Analyzer;
import com.istic.m2.vv.processor.collector.Collector;
import spoon.Launcher;

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

    }

    @Override
    public void setLauncher(Launcher launcher) {
        this.launcher = launcher;
    }

    @Override
    public Launcher getLauncher() {
        return this.launcher;
    }
}
