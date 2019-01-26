const express    = require('express');  
const memberRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const classParser = require("../Data/classParser");
const classes = require("../Data/classes");
const tokenHandler = require("../Data/tokenHandler");

memberRouter.get("/", function(req, res){
    var idMember = req.params.idMember;
    var idOperation = req.params.idOperation;
    let query = "SELECT eli_member.id as id, svnr, firstname, lastname, dateofbirth, dateofentry, phonenumber, " +
    " email, gender, id_department as idDepartment from eli_member",
    param = [];
    var apiToken = req.get("Token");

    try{
        if(apiToken == null || apiToken == undefined || (!tokenHandler.MemberTokenExists(apiToken) && !tokenHandler.AdminTokenExists(apiToken))){
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
                (result) => res.status(201).json({
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

memberRouter.put("/", function(req, res){
    var idMember = req.params.idMember;
    let query = "update eli_member set firstname=:firstname, lastname=:lastname, phonenumber=:phonenumber, email=:email, pin=:pin, id_department=:idDepartment where id = :id";
    var member = req.body;
    param = [member.firstname, member.lastname, member.phonenumber, member.email, member.pin, member.idDepartment, member.id];
    var apiToken = req.get("Token");
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