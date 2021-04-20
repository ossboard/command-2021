package com.github.spring.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RunCore {

    private static Logger log = LoggerFactory.getLogger(RunCore.class);

    private String mode = System.getProperty("mode");

    public RunCore() throws Exception {
        switch (mode) {
            case "test": test(); break;
            case "mig": mig(); break;
        }
    }

    private void test() {
        System.out.println("TEST");
    }

    private void mig() {
        System.out.println("MIG");
    }

}