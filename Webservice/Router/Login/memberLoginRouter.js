const express    = require('express');  
const loginRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../../Data/dbAccess");
const classParser = require("../../Data/classParser");
const classes = require("../../Data/classes");
const tokenHandler = require("../../Data/tokenHandler");

loginRouter.post("/", function(req, res){
    var credentials = req.body;
    let query = "select id, svnr, firstname, lastname, dateofbirth, dateofentry, phonenumber, email, gender, id_department as department from eli_member where phonenumber = :phonenumber" + 
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
                    var token = tokenHandler.CreateToken(id);
                    tokenHandler.AddMemberToken(token);
                    res.setHeader('Token', token);
                    res.status(200).json({
                        id: id,
                        firstname: result.rows[0][2],
                        lastname: result.rows[0][3],
                        phonenumber: result.rows[0][6],
                        email: result.rows[0][7]
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

loginRouter.delete("/", function(req, res){
    try{
        var apiToken = req.get("Token");
        tokenHandler.DeleteMemberToken(apiToken);
        res.status(200).send();
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});

module.exports = loginRouter;