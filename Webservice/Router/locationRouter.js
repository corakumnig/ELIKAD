const express    = require('express');  
const locationRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const classParser = require("../Data/classParser");
const classes = require("../Data/classes");
const tokenHandler = require("../Data/tokenHandler");

locationRouter.get("/", function (req, res){
    var idLocation = req.params.idLocation;
    let query = "select * from eli_location whereS";
    var param = [];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'member' && userGroup != 'admin' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            if(idLocation != null){
                query +=  " where id = :idLocation";
                param.push(idLocation);
            }
            oracleConnection.execute(query, param,
                (result) => res.status(200).json(classParser(result.rows, classes.Location)),
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

locationRouter.post("/members/:idMember", function(req, res){
    var idMember = req.params.idMember;
    var idOperation = req.params.idOperation;
    let query = "insert into eli_operation_member values(:idOperation, :idMember)";
    var param = [idOperation, idMember];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'admin' && userGroup != 'member' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            if(idOperation == undefined || idMember == undefined){
                res.status(400).json({
                    message: "Parameter missing"
                });
            }
            else{
                oracleConnection.execute(query, param,
                    (result) => res.status(200).json({
                        message: 'Added successfully',
                        details: result
                    }),
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

locationRouter.delete("/members/:idMember", function(req, res){
    var idMember = req.params.idMember;
    var idOperation = req.params.idOperation;
    let query = "delete from eli_operation_member where id_operation = :idOperation and id_member = :idMember";
    var param = [idOperation, idMember];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'admin' && userGroup != 'member' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            if(idOperation == undefined || idMember == undefined){
                res.status(400).json({
                    message: "Parameter missing"
                });
            }
            else{
                oracleConnection.execute(query, param,
                    (result) => res.status(200).json({
                        message: 'Deleted successfully',
                        details: result
                    }),
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

module.exports = locationRouter;