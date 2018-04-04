const FbApp = require("../services/firebase");
const requireLogin = require("./middleware/requireLogin");
const searchShelters = require("../services/searchShelters");

module.exports = app => {
	app.get("/shelters", requireLogin, (req, res) => {
		let ref = FbApp.database().ref("Shelters/");

		ref.on("value", function(snapshot) {
		  res.render("shelters", { shelters: snapshot.val() });
		}, function (errorObject) {
		  console.log("The read failed: " + errorObject.code);
		  res.send(errorObject.code);
		});
	});

	app.get("/shelters/search", requireLogin, (req, res) => {
		let ref = FbApp.database().ref("Shelters/");

		ref.on("value", function(snapshot) {
		  res.render("search", { shelters: snapshot.val() });
		}, function (errorObject) {
		  console.log("The read failed: " + errorObject.code);
		  res.send(errorObject.code);
		});
	});

	app.post("/shelters/search", requireLogin, (req, res) => {
		let gender = req.body.gender;
		let ageRange = req.body.ageRange;
		let name = req.body.name;

		let ref = FbApp.database().ref("Shelters/");

		ref.on("value", function(snapshot) {
		  let shelters = searchShelters(snapshot.val(), name, gender, ageRange);

		  res.render("shelters", { shelters });
		}, function (errorObject) {
		  console.log("The read failed: " + errorObject.code);
		  res.send(errorObject.code);
		});
	});
};