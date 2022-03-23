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
            prop.store(new FileWriter("src/main/java/com/example/workingwithpvc/test.properties"),"added value "+value);
            updated = true;
            LOGGER.info("Wrote to file "+value);
        }
        return updated;
    }

    @GetMapping("/read")
    public List<String> read() throws Exception {
        FileReader fileReader = new FileReader("/var/pvc/hello.txt");
        ArrayList<String> string = new ArrayList<>();
        BufferedReader br = new BufferedReader(fileReader);
        String currentline = br.readLine();
        while (currentline != null){
            string.add(currentline);
            currentline = br.readLine();
        }

        return string;

    }

}
