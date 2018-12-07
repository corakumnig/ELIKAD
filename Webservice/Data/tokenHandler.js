module.exports = (function(){
    var tokens = [];
    function _createToken(id) {
        length = 32;
        chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        var result = '';
        for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
        return id + "_" + result;
    }
    
    function _addToken(token){
        if(tokens.indexOf(token) >= 0)
            throw "Already logged in"
        tokens.push(token);
    }

    function _deleteToken(token){
        var idx = tokens.indexOf(token);
        if(idx == -1)
            throw "User not logged in"
        tokens.slice(idx);
    }

    function _tokenExists(token){
        return (tokens.indexOf(token) >= 0);
    }

    return{
        CreateToken: _createToken,
        AddToken: _addToken,
        DeleteToken: _deleteToken,
        TokenExists: _tokenExists
    }
})();