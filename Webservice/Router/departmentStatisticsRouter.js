var express    = require('express');
const departmentStatisticsRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const classParser = require("../Data/classParser");
const classes = require("../Data/classes");
const tokenHandler = require("../Data/tokenHandler");

departmentStatisticsRouter.get('/', function(req, res) {
    var idDepartment = req.params.idDepartment;
    var numOfMem;
    var numOfMemInOps;
    var numOfOps;

    let queryNumOfOps = "select count(*) from eli_operation" 
        + " inner join eli_operation_dept"
        + " on eli_operation_dept.id_operation = eli_operation.id"
        + " where id_department = :idDepartment";

    let queryNumOfMemInOps = "select count(*) from eli_operation"
        + " inner join eli_operation_member"
        + " on eli_operation_member.ID_OPERATION = eli_operation.id"
        + " inner join eli_operation_dept"
        + " on eli_operation_dept.ID_OPERATION = eli_operation.id"
        + " where eli_operation_dept.ID_DEPARTMENT = :idDepartment";

    let queryNumOfMem = "select count(*) from eli_member"
        + " inner join eli_department"
        + " on eli_member.ID_DEPARTMENT = eli_department.id"
        + " where eli_department.ID = :idDepartment";

    param = [idDepartment];
    try{
        oracleConnection.execute(queryNumOfOps, param,
            (result) => {
                numOfOps = result.rows[0][0];
                oracleConnection.execute(queryNumOfMemInOps, param,
                    (result) => {
                        numOfMemInOps = result.rows[0][0];
                        oracleConnection.execute(queryNumOfMem, param,
                            (result) => {
                                numOfMem = result.rows[0][0];
                                res.status(200).json({
                                    numOfOps: numOfOps,
                                    numOfMemInOps: numOfMemInOps,
                                    numOfMem: numOfMem
                                });
                        },
                        (err) => res.status(403).json({
                            message: err.message,
                            details: err
                        }));
                },
                (err) => res.status(403).json({
                    message: err.message,
                    details: err
                })
                );
        },
        (err) => res.status(403).json({
            message: err.message,
            details: err
        })
        );
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    } 
});

module.exports = departmentStatisticsRouter;