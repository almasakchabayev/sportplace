package com.epam.aa.sportplace;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Runner {
    public static void main(String[] args) {
        Properties defaultProps = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("defaultProperties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            defaultProps.load(in);
            System.out.println(defaultProps);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

// create application properties with default
        Properties applicationProps = new Properties(defaultProps);

// now load properties
// from last invocation
        try {
            in = new FileInputStream("appProperties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            applicationProps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
