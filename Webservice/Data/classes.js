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

class Department {
    constructor(id, name, location, organization) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.location = location;
        this.organization = organization;
    }
}

class Location {
    constructor(id, name, location, organization) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.location = location;
        this.organization = organization;
    }
}


module.exports = {
    Member: Member,
    Department: Department
};