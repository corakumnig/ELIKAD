const express    = require('express');  
const loginRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../../Data/dbAccess");
const tokenHandler = require("../../Data/tokenHandler");

loginRouter.post("/", function(req, res){
    var credentials = req.body;
    let query = "select eli_member.id as id, firstname, lastname, id_department from eli_admin inner join eli_member"
        + " on eli_admin.ID = eli_member.id_admin"
        + " where username = :Username and password = :Password";
    let queryById = "select eli_member.id as id, firstname, lastname, id_department from eli_admin inner join eli_member"
        + " on eli_admin.ID = eli_member.id_admin"
        + " where eli_member.id = :Id";
    var apiToken = req.get("Token");
    param = [credentials.Username, credentials.Password];

    if(apiToken != '' && apiToken != null){
        param = [tokenHandler.VerifyTokenId(apiToken)];
        query = queryById;
    }
    try{
        oracleConnection.execute(query, param,
            (result) => {
                if(result.rows.length == 0){
                    res.status(403).json({
                        message: "Wrong credentials"
                    })
                }
                else{
                    var user = {
                        id: result.rows[0][0],
                        firstname: result.rows[0][1],
                        lastname: result.rows[0][2],
                        idDepartment: result.rows[0][3],
                        group: "admin"
                    }

                    var token = tokenHandler.RegisterToken(user);
                    delete user.group;
                    res.setHeader('Token', token);
                    res.status(200).json(user);     
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
        tokenHandler.DeleteAdminToken(apiToken);
        res.status(200).send();
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});

module.exports = loginRouter;