package com.example.workingwithpvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PVCPractice {

    @GetMapping("/write")
    public Boolean hello(@RequestParam String key, @RequestParam String value) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream("/var/test.properties"));
        String oldvalue = (String) prop.setProperty(key,value);
        boolean updated = false;
        if (!value.equals(oldvalue)){
            prop.store(new FileWriter("/var/test.properties"),"added value "+value);
            updated = true;
            LOGGER.info("Wrote to file "+value);
        }
        return updated;
    }
    @GetMapping("/read")
    public Properties read() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("/var/test.properties"));
        return prop;
    }

}
