package com.tanmay.redis.hyperloglog_demo.service;

import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    private final VisitorService visitorService;

    public SimulationService(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    public void simulateVisits(int totalVisits) {
        System.gc(); // Run GC before measuring memory

        long startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();

        for (int i = 1; i <= totalVisits; i++) {
            // Simulate unique user IDs like user_1, user_2, ..., user_10000
            visitorService.recordVisit("user_" + i);

            if (i % 10000 == 0 || i == totalVisits) {
                System.out.println("✅ Simulated " + i + " visits.");
            }
        }

        long end = System.currentTimeMillis();
        long endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("✅ Simulated " + totalVisits + " visits in " + (end - start) + " ms.");
        System.out.println("🧠 Memory used during simulation: ~" + ((endMem - startMem) / 1024) + " KB");

        visitorService.logComparison();
    }
}
