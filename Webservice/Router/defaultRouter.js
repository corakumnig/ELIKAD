var express    = require('express');   
var defaultrouter = express.Router();

// test route to make sure everything is working (accessed at GET http://localhost:8080/api)
defaultrouter.get('/', function(req, res) {
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);
    if(userGroup != 'department' || userGroup != 'admin' || userGroup != 'member'){
        res.status(401).json({
            message: "Not authenticated"
        });
    }
    else{
        res.status(200).json({
            message: "Welcome to your API!"
        });
    }  
});

module.exports = defaultrouter;