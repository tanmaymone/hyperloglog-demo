package com.tanmay.redis.hyperloglog_demo.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

// service/VisitorService.java
@Service
public class VisitorService {

    private final RedisTemplate<String, String> redisTemplate;
    private final Set<String> exactSet = new HashSet<>();

    public VisitorService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void recordVisit(String userId) {
        String key = getKeyForToday();
        redisTemplate.opsForHyperLogLog().add(key, userId);
        exactSet.add(userId);
    }

    public long getApproxUniqueVisitorsToday() {
        String key = getKeyForToday();
        return redisTemplate.opsForHyperLogLog().size(key);
    }

    private String getKeyForToday() {
        return "visitors:" + LocalDate.now();
    }

    public int getExactUniqueVisitorsToday() {
        return exactSet.size();
    }

    public void logComparison() {
        long estimate = getApproxUniqueVisitorsToday();
        int exact = getExactUniqueVisitorsToday();
        double error = 100.0 * Math.abs(exact - estimate) / exact;

        long approxMemory = 12 * 1024; // ~12KB for HyperLogLog
        long exactMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("\n========== Visitor Stats ==========");
        System.out.println("🔢 HyperLogLog Estimate : " + estimate);
        System.out.println("🎯 Exact Unique Count    : " + exact);
        System.out.printf("📉 Estimation Error      : %.2f%%\n", error);
        System.out.println("📦 HLL Memory (fixed)    : ~12 KB");
        System.out.println("💾 Exact Set Memory Used : ~" + (exactMemory / 1024) + " KB");
        System.out.println("==================================\n");
    }
}
