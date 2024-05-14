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
INSERT INTO VaiTro VALUES (N'Admin');
INSERT INTO VaiTro VALUES (N'Quản lý');
INSERT INTO VaiTro VALUES (N'Nhân viên');

SELECT * FROM VaiTro


--====================================================================================================---


CREATE TABLE KhachHang
(
	IdKhachHang  BIGINT IDENTITY(1,1),
	TenKhachHang NVARCHAR(255) NOT NULL,
	GioiTinh NCHAR(5),
	SoDienThoai CHAR(10),
	Email VARCHAR(100),
	Diem BIGINT NOT NULL,
	CONSTRAINT PK_KhachHang PRIMARY KEY(IdKhachHang),
	CONSTRAINT UNI_SoDienThoai_KhachHang UNIQUE(SoDienThoai),
	CONSTRAINT UNI_Email_KhachHang UNIQUE(Email),
	CONSTRAINT CHK_GioiTinh_KH CHECK (GioiTinh=N'Nam' OR GioiTinh=N'Nữ'),
	CONSTRAINT CHK_Diem_KH CHECK (Diem >= 0),
);

INSERT INTO KhachHang VALUES (N'Vũ Văn Anh',N'Nam','0393123456','nhatthienhuonglogistics@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Trần Thái An',N'Nam','0393755621','thienhuonglogistics@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Lê Bích Thủy',N'Nữ','0979598491','customer3@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Trần Mỹ Huyền',N'Nữ','0979598492','vantaiduongviet@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Lê Ly Trang Nhi',N'Nữ','0393888888','taynambacsg@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Thị Thu',N'Nữ','0393888889','vantaivohongphat@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Phạm Hồng Thái',N'Nam','0393123457','customer7@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Văn Tú',N'Nam','0393123458','customer8@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Lê Thị Lan',N'Nữ','0393123459','customer9@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Minh Hiếu',N'Nam','0393123460','customer10@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Trần Văn Đức',N'Nam','0393123461','customer11@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Thị Mai',N'Nữ','0393123462','customer12@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Lê Thị Thu',N'Nữ','0393123463','customer13@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Phạm Văn Hải',N'Nam','0393123464','customer14@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Trần Thị Nhung',N'Nữ','0393123465','customer15@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Lê Văn Hưng',N'Nam','0393123466','customer16@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Thị Trinh',N'Nữ','0393123467','customer17@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Phạm Văn Thanh',N'Nam','0393123468','customer18@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Trần Thị Quỳnh',N'Nữ','0393123469','customer19@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Văn Lợi',N'Nam','0393123470','customer20@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Phạm Thị Ngọc',N'Nam','0393123471','customer21@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Trần Văn Nam',N'Nam','0393123472','customer22@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Thị Hồng',N'Nữ','0393123473','customer23@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Lê Văn Phúc',N'Nam','0393123474','customer24@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Thị Kim',N'Nữ','0393123475','customer25@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Thị Hương',N'Nữ','0393123476','customer26@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Trần Thị Phương',N'Nữ','0393123477','customer27@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Lê Thị Hải Yến',N'Nữ','0393123478','customer28@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Phạm Thị Trang',N'Nữ','0393123479','customer29@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Thị Linh',N'Nữ','0393123480','customer30@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Trần Thị Thuỳ Dung',N'Nữ','0393123481','customer31@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Lê Thị Thanh Hằng',N'Nữ','0393123482','customer32@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Thị Hà',N'Nữ','0393123483','customer33@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Trần Thị Hồng Loan',N'Nữ','0393123484','customer34@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Phạm Thị Ánh Ngọc',N'Nữ','0393123485','customer35@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Thị Thảo Vy',N'Nữ','0393123486','customer36@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Trần Thị Hồng Nhung',N'Nữ','0393123487','customer37@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Trần Thị Thu Trang',N'Nữ','0393123488','customer38@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Thị Diệu Linh',N'Nữ','0393123489','customer39@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Lê Thị Thanh Trúc',N'Nữ','0393123490','customer40@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Phạm Thị Lan Anh',N'Nữ','0393123491','customer41@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Trần Thị Bích Ngọc',N'Nữ','0393123492','customer42@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Nguyễn Thị Thanh Hương',N'Nữ','0393123493','customer43@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Lê Thị Hoài Thu',N'Nữ','0393163493','customer44@gmail.com',0)
INSERT INTO KhachHang VALUES (N'Phạm Thị Mỹ Linh',N'Nữ','0393123494','customer45@gmail.com',0)
SELECT *FROM KhachHang


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
INSERT INTO NhanVien VALUES('admin', 'p9rcbUNiWF+AC9dfPLx3Aw==:TH0x1hxXoAJ42iQtrE+gei32Xb4fC9vixX07MFQJ/o8=', 1, N'Vũ Văn Anh', '13/02/2003', N'Nam', N'Tây Thạnh, Tân Phú, Thành phố Hồ Chí Minh', '0393222222', 'fashionshop@gmail.com', 1, 0)
INSERT INTO NhanVien VALUES('quanly1', 'dsV+l826qrW5ODfeJSgtxw==:OUPGYu3wnSteta4RgLQAOFGs4WBjQU2giCyqH3voHJU=', 2, N'Hà Tri Thủy', '17/01/2003', N'Nam', N'Tân Sơn Nhì, Tân Phú, Thành phố Hồ Chí Minh', '0393222', 'hatrithuy@gmail.com', 1, 0)
INSERT INTO NhanVien VALUES('quanly2', 'kFvKb3+ozhMLMWfg+aNQMA==:ltF678huOnTlfii2HcrFXC/wwgeVtKC0B1ZfgKqfR6Q=', 2, N'Huỳnh Tuấn Khang', '07/04/2003', N'Nam', N'Phường 7, Quận 5, Thành phố Hồ Chí Minh, Việt Nam', '0393555222', 'khanghuynh@gmail.com', 1, 0)
INSERT INTO NhanVien VALUES('nhanvien1', 'v9aBB6yfqNJoSBi2Vf0uRg==:xwofWGdGirLjMlHJhqJJ/8q2c8ZbVt4ruAtJkNxOQiM=', 3, N'Đinh Tuyết Anh', '20/11/1990', N'Nữ', N'212-242 Đ. Độc Lập, Tân Thành, Tân Phú, Thành phố Hồ Chí Minh, Việt Nam', '0393666222', 'tuyeanhdinh20@gmail.com', 1, 0)
INSERT INTO NhanVien VALUES('nhanvien2', '40lBz13pZB7RdbogthjyJQ==:9hxAgrPvq9il9AOduQqex+v0X1BdXjjjH0UGbcY77Hc=', 3, N'Nguyễn Lê Huyền Trang', '14/03/1998', N'Nữ', N'Bình Hưng Hòa A, Bình Hưng Hoà A, Bình Tân, Thành phố Hồ Chí Minh, Việt Nam', '0393777222', 'trangnguyen14@gmail.com', 1, 0)
INSERT INTO NhanVien VALUES('nhanvien3', '62iKOrTvxvPi7UPL9EKOZA==:5G1rmikOL9UCl86oVtOL1At260kEoM2O4BlXAaJ1PIQ=', 3, N'Huỳnh Thái Cường', '21/06/2000', N'Nam', N'131 Đ. 26/3, Bình Hưng Hoà, Bình Tân, Thành phố Hồ Chí Minh, Việt Nam', '0393000222', 'cuonghuynh21@gmail.com', 1, 0)
--====================================================================================================---
CREATE TABLE LoaiSanPhamCha
(
	IdLoaiSPCha INT IDENTITY(1,1),
	TenLoaiSPCha NVARCHAR(255),
	CONSTRAINT PK_IdLoaiSPCha PRIMARY KEY(IdLoaiSPCha),
	CONSTRAINT UNI_TenLoaiSPCha UNIQUE(TenLoaiSPCha)
);
INSERT INTO LoaiSanPhamCha VALUES (N'Nam');
INSERT INTO LoaiSanPhamCha VALUES (N'Nữ');
INSERT INTO LoaiSanPhamCha VALUES (N'Trẻ em');
INSERT INTO LoaiSanPhamCha VALUES (N'Phụ Kiện');
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
INSERT INTO LoaiSanPham VALUES (N'Áo nam',1);
INSERT INTO LoaiSanPham VALUES (N'Quần nam',1);
INSERT INTO LoaiSanPham VALUES (N'Vest - Blazer',1);
INSERT INTO LoaiSanPham VALUES (N'Áo khoác nam',1);
INSERT INTO LoaiSanPham VALUES (N'Áo nữ',2);
INSERT INTO LoaiSanPham VALUES (N'Áo dài',2);
INSERT INTO LoaiSanPham VALUES (N'Áo khoác nữ',2);
INSERT INTO LoaiSanPham VALUES (N'Quần nữ',2);
INSERT INTO LoaiSanPham VALUES (N'Đầm',2);
INSERT INTO LoaiSanPham VALUES (N'Váy',2);
INSERT INTO LoaiSanPham VALUES (N'Chân váy',2);
INSERT INTO LoaiSanPham VALUES (N'Quần',3);
INSERT INTO LoaiSanPham VALUES (N'Áo',3);
INSERT INTO LoaiSanPham VALUES (N'Mắt kính',4);
INSERT INTO LoaiSanPham VALUES (N'Giày - Dép',4);
INSERT INTO LoaiSanPham VALUES (N'Mũ - Nón',4);
INSERT INTO LoaiSanPham VALUES (N'Vớ - Tất',4);
INSERT INTO LoaiSanPham VALUES (N'Túi - Ví',4);
INSERT INTO LoaiSanPham VALUES (N'Thắt lưng',4);
INSERT INTO LoaiSanPham VALUES (N'Đồng hồ',4);
SELECT * FROM LoaiSanPham

--====================================================================================================---
CREATE TABLE SanPham (
  IdSanPham BIGINT IDENTITY(1,1),
  TenSanPham NVARCHAR(255) NOT NULL,
  IdLoaiSP INT NOT NULL,
  GiaBan BIGINT,
  GiamGia INT,
  MoTa NVARCHAR(MAX),
  NgayThem Date,
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
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Đầm ngắn 2 dây', 9, 1319000, 20,N'Đầm ngắn 2 dây cup ngực sang trọng, gợi cảm\nTrang phục phù hợp dạo phố, thường ngày, đi tiệc...\nKích thước áo: S - M - L\nChiều dài: S : 60,5 cm - M: 62 cm - L : 63,5 cm','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Blazer nam trắng', 3, 2199000, 30,N'Áo Blazer nam phom Premio phù hợp mọi dáng người\nGam màu trung tính, lịch lãm tạo điểm nhấn nổi bật và thời thượng','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo 2 dây xếp li', 5, 359000, 15, N'Áo 2 dây xếp li thời trang, nữ tính\nTrang phục phù hợp dạo phố, thường ngày, đi tiệc...\nKích thước áo: S - M - L\nS : 38.7cm &#8226; M : 39.4cm &#8226; L : 40.1cm','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Sơ mi tay dài', 1,  1144000, 15, N'Áo sơ mi dài tay phom Regular fit có độ suông rộng vừa đủ\nThiết kế chỉn chu đến từng chi tiết với tà lượn, túi ngực.','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo blazer oversize', 7, 790000, 20, N'Áo blazer oversize chất liệu Tweed thời trang\nTrang phục phù hợp dạo phố, đi làm, đi tiệc....\nKích thước áo: S - M - L\nS: 67.5cm - M: 68.5cm - L: 69.5cm','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo polo nam', 1, 524000, 5, N'Áo polo chất liệu polyester pha cafe, cổ đức tay cộc, phom regular.','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Chân váy midi xẻ', 11,  749000, 25, N'Chân váy midi xẻ nữ tính\nTrang phục phù hợp dạo phố, thường ngày,...\nKích thước áo: S - M - L\nS: 62,5cm &#8226; M: 63,5cm &#8226; L: 64,5cm','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Air Jordan I High G', 15,  4100000, 28, N'Giày thể thao\nTrang phục phù hợp dạo phố, thường ngày,...\nKích thước giày: 36 &#8594; 46','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Váy lửng dáng xoè', 10,  599000, 20, N'Váy lửng dáng xoè nữ tính\nTrang phục phù hợp dạo phố, thường ngày,đi tiệc...\nKích thước áo: S - M - L\nS : 70 cm - M : 72 cm - L : 72 cm','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Mắt Kính Polygon', 14,  519000, 30, N'Mắt kính polygon Classic kim loại thời trang\nThiết kế phù hợp phối với nhiều trang phục thời trang đa dạng\nHộp kính tam giác da PU chống nước, nắp nam châm và kèm khăn lau kính','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Khoác blazer oversize sọc', 7,1689000, 30,  N'Khoác blazer oversized phối denim cá tính\nTrang phục phù hợp dạo phố, thường ngày,...\nKích thước áo: S - M - L\nS : 43,2 cm - M : 45,7cm - L : 48,2 cm','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Đầm nhung 2 dây midi', 9,  874000, 25,  N'Đầm nhung 2 dây midi thời trang, sang trọng\nTrang phục phù hợp dạo phố, thường ngày, đi tiệc...\nKích thước áo: S - M - L\nS : 88cm - M : 89,5cm - L : 91cm','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Đầm Thun Mini Tay Lở Dáng Xoè', 9,  588000, 10, N'Đầm thun mini tay lở dáng xoè thiết kế basic tôn dáng\nTrang phục phù hợp dạo phố, thường ngày, đi tiệc...\nKích thước áo: S - M - L\nS : 79cm - M : 81cm - L : 83cm','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Đầm Mini Tay Dài, Đắp Chéo Xếp Ly', 9, 682000, 25, N'Đầm Đầm mini tay dài, đắp chéo xếp ly thanh lịch, ôm eo tạo điểm nhấn\nTrang phục phù hợp dạo phố, thường ngày,đi tiệc, đi làm...','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai)VALUES(N'Set Quần Short Áo Sơ Mi Oversize Tay Phồng', 5, 784000, 25,  N'Áo sơ mi oversize tay phồng năng động, trẻ trung\nTrang phục phù hợp đi làm, thường ngày,...\nKích thước áo: F (Freesize)','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Đầm 2 dây dài buộc nơ vai', 9,  799000, 10,  N'Đầm 2 dây dài buộc nơ vai.\nTrẻ trung - Nữ tính.\nTrang phục dạo phố. Kiểu dáng: Đầm lửng\nChất liệu: twill\nMàu sắc: Đỏ\nKích cỡ: S-M-L)','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Đầm bút chì phối Organza', 9,  699000, 15,  N'Đầm bút chì phối Organza.\nThanh lịch - Hiện đại..\nTrang phục phù hợp dạo phố, đi tiệc,...','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo Thun BabyTee Tay Ngắn In Hình', 5, 399000, 30,  N'Áo thun BabyTee tay ngắn in hình trẻ trung, năng động.\nTrang phục phù hợp dạo phố, thường ngày, đi học...','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Đầm Mini Phối Ren Cúp Ngực Tay Dài', 9, 599000, 17,  N'Đầm mini phối ren cúp ngực tay dài thời trang, gợi cảm\nTrang phục phù hợp dạo phố, đi tiệc,...','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Đầm lửng bẹt vai nhún tà', 9, 699000, 15,  N'Đầm lửng bẹt vai nhún tà.\nTrẻ trung - Nữ tính.','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo Vest Blazer Nam Tay Dài Trơn', 3,  1400000, 8, N'Áo vest cũng mang đến một hình ảnh chỉnh chu, sang trọng và nam tính quyến rũ\nTrang phục phù hợp lễ cưới, sự kiện, tham gia tiệc, đi làm, hẹn hò,... ','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo Vest Blazer Nam Kẻ Sọc Dọc', 3, 1340000, 10, N'Áo vest cũng mang đến một hình ảnh chỉnh chu, sang trọng và nam tính quyến rũ\nTrang phục phù hợp môi trường làm việc công sở hoặc những buổi tiệc sang trọng,... ','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo sơ mi dài tay dây rút', 5,  599000, 10, N'Áo sơ mi dài tay dây rút  sành điệu, hiện đại\nTrang phục phù hợp dạo phố, thường ngày,đi chơi... ','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo Cropped Blazer', 5,  599000, 0,  N'Áo Cropped Blazer cá tính\nTrang phục phù hợp dạo phố, thường ngày,... Kiểu dáng: \nChất liệu: Cotton.\nMàu sắc: Đen-Nâu\nKích cỡ: S-M-L\nGiá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo Parka Hai Mặt', 1,  980000, 20, N'Hai phong cách trong một chiếc áo.\nÁo parka không thấm nước để bảo vệ chống lại các yếu tố thời tiết. Kiểu dáng: \nChất liệu: Mặt Trước: 100% Polyeste/ Mặt Sau: 100% Polyeste.\nMàu sắc: Đen-Nâu\nKích cỡ: L-XL-XXL\nGiá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Đồng hồ ORIENT Star 39.3 mm Nam', 20,  19460000, 30, N'Đến từ hãng đồng hồ Orient, thương hiệu Nhật Bản nổi tiếng với nhiều chiếc đồng hồ thời thượng\nĐồng hồ cơ tự động, bền bỉ, không cần dùng pin, lên dây cót bằng chuyển động của cổ tay\nVới đường kính mặt 39.3 mm, dành cho bạn nam có cổ tay với chu vi từ 18 - 19 cm\nChiếc đồng hồ nam có dây đeo và khung viền được làm từ thép không gỉ bền bỉ, chống ăn mòn và oxi hóa tốt\nHệ số chống nước 10 ATM, người dùng yên tâm đeo đồng hồ khi bơi lội, tắm rửa, đi mưa, rửa tay. Không nên đeo đồng hồ khi đi lặn Đường kính mặt: 39.3 mm\nDây đeo Khung viền: Thép không gỉ\nLoại máy: Cơ tự động\nGiá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Đồng hồ CITIZEN Mechanical 42 mm Nam', 20, 11185000, 20, N'Mẫu đồng hồ đến từ thương hiệu Citizen - một trong những thương hiệu nổi tiếng và uy tín đến từ Nhật Bản\nĐồng hồ CITIZEN Mechanical 42 mm Nam NJ0080-50E sở hữu đường kính mặt 42 mm và độ rộng dây 18 mm.\nĐồng hồ cơ tự động, bền bỉ, không cần dùng pin, lên dây cót bằng chuyển động của cổ tay\n5 ATM là độ kháng nước của chiếc đồng hồ nam này, người dùng có thể an toàn đeo khi đi tắm, đi mưa. Lưu ý: không nên mang khi đi bơi, lặn. Đường kính mặt: 42 mm\nChất liệu mặt kính: Kính Sapphire\nDây đeo Khung viền: Thép không gỉ\nLoại máy: Cơ tự động\nGiá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo Sơ Mi Nam Oxford Tay Dài', 1, 569000, 6,  N'Thiết kế đặc biệt với phần cổ trụ, áo vẫn giữ nguyên được nét trang nhã và tối giản nhưng không mang lại cảm giác tẻ nhạt.\nForm dáng ôm vừa vặn, phần thân và tay áo suông, không ôm sẽ mang đến cảm giác rộng rãi, dễ chịu cho người mặc vào những ngày hè oi bức. Kiểu dáng: Fitted\nChất liệu: Cotton.\nMàu sắc: Đen-Trắng\nKích cỡ: L-XL-XXL\nGiá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo kiểu bẹt vai tay phồng', 5, 499000, 10, N'Miêu tả: ÁO BẸT VAI TAY PHỒNG.\nĐặc tính: Trẻ trung - Nữ tính - Gợi cảm.\nThể loại: Trang phục dạo phố, tiệc. Kiểu dáng: \nChất liệu: Poly xốp tổng hợp.\nMàu sắc: Xanh - Cam - Đen - Kem.\nGiá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo cúp ngang bẹt vai', 5, 499000, 10,  N'Miêu tả: ÁO CÚP NGANG BẸT VAI.\nĐặc tính: Hiện đại - Nữ tính.\nThể loại: Trang phục tiệc, dạo phố. Kiểu dáng: \nChất liệu: Vải poly tổng hợp.\nMàu sắc: Đen - Kem.\nGiá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo Kiểu Dây Yếm Cổ Tay Phồng', 5, 699000, 10, N'Miêu tả: ÁO KIỂU DÂY YẾM CỔ TAY PHỒNG.\nĐặc tính: Nữ tính - Cá tính.\nThể loại: Trang phục dạo phố.Kiểu dáng: \nChất liệu: Cotton gân.\nMàu sắc: Xanh - Kem - Đen.\nGiá đã bao gồm VAT)','05/05/2023', 1);
INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES(N'Áo kiểu tay lỡ cổ gấp nếp', 5, 699000, 15, N'Áo kiểu tay lỡ cổ gấp nếp nữ tính, sang trọng\nTrang phục phù hợp dạo phố, thường ngày,đi làm...Kiểu dáng: \nChất liệu: cotton.\nMàu sắc: Đen - Trắng - Kem.\nGiá đã bao gồm VAT)','05/05/2023', 1);
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
  CONSTRAINT UNI_KieuSanPham UNIQUE(IdSanPham, Mau),
  CONSTRAINT FK_KieuSanPham_SanPham FOREIGN KEY (IdSanPham) REFERENCES SanPham(IdSanPham),
);
INSERT INTO KieuSanPham VALUES(600001,1,N'trắng', 'product_img1.jpg', 'M|L',100);
INSERT INTO KieuSanPham VALUES(600002,1,N'đen', 'product_img1_den.jpg', 'M|L',100);
INSERT INTO KieuSanPham VALUES(600003,2,N'trắng', 'product_img2.jpg','S|M|L|XL|XXL',100);
INSERT INTO KieuSanPham VALUES(600004,2,N'xanh tím than', 'product_img2_xanhtimthan.jpg','S|M|L|XL|XXL',100);
INSERT INTO KieuSanPham VALUES(600005,3,N'kem','product_img3.jpg|', 'S|M|L',100);
INSERT INTO KieuSanPham VALUES(600006,3,N'đỏ','product_img3_do.jpg', 'S|M|L',100);
INSERT INTO KieuSanPham VALUES(600007,4,N'đen', 'product_img4.jpg', '38|39|40|41|42|43',100);   
INSERT INTO KieuSanPham VALUES(600008,5,N'be', 'product_img5.jpg', 'M|L',100);
INSERT INTO KieuSanPham VALUES(600009,5,N'đen', 'product_img5_den.jpg', 'M|L',100);
INSERT INTO KieuSanPham VALUES(600010,6,N'SB194', 'product_img6.jpg', 'S|L|XL',100);
INSERT INTO KieuSanPham VALUES(600011,7,N'be', 'product_img7.jpg', 'S|M|L',100);
INSERT INTO KieuSanPham VALUES(600012,7,N'nau','product_img7_nau.jpg','S|M|L',100);
INSERT INTO KieuSanPham VALUES(600013,7,N'den', 'product_img7_den.jpg', 'S|M|L',100);
INSERT INTO KieuSanPham VALUES(600014,8,N'green', 'product_img8.jpg', '36|37|38|39|40|41|42|43|44|45|46',100);
INSERT INTO KieuSanPham VALUES(600015,9,N'trangxam','product_img9.jpg', 'M',100);
INSERT INTO KieuSanPham VALUES(600016,10,N'greyblue','product_img10.jpg',' ',100);
INSERT INTO KieuSanPham VALUES(600017,10,N'den','product_img10_den.jpg',' ',100);
INSERT INTO KieuSanPham VALUES(600018,10,N'nau', 'product_img10_nau.jpg',' ',100);
INSERT INTO KieuSanPham VALUES(600019,11,N'Caro nâu', 'product_img11.jpg', 'S|M|L',100);
INSERT INTO KieuSanPham VALUES(600020,11,N'Caro đen', 'product_img11_den.jpg', 'S|M|L',100);
INSERT INTO KieuSanPham VALUES(600021,12,N'đen','product_img12.jpg', 'S|M|L',100);
INSERT INTO KieuSanPham VALUES(600022,13,N'xanh', 'product_img13.jpg', 'S|M|L',100);
INSERT INTO KieuSanPham VALUES(600023,13,N'đen', 'product_img13_den.jpg','S|M|L',100);
INSERT INTO KieuSanPham VALUES(600024,14,N'tim','product_img14.jpg', 'S|M|L',100);
SELECT * FROM KieuSanPham
--====================================================================================================---
CREATE TABLE HoaDonNhapKho
(
    IdHoaDonNhapKho BIGINT IDENTITY(1,1),
    NgayNhap DATETIME NOT NULL,
    IdNhanVien BIGINT NOT NULL,
	  CONSTRAINT PK_HoaDonNhapKho PRIMARY KEY(IdHoaDonNhapKho),
    CONSTRAINT FK_HoaDonNhapKho_NhanVien FOREIGN KEY(IdNhanVien) REFERENCES NhanVien(IdNhanVien)
);

--====================================================================================================---

CREATE TABLE ChiTietHoaDonNhapKho (
  IdHoaDonNhapKho BIGINT NOT NULL,
  IdKieuSanPham BIGINT NOT NULL,
  SoLuong INT,
  CONSTRAINT PK_ChiTietHoaDonNhapKho PRIMARY KEY(IdHoaDonNhapKho, IdKieuSanPham),
  CONSTRAINT FK_ChiTietHoaDonNhapKho_HoaDonNhapKho FOREIGN KEY(IdHoaDonNhapKho) REFERENCES HoaDonNhapKho(IdHoaDonNhapKho),
  CONSTRAINT FK_ChiTietHoaDonNhapKho_KieuSanPham FOREIGN KEY(IdKieuSanPham) REFERENCES KieuSanPham(IdKieuSanPham),
  CONSTRAINT CHK_SoLuong_CTHoaDon CHECK (SoLuong>=0)
);

--====================================================================================================---

CREATE TABLE HoaDon (
  IdHoaDon BIGINT IDENTITY(1,1),
  IdNhanVien BIGINT NOT NULL,
  IdKhachHang  BIGINT NOT NULL, 
  SoDienThoai VARCHAR(10),
  PhuongThucThanhToan NVARCHAR(30) NOT NULL,
  NgayXuatHD DATETIME NOT NULL,
  CONSTRAINT PK_HoaDon PRIMARY KEY(IdHoaDon),
  CONSTRAINT FK_HoaDon_NhanVien FOREIGN KEY(IdNhanVien) REFERENCES NhanVien(IdNhanVien),
  CONSTRAINT FK_HoaDon_KhachHang FOREIGN KEY(IdKhachHang) REFERENCES KhachHang(IdKhachHang)
);


set dateformat dmy
INSERT INTO HoaDon VALUES (1,1,'0393123456',N'Momo','21/01/2023');
INSERT INTO HoaDon VALUES (2,2,'0979541478',N'Momo','20/07/2023');
SELECT * FROM HoaDon

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


