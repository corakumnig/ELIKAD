const express    = require('express');  
const departmentRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const classParser = require("../Data/classParser");
const classes = require("../Data/classes");

departmentRouter.get("/", function(req, res){
    var SVNr = req.params.svnr;
    let query = "SELECT svnr, firstname, lastname, dateofbirth, dateofentry, phonenumber, " +
    " email, gender, id_department from eli_member",
    param = [];

    if(SVNr != null){
        try{
            query += " where svnr = :SVNr"
            param.push(SVNr);

            oracleConnection.execute(query, param,
                (result) => res.status(200).json(new Department(classParser(result.rows, classes.Member)),
                (err) => res.status(404).json({
                    message: err.message,
                    details: err
                })
            ));
        }
        catch(ex){
            res.status(500).send("500: " + ex);
        }
    }
    else{
        try{
            oracleConnection.execute(query, param,
                (result) => res.status(200).json(classParser(result.rows, classes.Member)),
                (err) => res.status(403).json({
                    message: err.message,
                    details: err
                })
            );
        }
        catch(ex){
            res.status(500).send("500: " + ex);
        }
    }
});

departmentRouter.post("/", function(req, res){
    var member = req.body;
    let query = "insert into eli_member values(:SVNr, :Firstname, :Lastname, to_date(:DateOfBirth, 'dd/MM/yyyy'), to_date(:DateOfEntry, 'dd/MM/yyyy'), :Phonenumber, :Email, null, null, :IdDepartment, :Gender)",
    param = [member.SVNr, member.Firstname, member.Lastname, member.DateOfBirth, 
        member.DateOfEntry, member.Phonenumber, member.Email, member.IdDepartment, member.Gender];
    try{
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
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});

departmentRouter.delete("/:SVNr", function(req, res){
    var SVNr = req.params.SVNr;
    let query = "delete from eli_member where svnr = :SVNr",
    param = [SVNr];
    try{
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
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});

module.exports = departmentRouter;