package com.greensla;

import java.nio.file.*;
import org.json.JSONObject;

public class WorkloadConfig {
    public int hosts;
    public int vms;
    public int cloudlets;
    public String mode;
    public double carbonFactor;

    public static WorkloadConfig load() {
        try {
            String text = new String(Files.readAllBytes(Paths.get("config.json")));
            JSONObject json = new JSONObject(text);

            WorkloadConfig c = new WorkloadConfig();
            c.hosts = json.getInt("hosts");
            c.vms = json.getInt("vms");
            c.cloudlets = json.getInt("cloudlets");
            c.mode = json.getString("mode");
            c.carbonFactor = json.getDouble("carbonFactor");

            return c;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.json: " + e.getMessage());
        }
    }
}
