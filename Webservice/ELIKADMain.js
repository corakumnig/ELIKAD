// BASE SETUP
// =============================================================================

// call the packages we need
var express    = require('express');        // call express
var app        = express();                 // define our app using express
var bodyParser = require('body-parser');
var morgan     = require('morgan');
var fs         = require('fs');
process.env.TZ = 'Europe/Amsterdam';
var port = process.env.PORT || 8080;        // set our port

// ROUTES FOR OUR API
// =============================================================================

var defaultrouter = require('./Router/defaultRouter');

var memberRouter = require('./Router/memberRouter');
var departmentRouter = require('./Router/departmentRouter');
var departmentLoginRouter = require('./Router/Login/departmentLoginRouter');
var memberLoginRouter = require('./Router/Login/memberLoginRouter');
var adminLoginRouter = require('./Router/Login/adminLoginRouter');
var operatorLoginRouter = require('./Router/Login/operatorLoginRouter');
var functionsRouter = require('./Router/functionRouter');
var operationsRouter = require('./Router/operationsRouter');
var departmentStatisticsRouter = require('./Router/departmentStatisticsRouter');
var operationReportRouter = require('./Router/operationReportRouter');
var locationRouter = require("./Router/locationRouter");

// configure app to use bodyParser()
// this will let us get the data from a POST
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

// REGISTER OUR ROUTES -------------------------------
// all of our routes will be prefixed with /api
app.use(express.static('Frontend'));

// START THE SERVER
// =============================================================================

var accessLogStream = fs.createWriteStream(__dirname + '/access.log', {flags: 'a'})
app.use(morgan('combined',  {"stream": accessLogStream}));

app.use('/api', defaultrouter);
defaultrouter.use("/login/department", departmentLoginRouter);
defaultrouter.use("/login/member", memberLoginRouter);
defaultrouter.use("/login/admin", adminLoginRouter);
defaultrouter.use("/login/operator", operatorLoginRouter);
defaultrouter.use('/members', memberRouter);
defaultrouter.use('/members/:idMember', memberRouter);
defaultrouter.use('/departments', departmentRouter);
defaultrouter.use('/departments/:idDepartment', departmentRouter);
departmentRouter.use('/operations', operationsRouter);
departmentRouter.use('/operations/:idOperation', operationsRouter);
departmentRouter.use('/members', memberRouter);
departmentRouter.use('/members/:idMember', memberRouter);
defaultrouter.use("/functions", functionsRouter);
defaultrouter.use("/functions/:idFunction", functionsRouter);
memberRouter.use("/functions", functionsRouter);
memberRouter.use("/functions/:idFunction", functionsRouter);
memberRouter.use("/operations", operationsRouter);
memberRouter.use("/operations/:idOperation", operationsRouter);
defaultrouter.use("/operations", operationsRouter);
defaultrouter.use("/operations/:idOperation", operationsRouter);
operationsRouter.use("/members", memberRouter);
operationsRouter.use("/members/:idMember", memberRouter);
departmentRouter.use("/statistic", departmentStatisticsRouter);
defaultrouter.use("/reports", operationReportRouter);
defaultrouter.use("/locations", locationRouter);
defaultrouter.use("/locations/:idLocation", locationRouter);

app.listen(port, () =>{
    console.log('Server started on port ' + port);
});