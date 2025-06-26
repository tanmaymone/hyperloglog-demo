package com.tanmay.redis.hyperloglog_demo.controller;

import com.tanmay.redis.hyperloglog_demo.service.VisitorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> visit(@PathVariable String userId) {
        visitorService.recordVisit(userId);
        return ResponseEntity.ok("Visit recorded for " + userId);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getUniqueVisitorCount() {
        return ResponseEntity.ok(visitorService.getApproxUniqueVisitorsToday());
    }
}
