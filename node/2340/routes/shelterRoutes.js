const FbApp = require("../services/firebase");
const requireLogin = require("./middleware/requireLogin");

module.exports = app => {
	app.get("/shelters", requireLogin, (req, res) => {
		var ref = FbApp.database().ref("Shelters/");

		ref.on("value", function(snapshot) {
		  res.render("shelters", { shelters: snapshot.val() });
		}, function (errorObject) {
		  console.log("The read failed: " + errorObject.code);
		  res.send(errorObject.code);
		});
	});
};