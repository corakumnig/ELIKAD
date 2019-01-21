const express    = require('express');  
const operationsRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const classParser = require("../Data/classParser");
const classes = require("../Data/classes");
const tokenHandler = require("../Data/tokenHandler");

operationsRouter.get("/", function (req, res){
    var idDepartment = req.params.idDepartment;
    var idMember = req.params.idMember;
    var idOperation = req.params.idOperation;

    let query = "select eli_operation.id as id, text, alarmlevel, datetime, caller from eli_operation inner join ELI_OPERATION_DEPT"
        + " on eli_operation.ID = ELI_OPERATION_DEPT.ID_OPERATION"
        + " inner join eli_department"
        + " on eli_department.ID = ELI_OPERATION_DEPT.ID_OPERATION";
    param = [];
    var apiToken = req.get("Token");

    try{
        if(apiToken == null || apiToken == undefined || (!tokenHandler.MemberTokenExists(apiToken) && !tokenHandler.DepartmentTokenExists(apiToken))){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            if(idDepartment != null){
                if(idOperation != null){
                    query += " where eli_department.id = :idDepartment and eli_operation.id = :idOperation";
                    param.push(idOperation);
                }
                else
                    query += " where eli_department.id = :idDepartment";
                param.push(idDepartment);

                oracleConnection.execute(query, param,
                    (result) => res.status(200).json(classParser(result.rows, classes.Operation)),
                    (err) => res.status(404).json({
                        message: err.message,
                        details: err
                    })
                );
            }
            else if(idMember != null){
                if(idOperation != null){
                    query += " where eli_member.id = :idMember and and eli_operation.id = :idOperation";
                    param.push(idOperation);
                }
                else
                    query += " where eli_member.id = :idMember";
                param.push(idMember);

                oracleConnection.execute(query, param,
                    (result) => res.status(200).json(classParser(result.rows, classes.Operation)),
                    (err) => res.status(404).json({
                        message: err.message,
                        details: err
                    })
                );
            }
            else {
                oracleConnection.execute(query, param,
                    (result) => res.status(200).json(classParser(result.rows, classes.Operation)),
                    (err) => res.status(403).json({
                        message: err.message,
                        details: err
                    })
                );
            }
        }
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});

module.exports = operationsRouter;