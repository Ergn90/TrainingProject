
INSERT INTO skala (SkalaName)
values ('Beginner'),('Intermediate'),('Advanced'),('Expert');
##############################
#insert scripts for location
##############################
insert into Location (LocationName)
values ('City'),('Stadt'),('Ville'),('Ciudad'),('Città'),('Cidade'),('Extern');
##############################
#insert scripts for courses
##############################

insert into Courses (CourseName,CourseStartDate,CourseEndDate,CourseRoom,CourseDescription)
values ('Java Trainee','2018-04-01','2018-10-01','EG01',''),
('Java Trainee','2018-10-01','2019-04-01','EG02',''),('Java Trainee','2019-04-01','2019-10-01','EG01', 'current course'),
('Java Trainee','2019-10-01','2020-04-01','EG02','next course'),('Java Trainee','2020-04-01','2020-10-01','EG01',"next course");
##############################
#insert scripts for trainee
##############################
insert into Trainee (LastName, FirstName, Birthday, Address, School, Email, LocationId)
values
('LastName1','FirstName1','1990-01-01', 'address1', 'Info','firstName1.lLastName1@mail.com',1),
('LastName2','FirstName2','1991-02-02', 'address2', 'Info','firstName2.lLastName2@mail.com',2),
('LastName3','FirstName3','1992-03-10', 'address3', 'Info','firstName3.lLastName3@mail.com',3),
('LastName4','FirstName4', '1993-04-10', 'address4','Info','firstName4.lLastName4@mail.com',4),
('LastName5','FirstName5', '1993-04-10', 'address5','Info','firstName5.lLastName5@mail.com',5),
('LastName6','FirstName6', '1994-05-05', 'address5','Info','firstName6.lLastName6@mail.com',6),
('LastName7','FirstName7', '1995-07-07', 'address6','Info','firstName7.lLastName7@mail.com',7),
('LastName8','FirstName8', '1996-08-08', 'address7','Info','firstName8.lLastName8.@mail.com',2),
('LastName9','FirstName9', '1997-09-09', 'address8','Info','firstName9.lLastName9.@mail.com',3);

##############################
#insert scripts for enrolled trainees
##############################

insert into enrolled_trainees (traineeID, CourseID, SkalaId)
values (1, 1,1);

insert into enrolled_trainees (traineeID, CourseID, SkalaId)
values (1, 2,1);

insert into enrolled_trainees (traineeID, CourseID, SkalaId)
values (2, 2,3);

insert into enrolled_trainees (traineeID, CourseID, SkalaId)
values (2, 3,3);

insert into enrolled_trainees (traineeID, CourseID,SkalaId)
values (2, 1,3);

insert into enrolled_trainees (traineeID, CourseID, SkalaId)
values (3, 1,3);

insert into enrolled_trainees (traineeID, CourseID, SkalaId)
values (3, 4,3);

insert into enrolled_trainees (traineeID, CourseID, SkalaId)
values (4, 4,3);

insert into enrolled_trainees (traineeID, CourseID, SkalaId)
values (4, 2,3);

