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
