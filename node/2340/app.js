var express = require("express");
var bodyParser = require("body-parser");
var path = require("path");
var FbApp = require("./services/firebase");
var app = express();

// view engine
app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "views"));

// Body parser middleware
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

// Set static path
app.use(express.static(path.join(__dirname, "public")));

require("./routes/authRoutes")(app);
require("./routes/shelterRoutes")(app);
app.get("/", function(req, res) {
    let currentUser = FbApp.auth().currentUser;
    if (!currentUser) {
        res.redirect("/login");
    }
    res.render("index", { currentUser })
});

app.listen(3000, function() {
    console.log("Running on port 3000");
});
