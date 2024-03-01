USE master;

if DB_ID('Strik_DB') IS NOT NULL
    DROP DATABASE Strik_DB;

CREATE DATABASE Strik_DB;
GO

USE Strik_DB;

CREATE TABLE Users (
   userID int IDENTITY(1,1) NOT NULL,
   username NVARCHAR(50) NOT NULL,
   hashedPassword NVARCHAR(128) NOT NULL,
   email NVARCHAR(50) NOT NULL,
   firstname NVARCHAR(50) NOT NULL,
   lastname NVARCHAR(50) NOT NULL,

   PRIMARY KEY (userID)
);

CREATE TABLE recipe (
    rec_id INT IDENTITY (1,1) NOT NULL,
    userID int NOT NULL FOREIGN KEY REFERENCES Users(userID) ON DELETE NO ACTION,
    rec_name NVARCHAR(100) NOT NULL,
    rec_desc NVARCHAR(MAX) NOT NULL,
    rec_note NVARCHAR(MAX),

    PRIMARY KEY (rec_id)
);

CREATE TABLE project (
     pro_id INT IDENTITY (1,1) NOT NULL,
     userID int NOT NULL FOREIGN KEY REFERENCES Users(userID) ON DELETE NO ACTION,
     pro_name NVARCHAR(100) NOT NULL,
     pro_desc NVARCHAR(MAX),
     pro_note NVARCHAR(MAX),

     PRIMARY KEY (pro_id)
);

--Get recipe
IF OBJECT_ID('recipe_getRecipe', 'P') IS NOT NULL
    DROP PROCEDURE recipe_getRecipe;
GO

CREATE PROCEDURE recipe_getRecipe @name NVARCHAR(100)
AS
SELECT * FROM recipe WHERE rec_name = @name;
GO

--Get project by name
IF OBJECT_ID('project_getProjectByName', 'P') IS NOT NULL
    DROP PROCEDURE project_getProjectByName;
GO

CREATE PROCEDURE project_getProjectByName @name NVARCHAR(100)
AS
SELECT * FROM project WHERE pro_name = @name;
GO

--Get projects by UserID
IF OBJECT_ID('project_getProjectsByUserID', 'P') IS NOT NULL
    DROP PROCEDURE project_getProjectsByUserID;
GO

CREATE PROCEDURE project_getProjectsByUserID @userID NVARCHAR(100)
AS
SELECT * FROM project WHERE userID = @userID;
GO

--Get all usernames
IF OBJECT_ID('users_getAllUsernames') IS NOT NULL
    DROP PROCEDURE users_getAllUsernames;
GO

CREATE PROCEDURE users_getAllUsernames
AS
SELECT username FROM Users;
GO

--Get hashedPassword
IF OBJECT_ID('users_getHashedPassword', 'P') IS NOT NULL
    DROP PROCEDURE users_getHashedPassword;
GO

CREATE PROCEDURE users_getHashedPassword @name NVARCHAR(100)
AS
SELECT hashedPassword FROM Users WHERE username = @name;
GO

--Get hashedPassword
IF OBJECT_ID('users_getUserIDByName', 'P') IS NOT NULL
    DROP PROCEDURE users_getUserIDByName;
GO

CREATE PROCEDURE users_getUserIDByName @name NVARCHAR(100)
AS
SELECT userID FROM Users WHERE username = @name;
GO

--Add project
IF OBJECT_ID('project_addProject', 'P') IS NOT NULL
    DROP PROCEDURE project_addProject;
GO

CREATE PROCEDURE project_addProject
    @name NVARCHAR(100),
    @userID NVARCHAR(100),
    @desc NVARCHAR(MAX),
    @note NVARCHAR(MAX)
AS
BEGIN
    INSERT INTO project (pro_name, userID, pro_desc, pro_note)
    VALUES (@name, @userID, @desc, @note)
END
GO

--Add User
IF OBJECT_ID('users_addUser', 'P') IS NOT NULL
    DROP PROCEDURE users_addUser;
GO

CREATE PROCEDURE users_addUser
    @name NVARCHAR(100),
    @password NVARCHAR(100),
    @email NVARCHAR(100),
    @firstname NVARCHAR(100),
    @lastname NVARCHAR(100)

AS
BEGIN
    INSERT INTO Users (username, hashedPassword, email, firstname, lastname)
    VALUES (@name, @password, @email, @firstname, @lastname)
END
GO
