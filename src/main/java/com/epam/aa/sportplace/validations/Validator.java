package com.epam.aa.sportplace.validations;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Validator {
    private static Map<String, Validator> instances;
    private URL schemaUrl;
    private Map<String, Constraint> constraints;

    private Validator(URL schemaUrl) {
        this.schemaUrl = schemaUrl;
        constraints = new HashMap<>();
    }

    public static Validator getInstance(URL schemaUrl) {
        if (instances.containsKey(schemaUrl.toString())) {
            return instances.get(schemaUrl.toString());
        }
        if (instances == null) {
            instances = new HashMap<>();
        }
        Validator validator = new Validator(schemaUrl);
        instances.put(schemaUrl.toString(), validator);
        return validator;
    }

    public boolean isValid(String constraintName, String data) {
        Constraint constraint = constraints.get(constraintName);
        if (constraint == null) {
            //log an error
            return true;
        }
        if (!correctDataType(data, constraint.getDataType()));
        if (constraint.hasAllowedValues())
            if (!constraint.getAllowedValues().contains(data))
                return false;
        try {
            double doubleValue = Double.parseDouble(data);
            if (constraint.hasMinExclusive())
                if (doubleValue <= constraint.getMinExclusive())
                    return false;
            if (constraint.hasMinInclusive())
                if (doubleValue < constraint.getMinInclusive())
                    return false;
            if (constraint.hasMaxExclusive())
                if (doubleValue >= constraint.getMaxExclusive())
                    return false;
            if (constraint.hasMaxInclusive())
                if (doubleValue > constraint.getMaxInclusive())
                    return false;
        } catch (NumberFormatException e) {
            //ignore
        }


    }
}
