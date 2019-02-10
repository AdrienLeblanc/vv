package com.istic.m2.vv.processor.impl;

import com.istic.m2.vv.processor.Runner;
import com.istic.m2.vv.processor.adder.Adder;
import com.istic.m2.vv.processor.analyzer.Analyzer;
import com.istic.m2.vv.processor.collector.Collector;
import spoon.Launcher;

/**
 * Runner implementation class
 * @author Erwan IQUEL - Adrien LEBLANC
 * @version 1.0
 */
public class RunnerImpl implements Runner {
    private Analyzer analyzer;
    private Collector collector;
    private Adder adder;
    private Launcher launcher;

    /**
     * Constructor
     * @param analyzer analyzer
     * @param collector collector
     * @param adder assertion adder
     */
    public RunnerImpl(Analyzer analyzer, Collector collector, Adder adder) {
        this.analyzer = analyzer;
        this.collector = collector;
        this.adder = adder;
        this.launcher = new Launcher();
    }

    @Override
    public void run() {

    }
}
