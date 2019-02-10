package com.istic.m2.vv.processor.analyzer.impl;

import com.istic.m2.vv.processor.analyzer.Analyzer;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnalyzerImpl implements Analyzer {
    @Override
    public Map<CtMethod, List<CtLocalVariable>> analyze(CtType<?> ctClass, String methodName) {
        Set<CtMethod<?>> ctMethods = ctClass.getMethods();
        Map<CtMethod, List<CtLocalVariable>> ret = new HashMap<>();

        for(CtMethod ctMethod : ctMethods) {
            if(methodName.equals(ctMethod.getSimpleName())) {
                ret.put(ctMethod, this.getLocalVariables(ctMethod));
            }
        }

        return ret;
    }

    @Override
    public List<CtLocalVariable> getLocalVariables(CtMethod testMethod) {
        TypeFilter filter = new TypeFilter(CtLocalVariable.class) {
            public boolean matches(CtLocalVariable localVariable) {
                return !localVariable.getType().isPrimitive();
            }
        };

        return testMethod.getElements(filter);
    }
}
