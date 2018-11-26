var express    = require('express');   
var defaultrouter = express.Router();

// test route to make sure everything is working (accessed at GET http://localhost:8080/api)
defaultrouter.get('/', function(req, res) {
    var host = 'http://localhost:8080/api';
    res.json({ 
        message: 'hooray! welcome to our api!',
        members: host + '/members'
    });  
});

module.exports = defaultrouter;