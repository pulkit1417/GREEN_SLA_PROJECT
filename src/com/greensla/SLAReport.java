package com.greensla;

public class SLAReport {
    public double executionTime;
    public boolean slaViolated;

    public SLAReport(double executionTime, boolean slaViolated) {
        this.executionTime = executionTime;
        this.slaViolated = slaViolated;
    }
}
