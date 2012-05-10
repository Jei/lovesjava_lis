create database CarPooling;

use CarPooling;

create table User (
  USER_ID int not null AUTO_INCREMENT,
  USER_NAME char(50) not null,
  USER_SNAME char(50) not null,
  USER_EMAIL char(255) not null,
  USER_PASS char(32) not null,
  USER_ADM int not null,
  USER_GENDER char(1),
  USER_BIRTH date,
  USER_CF char(16),
  USER_BLOCKED int not null,
  primary key(USER_ID)
); 

insert into User (USER_NAME, USER_SNAME, USER_EMAIL, USER_PASS, USER_ADM, USER_BLOCKED)
  values ("Horus", "Dio Uccello", "horus@giza.com", "5e7699f3c01b2c699017bc25fec8d44f", 1, 0);