/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ic.icproject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author leite
 */
public class ThreadPool {

    public static void main(String[] args) {
        try{
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        PerformanceTest task = new PerformanceTest();
        exec.scheduleAtFixedRate(task, 5, 60, TimeUnit.SECONDS);
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
