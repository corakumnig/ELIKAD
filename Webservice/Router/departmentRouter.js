const express    = require('express');  
const departmentRouter = express.Router({mergeParams: true}); 
const oracleConnection = require("../Data/dbAccess");
const classParser = require("../Data/classParser");
const classes = require("../Data/classes");
const tokenHandler = require("../Data/tokenHandler");

departmentRouter.get("/", function(req, res){
    var idDepartment = req.params.idDepartment;
    var apiToken = req.get("Token");
    let query = "select eli_department.id as id, eli_department.NAME as name, eli_organization.NAME as organization,"
    + " eli_region.name as region, eli_regiontype.TYPE as regiontype,"
    + " eli_location.id as idLocation, eli_location.housenumber as housenumber, eli_location.street as street, eli_location.postalcode as postalcode,"
    +    " eli_location.village as village"
    + " from eli_department inner join eli_location"
    + " on eli_location.id = eli_department.id_location"
    + " inner join eli_organization"
    + " on eli_organization.id = eli_department.id_organization"
    + " inner join eli_region"
    + " on eli_region.id = eli_location.id_region"
    + " inner join eli_regiontype"
    + " on eli_regiontype.id = eli_region.regiontype";
    var param = [];
    var userGroup = tokenHandler.VerifyToken(apiToken);

    if(userGroup != 'department' && userGroup != 'admin' && userGroup != 'operator'){
        res.status(401).json({
            message: "Not authenticated"
        });
    }
    else{
        if(idDepartment != null){
            try{
                query += " where eli_department.id = :idDepartment"
                param.push(idDepartment);

                oracleConnection.execute(query, param,
                    (result) => res.status(200).json(classParser(result.rows, classes.Department)),
                    (err) => res.status(404).json({
                        message: err.message,
                        details: err
                    })
                );
            }
            catch(ex){
                res.status(500).send("500: " + ex);
            }
        }
        else{
            try{
                oracleConnection.execute(query, param,
                    (result) => res.status(200).json(classParser(result.rows, classes.Department)),
                    (err) => res.status(403).json({
                        message: err.message,
                        details: err
                    })
                );
            }
            catch(ex){
                res.status(500).send("500: " + ex);
            }
        }
    }
});

departmentRouter.post("/", function(req, res){
    var apiToken = req.get("Token");
    var member = req.body;
    let query = "insert into eli_member values(:SVNr, :Firstname, :Lastname, to_date(:DateOfBirth, 'dd/MM/yyyy'), to_date(:DateOfEntry, 'dd/MM/yyyy'), :Phonenumber, :Email, null, null, :IdDepartment, :Gender)";
    var param = [member.SVNr, member.Firstname, member.Lastname, member.DateOfBirth, 
        member.DateOfEntry, member.Phonenumber, member.Email, member.IdDepartment, member.Gender];
    var userGroup = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'admin' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            oracleConnection.execute(query, param,
                (result) => res.status(200).json({
                    message: 'Creation successful',
                    details: result
                }),
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

departmentRouter.delete("/:SVNr", function(req, res){
    var SVNr = req.params.SVNr;
    let query = "delete from eli_member where svnr = :SVNr";
    var param = [SVNr];
    var userGroup  = tokenHandler.VerifyToken(apiToken);

    try{
        if(userGroup != 'department' && userGroup != 'admin' && userGroup != 'operator'){
            res.status(401).json({
                message: "Not authenticated"
            });
        }
        else{
            oracleConnection.execute(query, param,
                (result) => res.status(200).json({
                    message: 'Delete successful',
                    details: result
                }),
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

module.exports = departmentRouter;