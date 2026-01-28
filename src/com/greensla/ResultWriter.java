package com.greensla;

import java.io.FileWriter;

public class ResultWriter {

    public static void write(Result normal, Result green) {
        try {
            FileWriter fw = new FileWriter("../green-sla-dashboard/public/results.json");

            fw.write("[\n");
            fw.write(toJson(normal) + ",\n");
            fw.write(toJson(green) + "\n");
            fw.write("]");

            fw.close();
            System.out.println("results.json written successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String toJson(Result r) {
        return String.format(
            "  { \"mode\": \"%s\", \"tasks\": %d, \"activeHosts\": %d, \"energy\": %.2f, \"carbon\": %.4f, \"cost\": %.2f, \"slaViolated\": %s }",
            r.mode, r.tasks, r.activeHosts, r.energy, r.carbon, r.cost, r.slaViolated
        );
    }
}
