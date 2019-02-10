package com.istic.m2.vv.processor;

import com.istic.m2.vv.processor.adder.Adder;
import com.istic.m2.vv.processor.analyzer.Analyzer;
import com.istic.m2.vv.processor.collector.Collector;
import com.istic.m2.vv.processor.impl.RunnerImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import spoon.Launcher;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RunnerTest {
    private String mavenProject = "../assertion-generation-entity";
    private String srcFolder = this.mavenProject + "/src/main/java";
    private String testFolder = this.mavenProject + "/src/test/java";
    private String testMethod = "testAge";
    private int numberOfAssertions = 2;

    private RunnerImpl runner;

    @Mock
    private Analyzer analyzer;

    @Mock
    private Collector collector;

    @Mock
    private Adder adder;

    @Mock
    private Launcher launcher;

    @Before
    public void setUp() {
        this.runner = new RunnerImpl(this.srcFolder, this.testFolder, this.testMethod, this.numberOfAssertions, this.analyzer, this.collector, this.adder);
        this.runner.setLauncher(this.launcher);
    }

    @Test
    public void run_ShouldCallAnalyzer() {
        this.runner.run();
        verify(this.analyzer).analyse();
    }

    @Test
    public void run_ShouldCallCollector() {
        this.runner.run();
        verify(this.collector).collect();
    }

    @Test
    public void run_ShouldCallAdder() {
        this.runner.run();
        verify(this.adder).add();
    }
}
