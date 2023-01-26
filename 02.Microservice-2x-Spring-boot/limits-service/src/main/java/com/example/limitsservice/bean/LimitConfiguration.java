package com.example.limitsservice.bean;

public class LimitConfiguration {
    private Integer maximum;
    private Integer minimum;

    public LimitConfiguration() {
    }

    public LimitConfiguration(Integer maximum, Integer minimum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public Integer getMinimum() {
        return minimum;
    }

    @Override
    public String toString() {
        return "LimitConfiguration{" +
                "maximum=" + maximum +
                ", minimum=" + minimum +
                '}';
    }
}
