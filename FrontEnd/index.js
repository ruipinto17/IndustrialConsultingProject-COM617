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

let tgurl = "";
app.post("/", (req, res) => {
  let targetUrl = req.body.TargetUrl;
  let threadPool = req.body.ThreadPool;
  let rampUpTime = req.body.RampUpTime;

  tgurl = targetUrl;
  function method() {
    superagent
      .post("localhost:5008/")
      .set("threads", threadPool)
      .set("targeturl", targetUrl)
      .set("rampup", rampUpTime)
      .end((err, res) => {
        // Calling the end function will send the request
      });
  }
  if (tgurl != null) {
    setInterval(method, 5000);
  }
});

app.listen(3000, () => {
  console.log("listen");
});
