drop table eli_region CASCADE CONSTRAINTS;
drop table eli_regionType CASCADE CONSTRAINTS;
drop table eli_location cascade constraints;
drop table eli_operationtype cascade constraints;
drop table eli_organization cascade constraints;
drop table eli_department cascade constraints;
drop table eli_function cascade constraints;
drop table eli_operator cascade constraints;
drop table eli_controlcenter cascade constraints;
drop table eli_operation cascade constraints;
drop table eli_member cascade constraints;
drop table eli_function_member cascade constraints;
drop table eli_operation_member cascade constraints;
drop table eli_admin cascade constraints;
drop table eli_operation_dept cascade constraints;

drop SEQUENCE eli_seq_region;
drop SEQUENCE eli_seq_function;
drop SEQUENCE eli_seq_member;
drop SEQUENCE eli_seq_department;
drop SEQUENCE eli_seq_operation;
drop SEQUENCE eli_seq_controlcenter;
drop SEQUENCE eli_seq_organization;
drop SEQUENCE eli_seq_operationtype;
drop SEQUENCE eli_seq_location;

create table eli_regiontype(
  id integer,
  type varchar2(50),
  
  constraint pk_eli_regiontype primary key(id)
);

create table eli_region(
  id integer,
  name varchar2(50),
  regiontype integer,
  id_parent integer,
  
  constraint pk_eli_region primary key(id),
  constraint fk_eli_region_regiontype foreign key(regiontype) references eli_regiontype(id)
);

create table eli_location(
  id integer,
  housenumber varchar2(10),
  street varchar2(50),
  postalcode integer,
  village varchar2(50),
  id_region integer,
  
  constraint pk_eli_location primary key(id),
  constraint fk_eli_location_region foreign key (id_region) references eli_region(id)
);

create table eli_operationtype(
  id integer,
  name varchar2(50),
  
  constraint pk_eli_operationtype primary key(id)
);

create table eli_organization(
  id integer,
  name varchar2(50),
  
  constraint pk_eli_organization primary key(id)
);

create table eli_department(
  id integer,
  name varchar2(50) not null,
  password varchar(100),
  image blob,
  id_organization integer,
  id_location integer,
  
  constraint pk_eli_department primary key(id),
  constraint uq_eli_department unique (name),
  constraint fk_eli_department_organization foreign key (id_organization) references eli_organization (id),
  constraint fk_eli_department_location foreign key(id_location) references eli_location (id)
);

create table eli_function(
  id integer,
  name varchar2(50),
  shortcut varchar2(10),
  
  constraint pk_eli_function primary key(id)
);

create table eli_controlcenter(
  id integer,
  name varchar2(50),
  id_location integer,
  
  constraint pk_eli_controlcenter primary key(id),
  constraint fk_eli_controlcenter_location foreign key(id_location) references eli_location (id)
);

create table eli_operator(
  username varchar2(50),
  password varchar2(50),
  id_controlcenter integer,
  
  constraint pk_eli_operator primary key(username),
  constraint fk_eli_operator_controlcenter foreign key(id_controlcenter) references eli_controlcenter(id)
);

create table eli_admin(
  username varchar2(50),
  password varchar2(50),
  
  constraint pk_eli_admin primary key(username)
);


create table eli_operation(
  id integer,
  datetime date,
  caller varchar2(100),
  description varchar2(500),
  alarmlevel varchar(50),
  id_controlcenter integer,
  id_location integer,
  id_operationtype integer,
  
  constraint pk_eli_operation primary key(id),
  constraint fk_eli_operation_controlcenter foreign key(id_controlcenter) references eli_controlcenter(id),
  constraint fk_eli_operation_location foreign key(id_location) references eli_location(id),
  constraint fk_eli_operation_operationtype foreign key(id_operationtype) references eli_operationtype(id)
);

create table eli_member(
  id integer,
  svNr varchar2(10),
  firstname varchar2(50),
  lastname varchar2(50),
  dateOfBirth date,
  dateOfEntry date,
  phonenumber varchar2(20),
  email varchar(50),
  operator_username varchar2(50),
  admin_username varchar2(50),
  id_department int,
  gender varchar2(50),
  
  constraint pk_eli_id primary key (id),
  constraint uq_eli_svNr unique (svNr),
  constraint fk_eli_member_operator foreign key (operator_username) references eli_operator (username),
  constraint fk_eli_member_admin foreign key (admin_username) references eli_admin (username),
  constraint fk_eli_department foreign key (id_department) references eli_department(id)
);

create table eli_function_member(
  id_function integer,
  id_member integer,
  
  constraint pk_eli_func_member primary key(id_function, id_member),
  constraint fk_eli_func_member_function foreign key (id_function) references eli_function(id),
  constraint fk_eli_func_member_member foreign key(id_member) references eli_member(id)
);

create table eli_operation_member(
  id_operation integer,
  id_member integer,
  
  constraint pk_eli_op_member primary key(id_operation, id_member),
  constraint fk_eli_op_member_operation foreign key (id_operation) references eli_operation(id),
  constraint fk_eli_op_member_member foreign key(id_member) references eli_member(id)
);

create table eli_operation_dept(
  id_operation integer,
  id_department integer,
  
  constraint pk_eli_op_dept primary key(id_operation, id_department),
  constraint fk_eli_op_dept_dept foreign key (id_department) references eli_department(id),
  constraint fk_eli_op_dept_operation foreign key(id_operation) references eli_operation(id)
);

CREATE SEQUENCE eli_seq_region START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE eli_seq_function START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE eli_seq_member START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE eli_seq_department START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE eli_seq_operation START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE eli_seq_controlcenter START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE eli_seq_organization START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE eli_seq_operationtype START WITH 0 INCREMENT BY 1 MINVALUE 0;
CREATE SEQUENCE eli_seq_location START WITH 0 INCREMENT BY 1 MINVALUE 0;

insert into eli_regiontype values(1, 'Continent');
insert into eli_regiontype values(2, 'State');
insert into eli_regiontype values(3, 'Federalstate');
insert into eli_regiontype values(4, 'District');
insert into eli_regiontype values(5, 'Section');

insert into eli_region values(eli_seq_region.nextval, 'Europa', 1, null);
insert into eli_region values(eli_seq_region.nextval, '÷sterreich', 2, 1);
insert into eli_region values(eli_seq_region.nextval, 'K‰rnten', 3, 2);
insert into eli_region values(eli_seq_region.nextval, 'Feldkirchen', 4, 3);
insert into eli_region values(eli_seq_region.nextval, 'Spittal', 4, 3);
insert into eli_region values(eli_seq_region.nextval, 'Spittal/Lurnfeld', 5, 5);
insert into eli_region values(eli_seq_region.nextval, 'Oberes Gurktal', 5, 4);
insert into eli_region values(eli_seq_region.nextval, 'Feldkirchen/Ossiacher See', 5, 4);
insert into eli_region values(eli_seq_region.nextval, 'Oberk‰rnten', 5, 3);

insert into eli_organization values(eli_seq_organization.nextval, 'Feuerwehr');
insert into eli_organization values(eli_seq_organization.nextval, 'Wasserrettung');

insert into eli_location values(eli_seq_location.nextval, '27', 'B95 Turracherbundesstraﬂe', 9562, 'Himmelberg', 7);
insert into eli_location values(eli_seq_location.nextval, '57', 'Gnesau', 9563, 'Gnesau', 7);
insert into eli_location values(eli_seq_location.nextval, '80', 'Lieserhofer Straﬂe', 9851, 'Lieserbr¸cke', 6);
insert into eli_location values(eli_seq_location.nextval, '100', 'am Wasser', 9872, 'Millstatt', 9);
insert into eli_location values(eli_seq_location.nextval, '20', 'Rosenegger Straﬂe', 9020, 'Klagenfurt', 3);
insert into eli_location values(eli_seq_location.nextval, '40a', 'Bahnhofstraﬂe', 9560, 'Feldkichen in K‰rnten', 4);
insert into eli_location values(eli_seq_location.nextval, null, 'St. Stefan', 9560, 'Feldkichen in K‰rnten', 8);
insert into eli_location values(eli_seq_location.nextval, '34b', 'Kirchgasse', 9560, 'Feldkichen in K‰rnten', 8);


insert into eli_department values(eli_seq_department.nextval, 'FF Himmelberg', 'TestHimmelber', null, 1, 1);
insert into eli_department values(eli_seq_department.nextval, 'FF Gnesau', 'TestGnesau', null, 1, 2);
insert into eli_department values(eli_seq_department.nextval, 'FF Lieserhofen', 'TestLieserhofen', null, 1, 3);
insert into eli_department values(eli_seq_department.nextval, '÷WR Millstatt', 'TestMillstatt', null, 2, 4);

insert into eli_controlcenter values(eli_seq_controlcenter.nextval, 'LAWZ Klagenfurt', 5);
insert into eli_controlcenter values(eli_seq_controlcenter.nextval, 'BAWZ Feldkirchen', 6);

insert into eli_function values(eli_seq_function.nextval, 'Atemschutz', 'ATS');
insert into eli_function values(eli_seq_function.nextval, 'Einsatzleiter', 'EL');
insert into eli_function values(eli_seq_function.nextval, 'Sanit‰ter', 'SAN');
insert into eli_function values(eli_seq_function.nextval, 'Technischer Einsatz', 'TE');
insert into eli_function values(eli_seq_function.nextval, 'Maschninist', 'MA');
insert into eli_function values(eli_seq_function.nextval, 'Taucher', 'TAUCH');

insert into eli_operator values('kraschlc', 'Test123', 1);
insert into eli_operator values('rajick', 'Test123', 2);
insert into eli_operator values('kumnigc', 'Test123', 1);

insert into eli_admin values('kraschlc', 'Test123');
insert into eli_admin values('rajick', 'Test123');
insert into eli_admin values('kumnigc', 'Test123');

insert into eli_member values(eli_seq_member.nextval, '1234030999', 'Christof', 'Kraschl', '03.09.1999', '01.07.2015', '+435647126482', 'christof@hero.com', 'kraschlc', 'kraschlc',1, 'Male');
insert into eli_member values(eli_seq_member.nextval, '1234200300', 'Cora', 'Kumnig', '20.03.2000', '15.08.2016', '+435647345382', 'cora@hero.com', 'kumnigc', 'kumnigc',4, 'Female');
insert into eli_member values(eli_seq_member.nextval, '1234141199', 'Kristian', 'Rajic', '14.11.1999', '02.11.2018', '+43523453482', 'kristian@hero.com', 'rajick', 'rajick', 1, 'Male');
insert into eli_member values(eli_seq_member.nextval, '1234120357', 'Hans', 'Zimmer', '12.03.1957', '15.01.1977', '+433467453482', 'hans@hero.com', null, null,3, 'Male');

insert into eli_function_member values(2, 1);
insert into eli_function_member values(5, 1);
insert into eli_function_member values(6, 2);
insert into eli_function_member values(2, 4);
insert into eli_function_member values(1, 4);

insert into eli_operationtype values(eli_seq_operationtype.nextval, 'Brandeinsatz');
insert into eli_operationtype values(eli_seq_operationtype.nextval, 'Techn. Einsatz');
insert into eli_operationtype values(eli_seq_operationtype.nextval, 'Verkehrsunfall');
insert into eli_operationtype values(eli_seq_operationtype.nextval, 'Med. Hilfeleistung');
insert into eli_operationtype values(eli_seq_operationtype.nextval, 'Taucheinsatz');

insert into eli_operation values(eli_seq_operation.nextval, to_date('02.11.2018 21:39', 'DD.MM.YYYY HH24:mi'), 'Einstein Neutron', 'Kaminbrand  beim vulgo Oraclebauer', '1', 2, 8, 1);
insert into eli_operation values(eli_seq_operation.nextval, to_date('01.10.2018 22:51', 'DD.MM.YYYY HH24:mi'), 'Zweistein Proton', 'Person beim Flatschacher See verschwunden', '2', 1, 7, 5);

insert into eli_operation_dept values(1, 1);
insert into eli_operation_dept values(2, 1);
insert into eli_operation_dept values(2, 2);
insert into eli_operation_dept values(2, 3);
insert into eli_operation_dept values(2, 4);

insert into eli_operation_member values(1, 1);

commit;