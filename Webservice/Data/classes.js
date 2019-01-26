class Member {
    constructor(id, svNr, firstname, lastname, dateOfBirth, dateOfEntry, phonenumber, email, gender, idDepartment) {
        this.id = id;
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
    constructor(id, name, organization, region, regiontype, idLocation, housenumber, street, postalcode, village) {
        this.id = id;
        this.name = name;
        this.organization = organization;
        this.region = region;
        this.regiontype = regiontype;
        this.location = new Location(idLocation, housenumber, street, postalcode, village);
    }
}

class Location {
    constructor(id, housenumber, street, postalcode, village) {
        this.id = id;
        this.housenumber = housenumber;
        this.street = street;
        this.postalcode = postalcode;
        this.village = village;
    }
}

class Function {
    constructor (id, name, shortcut){
        this.id = id;
        this.name = name;
        this.shortcut = shortcut;
    }
}

class Operation {
    constructor (id, text, alarmlevel, datetime, caller, operationTpye, controlcenterName){
        this.id = id;
        this.text = text;
        this.alarmlevel = alarmlevel;
        this.datetime = datetime;
        this.caller = caller;
        this.operationTpye = operationTpye;
        this.controlcenterName = controlcenterName;
    }
}


module.exports = {
    Member: Member,
    Department: Department,
    Location: Location,
    Function: Function,
    Operation: Operation
};