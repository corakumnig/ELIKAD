class Member {
    constructor(svNr, firstname, lastname, dateOfBirth, dateOfEntry, phonenumber, email, gender, idDepartment) {
        this.svNr = svNr;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEntry = dateOfEntry;
        this.phonenumber = phonenumber;
        this.email = email;
        this.gender = gender;
        this.idDepartment = idDepartment;
    }
}


module.exports = {
    Member: Member
};