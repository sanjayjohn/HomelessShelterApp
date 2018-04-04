const FbApp = require("../services/firebase");

FbApp.auth().onAuthStateChanged(user => {
	if (user) {
		console.log("LOGGED IN");
		console.log(user);
	} else {
		console.log("LOGGED OUT");
	}
});

module.exports = app => {
	app.get("/register", function(req, res) {
	    res.render("registration");
	});

	app.get("/api/current_user", (req, res) => {
		res.send(FbApp.auth().currentUser);
	});

	app.get("/login", (req, res) => {
		res.render("login");
	});

	app.post("/login", function(req, res) {
	    const { email, password } = req.body;
	    firebase
	        .auth()
	        .signInWithEmailAndPassword(email, password)
	        .then(() => {
	            res.redirect("/");
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
}