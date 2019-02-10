package com.istic.m2.vv.processor;

import spoon.Launcher;

/**
 * Runner city
 * Launch assertion generation workflow
 * @author Erwan IQUEL - Adrien LEBLANC
 * @version 1.0
 */
public interface Runner {
    /**
     * Launch assertion generation workflow
     */
    public void run();

    /**
     * Setter
     * @param launcher new Launcher instance
     */
    public void setLauncher(Launcher launcher);

    /**
     * Getter
     * @return current Launcher instance
     */
    public Launcher getLauncher();
}
