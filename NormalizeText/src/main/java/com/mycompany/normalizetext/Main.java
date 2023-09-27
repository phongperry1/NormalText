package com.mycompany.normalizetext;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = null;
        PrintWriter pw = null;
        Manager mn = new Manager();

        try {
            int countLine = mn.countLine();
            int count = 1;
            
            br = new BufferedReader(new FileReader(new File("test.txt")));
            pw = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)));
            
            String line;
            while ((line = br.readLine()) != null) {
                if (mn.isLineEmpty(line)) {
                    continue;
                }
                line = mn.formatOneSpace(line);
                line = mn.formatSpecialCharacters(line);
                line = mn.noSpaceQuotes(line);
                line = mn.firstUpperCase(line);
                line = mn.lastAddDot(line);
                pw.print(line);
                if (count < countLine) {
                    pw.print(System.getProperty("line.separator"));
                }
                count++;
            }
            
            System.out.println("Normalize successful.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }

