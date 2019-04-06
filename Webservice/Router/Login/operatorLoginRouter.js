const express    = require('express');  
const operatorLoginRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../../Data/dbAccess");
const tokenHandler = require("../../Data/tokenHandler");

operatorLoginRouter.post("/", function(req, res){
    var credentials = req.body;
    let query = "select eli_member.id as id, firstname, lastname, id_department from eli_operator inner join eli_member"
        + " on eli_operator.ID = eli_member.id_operator"
        + " where username = :Username and password = :Password";
    let queryById = "select eli_member.id as id, firstname, lastname, id_department from eli_operator inner join eli_member"
        + " on eli_operator.ID = eli_member.id_operator"
        + " where eli_member.id = :Id";
    var apiToken = req.get("Token");
    param = [credentials.username, credentials.password];

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
                        group: "operator"
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

module.exports = operatorLoginRouter;