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
  primary key(USER_ID)
); 
