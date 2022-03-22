package com.example.workingwithpvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PVCPractice {

    @GetMapping("/")
    public String hello() throws IOException {
        FileWriter fileWriter = new FileWriter("/var/pvc/hello.txt",true);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        bw.write("Hi There this is me, Bereket from west side campton");
        bw.newLine();
        bw.close();
        return "Wrote to file";
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
