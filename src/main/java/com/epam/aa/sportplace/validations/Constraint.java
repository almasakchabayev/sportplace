package com.epam.aa.sportplace.validations;

import java.util.ArrayList;
import java.util.List;

public class Constraint {
    private String dataType;
    private double minInclusive;
    private double minExclusive;
    private double maxInclusive;
    private double maxExclusive;
    private List<String> allowedValues;

    public Constraint() {
    }


    public double getMinInclusive() {
        return minInclusive;
    }

    public void setMinInclusive(double minInclusive) {
        this.minInclusive = minInclusive;
    }

    public boolean hasMinInclusive() {
        return minInclusive != Double.NaN;
    }

    public double getMinExclusive() {
        return minExclusive;
    }

    public void setMinExclusive(double minExclusive) {
        this.minExclusive = minExclusive;
    }

    public boolean hasMinExclusive() {
        return minExclusive != Double.NaN;
    }

    public double getMaxInclusive() {
        return maxInclusive;
    }

    public void setMaxInclusive(double maxInclusive) {
        this.maxInclusive = maxInclusive;
    }

    public boolean hasMaxInclusive() {
        return maxInclusive != Double.NaN;
    }

    public double getMaxExclusive() {
        return maxExclusive;
    }

    public void setMaxExclusive(double maxExclusive) {
        this.maxExclusive = maxExclusive;
    }

    public boolean hasMaxExclusive() {
        return maxExclusive !=Double.NaN;
    }

    public void addAllowedValue(String value) {
        if (allowedValues == null) allowedValues = new ArrayList<>();
        allowedValues.add(value);
    }

    public List<String> getAllowedValues() {
        return allowedValues;
    }

    public boolean hasAllowedValues() {
        if (allowedValues.size() == 0) {
            return true;
        }
        return false;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
