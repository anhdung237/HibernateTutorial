alter table EMPLOYEE drop constraint FK65bgags9wjppbn5x7vjcqhext;
alter table EMPLOYEE drop constraint FKkcawqtfitoe3w528metq1o03c;
alter table EMPLOYEE drop constraint FK2a6tjfuq36r7idhfm7y9gscqu;
alter table TIMEKEEPER drop constraint FKifwkxix749p4scwkaeybcj4uc;
drop table DEPARTMENT;
drop table EMPLOYEE;
drop table SALARY_GRADE;
drop table TIMEKEEPER;
create table DEPARTMENT (DEPT_ID int not null, DEPT_NAME varchar(255) not null, DEPT_NO varchar(20) not null, LOCATION varchar(255), primary key (DEPT_ID));
create table EMPLOYEE (EMP_ID bigint not null, EMP_NAME varchar(50) not null, EMP_NO varchar(20) not null, HIRE_DATE date not null, IMAGE varbinary(MAX), JOB varchar(30) not null, SALARY float not null, DEPT_ID int not null, MNG_ID bigint, primary key (EMP_ID));
create table SALARY_GRADE (GRADE int not null, HIGH_SALARY float not null, LOW_SALARY float not null, primary key (GRADE));
create table TIMEKEEPER (Timekeeper_id varchar(36) not null, Date_Time datetime2 not null, In_Out char(1) not null, EMP_ID bigint not null, primary key (Timekeeper_id));
alter table DEPARTMENT add constraint UK504cmb4vdtk4qhlyo0gunu2ew unique (DEPT_NO);
alter table EMPLOYEE add constraint UK7fqco7dry69w4ba8sh8qn21b unique (EMP_NO);
alter table EMPLOYEE add constraint FK65bgags9wjppbn5x7vjcqhext foreign key (DEPT_ID) references DEPARTMENT;
alter table EMPLOYEE add constraint FKkcawqtfitoe3w528metq1o03c foreign key (MNG_ID) references EMPLOYEE;
alter table EMPLOYEE add constraint FK2a6tjfuq36r7idhfm7y9gscqu foreign key (EMP_ID) references EMPLOYEE;
alter table TIMEKEEPER add constraint FKifwkxix749p4scwkaeybcj4uc foreign key (EMP_ID) references EMPLOYEE;
alter table EMPLOYEE drop constraint FK65bgags9wjppbn5x7vjcqhext;
alter table EMPLOYEE drop constraint FKkcawqtfitoe3w528metq1o03c;
alter table EMPLOYEE drop constraint FK2a6tjfuq36r7idhfm7y9gscqu;
alter table TIMEKEEPER drop constraint FKifwkxix749p4scwkaeybcj4uc;
drop table DEPARTMENT;
drop table EMPLOYEE;
drop table SALARY_GRADE;
drop table TIMEKEEPER;
create table DEPARTMENT (DEPT_ID int not null, DEPT_NAME varchar(255) not null, DEPT_NO varchar(20) not null, LOCATION varchar(255), primary key (DEPT_ID));
create table EMPLOYEE (EMP_ID bigint not null, EMP_NAME varchar(50) not null, EMP_NO varchar(20) not null, HIRE_DATE date not null, IMAGE varbinary(MAX), JOB varchar(30) not null, SALARY float not null, DEPT_ID int not null, MNG_ID bigint, primary key (EMP_ID));
create table SALARY_GRADE (GRADE int not null, HIGH_SALARY float not null, LOW_SALARY float not null, primary key (GRADE));
create table TIMEKEEPER (Timekeeper_id varchar(36) not null, Date_Time datetime2 not null, In_Out char(1) not null, EMP_ID bigint not null, primary key (Timekeeper_id));
alter table DEPARTMENT add constraint UK504cmb4vdtk4qhlyo0gunu2ew unique (DEPT_NO);
alter table EMPLOYEE add constraint UK7fqco7dry69w4ba8sh8qn21b unique (EMP_NO);
alter table EMPLOYEE add constraint FK65bgags9wjppbn5x7vjcqhext foreign key (DEPT_ID) references DEPARTMENT;
alter table EMPLOYEE add constraint FKkcawqtfitoe3w528metq1o03c foreign key (MNG_ID) references EMPLOYEE;
alter table EMPLOYEE add constraint FK2a6tjfuq36r7idhfm7y9gscqu foreign key (EMP_ID) references EMPLOYEE;
alter table TIMEKEEPER add constraint FKifwkxix749p4scwkaeybcj4uc foreign key (EMP_ID) references EMPLOYEE;
alter table EMPLOYEE drop constraint FK65bgags9wjppbn5x7vjcqhext;
alter table EMPLOYEE drop constraint FKkcawqtfitoe3w528metq1o03c;
alter table EMPLOYEE drop constraint FK2a6tjfuq36r7idhfm7y9gscqu;
alter table TIMEKEEPER drop constraint FKifwkxix749p4scwkaeybcj4uc;
drop table DEPARTMENT;
drop table EMPLOYEE;
drop table SALARY_GRADE;
drop table TIMEKEEPER;
create table DEPARTMENT (DEPT_ID int not null, DEPT_NAME varchar(255) not null, DEPT_NO varchar(20) not null, LOCATION varchar(255), primary key (DEPT_ID));
create table EMPLOYEE (EMP_ID bigint not null, EMP_NAME varchar(50) not null, EMP_NO varchar(20) not null, HIRE_DATE date not null, IMAGE varbinary(MAX), JOB varchar(30) not null, SALARY float not null, DEPT_ID int not null, MNG_ID bigint, primary key (EMP_ID));
create table SALARY_GRADE (GRADE int not null, HIGH_SALARY float not null, LOW_SALARY float not null, primary key (GRADE));
create table TIMEKEEPER (Timekeeper_id varchar(36) not null, Date_Time datetime2 not null, In_Out char(1) not null, EMP_ID bigint not null, primary key (Timekeeper_id));
alter table DEPARTMENT add constraint UK504cmb4vdtk4qhlyo0gunu2ew unique (DEPT_NO);
alter table EMPLOYEE add constraint UK7fqco7dry69w4ba8sh8qn21b unique (EMP_NO);
alter table EMPLOYEE add constraint FK65bgags9wjppbn5x7vjcqhext foreign key (DEPT_ID) references DEPARTMENT;
alter table EMPLOYEE add constraint FKkcawqtfitoe3w528metq1o03c foreign key (MNG_ID) references EMPLOYEE;
alter table EMPLOYEE add constraint FK2a6tjfuq36r7idhfm7y9gscqu foreign key (EMP_ID) references EMPLOYEE;
alter table TIMEKEEPER add constraint FKifwkxix749p4scwkaeybcj4uc foreign key (EMP_ID) references EMPLOYEE;
