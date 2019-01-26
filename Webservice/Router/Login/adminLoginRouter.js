const express    = require('express');  
const loginRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../../Data/dbAccess");
const classParser = require("../../Data/classParser");
const classes = require("../../Data/classes");
const tokenHandler = require("../../Data/tokenHandler");
const tokens = [];

loginRouter.post("/", function(req, res){
    var credentials = req.body;
    let query = "select eli_member.id as id, firstname, lastname, id_department from eli_admin inner join eli_member"
        + " on eli_admin.ID = eli_member.id_admin"
        + " where username = :Username and password = :Password";
    param = [credentials.Username, credentials.Password];
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
                    var firstname = result.rows[0][1];
                    var lastname = result.rows[0][2];
                    var idDepartment = result.rows[0][3];
                    var token = tokenHandler.CreateToken(id);

                    tokenHandler.AddAdminToken(token);
                    res.setHeader('Token', token);
                    res.status(202).json({
                        id: id,
                        firstname: firstname,
                        lastname: lastname,
                        idDepartment: idDepartment
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