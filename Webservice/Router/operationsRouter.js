const express    = require('express');  
const operationsRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const mongoConnection = require("../Data/mongoAccess");
const classParser = require("../Data/classParser");
const classes = require("../Data/classes");
const tokenHandler = require("../Data/tokenHandler");
const http = require('http').Server(express);
const socketio = require('socket.io')(http);


operationsRouter.get("/", function (req, res){
    var idDepartment = req.params.idDepartment;
    var idMember = req.params.idMember;
    var idOperation = req.params.idOperation;
    var year = req.query.year;
    let query = "select eli_operation.id as id, text, alarmlevel, start_datetime as startDatetime, end_datetime as endDatetime, caller, eli_operationtype.name as type, "
        + " eli_controlcenter.name as controlcenterName, eli_operation.id_location as idLocation from eli_operation"
        + " inner join eli_operationtype"
        + " on eli_operationtype.id = eli_operation.ID_OPERATIONTYPE"
        + " inner join eli_controlcenter"
        + " on eli_controlcenter.id = eli_operation.id_controlcenter";
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
            if(idDepartment != null){
                query +=  " inner join ELI_OPERATION_DEPT"
                    + " on eli_operation.ID = ELI_OPERATION_DEPT.ID_OPERATION"
                    + " inner join eli_department"
                    + " on eli_department.ID = ELI_OPERATION_DEPT.id_department where eli_department.id = :idDepartment";
                if(idOperation != null){
                    query += " and eli_operation.id = :idOperation";
                    param.push(idOperation);
                }
                param.push(idDepartment);
            }
            else if(idMember != null){
                query += " inner join eli_operation_member"
                    + " on eli_operation_member.id_operation = eli_operation.id"
                    + " inner join eli_member"
                    + " on eli_member.ID = eli_operation_member.id_member"
                if(idOperation != null){
                    query += " where eli_member.id = :idMember and and eli_operation.id = :idOperation";
                    param.push(idOperation);
                }
                else
                    query += " where eli_member.id = :idMember";
                param.push(idMember);
            }
            else if (idOperation != null){
                param.push(idOperation);
                query += " where eli_operation.id = :idOperation";
            }
            if(year != undefined){
                param.push(year);
                if(query.includes("where"))
                    query += " and extract(year from eli_operation.end_datetime) = :year";
                else
                    query += " where extract(year from eli_operation.end_datetime) = :year";
            }
            oracleConnection.execute(query, param,
                (result) => res.status(200).json(classParser(result.rows, classes.Operation)),
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

operationsRouter.get("/wasntthere", function(req, res){
    var idDepartment = req.params.idDepartment;
    var idOperation = req.params.idOperation;
    let query = "select eli_member.id as id, svnr, firstname, lastname, dateofbirth, dateofentry, phonenumber, "
        + " email, gender, id_department as idDepartment from eli_member"
        + " where id not in("
        + " select eli_member.id from eli_operation_member"
        + " inner join eli_member"
        + " on eli_member.id = eli_operation_member.id_member"
        + " inner join ELI_DEPARTMENT"
        + " on ELI_DEPARTMENT.id = eli_member.ID_DEPARTMENT"
        + " where eli_member.id_department = :idDepartment and id_operation = :idOperation"
        + " ) and id_department = :idDepartment";
    var param = [idDepartment, idOperation];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'member' && userGroup != 'admin' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            oracleConnection.execute(query, param,
                (result) => res.status(200).json(classParser(result.rows, classes.Member)),
                (err) => res.status(403).json({
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

operationsRouter.get("/wasthere", function(req, res){
    var idDepartment = req.params.idDepartment;
    var idOperation = req.params.idOperation;
    let query = "select eli_member.id as id, svnr, firstname, lastname, dateofbirth, dateofentry, phonenumber, "
        + " email, gender, id_department as idDepartment from eli_member"
        + " where id in("
        + " select eli_member.id from eli_operation_member"
        + " inner join eli_member"
        + " on eli_member.id = eli_operation_member.id_member"
        + " inner join ELI_DEPARTMENT"
        + " on ELI_DEPARTMENT.id = eli_member.ID_DEPARTMENT"
        + " where eli_member.id_department = :idDepartment and id_operation = :idOperation"
        + " ) and id_department = :idDepartment";;
    var param = [idDepartment, idOperation];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'member' && userGroup != 'admin' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            oracleConnection.execute(query, param,
                (result) => res.status(200).json(classParser(result.rows, classes.Member)),
                (err) => res.status(403).json({
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

operationsRouter.get("/departments/:idDepartment/years", function(req, res){
    var idDepartment = req.params.idDepartment;
    let query = "select EXTRACT(year FROM eli_operation.end_datetime) as years from eli_operation"
    + " inner join eli_operation_dept"
    + " on eli_operation.id = eli_operation_dept.id_operation"
    + " where eli_operation_dept.ID_DEPARTMENT = :idDepartment"
    + " group by EXTRACT(year FROM eli_operation.end_datetime) order by years desc";
    var param = [idDepartment];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'member' && userGroup != 'admin' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            oracleConnection.execute(query, param,
                (result) => res.status(200).json(classParser(result.rows, Number)),
                (err) => res.status(403).json({
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

operationsRouter.get("/wasthere", function(req, res){
    var idDepartment = req.params.idDepartment;
    var idOperation = req.params.idOperation;
    let query = "select * from eli_member"
        + " where id in("
        + " select eli_member.id from eli_operation_member"
        + " inner join eli_member"
        + " on eli_member.id = eli_operation_member.id_member"
        + " inner join ELI_DEPARTMENT"
        + " on ELI_DEPARTMENT.id = eli_member.ID_DEPARTMENT"
        + " where eli_member.id_department = :idDepartment and id_operation = :idOperation"
        + ") and id_department = :idDepartment";;
    var param = [idDepartment, idOperation];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'member' && userGroup != 'admin' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            oracleConnection.execute(query, param,
                (result) => res.status(200).json(classParser(result.rows, classes.Member)),
                (err) => res.status(403).json({
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

/* operationsRouter.post("/", function(req, res){
    mongoConnection.execute((db, dbo) =>{
        dbo.collection("Operations").insertOne(req.body, function(err) {
            if (err) {
                onError();
            }
            res.status(200).json({
                message: "Operation inserted"
            });
            db.close();
        });
    },
    (err) =>{
        res.status(409).json(
            {
                message: err.message
            }
        )
    })
}); */

operationsRouter.post("/", function(req, res){
    var operation = req.body;
    let query = "insert into eli_operation values(:opsid, to_date(:datetime, 'dd.MM.yyyy HH24:MI'), null, :caller, :description, :alarmlevel, :idControlcenter, :idLocation, null)";
    var querySeq = "select eli_seq_operation.nextval from dual";
    var param = [];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);
    var opsid;

    try{
        if(userGroup != 'department' && userGroup != 'member' && userGroup != 'admin' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            oracleConnection.execute(querySeq, param,
                (result1) => {
                    opsid = result1.rows[0][0];
                    param = [opsid, operation.datetime, operation.caller, operation.description, operation.alarmlevel, 
                        operation.idControlcenter, operation.idLocation];
                    oracleConnection.execute(query, param,
                        (result) => {
                            res.setHeader('idOperation', opsid);
                            res.status(200).send();
                    },
                    (err) => res.status(403).json({
                        message: err.message,
                        details: err
                    })
                )
            },
            (err) => res.status(403).json({
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

operationsRouter.get("/:idDepartment/active", function(req, res){
    var idDepartment = req.params.idDepartment;
    let query = "select eli_operation.id as id, text, alarmlevel, start_datetime as startDatetime, end_datetime as endDatetime, caller, eli_operationtype.name as type, "
        + " eli_controlcenter.name as controlcenterName, eli_operation.id_location as idLocation from eli_operation"
        + " inner join eli_operation_dept"
        + " on eli_operation_dept.id_operation = eli_operation.id"
        + " inner join eli_operationtype"
        + " on eli_operationtype.id = eli_operation.ID_OPERATIONTYPE"
        + " inner join eli_controlcenter"
        + " on eli_controlcenter.id = eli_operation.id_controlcenter"
        + " where eli_operation_dept.id_department = :idDepartment and end_datetime is null";
    var param = [idDepartment];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'member' && userGroup != 'admin' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            oracleConnection.execute(query, param,
                (result) => res.status(200).json(classParser(result.rows, classes.Operation)),
                (err) => res.status(403).json({
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

operationsRouter.post("/departments/:idDepartment", function(req, res){
    var idDepartment = req.params.idDepartment;
    var idOperation = req.params.idOperation;
    let query = "insert into eli_operation_dept values(:idOperation, :idDepartment)";
    var param = [idOperation, idDepartment];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'member' && userGroup != 'admin' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            if(idOperation == undefined){
                res.status(400).json({
                    message: "Parameter missing"
                });
            }
            else{
                oracleConnection.execute(query, param,
                    (result) => res.status(200).json({
                        message: 'Insert succeeded',
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


operationsRouter.post("/members/:idMember", function(req, res){
    var idMember = req.params.idMember;
    var idOperation = req.params.idOperation;
    let query = "insert into eli_operation_member values(:idOperation, :idMember)";
    var param = [idOperation, idMember];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'member' && userGroup != 'admin' && userGroup != 'operator'){
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

operationsRouter.put("", function(req, res){
    var idOperation = req.params.idOperation;
    let query = "update eli_operation set end_datetime = sysdate where id = :idOperation";
    var param = [idOperation];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'member' && userGroup != 'admin' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            if(idOperation == undefined){
                res.status(400).json({
                    message: "Parameter missing"
                });
            }
            else{
                oracleConnection.execute(query, param,
                    (result) => res.status(200).json({
                        message: 'Ended successfully',
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

operationsRouter.delete("/members/:idMember", function(req, res){
    var idMember = req.params.idMember;
    var idOperation = req.params.idOperation;
    let query = "delete from eli_operation_member where id_operation = :idOperation and id_member = :idMember";
    var param = [idOperation, idMember];
    var apiToken = req.get("Token");
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'member' && userGroup != 'admin' && userGroup != 'operator'){
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

module.exports = operationsRouter;