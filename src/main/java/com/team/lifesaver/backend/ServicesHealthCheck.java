package com.team.lifesaver.backend;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by pavan.t on 03/01/15.
 */
public class ServicesHealthCheck extends HealthCheck {

    private static final String EXPECTED_HEALTH_CHECK_PROPERTY = "Lifesaver";
    private String healthCheckProperty;

    public ServicesHealthCheck(String healthCheckProperty) {
        this.healthCheckProperty = healthCheckProperty;
    }

    @Override
    protected Result check() throws Exception {
        Result result = Result.healthy();
        if (!EXPECTED_HEALTH_CHECK_PROPERTY.equals(healthCheckProperty)) {
            result = Result.unhealthy("Expected configuration not loaded.");
        }
        return result;
    }
}
