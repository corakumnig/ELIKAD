const oracledb = require('oracledb');


const DB_STRING = process.env.DB_STRING || "212.152.179.117/ora11g",
    DB_USER = process.env.DB_USER || "d5a08",
    DB_PASS = process.env.DB_PASS || "d5a",
    options = {
        autoCommit: true
    };
/*
const DB_STRING = process.env.DB_STRING || "192.168.128.152/ora11g",
DB_USER = process.env.DB_USER || "d5a08",
DB_PASS = process.env.DB_PASS || "d5a",
options = {
    autoCommit: true
};
*/
module.exports = {
    execute: (query, param, onSuccess, onError) => {
        oracledb.getConnection({
            user: DB_USER,
            password: DB_PASS,
            connectString: DB_STRING
        }, (err, connection) => {
            if (err)
                closeAfter(connection, onError, [err]);
            else
                connection.execute(query, param, options, (err, result) => {
                    if (err)
                        closeAfter(connection, onError, [err]);
                    else
                        closeAfter(connection, onSuccess, [result]);
                });
        });
    }
};

function closeAfter(connection, callback, params) {
    callback(...params);
    if (connection)
        connection.close((err) => {
            if (err)
                console.error(err.message);
        });
}