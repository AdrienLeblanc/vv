package com.istic.m2.vv.processor.analyzer;

import com.istic.m2.vv.processor.analyzer.impl.AnalyzerImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnalyzerTest {
    private Analyzer analyzer;

    @Mock
    private CtClass ctClass;

    @Mock
    private CtMethod method1;

    @Mock
    private CtMethod method2;

    @Mock
    private CtLocalVariable element;

    private Set<CtMethod> methods;

    private List<CtLocalVariable> variables;

    @Before
    public void setUp() {
        this.analyzer = new AnalyzerImpl();
        this.methods = new HashSet<>();
        this.methods.add(this.method1);
        this.methods.add(this.method2);
        this.variables = new ArrayList<>();
        this.variables.add(this.element);
    }

    @Test
    public void analyzer_FindLocalVariablesFromGivenMethod() {
        when(this.method1.getSimpleName()).thenReturn("method");
        when(this.ctClass.getMethods()).thenReturn(this.methods);
        doReturn(this.variables).when(this.method1).getElements(any());

        Map<CtMethod, List<CtLocalVariable>> expected = new HashMap<>();
        expected.put(this.method1, this.variables);

        assertEquals(expected, this.analyzer.analyze(this.ctClass, "method"));
    }

    @Test
    public void analyzer_ShouldNotFindLocalVariablesFromGivenMethod() {
        when(this.method1.getSimpleName()).thenReturn("method");
        when(this.ctClass.getMethods()).thenReturn(this.methods);

        assertEquals(0, this.analyzer.analyze(this.ctClass, "method").get(this.method1).size());
    }

    @Test
    public void analyzer_ShouldNotFindLocalVariablesWithoutMethodName() {
        when(this.ctClass.getMethods()).thenReturn(this.methods);
        assertEquals(0, this.analyzer.analyze(this.ctClass, "method").size());
    }

    @Test
    public void analyzer_ShouldNotFindLocalVariablesWithoutMethods() {
        assertEquals(0, this.analyzer.analyze(this.ctClass, "method").size());
    }

    @Test
    public void getLocalVariable_ReturnListOfLocalVariable() {
        doReturn(this.variables).when(this.method1).getElements(any());
        assertEquals(1, this.analyzer.getLocalVariables(this.method1).size());
    }

    @Test
    public void getLocalVariable_ReturnEmptyList() {
        assertEquals(0, this.analyzer.getLocalVariables(this.method1).size());
    }
}
