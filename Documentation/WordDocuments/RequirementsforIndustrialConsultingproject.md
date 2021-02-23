**Requirements for Industrial Consulting project:**

+------------------------------------------------+--------------------+
| **Functional**                                 | **Non-functional** |
+================================================+====================+
| -   Create a test plan                         | -   Unit test      |
|                                                |                    |
|     -   Canary testing                         |                    |
|                                                |                    |
|     -   Green/blue/red testing                 |                    |
|                                                |                    |
|     -   Integration testing                    |                    |
+------------------------------------------------+--------------------+
| -   Make a request every x time (every 5 mins  |                    |
|     > not sure)                                |                    |
+------------------------------------------------+--------------------+
| -   Store performance metrics in influxDB      |                    |
|     > (metrics tbd)                            |                    |
|                                                |                    |
|     -   Status (main metric)                   |                    |
|                                                |                    |
|     -   Latency                                |                    |
|                                                |                    |
|     -   Sample size(Bytes)                     |                    |
|                                                |                    |
|     -   Ms response(Maybe)                     |                    |
|                                                |                    |
|     -   Amount of Users(Our own site)          |                    |
+------------------------------------------------+--------------------+
| -   Visually represent stored metrics in       |                    |
|     > graphics with Grafana                    |                    |
+------------------------------------------------+--------------------+
| -   Developing a website to display graphics   |                    |
|     > from grafana.                            |                    |
+------------------------------------------------+--------------------+
| -   Work on API requests from grafana in order |                    |
|     > to display the graphs                    |                    |
+------------------------------------------------+--------------------+
| -   Diagrams:                                  |                    |
|                                                |                    |
|     -   Use case scenarios                     |                    |
|                                                |                    |
|     -   Sequence diagram                       |                    |
|                                                |                    |
|     -   Architecture/Deployment Diagram        |                    |
|                                                |                    |
|     -   Robustness diagram (maybe)             |                    |
+------------------------------------------------+--------------------+

Application Goal

-   Solution that continuously displays UX and availability monitoring
    > test results on a web page.

How are we doing it

How are we evaluating it

**Task(s) allocation:**

**Leites and Aeryk:** JMeter implementations

**Afonso and Rui:** InfluxDB and Grafana

**Tebogo:** Docker

**Meetings:**

**Tue:** 12-1pm

**Thu:** 2pm - ?

notes 16/02/21 -

docker compose to spin up container

jmeter

influxdb

grafana

rest call to push JMX to application

overall description of project

system diagram

project plan

open GitHub

create template for report and presentation and assign parts to team
members

presentation on 02/03 with openNMS
