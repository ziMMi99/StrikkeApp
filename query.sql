USE master;

if DB_ID('Strik_DB') IS NOT NULL
    DROP DATABASE Strik_DB;

CREATE DATABASE Strik_DB;
GO

USE Strik_DB;

CREATE TABLE recipe (
     rec_id INT IDENTITY (1,1) NOT NULL,
     rec_name NVARCHAR(100) NOT NULL,

     PRIMARY KEY (rec_id)
 );

CREATE TABLE project (
    pro_id INT IDENTITY (1,1) NOT NULL,
    pro_name NVARCHAR(100) NOT NULL,

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

INSERT INTO recipe (name) VALUES ('Cozy Cable Knit Scarf');
INSERT INTO recipe (name) VALUES ('Chunky Knit Blanket');
INSERT INTO recipe (name) VALUES ('Fair Isle Mittens');
INSERT INTO recipe (name) VALUES ('Lacy Knit Shawl');
INSERT INTO recipe (name) VALUES ('Beanie with Pom-Pom');

INSERT INTO project (name) VALUES ('Knitted Sweater Project');
INSERT INTO project (name) VALUES ('Baby Blanket Gift');
INSERT INTO project (name) VALUES ('Knit Christmas Stockings');
INSERT INTO project (name) VALUES ('Knitting Circle Meetup');
INSERT INTO project (name) VALUES ('Knit Hat Donation Drive');