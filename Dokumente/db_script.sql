drop table region CASCADE CONSTRAINTS;
drop table regionType CASCADE CONSTRAINTS;
drop table location cascade constraints;
drop table operationtype cascade constraints;
drop table organization cascade constraints;
drop table department cascade constraints;
drop table function cascade constraints;
drop table operator cascade constraints;
drop table controlcenter cascade constraints;
drop table operation cascade constraints;
drop table member cascade constraints;
drop table function_member cascade constraints;
drop table operation_member cascade constraints;
drop table dept_member cascade constraints;
drop table operation_dept cascade constraints;

drop SEQUENCE seq_region;
drop SEQUENCE seq_function;
drop SEQUENCE seq_member;
drop SEQUENCE seq_department;
drop SEQUENCE seq_operation;
drop SEQUENCE seq_controlcenter;
drop SEQUENCE seq_organization;
drop SEQUENCE seq_operationtype;
drop SEQUENCE seq_location;

create table regiontype(
  id integer,
  type varchar2(50),
  
  constraint pk_regiontype primary key(id)
);

create table region(
  id integer,
  name varchar2(50),
  regiontype integer,
  id_parent integer,
  
  constraint pk_region primary key(id),
  constraint fk_region_regiontype foreign key(regiontype) references regiontype(id)
);

create table location(
  id integer,
  housenumber varchar2(10),
  street varchar2(50),
  postalcode integer,
  village varchar2(50),
  id_region integer,
  
  constraint pk_location primary key(id),
  constraint fk_location_region foreign key (id_region) references region(id)
);

create table operationtype(
  id integer,
  name varchar2(50),
  
  constraint pk_operationtype primary key(id)
);

create table organization(
  id integer,
  name varchar2(50),
  
  constraint pk_organization primary key(id)
);

create table department(
  id integer,
  name varchar2(50),
  id_organization integer,
  id_location integer,
  
  constraint pk_department primary key(id),
  constraint fk_department_organization foreign key (id_organization) references organization (id),
  constraint fk_department_location foreign key(id_location) references location (id)
);

create table function(
  id integer,
  name varchar2(50),
  shortcut varchar2(10),
  
  constraint pk_function primary key(id)
);

create table controlcenter(
  id integer,
  name varchar2(50),
  id_location integer,
  
  constraint pk_controlcenter primary key(id),
  constraint fk_controlcenter_location foreign key(id_location) references location (id)
);

create table operator(
  username varchar2(50),
  password varchar2(50),
  id_controlcenter integer,
  
  constraint pk_operator primary key(username),
  constraint fk_operator_controlcenter foreign key(id_controlcenter) references controlcenter(id)
);


create table operation(
  id integer,
  datetime date,
  caller varchar2(100),
  text varchar2(500),
  alarmlevel varchar(50),
  id_controlcenter integer,
  id_location integer,
  id_operationtype integer,
  
  constraint pk_operation primary key(id),
  constraint fk_operation_controlcenter foreign key(id_controlcenter) references controlcenter(id),
  constraint fk_operation_location foreign key(id_location) references location(id),
  constraint fk_operation_operationtype foreign key(id_operationtype) references operationtype(id)
);

create table member(
  svNr integer,
  firstname varchar2(50),
  lastname varchar2(50),
  dateOfBirth date,
  dateOfEntry date,
  phonenumber varchar2(20),
  email varchar(50),
  operator_username varchar2(50),
  
  constraint pk_svNr primary key (svNr),
  constraint fk_member_operator foreign key (operator_username) references operator (username)
);

create table function_member(
  id_function integer,
  svnr_member integer,
  
  constraint pk_function_member primary key(id_function, svnr_member),
  constraint fk_function_member_function foreign key (id_function) references function(id),
  constraint fk_function_member_member foreign key(svnr_member) references member(svnr)
);

create table operation_member(
  id_operation integer,
  svnr_member integer,
  
  constraint pk_operation_member primary key(id_operation, svnr_member),
  constraint fk_operation_member_operation foreign key (id_operation) references operation(id),
  constraint fk_operation_member_member foreign key(svnr_member) references member(svnr)
);

create table dept_member(
  id_department integer,
  svnr_member integer,
  
  constraint pk_dept_member primary key(id_department, svnr_member),
  constraint fk_dept_member_dept foreign key (id_department) references department(id),
  constraint fk_dept_member_member foreign key(svnr_member) references member(svnr)
);

create table operation_dept(
  id_operation integer,
  id_department integer,
  
  constraint pk_operation_dept primary key(id_operation, id_department),
  constraint fk_operation_dept_dept foreign key (id_department) references department(id),
  constraint fk_operation_dept_operation foreign key(id_operation) references operation(id)
);

CREATE SEQUENCE seq_region START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE seq_function START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE seq_member START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE seq_department START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE seq_operation START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE seq_controlcenter START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE seq_organization START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE seq_operationtype START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE seq_location START WITH 0 INCREMENT BY 1 MINVALUE 0;

insert into regiontype values(1, 'Continent');
insert into regiontype values(2, 'State');
insert into regiontype values(3, 'Federalstate');
insert into regiontype values(4, 'District');
insert into regiontype values(5, 'Section');

insert into region values(seq_region.nextval, 'Europa', 1, null);
insert into region values(seq_region.nextval, '÷sterreich', 2, 1);
insert into region values(seq_region.nextval, 'K‰rnten', 3, 2);
insert into region values(seq_region.nextval, 'Feldkirchen', 4, 3);
insert into region values(seq_region.nextval, 'Spittal', 4, 3);
insert into region values(seq_region.nextval, 'Spittal/Lurnfeld', 5, 5);
insert into region values(seq_region.nextval, 'Oberes Gurktal', 5, 4);
insert into region values(seq_region.nextval, 'Feldkirchen/Ossiacher See', 5, 4);
insert into region values(seq_region.nextval, 'Oberk‰rnten', 5, 3);

insert into organization values(seq_organization.nextval, 'Feuerwehr');
insert into organization values(seq_organization.nextval, 'Wasserrettung');

insert into location values(seq_location.nextval, '27', 'B95 Turracherbundesstraﬂe', 9562, 'Himmelberg', 7);
insert into location values(seq_location.nextval, '57', 'Gnesau', 9563, 'Gnesau', 7);
insert into location values(seq_location.nextval, '80', 'Lieserhofer Straﬂe', 9851, 'Lieserbr¸cke', 6);
insert into location values(seq_location.nextval, '100', 'am Wasser', 9872, 'Millstatt', 9);
insert into location values(seq_location.nextval, '20', 'Rosenegger Straﬂe', 9020, 'Klagenfurt', 3);
insert into location values(seq_location.nextval, '40a', 'Bahnhofstraﬂe', 9560, 'Feldkichen in K‰rnten', 4);
insert into location values(seq_location.nextval, null, 'St. Stefan', 9560, 'Feldkichen in K‰rnten', 8);
insert into location values(seq_location.nextval, '34b', 'Kirchgasse', 9560, 'Feldkichen in K‰rnten', 8);


insert into department values(seq_department.nextval, 'FF Himmelberg', 1, 1);
insert into department values(seq_department.nextval, 'FF Gnesau', 1, 2);
insert into department values(seq_department.nextval, 'FF Lieserhofen', 1, 3);
insert into department values(seq_department.nextval, '÷WR Millstatt', 2, 4);

insert into controlcenter values(seq_controlcenter.nextval, 'LAWZ Klagenfurt', 5);
insert into controlcenter values(seq_controlcenter.nextval, 'BAWZ Feldkirchen', 6);

insert into function values(seq_function.nextval, 'Atemschutz', 'ATS');
insert into function values(seq_function.nextval, 'Einsatzleiter', 'EL');
insert into function values(seq_function.nextval, 'Sanit‰ter', 'SAN');
insert into function values(seq_function.nextval, 'Technischer Einsatz', 'TE');
insert into function values(seq_function.nextval, 'Maschninist', 'MA');
insert into function values(seq_function.nextval, 'Taucher', 'TAUCH');

insert into operator values('kraschlc', 'Test123', 1);
insert into operator values('rajick', 'Test123', 2);
insert into operator values('kumnigc', 'Test123', 1);

insert into member values(1234030999, 'Christof', 'Kraschl', '03.09.1999', '01.07.2015', '+435647126482', 'christof@hero.com', 'kraschlc');
insert into member values(1234200300, 'Cora', 'Kumnig', '20.03.2000', '15.08.2016', '+435647345382', 'cora@hero.com', 'kumnigc');
insert into member values(1234141199, 'Kristian', 'Rajic', '14.11.1999', '02.11.2018', '+43523453482', 'kristian@hero.com', 'rajick');
insert into member values(1234120357, 'Hans', 'Zimmer', '12.03.1957', '15.01.1977', '+433467453482', 'hans@hero.com', null);

insert into function_member values(2, 1234030999);
insert into function_member values(5, 1234030999);
insert into function_member values(6, 1234200300);
insert into function_member values(2, 1234120357);
insert into function_member values(1, 1234120357);

insert into dept_member values(1, 1234030999);
insert into dept_member values(1, 1234141199);
insert into dept_member values(3, 1234120357);
insert into dept_member values(2, 1234120357);
insert into dept_member values(4, 1234200300);

insert into operationtype values(seq_operationtype.nextval, 'Brandeinsatz');
insert into operationtype values(seq_operationtype.nextval, 'Techn. Einsatz');
insert into operationtype values(seq_operationtype.nextval, 'Verkehrsunfall');
insert into operationtype values(seq_operationtype.nextval, 'Med. Hilfeleistung');
insert into operationtype values(seq_operationtype.nextval, 'Taucheinsatz');

insert into operation values(seq_operation.nextval, to_date('02.11.2018 21:39', 'DD.MM.YYYY HH24:mi'), 'Einstein Neutron', 'Kaminbrand  beim vulgo Oraclebauer', '1', 2, 8, 1);
insert into operation values(seq_operation.nextval, to_date('01.10.2018 22:51', 'DD.MM.YYYY HH24:mi'), 'Zweistein Proton', 'Person beim Flatschacher See verschwunden', '2', 1, 7, 5);

insert into operation_dept values(1, 1);
insert into operation_dept values(2, 1);
insert into operation_dept values(2, 2);
insert into operation_dept values(2, 3);
insert into operation_dept values(2, 4);

commit;