const express    = require('express');  
const loginRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../../Data/dbAccess");
const tokenHandler = require("../../Data/tokenHandler");

loginRouter.post("/", function(req, res){
    var credentials = req.body;
    let query = "select eli_member.id, svnr, firstname, lastname, dateofbirth, dateofentry, phonenumber, email, gender, id_department as department, id_department as idDepartment, eli_function.name from eli_member"
        + " inner join eli_function_member"
        + " on eli_function_member.id_member = eli_member.id"
        + " inner join eli_function"
        + " on eli_function.id = eli_function_member.id_function"
        + " where phonenumber = :phonenumber"
        + " and pin = :pin and eli_function.name = 'Kommandant'"
        + " union"
        + " select eli_member.id, svnr, firstname, lastname, dateofbirth, dateofentry, phonenumber, email, gender, id_department as department, id_department as idDepartment, null from eli_member"
        + " where phonenumber = :phonenumber"
        + " and pin = :pin";
    var param = [credentials.phonenumber, credentials.pin];
    try{
        oracleConnection.execute(query, param,
            (result) => {
                if(result.rows.length == 0){
                    res.status(403).json({
                        message: "Wrong credentials"
                    })
                }
                else{
                    var member = {
                        id: result.rows[0][0],
                        firstname: result.rows[0][2],
                        lastname: result.rows[0][3],
                        phonenumber: result.rows[0][6],
                        email: result.rows[0][7],
                        idDepartment: result.rows[0][9],
                        isCommanda: false,
                        group: "member"
                    }

                    if(result.rows.length == 2)
                        member.isCommanda = true;
                    var token = tokenHandler.RegisterToken(member);
                    delete member.group;
                    res.setHeader('Token', token);
                    res.status(200).json(member);     
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