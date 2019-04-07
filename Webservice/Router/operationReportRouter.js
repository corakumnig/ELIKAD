const express    = require('express');  
const operationReportRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const mongoConnection = require("../Data/mongoAccess");
const classParser = require("../Data/classParser");
const classes = require("../Data/classes");
const tokenHandler = require("../Data/tokenHandler");


operationReportRouter.get("/", function (req, res){
    var idOperation = req.params.idOperation;
    var query = {};
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'admin' && userGroup != 'member'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            if(idOperation != undefined)
                query.operationId = parseInt(idOperation);
            mongoConnection.execute((db, dbo) =>{
                dbo.collection("Reports").find(query).toArray(function(err, result) {
                    if (err) {
                        res.status(403).json({message: err});
                    }
                    else{
                        res.status(200).json(result);
                    }
                    db.close();
                });
            },
            (err) =>{
                res.status(409).json(
                    {
                        message: err.message
                    }
                )
            });
        }
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});

operationReportRouter.post("/", function(req, res){
    try{
        var query = {operationId: req.body.operationId};
        var apiToken = req.get("Token");
        var userGroup = tokenHandler.VerifyToken(apiToken);
        if(userGroup != 'admin' && userGroup != 'member'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            if(query.operationId == undefined)
                res.status(400).json({message: "idOperation not found"});
            else{
                mongoConnection.execute((db, dbo) =>{
                    dbo.collection("Reports").update(query, req.body, {upsert: true}, function(err) {
                        if (err) {
                            res.status(403).json({message: err});
                        }
                        else{
                            res.status(200).json({
                                message: "Operation inserted"
                            });
                        }
                        db.close();
                    });
                },
                (err) =>{
                    res.status(409).json(
                        {
                            message: err.message
                        }
                    )
                });
            }
        }
    }
    catch(ex){
        res.status(500).send("500: " + ex);
    }
});

module.exports = operationReportRouter;