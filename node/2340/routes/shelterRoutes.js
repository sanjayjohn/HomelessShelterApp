const FbApp = require("../services/firebase");
const requireLogin = require("./middleware/requireLogin");

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

	app.post("/shelters/search/results", requireLogin, (req, res) => {
		let gender = req.body.gender;
		let ref = FbApp.database().ref("Shelters/");

		ref.on("value", function(snapshot) {
		  let shelters = snapshot.val();
		  let filtered = {};

		  for (const key in shelters) {
		  	if (shelters.hasOwnProperty(key)) {
		  		let shelter = shelters[key];
		  		let restrictions = shelter.Restrictions;
		  		let maleRegex = /\bmale\b|\bmen\b/gi;
		  		let femaleRegex = /\bfemale\b|\bwomen\b/gi
		  		if (gender === "male") {
		  			if (restrictions.match(maleRegex)) {
		  				filtered[key] = shelter;
		  			} else if (!restrictions.match(femaleRegex)) {
		  				filtered[key] = shelter;
		  			}
		  		} else if (gender === "female") {
		  			if (restrictions.match(femaleRegex)) {
		  				filtered[key] = shelter;
		  			} else if (!restrictions.match(maleRegex)) {
		  				filtered[key] = shelter;
		  			}
		  		} else {
		  			filtered[key] = shelter;
		  		}
		  	}
		  }

		  res.render("shelters", { shelters: filtered });
		}, function (errorObject) {
		  console.log("The read failed: " + errorObject.code);
		  res.send(errorObject.code);
		});
	});
};