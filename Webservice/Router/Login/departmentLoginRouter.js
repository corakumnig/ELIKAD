const express    = require('express');  
const loginRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../../Data/dbAccess");
const classParser = require("../../Data/classParser");
const classes = require("../../Data/classes");
const tokenHandler = require("../../Data/tokenHandler");

loginRouter.post("/", function(req, res){
    var credentials = req.body;
    let query = "select id, name from eli_department"
    + " where name = :name and PASSWORD like :password",
    param = [credentials.name, credentials.password];
    try{
        oracleConnection.execute(query, param,
            (result) => {
                if(result.rows.length == 0){
                    res.status(401).json({
                        message: 'Wrong credentials!'
                    });   
                }
                else{
                    var id = result.rows[0][0];
                    var name = result.rows[0][1];
                    var token = tokenHandler.CreateDepartmentToken(id);
                    tokenHandler.AddDepartmentToken(token);
                    res.status(202).json({
                        id: id,
                        name: name,
                        token: token
                    });     
                }    
        },
            (err) => res.status(500).json({
                message: err.message,
                details: err
            })
        );
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});

module.exports = loginRouter;