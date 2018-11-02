select * from region;
select * from regiontype;

select * from region inner join regiontype
on region.REGIONTYPE = regiontype.id;

select * from region inner join regiontype on region.regiontype = regiontype.id
where region.id in(SELECT id FROM region
      START WITH region.id = 1
      CONNECT BY PRIOR  region.id = region.id_parent);