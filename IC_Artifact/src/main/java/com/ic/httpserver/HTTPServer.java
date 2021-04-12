/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ic.httpserver;

import com.ic.icproject.PerformanceTest;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author leite
 */
public class HTTPServer {

    // Parameters to be passed to PerformanceTest
    public static String targetUrl = "https://blazedemo.com";
    public static String threads = "10";
    public static String rampup = "5";
    public static String users = null;

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
            String line;

            while ((line = in.readLine()) != null) {
                if (line.toLowerCase().indexOf("targeturl") != -1) {
                    String[] linesplit = line.split(" ");
                    targetUrl = linesplit[1];
                    System.out.println("TargetUrl - " + targetUrl);
                } else if (line.toLowerCase().indexOf("threads") != -1) {
                    String[] linesplit = line.split(" ");
                    threads = linesplit[1];
                    System.out.println("Threads - " + threads);
                } else if (line.toLowerCase().indexOf("rampup") != -1) {
                    String[] linesplit = line.split(" ");
                    rampup = linesplit[1];
                    System.out.println("Rampup - " + rampup);
                } else if (line.toLowerCase().indexOf("users") != -1) {
                    String[] linesplit = line.split(" ");
                    users = linesplit[1];
                    System.out.println("Users - " + users);
                } else if (line.isEmpty()) {
                    break;
                }
            }
            ss.close();

            if (ss.isClosed()) {
                run();
            }

        } catch (IOException e) {
            System.err.println(e);
            System.err.println("Usage: java HttpMirror 5008");
        }
    }

    public static void run() {
        try {
            ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

//            String PATHTOTESTPAGE = "https://blazedemo.com/reserve.php";
            String METHOD = "POST";
//            String Website = "https://blazedemo.com";
//            String Thread = "4";
//            String Rampup = "30";

            PerformanceTest task = new PerformanceTest(targetUrl, METHOD, targetUrl, threads, rampup);
            exec.scheduleAtFixedRate(task, 5, Long.parseLong(rampup), TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
