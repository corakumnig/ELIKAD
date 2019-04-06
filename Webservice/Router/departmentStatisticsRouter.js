var express    = require('express');
const departmentStatisticsRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const tokenHandler = require("../Data/tokenHandler");
const oracledb = require("oracledb");

departmentStatisticsRouter.get('/', function(req, res) {
    var idDepartment = req.params.idDepartment;
    var numberOfMembers;
    var numberOfMembersInOperations;
    var numberOfOperations;
    let query = "BEGIN eli_statistics(:ID_DEPARTMENT, :NUMBER__MEMBERS, :NUMBER_MEMBERS_IN_OPERATIONS, :NUMBER_OPERATIONS); END;"
    var apiToken = req.get("Token");

    var param = {ID_DEPARTMENT: idDepartment,  NUMBER__MEMBERS: {  type: oracledb.NUMBER, dir: oracledb.BIND_OUT }, 
        NUMBER_MEMBERS_IN_OPERATIONS: { type: oracledb.NUMBER, dir: oracledb.BIND_OUT }, 
        NUMBER_OPERATIONS: { type: oracledb.NUMBER, dir: oracledb.BIND_OUT }};
    var userGroup  = tokenHandler.VerifyToken(apiToken);
    
    try{
        if(userGroup != 'admin'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            oracleConnection.execute(query, param,
                (result) => {
                    res.status(200).json({
                        numberOfOperations: result.outBinds.NUMBER_OPERATIONS,
                        numberOfMembers: result.outBinds.NUMBER__MEMBERS,
                        numberOfMembersInOperations: result.outBinds.NUMBER_MEMBERS_IN_OPERATIONS
                    });
                },
                (err) => res.status(403).json({
                    message: err.message,
                    details: err
                }),
            (err) => res.status(403).json({
                message: err.message,
                details: err
            })
            );
        }
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    } 
});

module.exports = departmentStatisticsRouter;