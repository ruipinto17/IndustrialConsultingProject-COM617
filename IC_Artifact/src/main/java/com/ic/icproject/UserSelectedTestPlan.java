/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ic.icproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jmeter.visualizers.backend.BackendListener;
import org.apache.jmeter.visualizers.backend.BackendListenerClient;
import org.apache.jmeter.visualizers.backend.BackendListenerGui;
import org.apache.jorphan.collections.HashTree;

/**
 *
 * @author tebog
 */
public class UserSelectedTestPlan implements Runnable {

    @Override
    public void run() {
        try {
            URL userInput = new URL("http://localhost:3000/userTestPlan.jmx");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(userInput.openStream()));
            System.out.print(in);
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./src/main/resources/userTestPlan.jmx")));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                bw.write(inputLine);
                bw.newLine();
                System.out.print("Looping");
            }
            bw.flush();
            in.close();
            
            System.out.print("----- Creating JMeter Engine\n");
            StandardJMeterEngine jmeter = new StandardJMeterEngine();

            JMeterUtils.loadJMeterProperties("./src/main/resources/apache-jmeter-5.4.1/bin/jmeter.properties"); // /path/to/your/jmeter/bin/jmeter.properties
            JMeterUtils.setJMeterHome("./src/main/resources/apache-jmeter-5.4.1"); // /path/to/your/jmeter
            JMeterUtils.initLocale();
            SaveService.loadProperties();

            HashTree testPlanTree = SaveService.loadTree(new File("./src/main/resources/userTestPlan.jmx")); // /path/to/your/jmeter/extras/Test.jmx
            Summariser summer = null;
            String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
            if (summariserName.length() > 0) {
                summer = new Summariser(summariserName);
            }

            String logFile = "./target/standardTest" + Instant.now().toString().replace(":", "-") + ".jtl\""; // /path/to/results/file.jtl
            ResultCollector logger = new ResultCollector(summer);
            logger.setFilename(logFile);
            testPlanTree.add(testPlanTree.getArray()[0], logger);

            jmeter.configure(testPlanTree);
            System.out.print("----- Running Test Plan\n");
            jmeter.run();

        } catch (IOException ex) {
            Logger.getLogger(UserSelectedTestPlan.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.print("----- Loading Test Plan\n");
    }

}
