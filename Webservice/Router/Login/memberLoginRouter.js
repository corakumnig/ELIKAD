const express    = require('express');  
const loginRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../../Data/dbAccess");
const classParser = require("../../Data/classParser");
const classes = require("../../Data/classes");
const tokenHandler = require("../../Data/tokenHandler");

loginRouter.post("/", function(req, res){
    var credentials = req.body;
    let query = "select * from eli_member where phonenumber = :phonenumber" + 
        " and pin = :pin",
    param = [credentials.phonenumber, credentials.pin];
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
                    var token = tokenHandler.CreateMemberToken(id);
                    tokenHandler.AddMemberToken(token);
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