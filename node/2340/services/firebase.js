firebase = require("firebase");
var { firebaseConfig } = require("../config/config");

var FbApp = firebase.initializeApp(firebaseConfig);
module.exports = FbApp; 