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

var defaultrouter = require('./Router/defaultRouter');
/*
var sessionRouter = require('./serviceLayer/sessionRouter.js');             // get an instance of the express Router             // get an instance of the express Router
var ratingsRouter = require('./serviceLayer/ratingsRouter.js');              // get an instance of the express Router
var speakerRouter = require('./serviceLayer/speakerRouter.js');
*/

var memberRouter = require('./Router/memberRouter');
var departmentRouter = require('./Router/departmentRouter');
var departmentLoginRouter = require('./Router/Login/departmentLoginRouter');
var memberLoginRouter = require('./Router/Login/memberLoginRouter');
var adminLoginRouter = require('./Router/Login/adminLoginRouter');
var functionsRouter = require('./Router/functionRouter');

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
app.use('/api', defaultrouter)
defaultrouter.use("/login/department", departmentLoginRouter);
defaultrouter.use("/login/member", memberLoginRouter);
defaultrouter.use("/login/admin", adminLoginRouter);
defaultrouter.use('/members', memberRouter);
defaultrouter.use('/members/:idMember', memberRouter);
defaultrouter.use('/departments', departmentRouter);
defaultrouter.use('/departments/:idDepartment', departmentRouter);
defaultrouter.use("/functions", functionsRouter);
defaultrouter.use("/functions/:idFunction", functionsRouter);
memberRouter.use("/functions", functionsRouter);
memberRouter.use("/functions/:idFunction", functionsRouter);
console.log('Server started on port ' + port);