const FbApp = require("../../services/firebase"); 
module.exports = (req, res, next) => {
	if (!FbApp.auth().currentUser) {
		return res.status(401).send({ error: "User is not logged in"});
	}

	next();
};