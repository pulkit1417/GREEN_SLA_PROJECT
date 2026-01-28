package com.greensla;

public class MainSimulator {

    public static void main(String[] args) {

        WorkloadConfig baseConfig = WorkloadConfig.load();

        // NORMAL run
        WorkloadConfig normal = new WorkloadConfig();
        normal.hosts = baseConfig.hosts;
        normal.vms = baseConfig.vms;
        normal.cloudlets = baseConfig.cloudlets;
        normal.mode = "NORMAL";
        normal.carbonFactor = 0.0007;

        // GREEN run
        WorkloadConfig green = new WorkloadConfig();
        green.hosts = baseConfig.hosts;
        green.vms = baseConfig.vms;
        green.cloudlets = baseConfig.cloudlets;
        green.mode = "GREEN";
        green.carbonFactor = baseConfig.carbonFactor;

        System.out.println("Running NORMAL simulation...");
        Result normalResult = GreenSLASimulator.run(normal);

        System.out.println("Running GREEN simulation...");
        Result greenResult = GreenSLASimulator.run(green);

        ResultWriter.write(normalResult, greenResult);

        System.out.println("Simulation completed.");
    }
}
