CREATE DATABASE [CAUHOITRACNGHIEM]

DROP TABLE CAUHOI

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
(4, 'Kim loai nao c� trang thai long o nhiet do thuong?', 'A. Sat', 'B. Kem', 'C. Thuy ngan', 'D. Dong', 'C'),
(5, 'Kim loai nao cung nhat?', 'A. Sat', 'B. Kem', 'C. Thuy ngan', 'D. Crom', 'D'),
(6, 'Mot nam co bao nhieu thang?', 'A. 7', 'B. 30', 'C. 12', 'D. 28', 'C')

SELECT * FROM CAUHOI WHERE ID IN (1, 3, 4)