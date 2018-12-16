const express    = require('express');
const functionsRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const classParser = require("../Data/classParser");
const classes = require("../Data/classes");
const tokenHandler = require("../Data/tokenHandler");

/*
functionsRouter.get("/", function(req, res){
    var id = req.params.id;
    let query = "SELECT id, name, shortcut from eli_function",
    param = [];
    var apiToken = req.get("Token");

    try{
        //if(apiToken == null || apiToken == undefined || !tokenHandler.MemberTokenExists(apiToken)){
        if(false){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            if(id != null){
                    query += " where id = :id"
                    param.push(id);

                    oracleConnection.execute(query, param,
                        (result) => res.status(200).json(classParser(result.rows, classes.Function)),
                        (err) => res.status(404).json({
                            message: err.message,
                            details: err
                        })
                    );
            }
            else{
                oracleConnection.execute(query, param,
                    (result) => res.status(200).json(classParser(result.rows, classes.Function)),
                    (err) => res.status(403).json({
                        message: err.message,
                        details: err
                    })
                );
            }
        }
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});
*/

functionsRouter.get("/", function(req, res){
    var idFunction = req.params.idFunction;
    var idMember = req.params.idMember;
    let query = "SELECT eli_function.id, eli_function.name, eli_function.shortcut from eli_member inner join eli_function_member"
        + " on eli_function_member.id_member = eli_member.id"
        + " inner join eli_function"
        + " on eli_function.id = eli_function_member.id_function",
    param = [];
    var apiToken = req.get("Token");

    try{
        //if(apiToken == null || apiToken == undefined || !tokenHandler.MemberTokenExists(apiToken)){
        if(false){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            if(idMember != null && idFunction != null){
                query += " where eli_member.id = :idMember and eli_function.id = :idFunction"
                param.push(idMember);
                param.push(idFunction);
            }
            else if(idMember != null && idFunction == null)
            {
                query += " where eli_member.id = :idMember";
                param.push(idMember);
            }
            else if(idMember == null && idFunction != null){
                query += " where eli_function.id = :idFunction";
                param.push(idFunction);
            }

            oracleConnection.execute(query, param,
                (result) => res.status(200).json(classParser(result.rows, classes.Function)),
                (err) => res.status(404).json({
                    message: err.message,
                    details: err
                })
            );
        }
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});

module.exports = functionsRouter;