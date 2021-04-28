package com.ic.icproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
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
 * @author leite
 */
public class PerformanceTest implements Runnable {

    public String Website;
    public String Thread;
    public String Rampup;

    public PerformanceTest(String Website, String Thread, String Rampup) {

        this.Website = Website;
        this.Thread = Thread;
        this.Rampup = Rampup;
    }

    @Override
    public void run() {
        try {
            System.out.print("----- Creating JMeter Engine\n");
            StandardJMeterEngine jmeter = new StandardJMeterEngine();

            JMeterUtils.loadJMeterProperties("./src/main/resources/apache-jmeter-5.4.1/bin/jmeter.properties"); // /path/to/your/jmeter/bin/jmeter.properties
            JMeterUtils.setJMeterHome("./src/main/resources/apache-jmeter-5.4.1"); // /path/to/your/jmeter
            JMeterUtils.initLocale();
            SaveService.loadProperties();

            System.out.print("----- Loading Test Plan\n");
  
            HashTree testPlanTree = SaveService.loadTree(new File("./src/main/resources/solentTest.jmx")); // /path/to/your/jmeter/extras/Test.jmx
            Summariser summer = null;
            String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
            if (summariserName.length() > 0) {
                summer = new Summariser(summariserName);
            }

            String logFile = "./target/standardTest" + Instant.now().toString().replace(":", "-") + ".jtl\""; // /path/to/results/file.jtl
            ResultCollector logger = new ResultCollector(summer);
            logger.setFilename(logFile);
            testPlanTree.add(testPlanTree.getArray()[0], logger);

            System.out.print("----- Adding Arguments to Test Plan\n");
            Arguments args = new Arguments();
            args.addArgument("Website", this.Website);
            args.addArgument("Threads", this.Thread);
            args.addArgument("Ramp-up-Time", this.Rampup);
            testPlanTree.add(args);

            jmeter.configure(testPlanTree);
            System.out.print("----- Running Test Plan\n");
            jmeter.run();

        } catch (IOException t) {
            System.out.print(t);
        }
    }

}
