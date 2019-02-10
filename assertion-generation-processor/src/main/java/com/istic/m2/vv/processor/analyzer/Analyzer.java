package com.istic.m2.vv.processor.analyzer;

import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;

import java.util.List;
import java.util.Map;

/**
 * Analyzer city
 * @author Erwan IQUEL - Adrien LEBLANC
 * @version 1.0
 */
public interface Analyzer {
    /**
     * Launch analyze phase
     * @param ctClass
     * @param methodName
     */
    Map<CtMethod, List<CtLocalVariable>> analyze(CtType<?> ctClass, String methodName);

    /**
     * Get local variables from a given method
     * @param testMethod given method
     * @return local variables
     */
    List<CtLocalVariable> getLocalVariables(CtMethod testMethod);
}
