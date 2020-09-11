CREATE DATABASE [CAUHOITRACNGHIEM]
GO

USE CAUHOITRACNGHIEM
GO

CREATE TABLE CAUHOI(
ID int PRIMARY KEY,
CAUHOI nvarchar(100),
A nvarchar(80),
B nvarchar(80),
C nvarchar(80),
D nvarchar(80),
DAPANDUNG varchar(1))

INSERT INTO CAUHOI (ID, CAUHOI, A, B, C, D, DAPANDUNG) VALUES
(1, 'Ba mau co ban la?', 'A. Do, cam, vang', 'B. Do cam trang', 'C. Xanh, luc, lam', 'D. Do, luc, lam', 'D'),
(2, 'Cau vong co may mau?', 'A. 7', 'B. 4', 'C. 9', 'D. 8', 'A'),
(3, 'Ket qua cua phep toan 25 - 5 la?', 'A. 15', 'B. 20', 'C. 25', 'D. Tat ca deu sai', 'B'),
(4, 'Kim loai nao co trang thai long o nhiet do thuong?', 'A. Sat', 'B. Kem', 'C. Thuy ngan', 'D. Dong', 'C'),
(5, 'Kim loai nao cung nhat?', 'A. Sat', 'B. Kem', 'C. Thuy ngan', 'D. Crom', 'D'),
(6, 'Mot nam co bao nhieu thang?', 'A. 7', 'B. 30', 'C. 12', 'D. 28', 'C'),
(7, 'Tac gia Truyen Kieu la ai?', 'A. Nguyen Du', 'B. Lao Hac', 'C. Xuan Dieu', 'D. Kim Trong', 'A'),
(8, 'Quoc khanh nuoc Viet Nam la ngay nao?', 'A. 08/03', 'B. 20/11', 'C. 02/09', 'D. 01/01', 'C'),
(9, 'Quan dao Hoang Sa va Truong Sa la cua nuoc nao?', 'A. Trung Quoc', 'B. My', 'C. Philipins', 'D. Viet Nam', 'D'),
(10, 'Ngay giai phong hoan toan mien Nam thong nhat dat nuoc la ngay nao?', 'A. 12/06/1975', 'B. 23/10/1966', 'C. 30/04/1975', 'D. 30/04/1945', 'C')

SELECT TOP 1 * FROM CAUHOI ORDER BY NEWID() //CHỌN NGẪU NHIÊN MỘT CÂU HỎI