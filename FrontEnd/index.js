import Express from "express";
import bodyParser from "body-parser";
import superagent from "superagent";
import fs from "fs";
import { fileURLToPath } from "url";
import { dirname } from "path";
import NodeCache from "node-cache";

const myCache = new NodeCache();

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const app = Express();
app.set("view engine", "ejs");
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(Express.static("public"));

app.get("/userTestPlan.jmx", (req, res) => {
  res.sendFile(__dirname + "/userTestPlan.jmx");
});
app.get("/", (req, res) => {
  if (myCache.get("targetUrl") == undefined) {
    let targetUrl = "Not running";
    let threadPool = "Not running";
    let rampUpTime = "Not running";
    let tinterval = "Not running";
    let timeRange = "Not running";

    res.render("index", {
      title: "Home Page",
      targetUrl: targetUrl,
      threadPool: threadPool,
      rampUpTime: rampUpTime,
      tinterval: tinterval,
      timeRange: 24,
    });
  } else {
    res.render("index", {
      title: "Home Page",
      targetUrl: myCache.get("targetUrl"),
      threadPool: myCache.get("threadPool"),
      rampUpTime: myCache.get("rampUpTime"),
      start: myCache.get("start"),
      tinterval: myCache.get("tinterval"),
      timeRange: myCache.get("timeRange"),
    });
  }
});

app.post("/", (req, res) => {
  let targetUrl = req.body.TargetUrl;
  let threadPool = req.body.ThreadPool;
  let rampUpTime = req.body.RampUpTime;
  let engine = req.body.engine;
  let tinterval = req.body.interval;
  let timeRange = req.body.range;

  let start;
  if (engine == null) {
    start = "false";
  } else {
    start = "true";
  }
  superagent
    .post("http://webmontiortool.uksouth.cloudapp.azure.com:5000/") //<- Open Socket on our Jmeter Application
    .set("threads", threadPool)
    .set("targeturl", targetUrl)
    .set("rampup", rampUpTime)
    .set("keeprunning", start)
    .set("timeinterval", tinterval)
    .end((err, res) => {});

  if (start == "false") {
    myCache.mset([
      { key: "targetUrl", val: "Not running" },
      { key: "threadPool", val: "Not running" },
      { key: "rampUpTime", val: "Not running" },
      { key: "engine", val: "Not running" },
      { key: "tinterval", val: "Not running" },
      { key: "timeRange", val: 24 },
    ]);
  } else {
    myCache.mset([
      { key: "targetUrl", val: targetUrl },
      { key: "threadPool", val: threadPool },
      { key: "rampUpTime", val: rampUpTime },
      { key: "engine", val: engine },
      { key: "tinterval", val: tinterval },
      { key: "timeRange", val: timeRange },
    ]);
  }
  res.render("index", {
    title: "Home Page",
    targetUrl: myCache.get("targetUrl"),
    threadPool: myCache.get("threadPool"),
    rampUpTime: myCache.get("rampUpTime"),
    start: myCache.get("start"),
    tinterval: myCache.get("tinterval"),
    timeRange: myCache.get("timeRange"),
  });
});

app.listen(3000, () => {
  console.log("listen");
});
