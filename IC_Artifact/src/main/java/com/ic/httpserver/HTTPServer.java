/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ic.httpserver;

import java.io.BufferedReader;
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

/**
 *
 * @author leite
 */
public class HTTPServer {
    
    
    public static void main(String args[]) {
        try {
            
            System.out.print("Starting HTTP Server\n");
            
            String[] parameters = new String[30];
            
            // Create a ServerSocket to listen on that port.
            ServerSocket ss = new ServerSocket(5009);

            // Loop for handling connections
            System.out.print("Loop Starting\n");
            for (;;) {
                

                Socket client = ss.accept();
                System.out.print("Connections Established\n");

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream());

                out.print("HTTP/1.1 200 \r\n"); 
                out.print("Content-Type: text/plain\r\n");
                out.print("Connection: close\r\n");
                out.print("\r\n"); 
                
                System.out.print("Extracting Parameters\n");
                int i = 0;
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.length() == 0)
                        break;
//                    out.print(line + "\r\n");
                    parameters[i] = line;
//                    System.out.println(i + " - " + parameters[i]);
                    i++;
                }


                System.out.print("Closing Services\n");
                out.close(); 
                in.close(); 
                client.close(); 
                System.out.print("Services Closed\n");
                
                System.out.print("Extracted Parameters : \n");
                //Set parameters to variables.                
                String targetURL = parameters[17].substring(parameters[17].indexOf(":") + 1);               
                System.out.println("TARGET URL --- " + targetURL);
                String users = parameters[18].substring(parameters[18].indexOf(":") + 1);               
                System.out.println("USERS --- " + users);
                String threads = parameters[19].substring(parameters[19].indexOf(":") + 1);               
                System.out.println("THREADS --- " + threads);
                
            } // Now loop again, waiting for the next connection
        } 
        catch (IOException e) {
            System.err.println(e);
            System.err.println("Usage: java HttpMirror 5009");
        }
    }
    
}
