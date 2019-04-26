drop database  trainee_programm_db;

CREATE DATABASE trainee_programm_db;

use trainee_programm_db;

CREATE TABLE Skala (
    SkalaId int NOT NULL AUTO_INCREMENT,
    SkalaName enum('Beginner','Intermediate','Advanced','Expert') NOT NULL,
    PRIMARY KEY (SkalaId)
);

CREATE TABLE Location (
    LocationId int NOT NULL AUTO_INCREMENT,
    LocationName varchar(255) NOT NULL,
    PRIMARY KEY ( LocationId)
);

CREATE TABLE Trainee (
	TraineeID int NOT NULL AUTO_INCREMENT,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255) NOT NULL,
    Birthday Date not null,
    Address varchar(255) NOT NULL,
    School varchar(255) NOT NULL,
	Email varchar(255) NOT NULL,
	LocationId int NOT NULL,
    PRIMARY KEY (TraineeID),
	FOREIGN KEY ( LocationId) REFERENCES Location( LocationId),
    check ((Today.year-Birthday.year) >18)
); 


CREATE TABLE Courses (
    CourseID int NOT NULL AUTO_INCREMENT,
    CourseName varchar(255) not null,
    CourseStartDate date NOT NULL,
	CourseEndDate date NOT NULL,
    CourseRoom    enum('EG01','EG02','OG01','OG02') NOT NULL,
	CourseDescription varchar(255) NULL, #optional
    PRIMARY KEY (CourseId),
    check (CourseEndDate>CourseStartDate)
);

CREATE TABLE Enrolled_Trainees (
	TraineeID int NOT NULL ,
    CourseID int NOT NULL,
    SkalaID int NOT NULL,
	FOREIGN KEY (TraineeID) REFERENCES Trainee(TraineeID),
    FOREIGN KEY (CourseID) REFERENCES Courses(CourseID),
     FOREIGN KEY (SkalaID) REFERENCES Skala(SkalaId),
    PRIMARY KEY (TraineeID,CourseId, SkalaId)
    
);
alter table Trainee
ADD CONSTRAINT check_email  CHECK(Email LIKE '%___@___%') 