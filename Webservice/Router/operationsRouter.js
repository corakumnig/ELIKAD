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

    let query = "select eli_operation.id as id, text, alarmlevel, datetime, caller, eli_operationtype.name as operationType,"
        + " eli_controlcenter.name as controlcenterName from eli_operation"
        + " inner join eli_operationtype"
        + " on eli_operationtype.id = eli_operation.ID_OPERATIONTYPE"
        + " inner join eli_controlcenter"
        + " on eli_controlcenter.id = eli_operation.id_controlcenter";
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
                query +=  " inner join ELI_OPERATION_DEPT"
                    + " on eli_operation.ID = ELI_OPERATION_DEPT.ID_OPERATION"
                    + " inner join eli_department"
                    + " on eli_department.ID = ELI_OPERATION_DEPT.id_department where eli_department.id = :idDepartment";
                if(idOperation != null){
                    query += " and eli_operation.id = :idOperation";
                    param.push(idOperation);
                }
                param.push(idDepartment);
            }
            else if(idMember != null){
                query += " inner join eli_operation_member"
                    + " on eli_operation_member.id_operation = eli_operation.id"
                    + " inner join eli_member"
                    + " on eli_member.ID = eli_operation_member.id_member"
                if(idOperation != null){
                    query += " where eli_member.id = :idMember and and eli_operation.id = :idOperation";
                    param.push(idOperation);
                }
                else
                    query += " where eli_member.id = :idMember";
                param.push(idMember);
            }
            else if (idOperation != null){
                param.push(idOperation);
                query += " where eli_operation.id = :idOperation";
            }
            oracleConnection.execute(query, param,
                (result) => res.status(200).json(classParser(result.rows, classes.Operation)),
                (err) => res.status(404).json({
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

module.exports = operationsRouter;