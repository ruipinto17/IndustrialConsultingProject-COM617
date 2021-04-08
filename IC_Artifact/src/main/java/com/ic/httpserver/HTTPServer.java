/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ic.httpserver;

import com.ic.icproject.PerformanceTest;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author leite
 */
public class HTTPServer {

    public static void main(String args[]) throws IOException {
        try {
            
            Socket client;

            System.out.print("Starting HTTP Server\n");

            String[] parameters = new String[30];

            // Create a ServerSocket to listen on that port.
            ServerSocket ss = new ServerSocket(5008);

            // Loop for handling connections
            System.out.print("Loop Starting\n");

             client = ss.accept();
            System.out.print("Connections Established\n");

            DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream());

            out.print("HTTP/1.1 200 \r\n");
            out.print("Content-Type: text/plain\r\n");
            out.print("Connection: close\r\n");
            out.print("\r\n");

            System.out.print("Extracting Parameters\n");
            int i = 0;
            String line;
            String end = "end" ;
            while ((line = in.readLine()) != null && end == "end") {
                if (line.toLowerCase().indexOf("thread") != -1) {
                    String[] linesplit = line.split(" ");
                    String thread = linesplit[1];
                    System.out.println(thread);
                } else if (line.toLowerCase().indexOf("url") != -1) {
                    String[] linesplit = line.split(" ");
                    end = linesplit[1];
                    System.out.println(end);
                    
                }
               
                // Now loop again, waiting for the next connection
            }
            ss.close();
            
            if (ss.isClosed())
            {
                run();
            }           
          
            
                        
        } catch (IOException e) {
            System.err.println(e);
            System.err.println("Usage: java HttpMirror 5009");
        }
    }
    
    public static void run() {
         try{
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        
        String PATHTOTESTPAGE = "https://blazedemo.com/reserve.php";
        String METHOD = "POST";
        String Website = "https://blazedemo.com";
        String Thread = "10";
        String Rampup = "5";
        
        PerformanceTest task = new PerformanceTest(PATHTOTESTPAGE,METHOD,Website, Thread,Rampup);
        exec.scheduleAtFixedRate(task, 5, 5, TimeUnit.SECONDS);
        } catch(Exception e){
            System.out.println(e);
        }
        
    }
}
