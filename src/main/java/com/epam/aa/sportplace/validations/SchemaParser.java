package com.epam.aa.sportplace.validations;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchemaParser {
    private URL schemaUrl;
    private Map<String, Constraint> constraints;
    private Namespace schemaNamespace;
    private static final String SCHEMA_NAMESPACE_URI = "http://www.w3.org/2001/XMLSchema";


    public SchemaParser(URL schemaUrl) throws IOException {
        this.schemaUrl = schemaUrl;
        constraints = new HashMap<>();
        schemaNamespace = Namespace.getNamespace(SCHEMA_NAMESPACE_URI);
        parseSchema();
    }

    public Map getConstraints() {
        return constraints;
    }

    public Constraint getConstraint(String constraintName) {
        return constraints.get(constraintName);
    }

    private void parseSchema() throws IOException {
        SAXBuilder builder = new SAXBuilder();
        try {
            Document schemaDocument = builder.build(schemaUrl);
            List<Element> elements = schemaDocument
                    .getRootElement().getChildren("xs:element", schemaNamespace);
            for (Element element : elements) {
                handleAttribute(element);
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }

    private void handleAttribute(Element element) throws IOException {
        String name = element.getAttributeValue("name");
        if (name == null) throw new IOException("All schema elements must have names");
        Constraint constraint = new Constraint();
        String schemaType = element.getAttributeValue("type");
        if (schemaType != null) {
            constraint.setDataType(DataConverter.getInstance().getJavaType(schemaType));
        }
        Element simpleType = element.getChild("xs:simpleType", schemaNamespace);
        if (simpleType == null) {
            return;
        }
        Element restriction = element.getChild("xs:restriction");
        schemaType = simpleType.getAttributeValue("xs:base");
        if (schemaType == null) throw new IOException("No data type specified for constraint " + name);
        constraint.setDataType(DataConverter.getInstance().getJavaType(schemaType));
        List<Element> allowedValues = restriction.getChildren("xs:enumeration", schemaNamespace);
        if (allowedValues != null) {
            for (Element allowedValue : allowedValues) {
                constraint.addAllowedValue(allowedValue.getAttributeValue("value"));
            }
        }
        Element boundary = simpleType.getChild("xs:minExclusive", schemaNamespace);
        if (boundary != null) {
            Double value = new Double(boundary.getAttributeValue("value"));
            constraint.setMinExclusive(value.doubleValue());
        }
        boundary = simpleType.getChild("xs:minInclusive", schemaNamespace);
        if (boundary != null) {
            Double value = new Double(boundary.getAttributeValue("value"));
            constraint.setMinInclusive(value.doubleValue());
        }
        boundary = simpleType.getChild("xs:maxExclusive", schemaNamespace);
        if (boundary != null) {
            Double value = new Double(boundary.getAttributeValue("value"));
            constraint.setMaxExclusive(value.doubleValue());
        }
        boundary = simpleType.getChild("xs:maxInclusive", schemaNamespace);
        if (boundary != null) {
            Double value = new Double(boundary.getAttributeValue("value"));
            constraint.setMaxInclusive(value.doubleValue());
        }
        constraints.put(name, constraint);
    }
}
