USE master;

if DB_ID('Strik_DB') IS NOT NULL
    DROP DATABASE Strik_DB;

CREATE DATABASE Strik_DB;
GO

USE Strik_DB;

CREATE TABLE recipe (
     rec_id INT IDENTITY (1,1) NOT NULL,
     rec_name NVARCHAR(100) NOT NULL,
     rec_desc NVARCHAR(MAX) NOT NULL,
     rec_note NVARCHAR(MAX),

     PRIMARY KEY (rec_id)
 );

CREATE TABLE project (
    pro_id INT IDENTITY (1,1) NOT NULL,
    pro_name NVARCHAR(100) NOT NULL,
    pro_desc NVARCHAR(MAX),
    pro_note NVARCHAR(MAX),

    PRIMARY KEY (pro_id)
);

-- CREATE PROCEDURE statements
IF OBJECT_ID('recipe_getRecipe', 'P') IS NOT NULL
    DROP PROCEDURE recipe_getRecipe;
GO

CREATE PROCEDURE recipe_getRecipe @name NVARCHAR(100)
AS
SELECT * FROM recipe WHERE rec_name = @name;
GO

IF OBJECT_ID('project_getProject', 'P') IS NOT NULL
    DROP PROCEDURE project_getProject;
GO

CREATE PROCEDURE project_getProject @name NVARCHAR(100)
AS
SELECT * FROM project WHERE pro_name = @name;
GO

IF OBJECT_ID('project_addProject', 'P') IS NOT NULL
    DROP PROCEDURE project_addProject;
GO

CREATE PROCEDURE project_addProject
    @name NVARCHAR(100),
    @desc NVARCHAR(MAX),
    @note NVARCHAR(MAX)
AS
BEGIN
    INSERT INTO project (pro_name, pro_desc, pro_note)
    VALUES (@name, @desc, @note)
END
GO
