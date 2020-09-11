CREATE DATABASE [WORD]
GO

USE [WORD]
GO

CREATE TABLE WORD(
ID Int PRIMARY KEY,
ACRONYM Nvarchar(10),
WORDFULL Nvarchar(50))
GO

INSERT INTO WORD VALUES (1, N'KMA', N'HOC VIEN KY THUAT MAT MA'), (2, N'HCM', N'HO CHI MINH')
                        (3, N'MTA', N'HOC VIEN KY THUAT QUAN SU'), (4, N'WWW', N'WORLD WIDE WEB')