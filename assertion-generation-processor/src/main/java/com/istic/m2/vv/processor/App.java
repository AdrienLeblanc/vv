package com.istic.m2.vv.processor;

/**
 * Main city
 * @author Erwan IQUEL - Adrien LEBLANC
 * @version 1.0
 */
public class App {
    public static void main( String[] args ) {
        final String srcFolder = new StringBuilder(args[0]).append("/src/main/java").toString();
        final String testFolder = new StringBuilder(args[0]).append("/src/test/java").toString();
        final String methodToAddAssertions = args[1];
        final int numberOfAssertions = (args.length < 2) ? 1 : Integer.parseInt(args[3]);
    }
}
