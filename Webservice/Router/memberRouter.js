const express    = require('express');  
const memberRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const classParser = require("../Data/classParser");
const classes = require("../Data/classes");
const tokenHandler = require("../Data/tokenHandler");

memberRouter.get("/", function(req, res){
    var idMember = req.params.idMember;
    var idOperation = req.params.idOperation;
    let query = "SELECT distinct eli_member.id as id, svnr, firstname, lastname, dateofbirth, dateofentry, phonenumber, " +
    " email, gender, id_department as idDepartment, eli_function.name as functionName from eli_member"
    + " inner join eli_function_member"
    + " on eli_function_member.id_member = eli_member.id"
    + " inner join eli_function"
    + " on eli_function.id = eli_function_member.id_function";
    param = [];
    var apiToken = req.get("Token");

    try{
        if(apiToken == null || apiToken == undefined || (!tokenHandler.MemberTokenExists(apiToken) && !tokenHandler.AdminTokenExists(apiToken) && !tokenHandler.DepartmentTokenExists(apiToken))){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            if(idOperation != null){
                param.push(idOperation);
                query += " inner join eli_operation_member on eli_member.id = eli_operation_member.id_member where id_operation = :idOperation";
                if(idMember != null){
                    query += " and id_member = :idMember";
                    param.push(idMember);
                }
            }
            else if(idMember != null){
                    query += " where id = :idMember"
                    param.push(idMember);
            }
            oracleConnection.execute(query, param,
                (result) => res.status(200).json(classParser(result.rows, classes.Member)),
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

memberRouter.post("/", function(req, res){
    var member = req.body;
    let query = "insert into eli_member values(:SVNr, :Firstname, :Lastname, to_date(:DateOfBirth, 'dd/MM/yyyy'), to_date(:DateOfEntry, 'dd/MM/yyyy'), :Phonenumber, :Email, null, null, :IdDepartment, :Gender)",
    param = [member.SVNr, member.Firstname, member.Lastname, member.DateOfBirth, 
        member.DateOfEntry, member.Phonenumber, member.Email, member.IdDepartment, member.Gender];
    var apiToken = req.get("Token");
    try{
        if(apiToken == null || apiToken == undefined || (!tokenHandler.MemberTokenExists(apiToken) && !tokenHandler.AdminTokenExists(apiToken))){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            oracleConnection.execute(query, param,
                (result) => res.status(200).json({
                    message: 'Creation successful',
                    details: result
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

memberRouter.delete("/", function(req, res){
    var idMember = req.params.idMember;
    let query = "delete from eli_member where id = :idMember",
    param = [idMember];
    var apiToken = req.get("Token");
    try{
        if(apiToken == null || apiToken == undefined || (!tokenHandler.MemberTokenExists(apiToken) && !tokenHandler.AdminTokenExists(apiToken))){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            oracleConnection.execute(query, param,
                (result) => res.status(200).json({
                    message: 'Delete successful',
                    details: result
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

memberRouter.put("/", function(req, res){ //toDo prüfen ob alle parameter != null sind und alles updaten
    var idMember = req.params.idMember;
    let query = "update eli_member set";
    var member = req.body;
    var param = [];
    var apiToken = req.get("Token");

    if(member.firstname != null){
        param.push(member.firstname);
        query += " firstname=:firstname";
    }
    if(member.lastname != null){
        param.push(member.lastname);
        query += ", lastname=:lastname";
    }
    if(member.phonenumber != null){
        param.push(member.phonenumber);
        query += ", phonenumber=:phonenumber";
    }
    if(member.email != null){
        param.push(member.email);
        query += ", email=:email";
    }
    if(member.idDepartment != null){
        param.push(member.idDepartment);
        query += ", id_department=:idDepartment";
    }
    if(member.dateOfBirth != null){
        param.push(member.dateOfBirth);
        query += ", dateofbirth=to_date(:dateofbirth, 'dd/MM/yyyy')";
    }
    param.push(idMember);
    query += " where id = :id";
    try{
        if(!tokenHandler.MemberTokenExists(apiToken) && !tokenHandler.AdminTokenExists(apiToken)){
            res.status(401).json({
                message: "Not authenticated"
            });
        } 
        else{
            if(idMember != null){
                oracleConnection.execute(query, param,
                    (result) => res.status(200).json({
                        message: 'Update successful',
                        details: result
                    }),
                    (err) => res.status(403).json({
                        message: err.message,
                        details: err
                    })
                );
            }
            else
                res.status(400).send();
        }   
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});

module.exports = memberRouter;