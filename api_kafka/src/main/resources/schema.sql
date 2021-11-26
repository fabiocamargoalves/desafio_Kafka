-- -----------------------------------------------------
-- table  
-- -----------------------------------------------------
create table if not exists orders (
  id int not null auto_increment,
  name varchar(150) not null,
  status varchar(50)not null,
  total double not null,
  primary key (id))
;