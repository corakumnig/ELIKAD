const express    = require('express');  
const loginRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../../Data/dbAccess");
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
                    var dept = {
                        id: result.rows[0][0],
                        name: result.rows[0][1],
                        group: "department"
                    };
                    var token = tokenHandler.RegisterToken(dept);
                    res.setHeader('Token', token);
                    delete dept.group;
                    res.status(200).json(dept);     
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

loginRouter.delete("/", function(req, res){
    try{
        var apiToken = req.get("Token");
        tokenHandler.DeleteDepartmentToken(apiToken);
        res.status(200).send();
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});

module.exports = loginRouter;