import Express from "express";
import bodyParser from "body-parser";
import superagent from "superagent";

const app = Express();
app.set("view engine", "ejs");
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(Express.static("public"));

app.get("/", (req, res) => {
  res.render("index", { title: "Home Page" });
});

app.post("/", (req, res) => {
  let targetUrl = req.body.TargetUrl;
  let threadPool = req.body.ThreadPool;
  let rampUpTime = req.body.RampUpTime;
  let engine = req.body.engine;
  let tinterval = req.body.interval;

  let start;
  if (engine == null) {
    start = "false";
  } else {
    start = engine;
  }
  superagent
    .post("wmicp.uksouth.cloudapp.azure.com:5000/")
    .set("threads", threadPool)
    .set("targeturl", targetUrl)
    .set("rampup", rampUpTime)
    .set("keeprunning", start)
    .set("timeinterval", tinterval)
    .end((err, res) => {
      // Calling the end function will send the request
    });
});

app.listen(3000, () => {
  console.log("listen");
});
