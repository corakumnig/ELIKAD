module.exports = (function(){
    var jwt = require("jsonwebtoken");
    const secret = "323nröasdiuv9eira";

    function _registerToken(user){
        return "Bearer " + jwt.sign(user, secret);
    }

    function _verifyToken(token){
        try{
            return jwt.verify(token.split(' ')[1], secret).group;
        }
        catch(ex){
            return null;
        }
    }

    function _verifyTokenId(token){
        try{
            return jwt.verify(token.split(' ')[1], secret).id;
        }
        catch(ex){
            return null;
        }
    }

    return {
        RegisterToken: _registerToken,
        VerifyToken: _verifyToken,
        VerifyTokenId: _verifyTokenId
    }
})();