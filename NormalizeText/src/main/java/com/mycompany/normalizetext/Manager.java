
package com.mycompany.normalizetext;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Manager {
    
    public String formatOneSpaceSpecial(String line, String character) {
        StringBuilder sb = new StringBuilder();
        String[] str = line.split("\\s*\\" + character + "\\s*");
        for (String ow : str) {
            sb.append(ow).append(" ").append(character);
            sb.append(" ");
        }
        return sb.toString().trim().substring(0, sb.length() - 3);
    }
    
    
    public String formatOneSpace(String line) {
        
        line = line.toLowerCase();
        line = line.replaceAll("\\s+", " ");
        line = formatOneSpaceSpecial(line, ".");
        line = formatOneSpaceSpecial(line, ",");
        line = formatOneSpaceSpecial(line, ":");
        line = formatOneSpaceSpecial(line, "\"");
        return line.trim();
    }
    
    
    public String formatSpecialCharacters(String line) {
        StringBuilder sb = new StringBuilder(line);
        for(int i = 0; i < sb.length() - 1; i++ ) {
            if(sb.charAt(i) == ' ' 
                    && sb.charAt(i + 1) == '.' 
                    || sb.charAt(i + 1) == ',' 
                    || sb.charAt(i + 1) == ':') {
             sb.deleteCharAt(i);
            }
        }
        return sb.toString().trim();
        
    }
    
    
    public String afterDotUpperCase(String line) {
        StringBuilder sb = new StringBuilder(line);
        int lengthLine = sb.length();
        for(int i = 0; i < sb.length() -2; i++) {
            if(sb.charAt(i) == '.') {
                char afterDot = sb.charAt(i + 2);
                sb.setCharAt(i+ 2, Character.toUpperCase(afterDot));
                
            }
        }
        return sb.toString().trim();
    }
    
    
    public String noSpaceQuotes(String line) {
        int countQuetes = 0;
        StringBuilder sb = new StringBuilder(line);
        for(int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '"' && countQuetes % 2 == 0) {
                sb.deleteCharAt(i + 1);
                countQuetes++;
            } else if(sb.charAt(i) == '"' && countQuetes % 2 == 1 && i != 0) {
                sb.deleteCharAt(i - 1);
                countQuetes++;
            }
        }
        return sb.toString().trim();
    }
    
    
    public String firstUpperCase(String line) {
        line = line.substring(3);
        StringBuilder sb = new StringBuilder(line);
        for(int i = 0; i < line.length(); i++) {
            if(Character.isLetter(line.charAt(i))) {
                sb.setCharAt(i, Character.toUpperCase(line.charAt(i)));
                break;
            }
        }
        return sb.toString().trim();
    }
    
    public String lastAddDot(String line) {
        if(line.endsWith(".")){
            return line;
        } else {
            return line + ".";
        }
        
}
    
    public boolean isLineEmpty(String line) {
        if(line.length() == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    
    
    public int countLine() {
        int countLine = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("test.txt")));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)));
            String line;
            while((line = br.readLine()) != null) {
                if(isLineEmpty(line)) {
                    continue;
                }
                countLine++;
            }
            br.close();
            pw.close();
        } catch (FileNotFoundException e) {
            System.err.println("Can't found");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return countLine;
    }
}
