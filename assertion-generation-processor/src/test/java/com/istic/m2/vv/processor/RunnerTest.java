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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

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

    @Before
    public void setUp() {
        this.runner = new RunnerImpl(this.srcFolder, this.testFolder, this.testMethod, this.numberOfAssertions, this.analyzer, this.collector, this.adder);
    }

    @Test
    public void run_ShouldCallAnalyzer() {
        this.runner.run();
        verify(this.analyzer, atLeast(1)).analyze(any(), any());
    }

    @Test
    public void run_ShouldCallCollector() {
        this.runner.run();
        verify(this.collector, atLeast(1)).collect();
    }

    @Test
    public void run_ShouldCallAdder() {
        this.runner.run();
        verify(this.adder, atLeast(1)).add();
    }
}
