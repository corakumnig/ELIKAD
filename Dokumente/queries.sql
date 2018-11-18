select eli_region.id as id, eli_region.name as name, eli_regiontype.id, type, level from eli_region inner join eli_regiontype on eli_region.regiontype = eli_regiontype.id
where eli_region.id in(
  SELECT id FROM eli_region
  START WITH eli_region.id = 1
  CONNECT BY PRIOR  eli_region.id = eli_region.id_parent
);
      
--Anzahl der Einsätze pro department
select eli_department.id, eli_department.name, count(*) as Anzahl_Einsätze from eli_department 
inner join ELI_OPERATION_DEPT on
ELI_OPERATION_DEPT.ID_DEPARTMENT = eli_department.ID
inner join eli_operation on
eli_operation.id = ELI_OPERATION_DEPT.ID_OPERATION
where eli_department.id = 1
group by eli_department.id, eli_department.name;

--Anzahl der Einsatzstäkre pro department
select eli_department.id, eli_department.name, count(*) as Einsatzstärke from ELI_OPERATION 
inner join ELI_OPERATION_MEMBER on
ELI_OPERATION_MEMBER.ID_OPERATION = ELI_OPERATION.ID
inner join eli_member on
eli_member.svnr = ELI_OPERATION_MEMBER.SVNR_MEMBER
inner join eli_department
on eli_department.id = eli_member.id_department
where eli_department.id = 1
group by eli_member.SVNR, eli_department.id, eli_department.name;

--Anzahl der Mitglieder pro department
select eli_department.id, eli_department.name, count(*) as Anzahl from eli_department
inner join eli_member
on eli_department.ID = eli_member.id_department
where eli_department.id = 1
group by eli_department.id, eli_department.name;