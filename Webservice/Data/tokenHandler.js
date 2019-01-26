module.exports = (function(){
    var departmentTokens = [];
    var memberTokens = [];
    var adminTokens = [];

    departmentTokens.push("1234");
    memberTokens.push("1234");
    adminTokens.push("1234");

    function _createToken() {
        var result = '';
        var length = 32;
        var chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
        return result;
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
        departmentTokens.splice(idx, 1);
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
        memberTokens.splice(idx, 1);
    }

    function _memberTokenExists(token){
        return (memberTokens.indexOf(token) >= 0);
    }

    function _addAdminToken(token){
        if(adminTokens.indexOf(token) >= 0)
            throw "Already logged in";
        adminTokens.push(token);
    }

    function _deleteAdminToken(token){
        var idx = adminTokens.indexOf(token);
        if(idx == -1)
            throw "User not logged in";
        adminTokens.splice(idx, 1);
    }

    function _adminTokenExists(token){
        return (adminTokens.indexOf(token) >= 0);
    }

    return{
        CreateToken: _createToken,
        AddDepartmentToken: _addDepartmentToken,
        DeleteDepartmentToken: _deleteDepartmentToken,
        DepartmentTokenExists: _departmentTokenExists,
        AddMemberToken: _addMemberToken,
        DeleteMemberToken: _deleteMemberToken,
        MemberTokenExists: _memberTokenExists,
        AddAdminToken: _addAdminToken,
        DeleteAdminToken: _deleteAdminToken,
        AdminTokenExists: _adminTokenExists
    }
})();