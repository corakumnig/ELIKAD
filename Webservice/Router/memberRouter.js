const express    = require('express');  
const testRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const classParser = require("../Data/classParser");
const classes = require("../Data/classes");

testRouter.get("/", function(req, res){
    let query = "SELECT svnr, firstname, lastname, dateofbirth, dateofentry, phonenumber, email, eli_gender.name as gender, id_department as idDepartment" +
    " from eli_member inner join eli_gender on eli_member.gender = eli_gender.id",
    param = [];
    try{
        oracleConnection.execute(query, param,
            (result) => res.status(200).json(classParser(result.rows, classes.Member)),
            (err) => res.status(404).json({
                message: err.message,
                details: err
            })
        );
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});

testRouter.post("/", function(req, res){
    let query = "insert into members()",
    param = [];
    try{
        oracleConnection.execute(query, param,
            (result) => res.status(200).json(classParser(result.rows, classes.Member)),
            (err) => res.status(404).json({
                message: err.message,
                details: err
            })
        );
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});

module.exports = testRouter;