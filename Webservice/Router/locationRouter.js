const express    = require('express');  
const locationRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const classParser = require("../Data/classParser");
const classes = require("../Data/classes");
const tokenHandler = require("../Data/tokenHandler");

locationRouter.get("/", function (req, res){
    var idLocation = req.params.idLocation;
    let query = "select * from eli_location";
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

locationRouter.post("/", function (req, res){
    var location = req.body;
    let query = "insert into eli_location values(:locid, :housenumber, :street, :postalcode, :city, null)";
    var querySeq = "select eli_seq_location.nextval from dual";
    var param = [];
    var param1 = [];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);
    var locid;

    try{
        if(userGroup != 'department' && userGroup != 'member' && userGroup != 'admin' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            oracleConnection.execute(querySeq, param1,
                (result1) =>{ 
                    locid = result1.rows[0][0];
                    param = [locid, location.housenumber, location.street, location.postalcode, location.city];
                    oracleConnection.execute(query, param,
                        (result) => {
                            res.setHeader('idLocation', locid);
                            res.status(200).json()
                    },
                        (err) => res.status(404).json({
                            message: err.message,
                            details: err
                        })
                    )
                },
                (err) => res.status(404).json({
                    message: err.message,
                    details: err
                })
            );
            /* oracleConnection.execute(querySeq, param, 
                function(result1){
                    locid = result1.rows[0][0];
                    param.push(locid);
                    oracleConnection.execute(query, param,
                        (result) => res.status(200).json({
                            message: 'Creation successful',
                            details: result
                        }),
                        (err) => res.status(404).json({
                            message: err.message,
                            details: err
                        })
                    )
                }
            ), 
            (err) => res.status(404).json({
                message: err.message,
                details: err
            }); */
            
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
                        id: 'Added successfully',
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