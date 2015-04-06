package com.epam.aa.sportplace.validation;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

public class Validators {

    public static <E> void validate(E e) {
        Class<?> aClass = e.getClass();
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(aClass);
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }
        JAXBSource source = null;
        try {
            source = new JAXBSource(jc, e);
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = null;
        try {
            schema = sf.newSchema(Validators.class.getResource("/validation/" +
                    aClass.getSimpleName().toLowerCase() + ".xsd"));
        } catch (SAXException e1) {
            e1.printStackTrace();
        }

        Validator validator = schema.newValidator();
        validator.setErrorHandler(new MyErrorHandler());
        try {
            validator.validate(source);
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
