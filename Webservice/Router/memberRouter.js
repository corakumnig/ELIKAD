const express    = require('express');  
const memberRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const classParser = require("../Data/classParser");
const classes = require("../Data/classes");
const tokenHandler = require("../Data/tokenHandler");

memberRouter.get("/", function(req, res){
    var idMember = req.params.idMember;
    var idOperation = req.params.idOperation;
    var idDepartment = req.params.idDepartment;
    var withFunctions = req.query.function == "true";
    let query = "SELECT distinct eli_member.id as id, svnr, firstname, lastname, dateofbirth, dateofentry, phonenumber, " +
        " email, gender, id_department as idDepartment " + ((withFunctions) ? ", eli_function.name as functionName" : "") + " from eli_member"
        + ((withFunctions) ?
         " FULL OUTER JOIN eli_function_member"
        + " on eli_function_member.id_member = eli_member.id"
        + " FULL OUTER JOIN eli_function"
        + " on eli_function.id = eli_function_member.id_function" : "");
    var param = [];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'admin' && userGroup != 'member'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            if(idDepartment != null){
                param.push(idDepartment);
                query += " inner join eli_department on eli_member.id_department = eli_department.id where id_department = :idDepartment";
                if(idMember != null){
                    query += " and id_department = :idDepartment";
                    param.push(idMember);
                }
            }
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

memberRouter.get("/withoutdepartment", function(req, res){
    let query = "select * from eli_member where id_department is null";
    var param = [];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'admin' && userGroup != 'member'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
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
    let query = "insert into eli_member values(eli_seq_member.nextVal, :sVNr, :firstname, :lastname, to_date(:dateOfBirth, 'dd/MM/yyyy'), to_date(:dateOfEntry, 'dd/MM/yyyy'), :phonenumber, :email, :pin, null, null, :idDepartment, :gender)";
    var param = [member.sVNr, member.firstname, member.lastname, member.dateOfBirth, 
        member.dateOfEntry, member.phonenumber, member.email, member.pin, member.idDepartment, member.gender];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'admin' && userGroup != 'member'){
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
    let query = "update eli_member set id_department = null where id = :idMember";
    var param = [idMember];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'admin' && userGroup != 'member'){
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

memberRouter.put("/", function(req, res){
    var idMember = req.params.idMember;
    let query = "update eli_member set";
    var member = req.body;
    var param = [];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);
    
    try{
        if(userGroup != 'department' && userGroup != 'admin' && userGroup != 'member'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            if(member.firstname != null || member.Firstname != null){
                param.push(member.firstname);
                query += " firstname=:firstname";
            }
            if(member.lastname != null || member.Lastname != null){
                param.push(member.lastname);
                query += ", lastname=:lastname";
            }
            if(member.phonenumber != null || member.Phonenumber != null){
                param.push(member.phonenumber);
                query += ", phonenumber=:phonenumber";
            }
            if(member.email != null || member.Email != null){
                param.push(member.email);
                query += ", email=:email";
            }
            if(member.idDepartment != null || member.IdDepartment != null){
                param.push(member.idDepartment);
                query += ", id_department=:idDepartment";
            }
            if(member.dateOfBirth != null || member.DateOfBirth != null){
                param.push(member.dateOfBirth);
                query += ", dateofbirth=to_date(:dateofbirth, 'dd/MM/yyyy')";
            }
            if(member.gender != null){
                param.push(member.gender);
                query += ", gender=:gender";
            }
            if(member.pin != null){
                param.push(member.pin);
                query += ", pin=:pin";
            }
            param.push(idMember);
            query += " where id = :id";

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