/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.utils;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author christina joy hartshorn
 */
public class WriteFile {
    
    public static void writeUserLog(String acitvity) throws IOException
    {
        /*
        System.out.println(acitvity);
        String filename ="src/files/UserActivity.txt", event;
        acitvity = acitvity + " at "+ LocalDateTime.now();
        System.out.println(acitvity);
        //create and/or open file
        FileWriter fwriter = new FileWriter(filename, true);
        PrintWriter outputFile = new PrintWriter(fwriter);
        //Write to file with the activtiy 
        outputFile.println(acitvity);*/
        
        
        String filename ="src/UserActivity.txt", item;
        item = acitvity + " at "+ LocalDateTime.now();

        //craete file writter opbject
        FileWriter fwriter = new FileWriter(filename, true);
        
        //create and open file
        PrintWriter outputFile = new PrintWriter(fwriter);  

         outputFile.println(item);
        //close file
        outputFile.close();
        //System.out.println("File written");
        
        
    }
    
    public static void writeLog(String acitvity, String filename) throws IOException
    {
        //create and/or open file
        filename = "src/files/" + filename;
        FileWriter fwriter = new FileWriter(filename, true);
        PrintWriter outputFile = new PrintWriter(fwriter);
        //Write to file with the activtiy 
        outputFile.println(acitvity);
    }
    
    public static void writeUsertTest() throws IOException
    {
        String filename ="src/UserActivity.txt", item;
        item = "tiny";

        //craete file writter opbject
        FileWriter fwriter = new FileWriter(filename, true);
        
        //create and open file
        PrintWriter outputFile = new PrintWriter(fwriter);  

         outputFile.println(item);
        //close file
        outputFile.close();
        System.out.println("File written");
    }
}
