package com.example.workingwithpvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

@RestController
public class PVCPractice {
    public final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @GetMapping("/write")
    public Boolean hello(@RequestParam String key, @RequestParam String value) throws IOException {
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream("/var/test.properties");
        prop.load(new FileInputStream("/var/test.properties"));
        fileInputStream.close();
        String oldvalue = (String) prop.setProperty(key,value);
        boolean updated = false;
        if (!value.equals(oldvalue)){
            FileWriter fileWriter = new FileWriter("src/main/java/com/example/workingwithpvc/test.properties");
            prop.store(fileWriter,"added value "+value);
            fileWriter.close();
            updated = true;
            LOGGER.info("Wrote to file "+value);
        }
        return updated;
    }

    @GetMapping("/read")
    public Properties read() throws Exception {
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream("/var/test.properties");
        prop.load(fileInputStream);
        fileInputStream.close();
        return prop;

    }

}
