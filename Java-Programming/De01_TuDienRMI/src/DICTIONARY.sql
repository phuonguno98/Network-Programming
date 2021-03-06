CREATE DATABASE [DICTIONARY]
GO

USE [DICTIONARY]
GO

CREATE TABLE DICTIONARY(
ID int PRIMARY KEY,
VIE nvarchar(50),
ENG varchar(50),
MEANING ntext)

INSERT INTO DICTIONARY(ID, VIE, ENG, MEANING) VALUES
(1, N'TRUONG HOC', 'SCHOOL', N'NƠI DẠY VÀ HỌC'),
(2, N'NHA HANG', 'RESTAURANT', N'NƠI ĂN UỐNG'),
(3, N'NHA THUOC', 'PHARMACY', N'NƠI BÁN THUỐC, VẬT TƯ Y TẾ'),
(4, N'KHACH SAN', 'HOTEL', N'CHO THUÊ CHỖ Ở TẠM THỜI'),
(5, N'BENH VIEN', 'HOSPITAL', N'NƠI KHÁM CHỮA BỆNH')

