package com.greensla;

public class GreenSLASimulator {

    public static Result run(WorkloadConfig config) {

        EnergyModel energyModel = new EnergyModel();
        Result r = new Result();

        boolean isGreen = config.mode.equals("GREEN");

        r.mode = config.mode;
        r.tasks = config.cloudlets;

        int activeHosts = isGreen ? Math.max(1, config.hosts / 2) : config.hosts;
        r.activeHosts = activeHosts;

        double energy = energyModel.calculateEnergy(activeHosts, config.cloudlets, isGreen);
        double carbon = energyModel.calculateCarbon(energy, config.carbonFactor);

        r.energy = energy;
        r.carbon = carbon;
        r.cost = energy * 0.25;

        // SLA LOGIC
        double loadFactor = config.cloudlets / (activeHosts * 100.0);

        if (isGreen && loadFactor > 0.9) {
            r.slaViolated = true;   // throttling causes delay
        } else {
            r.slaViolated = false;
        }

        return r;
    }
}
