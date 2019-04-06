var mongoClient = require('mongodb').MongoClient;
const user = encodeURIComponent('elikad');
const password = encodeURIComponent('elikad1!');
const authMechanism = 'DEFAULT';
const url = `mongodb://${user}:${password}@ds052968.mlab.com:52968/elikad`;

module.exports = {
    execute: (query, onError, res) => {
        mongoClient.connect(url, function(err, db) {  
            if (err) {
                onError(err);
            }
            else{
                var dbo = db.db("elikad");
                query(db, dbo);
            }
        }); 
    }
};