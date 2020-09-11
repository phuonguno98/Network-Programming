USE MATHANG
GO

CREATE TABLE MATHANG(
MAHANG int,
TENHANG nvarchar(50),
GIABAN Decimal(10),
SLTONKHO Decimal(10))

INSERT INTO MATHANG(MAHANG, TENHANG, GIABAN, SLTONKHO) VALUES
(1001, N'Mi Omachi xot bo ham', 6500, 600),
(1002, N'Mi De nhat Thit bam', 6000, 360),
(2001, N'Sua Vinamilk co duong', 6000, 5),
(2005, N'Sua dau nanh Fami', 4000, 52),
(3006, N'Dau an Tuong An (1L', 35000, 2),
(3012, N'Nuoc mam Nam ngu (1L)', 25000, 102)

SELECT * FROM MATHANG WHERE MAHANG = 1001