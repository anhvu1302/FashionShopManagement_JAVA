USE master
GO
DROP DATABASE QL_FashionShop


CREATE DATABASE QL_FashionShop
GO
USE QL_FashionShop

GO

CREATE TABLE VaiTro (
    IdVaiTro INT IDENTITY(1,1),
    TenVaiTro NVARCHAR(255) NOT NULL,
    CONSTRAINT PK_VaiTro PRIMARY KEY (IdVaiTro),
    CONSTRAINT UNI_TenVaiTro UNIQUE(TenVaiTro)
);
GO
SET IDENTITY_INSERT VaiTro ON 
INSERT VaiTro (IdVaiTro, TenVaiTro) VALUES (1, N'Admin')
INSERT VaiTro (IdVaiTro, TenVaiTro) VALUES (2, N'Quản lý')
INSERT VaiTro (IdVaiTro, TenVaiTro) VALUES (3, N'Nhân viên thu ngân')
INSERT VaiTro (IdVaiTro, TenVaiTro) VALUES (4, N'Nhân viên kho')
SET IDENTITY_INSERT VaiTro OFF
SELECT * FROM VaiTro


--====================================================================================================---


CREATE TABLE KhachHang
(
	IdKhachHang  BIGINT IDENTITY(1,1),
	TenKhachHang NVARCHAR(255) NOT NULL,
	GioiTinh NVARCHAR(5),
	SoDienThoai CHAR(10) NOT NULL,
	Email VARCHAR(100),
	Diem BIGINT NOT NULL,
	NgayThem DATETIME NOT NULL,
	CONSTRAINT PK_KhachHang PRIMARY KEY(IdKhachHang),
	CONSTRAINT UNI_SoDienThoai_KhachHang UNIQUE(SoDienThoai),
	CONSTRAINT UNI_Email_KhachHang UNIQUE(Email),
	CONSTRAINT CHK_GioiTinh_KH CHECK (GioiTinh=N'Nam' OR GioiTinh=N'Nữ'),
	CONSTRAINT CHK_Diem_KH CHECK (Diem >= 0),
);
SET IDENTITY_INSERT KhachHang ON 
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (1, N'Vũ Văn Anh', N'Nam', N'0393123456', N'nhatthienhuonglogistics@gmail.com', 0,CAST(N'2024-05-23T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (2, N'Trần Thái An', N'Nam', N'0393755621', N'thienhuonglogistics@gmail.com', 0,CAST(N'2024-05-23T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (3, N'Lê Bích Thủy', N'Nữ', N'0979598491', N'customer3@gmail.com', 0,CAST(N'2024-05-03T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (4, N'Trần Mỹ Huyền', N'Nữ', N'0979598492', N'vantaiduongviet@gmail.com', 0,CAST(N'2024-05-07T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (5, N'Lê Ly Trang Nhi', N'Nữ', N'0393888888', N'taynambacsg@gmail.com', 0,CAST(N'2024-05-08T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (6, N'Nguyễn Thị Thu', N'Nữ', N'0393888889', N'vantaivohongphat@gmail.com', 0,CAST(N'2024-05-09T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (7, N'Phạm Hồng Thái', N'Nam', N'0393123457', N'customer7@gmail.com', 0,CAST(N'2024-05-15T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (8, N'Nguyễn Văn Tú', N'Nam', N'0393123458', N'customer8@gmail.com', 0,CAST(N'2024-05-12T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (9, N'Lê Thị Lan', N'Nữ', N'0393123459', N'customer9@gmail.com', 0,CAST(N'2024-05-11T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (10, N'Nguyễn Minh Hiếu', N'Nam', N'0393123460', N'customer10@gmail.com', 0,CAST(N'2024-05-16T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (11, N'Trần Văn Đức', N'Nam', N'0393123461', N'customer11@gmail.com', 0,CAST(N'2024-05-15T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (12, N'Nguyễn Thị Mai', N'Nữ', N'0393123462', N'customer12@gmail.com', 0,CAST(N'2024-05-14T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (13, N'Lê Thị Thu', N'Nữ', N'0393123463', N'customer13@gmail.com', 0,CAST(N'2024-05-22T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (14, N'Phạm Văn Hải', N'Nam', N'0393123464', N'customer14@gmail.com', 0,CAST(N'2024-05-21T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (15, N'Trần Thị Nhung', N'Nữ', N'0393123465', N'customer15@gmail.com', 0,CAST(N'2024-05-26T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (16, N'Lê Văn Hưng', N'Nam', N'0393123466', N'customer16@gmail.com', 0,CAST(N'2024-05-26T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (17, N'Nguyễn Thị Trinh', N'Nữ', N'0393123467', N'customer17@gmail.com', 0,CAST(N'2024-05-27T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (18, N'Phạm Văn Thanh', N'Nam', N'0393123468', N'customer18@gmail.com', 0,CAST(N'2024-05-27T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (19, N'Trần Thị Quỳnh', N'Nữ', N'0393123469', N'customer19@gmail.com', 0,CAST(N'2024-05-27T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (20, N'Nguyễn Văn Lợi', N'Nam', N'0393123470', N'customer20@gmail.com', 0,CAST(N'2024-05-19T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (21, N'Phạm Thị Ngọc', N'Nam', N'0393123471', N'customer21@gmail.com', 0,CAST(N'2024-04-26T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (22, N'Trần Văn Nam', N'Nam', N'0393123472', N'customer22@gmail.com', 0,CAST(N'2024-04-23T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (23, N'Nguyễn Thị Hồng', N'Nữ', N'0393123473', N'customer23@gmail.com', 0,CAST(N'2024-05-15T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (24, N'Lê Văn Phúc', N'Nam', N'0393123474', N'customer24@gmail.com', 0,CAST(N'2024-05-25T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (25, N'Nguyễn Thị Kim', N'Nữ', N'0393123475', N'customer25@gmail.com', 0,CAST(N'2024-05-15T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (26, N'Nguyễn Thị Hương', N'Nữ', N'0393123476', N'customer26@gmail.com', 0,CAST(N'2024-05-19T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (27, N'Trần Thị Phương', N'Nữ', N'0393123477', N'customer27@gmail.com', 0,CAST(N'2024-05-20T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (28, N'Lê Thị Hải Yến', N'Nữ', N'0393123478', N'customer28@gmail.com', 0,CAST(N'2024-05-22T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (29, N'Phạm Thị Trang', N'Nữ', N'0393123479', N'customer29@gmail.com', 0,CAST(N'2024-05-27T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (30, N'Nguyễn Thị Linh', N'Nữ', N'0393123480', N'customer30@gmail.com', 0,CAST(N'2024-04-28T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (31, N'Trần Thị Thuỳ Dung', N'Nữ', N'0393123481', N'customer31@gmail.com', 0,CAST(N'2024-03-25T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (32, N'Lê Thị Thanh Hằng', N'Nữ', N'0393123482', N'customer32@gmail.com', 0,CAST(N'2024-04-25T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (33, N'Nguyễn Thị Hà', N'Nữ', N'0393123483', N'customer33@gmail.com', 0,CAST(N'2024-04-28T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (34, N'Trần Thị Hồng Loan', N'Nữ', N'0393123484', N'customer34@gmail.com', 0,CAST(N'2024-04-26T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (35, N'Phạm Thị Ánh Ngọc', N'Nữ', N'0393123485', N'customer35@gmail.com', 0,CAST(N'2024-04-28T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (36, N'Nguyễn Thị Thảo Vy', N'Nữ', N'0393123486', N'customer36@gmail.com', 0,CAST(N'2024-04-27T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (37, N'Trần Thị Hồng Nhung', N'Nữ', N'0393123487', N'customer37@gmail.com', 0,CAST(N'2024-05-15T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (38, N'Trần Thị Thu Trang', N'Nữ', N'0393123488', N'customer38@gmail.com', 0,CAST(N'2024-05-14T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (39, N'Nguyễn Thị Diệu Linh', N'Nữ', N'0393123489', N'customer39@gmail.com', 0,CAST(N'2024-04-28T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (40, N'Lê Thị Thanh Trúc', N'Nữ', N'0393123490', N'customer40@gmail.com', 0,CAST(N'2024-04-23T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (41, N'Phạm Thị Lan Anh', N'Nữ', N'0393123491', N'customer41@gmail.com', 0,CAST(N'2024-04-29T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (42, N'Trần Thị Bích Ngọc', N'Nữ', N'0393123492', N'customer42@gmail.com', 0,CAST(N'2024-05-02T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (43, N'Nguyễn Thị Thanh Hương', N'Nữ', N'0393123493', N'customer43@gmail.com', 0,CAST(N'2024-05-01T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (44, N'Lê Thị Hoài Thu', N'Nữ', N'0393163493', N'customer44@gmail.com', 0,CAST(N'2024-05-01T21:43:54.330' AS DateTime))
INSERT KhachHang (IdKhachHang, TenKhachHang, GioiTinh, SoDienThoai, Email, Diem, NgayThem) VALUES (45, N'Phạm Thị Mỹ Linh', N'Nữ', N'0393123494', N'customer45@gmail.com', 0,CAST(N'2024-04-23T21:43:54.330' AS DateTime))
SET IDENTITY_INSERT KhachHang OFF
SELECT * FROM KhachHang


--====================================================================================================---
CREATE TABLE NhanVien (
    IdNhanVien BIGINT IDENTITY(1,1),
    TenTaiKhoan VARCHAR(255) NOT NULL,
    MatKhau VARCHAR(255) NOT NULL,
    IdVaiTro INT NOT NULL,
    TenNhanVien NVARCHAR(255) NOT NULL,
    NgaySinh DATE,
    GioiTinh NCHAR(5),
    DiaChi NVARCHAR(255),
    SoDienThoai CHAR(10),
    Email VARCHAR(100),
    TonTai BIT NOT NULL,
    Cam BIT NOT NULL,
    CONSTRAINT PK_NhanVien PRIMARY KEY (IdNhanVien),
    CONSTRAINT UNI_TenTaiKhoan UNIQUE (TenTaiKhoan),
    CONSTRAINT FK_NhanVien_VaiTro FOREIGN KEY (IdVaiTro) REFERENCES VaiTro (IdVaiTro)
);
ALTER TABLE NhanVien
ADD CONSTRAINT DF_DiaChi_NhanVien DEFAULT N'Không xác định' FOR DiaChi;

SET DATEFORMAT DMY
SET IDENTITY_INSERT NhanVien ON 
INSERT INTO NhanVien (IdNhanVien, TenTaiKhoan, MatKhau, IdVaiTro, TenNhanVien, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, TonTai, Cam) VALUES(1,'admin', 'p9rcbUNiWF+AC9dfPLx3Aw==:TH0x1hxXoAJ42iQtrE+gei32Xb4fC9vixX07MFQJ/o8=', 1, N'Vũ Văn Anh', '13/02/2003', N'Nam', N'Tây Thạnh, Tân Phú, Thành phố Hồ Chí Minh', '0393222222', 'fashionshop@gmail.com', 1, 0)
INSERT INTO NhanVien (IdNhanVien, TenTaiKhoan, MatKhau, IdVaiTro, TenNhanVien, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, TonTai, Cam) VALUES(2,'quanly1', 'dsV+l826qrW5ODfeJSgtxw==:OUPGYu3wnSteta4RgLQAOFGs4WBjQU2giCyqH3voHJU=', 2, N'Hà Tri Thủy', '17/01/2003', N'Nam', N'Tân Sơn Nhì, Tân Phú, Thành phố Hồ Chí Minh', '0393222', 'hatrithuy@gmail.com', 1, 0)
INSERT INTO NhanVien (IdNhanVien, TenTaiKhoan, MatKhau, IdVaiTro, TenNhanVien, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, TonTai, Cam) VALUES(3,'quanly2', 'kFvKb3+ozhMLMWfg+aNQMA==:ltF678huOnTlfii2HcrFXC/wwgeVtKC0B1ZfgKqfR6Q=', 2, N'Huỳnh Tuấn Khang', '07/04/2003', N'Nam', N'Phường 7, Quận 5, Thành phố Hồ Chí Minh', '0393555222', 'khanghuynh@gmail.com', 1, 0)
INSERT INTO NhanVien (IdNhanVien, TenTaiKhoan, MatKhau, IdVaiTro, TenNhanVien, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, TonTai, Cam) VALUES(4,'thungan1', 'v9aBB6yfqNJoSBi2Vf0uRg==:xwofWGdGirLjMlHJhqJJ/8q2c8ZbVt4ruAtJkNxOQiM=', 3, N'Đinh Tuyết Anh', '20/11/1990', N'Nữ', N'212-242 Đ. Độc Lập, Tân Thành, Tân Phú, Thành phố Hồ Chí Minh', '0393666222', 'tuyeanhdinh20@gmail.com', 1, 0)
INSERT INTO NhanVien (IdNhanVien, TenTaiKhoan, MatKhau, IdVaiTro, TenNhanVien, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, TonTai, Cam) VALUES(5,'thungan2', '40lBz13pZB7RdbogthjyJQ==:9hxAgrPvq9il9AOduQqex+v0X1BdXjjjH0UGbcY77Hc=', 3, N'Nguyễn Lê Huyền Trang', '14/03/1998', N'Nữ', N'Bình Hưng Hòa A, Bình Hưng Hoà A, Bình Tân, Thành phố Hồ Chí Minh', '0393777222', 'trangnguyen14@gmail.com', 1, 0)
INSERT INTO NhanVien (IdNhanVien, TenTaiKhoan, MatKhau, IdVaiTro, TenNhanVien, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, TonTai, Cam) VALUES(6,'kho1', '62iKOrTvxvPi7UPL9EKOZA==:5G1rmikOL9UCl86oVtOL1At260kEoM2O4BlXAaJ1PIQ=', 4, N'Huỳnh Thái Cường', '21/06/2000', N'Nam', N'131 Đ. 26/3, Bình Hưng Hoà, Bình Tân, Thành phố Hồ Chí Minh', '0393000222', 'cuonghuynh21@gmail.com', 1, 0)
INSERT INTO NhanVien (IdNhanVien, TenTaiKhoan, MatKhau, IdVaiTro, TenNhanVien, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, TonTai, Cam) VALUES(7,'kho2', '62iKOrTvxvPi7UPL9EKOZA==:5G1rmikOL9UCl86oVtOL1At260kEoM2O4BlXAaJ1PIQ=', 4, N'Võ Yến Nhi', '21/06/2000', N'Nữ', N'Kênh nước đen, Bình Tân, Thành phố Hồ Chí Minh', '0393000444', 'yennhi21@gmail.com', 1, 0)
SET IDENTITY_INSERT NhanVien OFF
SELECT * FROM NhanVien
--====================================================================================================---
CREATE TABLE LoaiSanPhamCha
(
	IdLoaiSPCha INT IDENTITY(1,1),
	TenLoaiSPCha NVARCHAR(255),
	CONSTRAINT PK_IdLoaiSPCha PRIMARY KEY(IdLoaiSPCha),
	CONSTRAINT UNI_TenLoaiSPCha UNIQUE(TenLoaiSPCha)
);
SET IDENTITY_INSERT LoaiSanPhamCha ON 
INSERT INTO LoaiSanPhamCha(IdLoaiSPCha,TenLoaiSPCha) VALUES (1,N'Nam');
INSERT INTO LoaiSanPhamCha(IdLoaiSPCha,TenLoaiSPCha) VALUES (2,N'Nữ');
INSERT INTO LoaiSanPhamCha(IdLoaiSPCha,TenLoaiSPCha) VALUES (3,N'Trẻ em');
INSERT INTO LoaiSanPhamCha(IdLoaiSPCha,TenLoaiSPCha) VALUES (4,N'Phụ Kiện');
SET IDENTITY_INSERT LoaiSanPhamCha OFF
SELECT * FROM LoaiSanPhamCha
--====================================================================================================---

CREATE TABLE LoaiSanPham
(
	IdLoaiSP INT IDENTITY(1,1),
	TenLoaiSP NVARCHAR(255),
	IdLoaiSPCha INT,
	CONSTRAINT PK_LoaiSanPham PRIMARY KEY(IdLoaiSP),
	CONSTRAINT UNI_TenLoaiSP UNIQUE(TenLoaiSP),
	CONSTRAINT FK_LoaiSanPham_LoaiSanPhamCha FOREIGN KEY (IdLoaiSPCha) REFERENCES LoaiSanPhamCha(IdLoaiSPCha)
);
SET IDENTITY_INSERT LoaiSanPham ON 
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (1,N'Áo nam',1);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (2,N'Quần nam',1);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (3,N'Vest - Blazer',1);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (4,N'Áo khoác nam',1);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (5,N'Áo nữ',2);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (6,N'Áo dài',2);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (7,N'Áo khoác nữ',2);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (8,N'Quần nữ',2);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (9,N'Đầm',2);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (10,N'Váy',2);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (11,N'Chân váy',2);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (12,N'Quần',3);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (13,N'Áo',3);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (14,N'Mắt kính',4);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (15,N'Giày - Dép',4);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (16,N'Mũ - Nón',4);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (17,N'Vớ - Tất',4);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (18,N'Túi - Ví',4);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (19,N'Thắt lưng',4);
INSERT INTO LoaiSanPham (IdLoaiSP,TenLoaiSP,IdLoaiSPCha) VALUES (20,N'Đồng hồ',4);
SET IDENTITY_INSERT LoaiSanPham OFF
SELECT * FROM LoaiSanPham

--====================================================================================================---
CREATE TABLE SanPham (
  IdSanPham BIGINT IDENTITY(1,1),
  TenSanPham NVARCHAR(255) NOT NULL,
  IdLoaiSP INT NOT NULL,
  GiaBan BIGINT NOT NULL,
  GiamGia INT  NOT NULL,
  MoTa NVARCHAR(MAX),
  NgayThem Date NOT NULL,
  TonTai BIT NOT NULL,
  CONSTRAINT PK_SanPham PRIMARY KEY(IdSanPham),
  CONSTRAINT UNI_TenSanPham UNIQUE(TenSanPham),
  CONSTRAINT FK_SanPham_LoaiSanPham FOREIGN KEY (IdLoaiSP) REFERENCES LoaiSanPham(IdLoaiSP),
  CONSTRAINT CHK_Gia_SanPham CHECK(GiaBan >= 0),
  CONSTRAINT CHK_GiamGia_SanPham CHECK(GiamGia >= 0),
);
ALTER TABLE SanPham
ADD CONSTRAINT DF_SanPham_NgayThem DEFAULT GETDATE() FOR NgayThem;
GO
SET DATEFORMAT DMY
SET IDENTITY_INSERT SanPham ON 
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(1,N'Đầm ngắn 2 dây', 9, 1319000, 20,N'Đầm ngắn 2 dây cup ngực sang trọng, gợi cảmTrang phục phù hợp dạo phố, thường ngày, đi tiệc...Kích thước áo: S - M - LChiều dài: S : 60,5 cm - M: 62 cm - L : 63,5 cm','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(2,N'Blazer nam trắng', 3, 2199000, 30,N'Áo Blazer nam phom Premio phù hợp mọi dáng ngườiGam màu trung tính, lịch lãm tạo điểm nhấn nổi bật và thời thượng','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(3,N'Áo 2 dây xếp li', 5, 359000, 15, N'Áo 2 dây xếp li thời trang, nữ tínhTrang phục phù hợp dạo phố, thường ngày, đi tiệc...Kích thước áo: S - M - LS : 38.7cm &#8226; M : 39.4cm &#8226; L : 40.1cm','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(4,N'Sơ mi tay dài', 1,  1144000, 15, N'Áo sơ mi dài tay phom Regular fit có độ suông rộng vừa đủThiết kế chỉn chu đến từng chi tiết với tà lượn, túi ngực.','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(5,N'Áo blazer oversize', 7, 790000, 20, N'Áo blazer oversize chất liệu Tweed thời trangTrang phục phù hợp dạo phố, đi làm, đi tiệc....Kích thước áo: S - M - LS: 67.5cm - M: 68.5cm - L: 69.5cm','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(6,N'Áo polo nam', 1, 524000, 5, N'Áo polo chất liệu polyester pha cafe, cổ đức tay cộc, phom regular.','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(7,N'Chân váy midi xẻ', 11,  749000, 25, N'Chân váy midi xẻ nữ tínhTrang phục phù hợp dạo phố, thường ngày,...Kích thước áo: S - M - LS: 62,5cm &#8226; M: 63,5cm &#8226; L: 64,5cm','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(8,N'Air Jordan I High G', 15,  4100000, 28, N'Giày thể thaoTrang phục phù hợp dạo phố, thường ngày,...Kích thước giày: 36 &#8594; 46','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(9,N'Váy lửng dáng xoè', 10,  599000, 20, N'Váy lửng dáng xoè nữ tínhTrang phục phù hợp dạo phố, thường ngày,đi tiệc...Kích thước áo: S - M - LS : 70 cm - M : 72 cm - L : 72 cm','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(10,N'Mắt Kính Polygon', 14,  519000, 30, N'Mắt kính polygon Classic kim loại thời trangThiết kế phù hợp phối với nhiều trang phục thời trang đa dạngHộp kính tam giác da PU chống nước, nắp nam châm và kèm khăn lau kính','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(11,N'Khoác blazer oversize sọc', 7,1689000, 30,  N'Khoác blazer oversized phối denim cá tínhTrang phục phù hợp dạo phố, thường ngày,...Kích thước áo: S - M - LS : 43,2 cm - M : 45,7cm - L : 48,2 cm','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(12,N'Đầm nhung 2 dây midi', 9,  874000, 25,  N'Đầm nhung 2 dây midi thời trang, sang trọngTrang phục phù hợp dạo phố, thường ngày, đi tiệc...Kích thước áo: S - M - LS : 88cm - M : 89,5cm - L : 91cm','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(13,N'Đầm Thun Mini Tay Lở Dáng Xoè', 9,  588000, 10, N'Đầm thun mini tay lở dáng xoè thiết kế basic tôn dángTrang phục phù hợp dạo phố, thường ngày, đi tiệc...Kích thước áo: S - M - LS : 79cm - M : 81cm - L : 83cm','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(14,N'Đầm Mini Tay Dài, Đắp Chéo Xếp Ly', 9, 682000, 25, N'Đầm Đầm mini tay dài, đắp chéo xếp ly thanh lịch, ôm eo tạo điểm nhấnTrang phục phù hợp dạo phố, thường ngày,đi tiệc, đi làm...','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(15,N'Set Quần Short Áo Sơ Mi Oversize Tay Phồng', 5, 784000, 25,  N'Áo sơ mi oversize tay phồng năng động, trẻ trungTrang phục phù hợp đi làm, thường ngày,...Kích thước áo: F (Freesize)','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(16,N'Đầm 2 dây dài buộc nơ vai', 9,  799000, 10,  N'Đầm 2 dây dài buộc nơ vai.Trẻ trung - Nữ tính.Trang phục dạo phố. Kiểu dáng: Đầm lửngChất liệu: twillMàu sắc: ĐỏKích cỡ: S-M-L)','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(17,N'Đầm bút chì phối Organza', 9,  699000, 15,  N'Đầm bút chì phối Organza.Thanh lịch - Hiện đại..Trang phục phù hợp dạo phố, đi tiệc,...','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(18,N'Áo Thun BabyTee Tay Ngắn In Hình', 5, 399000, 30,  N'Áo thun BabyTee tay ngắn in hình trẻ trung, năng động.Trang phục phù hợp dạo phố, thường ngày, đi học...','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(19,N'Đầm Mini Phối Ren Cúp Ngực Tay Dài', 9, 599000, 17,  N'Đầm mini phối ren cúp ngực tay dài thời trang, gợi cảmTrang phục phù hợp dạo phố, đi tiệc,...','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(20,N'Đầm lửng bẹt vai nhún tà', 9, 699000, 15,  N'Đầm lửng bẹt vai nhún tà.Trẻ trung - Nữ tính.','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(21,N'Áo Vest Blazer Nam Tay Dài Trơn', 3,  1400000, 8, N'Áo vest cũng mang đến một hình ảnh chỉnh chu, sang trọng và nam tính quyến rũTrang phục phù hợp lễ cưới, sự kiện, tham gia tiệc, đi làm, hẹn hò,... ','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(22,N'Áo Vest Blazer Nam Kẻ Sọc Dọc', 3, 1340000, 10, N'Áo vest cũng mang đến một hình ảnh chỉnh chu, sang trọng và nam tính quyến rũTrang phục phù hợp môi trường làm việc công sở hoặc những buổi tiệc sang trọng,... ','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(23,N'Áo sơ mi dài tay dây rút', 5,  599000, 10, N'Áo sơ mi dài tay dây rút  sành điệu, hiện đạiTrang phục phù hợp dạo phố, thường ngày,đi chơi... ','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(24,N'Áo Cropped Blazer', 5,  599000, 0,  N'Áo Cropped Blazer cá tínhTrang phục phù hợp dạo phố, thường ngày,... Kiểu dáng: Chất liệu: Cotton.Màu sắc: Đen-NâuKích cỡ: S-M-LGiá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(25,N'Áo Parka Hai Mặt', 1,  980000, 20, N'Hai phong cách trong một chiếc áo.Áo parka không thấm nước để bảo vệ chống lại các yếu tố thời tiết. Kiểu dáng: Chất liệu: Mặt Trước: 100% Polyeste/ Mặt Sau: 100% Polyeste.Màu sắc: Đen-NâuKích cỡ: L-XL-XXLGiá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(26,N'Đồng hồ ORIENT Star 39.3 mm Nam', 20,  19460000, 30, N'Đến từ hãng đồng hồ Orient, thương hiệu Nhật Bản nổi tiếng với nhiều chiếc đồng hồ thời thượngĐồng hồ cơ tự động, bền bỉ, không cần dùng pin, lên dây cót bằng chuyển động của cổ tayVới đường kính mặt 39.3 mm, dành cho bạn nam có cổ tay với chu vi từ 18 - 19 cmChiếc đồng hồ nam có dây đeo và khung viền được làm từ thép không gỉ bền bỉ, chống ăn mòn và oxi hóa tốtHệ số chống nước 10 ATM, người dùng yên tâm đeo đồng hồ khi bơi lội, tắm rửa, đi mưa, rửa tay. Không nên đeo đồng hồ khi đi lặn Đường kính mặt: 39.3 mmDây đeo Khung viền: Thép không gỉLoại máy: Cơ tự độngGiá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(27,N'Đồng hồ CITIZEN Mechanical 42 mm Nam', 20, 11185000, 20, N'Mẫu đồng hồ đến từ thương hiệu Citizen - một trong những thương hiệu nổi tiếng và uy tín đến từ Nhật BảnĐồng hồ CITIZEN Mechanical 42 mm Nam NJ0080-50E sở hữu đường kính mặt 42 mm và độ rộng dây 18 mm.Đồng hồ cơ tự động, bền bỉ, không cần dùng pin, lên dây cót bằng chuyển động của cổ tay5 ATM là độ kháng nước của chiếc đồng hồ nam này, người dùng có thể an toàn đeo khi đi tắm, đi mưa. Lưu ý: không nên mang khi đi bơi, lặn. Đường kính mặt: 42 mmChất liệu mặt kính: Kính SapphireDây đeo Khung viền: Thép không gỉLoại máy: Cơ tự độngGiá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(28,N'Áo Sơ Mi Nam Oxford Tay Dài', 1, 569000, 6,  N'Thiết kế đặc biệt với phần cổ trụ, áo vẫn giữ nguyên được nét trang nhã và tối giản nhưng không mang lại cảm giác tẻ nhạt.Form dáng ôm vừa vặn, phần thân và tay áo suông, không ôm sẽ mang đến cảm giác rộng rãi, dễ chịu cho người mặc vào những ngày hè oi bức. Kiểu dáng: FittedChất liệu: Cotton.Màu sắc: Đen-TrắngKích cỡ: L-XL-XXLGiá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(29,N'Áo kiểu bẹt vai tay phồng', 5, 499000, 10, N'Miêu tả: ÁO BẸT VAI TAY PHỒNG.Đặc tính: Trẻ trung - Nữ tính - Gợi cảm.Thể loại: Trang phục dạo phố, tiệc. Kiểu dáng: Chất liệu: Poly xốp tổng hợp.Màu sắc: Xanh - Cam - Đen - Kem.Giá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(30,N'Áo cúp ngang bẹt vai', 5, 499000, 10,  N'Miêu tả: ÁO CÚP NGANG BẸT VAI.Đặc tính: Hiện đại - Nữ tính.Thể loại: Trang phục tiệc, dạo phố. Kiểu dáng: Chất liệu: Vải poly tổng hợp.Màu sắc: Đen - Kem.Giá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(31,N'Áo Kiểu Dây Yếm Cổ Tay Phồng', 5, 699000, 10, N'Miêu tả: ÁO KIỂU DÂY YẾM CỔ TAY PHỒNG.Đặc tính: Nữ tính - Cá tính.Thể loại: Trang phục dạo phố.Kiểu dáng: Chất liệu: Cotton gân.Màu sắc: Xanh - Kem - Đen.Giá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (IdSanPham,TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(32,N'Áo kiểu tay lỡ cổ gấp nếp', 5, 699000, 15, N'Áo kiểu tay lỡ cổ gấp nếp nữ tính, sang trọngTrang phục phù hợp dạo phố, thường ngày,đi làm...Kiểu dáng: Chất liệu: cotton.Màu sắc: Đen - Trắng - Kem.Giá đã bao gồm VAT)','05/05/2023', 1);
SET IDENTITY_INSERT SanPham OFF
SELECT * FROM SanPham

CREATE TABLE KieuSanPham (
  IdKieuSanPham BIGINT IDENTITY(1,1),
  BarCode INT NOT NULL,
  IdSanPham BIGINT NOT NULL,
  Mau NVARCHAR(255) NOT NULL,
  AnhSP VARCHAR(255) NOT NULL,
  Size NVARCHAR(255) NOT NULL,
  SoLuongTonKho INT NOT NULL,
  CONSTRAINT PK_KieuSanPham PRIMARY KEY(IdKieuSanPham),
  CONSTRAINT UNI_BarCode UNIQUE(BarCode),
  CONSTRAINT UNI_KieuSanPham UNIQUE(IdSanPham, Mau, Size),
  CONSTRAINT CHK_SoLuongTonKho CHECK(SoLuongTonKho>=0),
  CONSTRAINT FK_KieuSanPham_SanPham FOREIGN KEY (IdSanPham) REFERENCES SanPham(IdSanPham),
);
SET IDENTITY_INSERT KieuSanPham ON 
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(1,111111,1,N'Trắng', 'product_img1.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(2,339356,1,N'Trắng', 'product_img1.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(3,789424,1,N'Trắng', 'product_img1.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(4,155869,1,N'Đen', 'product_img1_den.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(5,666952,1,N'Đen', 'product_img1_den.jpg', 'XL',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(6,862686,2,N'Trắng', 'product_img2.jpg','S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(7,646950,2,N'Trắng', 'product_img2.jpg','M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(8,222222,2,N'Trắng', 'product_img2.jpg','L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(9,573868,2,N'Trắng', 'product_img2.jpg','XL',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(10,361181,2,N'Xanh tím than', 'product_img2_xanhtimthan.jpg','L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(11,103661,2,N'Xanh tím than', 'product_img2_xanhtimthan.jpg','XL',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(12,333333,3,N'Kem','product_img3.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(13,845222,3,N'Kem','product_img3.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(14,444444,3,N'Đỏ','product_img3_do.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(15,844875,3,N'Đỏ','product_img3_do.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(16,638335,4,N'Đen', 'product_img4.jpg', '38',100);  
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(17,730138,4,N'Đen', 'product_img4.jpg', '39',100);  
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(18,555555,4,N'Đen', 'product_img4.jpg', '40',100);  
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(19,411151,4,N'Đen', 'product_img4.jpg', '41',100);  
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(20,390097,5,N'Be', 'product_img5.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(21,493184,5,N'Be', 'product_img5.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(22,666666,5,N'Đen', 'product_img5_den.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(23,919125,5,N'Đen', 'product_img5_den.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(24,388590,6,N'Trắng viền xám', 'product_img6.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(25,247508,6,N'Trắng viền xám', 'product_img6.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(26,339344,6,N'Trắng viền xám', 'product_img6.jpg', 'XL',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(27,481300,7,N'Be', 'product_img7.jpg', 'SL',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(28,777777,7,N'Be', 'product_img7.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(29,414352,7,N'Be', 'product_img7.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(30,575627,7,N'Nâu','product_img7_nau.jpg','S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(31,367748,7,N'Nâu','product_img7_nau.jpg','M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(32,517494,7,N'Nâu','product_img7_nau.jpg','L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(33,177048,7,N'Đen', 'product_img7_den.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(34,977945,7,N'Đen', 'product_img7_den.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(35,201522,7,N'Đen', 'product_img7_den.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(36,353427,8,N'Xanh lá', 'product_img8.jpg', '36',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(37,888888,8,N'Xanh lá', 'product_img8.jpg', '37',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(38,885448,8,N'Xanh lá', 'product_img8.jpg', '38',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(39,518719,8,N'Xanh lá', 'product_img8.jpg', '39',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(40,349418,8,N'Xanh lá', 'product_img8.jpg', '40',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(41,413742,9,N'Trắng xám','product_img9.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(42,371114,10,N'Xanh xám','product_img10.jpg',' ',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(43,101270,10,N'Đen','product_img10_den.jpg',' ',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(44,348693,10,N'Nâu', 'product_img10_nau.jpg',' ',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(45,269178,11,N'Caro nâu', 'product_img11.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(46,248574,11,N'Caro nâu', 'product_img11.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(47,395736,11,N'Caro đen', 'product_img11_den.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(48,471909,12,N'Đen','product_img12.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(49,652912,12,N'Đen','product_img12.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(50,967396,12,N'Đen','product_img12.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(51,834432,13,N'Xanh', 'product_img13.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(52,832150,13,N'Xanh', 'product_img13.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(53,529517,13,N'Xanh', 'product_img13.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(54,305955,13,N'Đen', 'product_img13_den.jpg','S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(55,820501,13,N'Đen', 'product_img13_den.jpg','M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(56,519740,14,N'Tím','product_img14.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(57,713379,14,N'Tím','product_img14.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(58,754350,15,N'Trắng','product_img15.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(59,633769,15,N'Trắng','product_img15.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(60,928867,15,N'Trắng','product_img15.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(61,261605,16,N'Đỏ','product_img16.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(62,165003,16,N'Đỏ','product_img16.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(63,292172,16,N'Đỏ','product_img16.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(64,592098,16,N'Đỏ','product_img16.jpg', 'XL',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(65,143479,17,N'Đen','product_img17.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(66,535259,17,N'Đen','product_img17.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(67,795605,17,N'Đen','product_img17.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(68,824906,18,N'Trắng','product_img18.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(69,660646,18,N'Trắng','product_img18.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(70,400861,18,N'Trắng','product_img18.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(71,999999,19,N'Đen','product_img19.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(72,323084,19,N'Đen','product_img19.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(73,148621,19,N'Đen','product_img19.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(74,455188,20,N'Trắng','product_img20.jpg', 'S',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(75,843413,20,N'Trắng','product_img20.jpg', 'M',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(76,187131,20,N'Trắng','product_img20.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(77,790766,21,N'Be','product_img21.jpg', 'L',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(78,812359,21,N'Be','product_img21.jpg', 'XL',100);
INSERT INTO KieuSanPham (IdKieuSanPham, BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(79,581207,21,N'Be','product_img21.jpg', 'XXL',100);
SET IDENTITY_INSERT KieuSanPham OFF
SELECT * FROM KieuSanPham


--====================================================================================================---
CREATE TABLE HoaDonNhapKho
(
    IdHoaDonNhapKho BIGINT IDENTITY(1,1),
    IdNhanVien BIGINT NOT NULL,
	  TongTien BIGINT NOT NULL,
    NgayNhap DATETIME NOT NULL,
	  CONSTRAINT PK_HoaDonNhapKho PRIMARY KEY(IdHoaDonNhapKho),
    CONSTRAINT FK_HoaDonNhapKho_NhanVien FOREIGN KEY(IdNhanVien) REFERENCES NhanVien(IdNhanVien)
);

--====================================================================================================---

CREATE TABLE ChiTietHoaDonNhapKho (
  IdHoaDonNhapKho BIGINT NOT NULL,
  IdKieuSanPham BIGINT NOT NULL,
  SoLuong INT,
  DonGia BIGINT NOT NULL,
  CONSTRAINT PK_ChiTietHoaDonNhapKho PRIMARY KEY(IdHoaDonNhapKho, IdKieuSanPham),
  CONSTRAINT FK_ChiTietHoaDonNhapKho_HoaDonNhapKho FOREIGN KEY(IdHoaDonNhapKho) REFERENCES HoaDonNhapKho(IdHoaDonNhapKho),
  CONSTRAINT FK_ChiTietHoaDonNhapKho_KieuSanPham FOREIGN KEY(IdKieuSanPham) REFERENCES KieuSanPham(IdKieuSanPham),
  CONSTRAINT CHK_SoLuong_CTHoaDon CHECK (SoLuong>=0)
);

--====================================================================================================---

CREATE TABLE HoaDon (
  IdHoaDon BIGINT IDENTITY(1,1),
  IdNhanVien BIGINT NOT NULL,
  IdKhachHang  BIGINT, 
  TongTien BIGINT NOT NULL,
  DiemSuDung BIGINT NOT NULL,
  PhuongThucThanhToan NVARCHAR(30) NOT NULL,
  NgayXuatHD DATETIME NOT NULL,
  CONSTRAINT PK_HoaDon PRIMARY KEY(IdHoaDon),
  CONSTRAINT FK_HoaDon_NhanVien FOREIGN KEY(IdNhanVien) REFERENCES NhanVien(IdNhanVien),
  CONSTRAINT FK_HoaDon_KhachHang FOREIGN KEY(IdKhachHang) REFERENCES KhachHang(IdKhachHang)
);
SET IDENTITY_INSERT HoaDon ON 
INSERT HoaDon (IdHoaDon, IdNhanVien, IdKhachHang, TongTien, DiemSuDung, PhuongThucThanhToan, NgayXuatHD) VALUES (1, 1, 1, 859900, 7000, N'Tiền mặt', CAST(N'2024-05-23T21:43:54.330' AS DateTime))
INSERT HoaDon (IdHoaDon, IdNhanVien, IdKhachHang, TongTien, DiemSuDung, PhuongThucThanhToan, NgayXuatHD) VALUES (2, 1, 1, 859900, 7000, N'Tiền mặt', CAST(N'2024-05-25T21:43:54.330' AS DateTime))
INSERT HoaDon (IdHoaDon, IdNhanVien, IdKhachHang, TongTien, DiemSuDung, PhuongThucThanhToan, NgayXuatHD) VALUES (3, 1, 1, 305150, 0, N'Tiền mặt', CAST(N'2024-05-25T21:47:48.330' AS DateTime))
INSERT HoaDon (IdHoaDon, IdNhanVien, IdKhachHang, TongTien, DiemSuDung, PhuongThucThanhToan, NgayXuatHD) VALUES (4, 1, 1, 305150, 0, N'Tiền mặt', CAST(N'2024-05-25T21:48:51.780' AS DateTime))
INSERT HoaDon (IdHoaDon, IdNhanVien, IdKhachHang, TongTien, DiemSuDung, PhuongThucThanhToan, NgayXuatHD) VALUES (5, 1, 1, 305150, 0, N'Tiền mặt', CAST(N'2024-05-12T21:48:51.780' AS DateTime))
INSERT HoaDon (IdHoaDon, IdNhanVien, IdKhachHang, TongTien, DiemSuDung, PhuongThucThanhToan, NgayXuatHD) VALUES (6, 1, 1, 305150, 0, N'Tiền mặt', CAST(N'2024-05-25T21:51:40.660' AS DateTime))
INSERT HoaDon (IdHoaDon, IdNhanVien, IdKhachHang, TongTien, DiemSuDung, PhuongThucThanhToan, NgayXuatHD) VALUES (7, 1, 1, 305150, 0, N'Tiền mặt', CAST(N'2024-05-02T21:51:40.660' AS DateTime))
INSERT HoaDon (IdHoaDon, IdNhanVien, IdKhachHang, TongTien, DiemSuDung, PhuongThucThanhToan, NgayXuatHD) VALUES (8, 1, 1, 305150, 0, N'Tiền mặt', CAST(N'2024-05-25T22:03:25.917' AS DateTime))
INSERT HoaDon (IdHoaDon, IdNhanVien, IdKhachHang, TongTien, DiemSuDung, PhuongThucThanhToan, NgayXuatHD) VALUES (9, 1, 1, 305150, 0, N'Tiền mặt', CAST(N'2024-05-25T22:07:19.227' AS DateTime))
INSERT HoaDon (IdHoaDon, IdNhanVien, IdKhachHang, TongTien, DiemSuDung, PhuongThucThanhToan, NgayXuatHD) VALUES (10, 1, 1, 1277550, 0, N'Chuyển khoản', CAST(N'2024-05-26T10:33:05.287' AS DateTime))
INSERT HoaDon (IdHoaDon, IdNhanVien, IdKhachHang, TongTien, DiemSuDung, PhuongThucThanhToan, NgayXuatHD) VALUES (11, 1, 1, 305150, 0, N'Tiền mặt', CAST(N'2024-05-26T10:34:26.927' AS DateTime))
INSERT HoaDon (IdHoaDon, IdNhanVien, IdKhachHang, TongTien, DiemSuDung, PhuongThucThanhToan, NgayXuatHD) VALUES (12, 1, 1, 3315300, 0, N'Tiền mặt', CAST(N'2024-05-26T11:42:11.840' AS DateTime))
SET IDENTITY_INSERT HoaDon OFF
--====================================================================================================---


CREATE TABLE ChiTietHoaDon (
  IdHoaDon BIGINT NOT NULL,
  IdKieuSanPham BIGINT NOT NULL,
  SoLuong INT NOT NULL,
  DonGia BIGINT NOT NULL,
  CONSTRAINT PK_ChiTietHoaDon PRIMARY KEY(IdHoaDon, IdKieuSanPham),
  CONSTRAINT FK_ChiTietHoaDon_HoaDon FOREIGN KEY(IdHoaDon) REFERENCES HoaDon(IdHoaDon),
  CONSTRAINT FK_ChiTietHoaDon_KieuSanPham FOREIGN KEY(IdKieuSanPham) REFERENCES KieuSanPham(IdKieuSanPham),
  CONSTRAINT CHK_SoLuong_ChiTietHoaDon CHECK(SoLuong >= 0),
  CONSTRAINT CHK_DonGia_ChiTietHoaDon CHECK(DonGia >= 0)
);
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (1, 14, 1, 305150)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (2, 14, 1, 305150)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (2, 28, 1, 561750)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (3, 14, 1, 305150)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (4, 14, 1, 305150)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (5, 14, 1, 305150)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (6, 14, 1, 305150)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (7, 14, 1, 305150)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (8, 14, 1, 305150)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (9, 14, 1, 305150)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (10, 14, 1, 305150)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (10, 18, 1, 972400)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (11, 14, 1, 305150)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (12, 38, 1, 2952000)
INSERT ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (12, 43, 1, 363300)

--------------------------- Trigger -----------------------------

Go
CREATE TRIGGER trig_updated_SoLuongXuatKho_on_insert
ON ChiTietHoaDon
AFTER INSERT
AS
BEGIN
	UPDATE KieuSanPham
	SET SoLuongTonKho = SoLuongTonKho - inserted.SoLuong
	FROM KieuSanPham
	INNER JOIN inserted ON KieuSanPham.IdKieuSanPham = inserted.IdKieuSanPham;
END;

Go
CREATE TRIGGER trig_updated_SoLuongXuatKho_on_delete
ON ChiTietHoaDon
AFTER DELETE
AS
BEGIN
	UPDATE KieuSanPham
	SET SoLuongTonKho = SoLuongTonKho + deleted.SoLuong
	FROM KieuSanPham
	INNER JOIN deleted ON KieuSanPham.IdKieuSanPham = deleted.IdKieuSanPham;
END;

Go
CREATE TRIGGER trig_updated_SoLuongXuatKho_on_update
ON ChiTietHoaDon
AFTER UPDATE
AS
BEGIN
	UPDATE KieuSanPham
	SET SoLuongTonKho = SoLuongTonKho + deleted.SoLuong - inserted.SoLuong
	FROM KieuSanPham
	INNER JOIN deleted ON KieuSanPham.IdKieuSanPham = deleted.IdKieuSanPham
	INNER JOIN inserted ON KieuSanPham.IdKieuSanPham = inserted.IdKieuSanPham;
END;



Go
CREATE TRIGGER trig_updated_SoLuongNhapKho_on_insert
ON ChiTietHoaDonNhapKho
AFTER INSERT
AS
BEGIN
	UPDATE KieuSanPham
	SET SoLuongTonKho = SoLuongTonKho + inserted.SoLuong
	FROM KieuSanPham
	INNER JOIN inserted ON KieuSanPham.IdKieuSanPham = inserted.IdKieuSanPham;
END;

Go
CREATE TRIGGER trig_updated_SoLuongNhapKho_on_delete
ON ChiTietHoaDonNhapKho
AFTER DELETE
AS
BEGIN
	UPDATE KieuSanPham
	SET SoLuongTonKho = SoLuongTonKho - deleted.SoLuong
	FROM KieuSanPham
	INNER JOIN deleted ON KieuSanPham.IdKieuSanPham = deleted.IdKieuSanPham;
END;

Go
CREATE TRIGGER trig_updated_SoLuongNhapKho_on_update
ON ChiTietHoaDonNhapKho
AFTER UPDATE
AS
BEGIN
	UPDATE KieuSanPham
	SET SoLuongTonKho = SoLuongTonKho - deleted.SoLuong + inserted.SoLuong
	FROM KieuSanPham
	INNER JOIN deleted ON KieuSanPham.IdKieuSanPham = deleted.IdKieuSanPham
	INNER JOIN inserted ON KieuSanPham.IdKieuSanPham = inserted.IdKieuSanPham;
END;

--Trigger cập nhật tổng tiền nhập kho

GO
CREATE TRIGGER trg_update_TongTienHoaDonNhapKho
ON ChiTietHoaDonNhapKho
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;

    -- Cập nhật TongTien cho các hóa đơn bị ảnh hưởng
    UPDATE HoaDonNhapKho
    SET TongTien = (
        SELECT SUM(SoLuong * DonGia)
        FROM ChiTietHoaDonNhapKho
        WHERE ChiTietHoaDonNhapKho.IdHoaDonNhapKho = HoaDonNhapKho.IdHoaDonNhapKho
    )
    WHERE HoaDonNhapKho.IdHoaDonNhapKho IN (
        SELECT DISTINCT IdHoaDonNhapKho
        FROM inserted
        UNION
        SELECT DISTINCT IdHoaDonNhapKho
        FROM deleted
    );
END;


-------------------- Store Procedure ----------------------
GO
CREATE PROCEDURE sp_GetSalesByCategory
    @StartDate DATE,
    @EndDate DATE
AS
BEGIN
	IF @StartDate > @EndDate
    BEGIN
        PRINT 'Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc.'
        RETURN
    END
    SELECT 
        LSPC.IdLoaiSPCha,
        LSPC.TenLoaiSPCha,
        SUM(CT.SoLuong) AS SoLuongBan,
        SUM(CT.SoLuong * CT.DonGia) AS DoanhThu
    FROM HoaDon HD
    INNER JOIN ChiTietHoaDon CT ON HD.IdHoaDon = CT.IdHoaDon
    INNER JOIN KieuSanPham KSP ON CT.IdKieuSanPham = KSP.IdKieuSanPham
    INNER JOIN SanPham SP ON SP.IdSanPham = KSP.IdSanPham
    INNER JOIN LoaiSanPham LSP ON LSP.IdLoaiSP = SP.IdLoaiSP
    INNER JOIN LoaiSanPhamCha LSPC ON LSPC.IdLoaiSPCha = LSP.IdLoaiSPCha
    WHERE CAST(NgayXuatHD AS DATE) BETWEEN @StartDate AND @EndDate
    GROUP BY LSPC.IdLoaiSPCha, LSPC.TenLoaiSPCha
	ORDER BY DoanhThu DESC
END
--	EXEC sp_GetSalesByCategory @StartDate = '2024-05-01', @EndDate = '2025-05-30';

GO
CREATE PROCEDURE sp_GetSalesByProduct
    @StartDate DATE,
    @EndDate DATE
AS
BEGIN
	IF @StartDate > @EndDate
    BEGIN
        PRINT 'Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc.'
        RETURN
    END
    SELECT 
        SP.IdSanPham,
        SP.TenSanPham,
        SUM(CT.SoLuong) AS SoLuongBan,
        SUM(CT.SoLuong * CT.DonGia) AS DoanhThu
    FROM HoaDon HD
    INNER JOIN ChiTietHoaDon CT ON HD.IdHoaDon = CT.IdHoaDon
    INNER JOIN KieuSanPham KSP ON CT.IdKieuSanPham = KSP.IdKieuSanPham
    INNER JOIN SanPham SP ON SP.IdSanPham = KSP.IdSanPham
    WHERE CAST(NgayXuatHD AS DATE) BETWEEN @StartDate AND @EndDate
    GROUP BY SP.IdSanPham, SP.TenSanPham
	ORDER BY DoanhThu DESC
END
--	EXEC sp_GetSalesByProduct @StartDate = '2024-05-01', @EndDate = '2025-05-30';




GO
CREATE PROCEDURE sp_ThongKeKhachHangTheoNgay
    @StartDate DATE,
    @EndDate DATE
AS
BEGIN
    IF @StartDate > @EndDate
    BEGIN
        PRINT 'Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc.'
        RETURN
    END

    SELECT 
        CAST(NgayThem AS DATE) AS NgayThem,
        COUNT(*) AS SoLuongKhachHang
    FROM 
        KhachHang
    WHERE 
        CAST(NgayThem AS DATE) BETWEEN @StartDate AND @EndDate
    GROUP BY 
        CAST(NgayThem AS DATE)
    ORDER BY 
        NgayThem DESC;
END

--	EXEC sp_ThongKeKhachHangTheoNgay @StartDate = '2024-05-01', @EndDate = '2025-05-30';
