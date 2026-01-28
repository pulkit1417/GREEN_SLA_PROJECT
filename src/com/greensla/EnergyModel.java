package com.greensla;

public class EnergyModel {

    public double calculateEnergy(int activeHosts, int cloudlets, boolean greenMode) {

        double idlePowerPerHost = 120;   // base idle power
        double maxPowerPerHost = 250;    // max power at full load

        double loadFactor = cloudlets / (activeHosts * 100.0);  // load pressure

        if (loadFactor < 0.1) loadFactor = 0.1;
        if (loadFactor > 1.2) loadFactor = 1.2; // overload region

        double powerPerHost = idlePowerPerHost + (maxPowerPerHost - idlePowerPerHost) * loadFactor;

        double totalEnergy = powerPerHost * activeHosts * 10; // 10 = time window

        // GREEN has overhead of consolidation & throttling
        if (greenMode) {
            totalEnergy = totalEnergy * 0.85; // saves energy
        }

        // Overload inefficiency penalty
        if (loadFactor > 1.0) {
            totalEnergy = totalEnergy * 1.25;
        }

        return totalEnergy;
    }

    public double calculateCarbon(double energy, double carbonFactor) {
        return energy * carbonFactor;
    }
}
