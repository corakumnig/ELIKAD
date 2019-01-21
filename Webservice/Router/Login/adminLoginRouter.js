const express    = require('express');  
const loginRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../../Data/dbAccess");
const classParser = require("../../Data/classParser");
const classes = require("../../Data/classes");
const tokenHandler = require("../../Data/tokenHandler");
const tokens = [];

loginRouter.post("/", function(req, res){
    var credentials = req.body;
    let query = "select id from eli_department"
    + " where name = :name and PASSWORD like :password",
    param = [credentials.username, credentials.password];
    try{
        oracleConnection.execute(query, param,
            (result) => {
                if(result.rows.length == 0){
                    res.status(403).json({
                        message: "Wrong credentials"
                    })
                }
                else{
                    var id = result.rows[0][0];
                    var token = tokenHandler.CreateToken(id);
                    tokenHandler.AddAdminToken(token);
                    res.status(202).json({
                        id: id,
                        token: token
                    });     
                }    
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

module.exports = loginRouter;