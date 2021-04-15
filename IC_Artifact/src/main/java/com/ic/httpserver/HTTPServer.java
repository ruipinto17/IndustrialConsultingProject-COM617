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
import java.util.concurrent.ExecutorService;
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
    public static String timeInterval = "10";
    public static String isRunning = "true";
    public static PerformanceTest task = null;
    public static ScheduledExecutorService exec = null;
    public static Socket client = null;
    public static ServerSocket ss = null;

    public static void main(String args[]) throws IOException {
        try {
            System.out.print("----- Starting HTTP Server\n");

            // Create a ServerSocket to listen on that port.
            ss = new ServerSocket(5008);
            int running = 1;
            while (running == 1) {
                System.out.print("----- Listening for connections\n");
                client = ss.accept();
                receiveRequest();
            }

        } catch (IOException e) {
            System.err.println(e);
            System.err.println("----- Usage: java HttpMirror 5008");
        } finally {
            ss.close();
        }
    }

    public static void receiveRequest() throws IOException {
        System.out.print("----- Connections Established\n");

        DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream());

        out.print("HTTP/1.1 200 \r\n");
        out.print("Content-Type: text/plain\r\n");
        out.print("Connection: close\r\n");
        out.print("\r\n");

        System.out.print("----- Extracting Parameters\n");
        String line;

        while ((line = in.readLine()) != null) {
            if (line.toLowerCase().indexOf("targeturl") != -1) {
                String[] linesplit = line.split(" ");
                targetUrl = linesplit[1];
                System.out.println("----- TargetUrl - " + targetUrl);
            } else if (line.toLowerCase().indexOf("threads") != -1) {
                String[] linesplit = line.split(" ");
                threads = linesplit[1];
                System.out.println("----- Threads - " + threads);
            } else if (line.toLowerCase().indexOf("rampup") != -1) {
                String[] linesplit = line.split(" ");
                rampup = linesplit[1];
                System.out.println("----- Rampup - " + rampup);
            } else if (line.toLowerCase().indexOf("timeinterval") != -1) {
                String[] linesplit = line.split(" ");
                timeInterval = linesplit[1];
                System.out.println("----- timeInterval - " + timeInterval);
            } else if (line.toLowerCase().indexOf("keeprunning") != -1) {
                String[] linesplit = line.split(" ");
                isRunning = linesplit[1];
                System.out.println("----- keepRunning - " + isRunning);
            } else if (line.isEmpty()) {
                break;
            }
        }
        if (exec != null) {
            System.out.println("----- Shutting Down ScheduleExecutor");
            exec.shutdown();
            System.out.println("----- ScheduleExecutor Shut Down ");
        }
        if (isRunning.equalsIgnoreCase("true")) {
            runPerformanceTest();
        }
    }

    public static void runPerformanceTest() {
        try {
            System.err.println("----- Creating ScheduledExecutor");
            exec = Executors.newScheduledThreadPool(1);
            System.err.println("----- Creating PerformanceTask");
            task = new PerformanceTest(targetUrl, threads, rampup);
            System.err.println("----- Run ScheduledExecutor");
            exec.scheduleAtFixedRate(task, 0, 1, TimeUnit.MINUTES);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
