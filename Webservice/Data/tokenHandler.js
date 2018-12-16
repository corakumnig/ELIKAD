module.exports = (function(){
    var departmentTokens = [];
    var memberTokens = [];
    var adminTokens = [];

    function _createMemberToken(id) {
        var result = '';
        var length = 32;
        var chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
        return id + "_" + result;
    }

    function _createDepartmentToken(id) {
        var result = '';
        var length = 32;
        var chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
        return id + "_" + result;
    }
    
    function _addDepartmentToken(token){
        if(departmentTokens.indexOf(token) >= 0)
            throw "Already logged in";
        departmentTokens.push(token);
    }

    function _deleteDepartmentToken(token){
        var idx = departmentTokens.indexOf(token);
        if(idx == -1)
            throw "User not logged in";
        departmentTokens.slice(idx);
    }

    function _departmentTokenExists(token){
        return (departmentTokens.indexOf(token) >= 0);
    }

    function _addMemberToken(token){
        if(memberTokens.indexOf(token) >= 0)
            throw "Already logged in";
            memberTokens.push(token);
    }

    function _deleteMemberToken(token){
        var idx = memberTokens.indexOf(token);
        if(idx == -1)
            throw "User not logged in";
            memberTokens.slice(idx);
    }

    function _memberTokenExists(token){
        return (memberTokens.indexOf(token) >= 0);
    }

    return{
        CreateMemberToken: _createMemberToken,
        CreateDepartmentToken: _createDepartmentToken,
        AddDepartmentToken: _addDepartmentToken,
        DeleteDepartmentToken: _deleteDepartmentToken,
        DepartmentTokenExists: _departmentTokenExists,
        AddMemberToken: _addMemberToken,
        DeleteMemberToken: _deleteMemberToken,
        MemberTokenExists: _memberTokenExists
    }
})();