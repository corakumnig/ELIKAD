// sessionRaterMain.js

// BASE SETUP
// =============================================================================

// call the packages we need
var express    = require('express');        // call express
var app        = express();                 // define our app using express
var bodyParser = require('body-parser');


var port = process.env.PORT || 8080;        // set our port

// ROUTES FOR OUR API
// =============================================================================
/*
var defaultrouter = require('./serviceLayer/defaultRouter.js');
var sessionRouter = require('./serviceLayer/sessionRouter.js');             // get an instance of the express Router             // get an instance of the express Router
var ratingsRouter = require('./serviceLayer/ratingsRouter.js');              // get an instance of the express Router
var speakerRouter = require('./serviceLayer/speakerRouter.js');
*/

// configure app to use bodyParser()
// this will let us get the data from a POST
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

// REGISTER OUR ROUTES -------------------------------
// all of our routes will be prefixed with /api
app.use(express.static('Frontend'));
/*app.use('/api', defaultrouter);
defaultrouter.use('/sessions', sessionRouter);
defaultrouter.use('/sessions/:sessionId', sessionRouter);
defaultrouter.use('/ratings', ratingsRouter);
defaultrouter.use('/ratings/:ratingId', ratingsRouter);
defaultrouter.use('/speakers', speakerRouter);
defaultrouter.use('/speakers/:speakerId', speakerRouter);
sessionRouter.use('/ratings', ratingsRouter);
sessionRouter.use('/ratings/:ratingId', ratingsRouter);
*/
// START THE SERVER
// =============================================================================
app.listen(port);
console.log('Server started on port ' + port);