# IndustrialConsultingProject-COM617
### Industrial Consulting Project - OpenNMS

## OVERVIEW
Imagine that you are part of the Web Site Testing team for your employer, a major online educator. Your group has been asked to develop a software solution that can be used to automatically test the user experience and availability of the company's web site.
You should consider the principal sources of user experience degradation and develop tests which demonstrate when this is occurring. 
You will design a prototype solution that can be used to execute synthetic transactions against the web site and report the results of each test. You should consider integration testing, canary red / blue testing and production monitoring. The design should describe the software architecture of the solution and give details of how it can be used by the company's Devops team to perform repeated service assurance tests against the site.


## THE TEST PLATFORM
Your test platform should be web hosted and perform configurable synthetic transaction tests against the web site on a regular basis. The results of these tests should be stored for further analysis and it should be possible to graph the results over time. 
Your proof of concept should utilize the industry standard Apache Jmeter platform operating withing a cloud environment to perform and record tests against the web site. You should provide means to program and run these tests remotely through a simple ReST API.
You should also investigate other similar test technologies and compare your solution with industry best practice. 
You may use the Solent University web site as your target test platform for availability tests (but not load tests). 

## RESOURCES
Apache Jmeter https://jmeter.apache.org/

JMeter: Performance and Load Testing. Smith, Michael 2019 Carpenteria, CA linkedin.com (via solent library)

Master Apache JMeter - From Load Testing to DevOps Master Performance Testing with JMeter By Antonio Gomes Rodrigues, Bruno Demion (Milamber), Philippe Mouawad · 2019
Pro Apache JMeter Web Application Performance Testing By Sai Matam, Jagdeep Jain · 2017 Apress
