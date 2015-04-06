package com.epam.aa.sportplace.validation;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class Validator {
    private Map<String, Validator> instances;

    public static void init() {
        URL url = Validator.class.getClassLoader().getResource("/validation.validation.xsd");
        parseSchema(url);
    }
    public static void parseSchema(URL url) {
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
    public static Validator getInstance(String fieldName) {

    }

}
