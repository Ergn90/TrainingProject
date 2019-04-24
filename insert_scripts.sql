
INSERT INTO skala (SkalaName)
values ('Beginner');
INSERT INTO skala (SkalaName)
values ('Intermediate');
INSERT INTO skala (SkalaName)
values ('Advanced');
INSERT INTO skala (SkalaName)
values ('Expert');



##############################
#insert scripts for location
##############################
insert into Location (LocationName)
values ('Basel');

insert into Location (LocationName)
values ('Bern');

insert into Location (LocationName)
values ('Brugg');

insert into Location (LocationName)
values ('Bukarest');

insert into Location (LocationName)
values ('Duesseldorf');

insert into Location (LocationName)
values ('Frankfurt A. M.');

insert into Location (LocationName)
values ('Freiburg');

insert into Location (LocationName)
values ('Genf');

insert into Location (LocationName)
values ('Hamburg');

insert into Location (LocationName)
values ('Lausanne');

insert into Location (LocationName)
values ('Freiburg');

insert into Location (LocationName)
values ('Mannheim');

insert into Location (LocationName)
values ('Muenchen');

insert into Location (LocationName)
values ('Stuttgart');

insert into Location (LocationName)
values ('Wien');

insert into Location (LocationName)
values ('Zuerich');

insert into Location (LocationName)
values ('Extern');


##############################
#insert scripts for courses
##############################

;


insert into Courses (coursename,courseyear,CourseRoom)
values ('1','2018','EG01');

insert into Courses (coursename,courseyear,CourseRoom)
values ('2','2018','EG01');

insert into Courses (coursename,courseyear,CourseRoom)
values ('1','2019','EG01');

insert into Courses (coursename,courseyear,CourseRoom)
values ('2','2019','EG02');

insert into Courses (coursename,courseyear,CourseRoom)
values ('1','2020','EG01');

insert into Courses (coursename,courseyear,CourseRoom)
values ('2','2020','EG02');

##############################
#insert scripts for trainee
##############################

insert into Trainee (lastname, firstname,birthday, address, school, email, LocationId)
values ('Serban','Andrada','1995-06-03', 'Adr1', 'Bachelor Engineering', 'ersteemail@oio.com', 1);

insert into Trainee (lastname, firstname,birthday, address, school, email, LocationId)
values ('Tolstorukova','Dina','1980-02-10', 'Adr2', 'Mate', 'zweiteemail@oio.com', 2);

insert into Trainee (lastname, firstname,birthday, address, school, email, LocationId)
values ('Guillaume','Michel','1990-03-27', 'Adr3', 'Info', 'dritteemail@oio.com', 3);

insert into Trainee (lastname, firstname,birthday, address, school, email, LocationId)
values ('Rico','R', '1996-12-14', 'Adr4','Info','vierteemail@oio.com', 17);




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

