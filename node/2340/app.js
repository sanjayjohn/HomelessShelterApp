var express = require("express");
var bodyParser = require("body-parser");
var path = require("path");
var firebase = require("firebase");
var { firebaseConfig } = require("./config/config");

var app = express();

firebase.initializeApp(firebaseConfig);

/*
var logger = function(req, res, next){
    console.log('logging');
    next();
}

app.use(logger);
*/

// view engine
app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "views"));

// Body parser middleware
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

// Set static path
app.use(express.static(path.join(__dirname, "public")));

app.get("/", function(req, res) {
    res.render("index");
});

app.get("/register", function(req, res) {
    res.render("registration");
});

app.get("/home", function(req, res) {
    res.send("LOGGED IN");
});

app.post("/login", function(req, res) {
    const { email, password } = req.body;
    firebase
        .auth()
        .signInWithEmailAndPassword(email, password)
        .then(() => {
            res.redirect("/home");
        })
        .catch(err => {
            console.log("LOGIN FAILED: ", err);
            res.send("Login failed");
        });
});

app.post("/register", function(req, res) {
    const { email, password } = req.body;
    firebase
        .auth()
        .createUserWithEmailAndPassword(email, password)
        .then(() => {
            res.redirect("/");
        })
        .catch(err => {
            console.log("REGISTRATION FAILED: ", err);
            res.send("Registration failed.");
        });
});

app.listen(3000, function() {
    console.log("Hello World");
});
