CREATE DATABASE FINALASS_GROUP6_PRO10411
USE FINALASS_GROUP6_PRO10411
GO


-- Voucher 
CREATE TABLE Voucher(
Id INT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
NgayApDung DATE DEFAULT NULL,
NgayKetThuc DATE DEFAULT NULL,
PhanTram float default 0,
)
GO


-- SanPham
CREATE TABLE SanPham(
Id INT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
Create_at DATE DEFAULT NULL,
Update_at DATE DEFAULT NULL,
Deleted bit default 0,
)
GO
-- NSX
CREATE TABLE NSX(
Id INT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
QuocGia NVARCHAR(30)
)
GO
-- Anh
CREATE TABLE Anh(
Id INT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
Cover bit DEFAULT 0
)
GO

-- ThanhPhan
CREATE TABLE ThanhPhan(
Id INT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30)
)
GO
-- LoaiSP
CREATE TABLE LoaiSP(
Id INT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
IdTP int
)
GO
-- ChucVu
CREATE TABLE ChucVu(
Id INT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30)
)
GO
-- TaiKhoan
CREATE TABLE TaiKhoan(
Id INT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE,
HoTen NVARCHAR(50) DEFAULT NULL,
GioiTinh NVARCHAR(10) DEFAULT NULL,
NgaySinh DATE DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
MatKhau VARCHAR(MAX) DEFAULT NULL,
email varchar(Max) DEFAULT null,
IdCV INT,
Status bit DEFAULT 0,
Create_at DATE DEFAULT NULL,
Update_at DATE DEFAULT NULL,
Deleted bit default 0

)
GO  

--HoaDon
CREATE TABLE HoaDon(
Id INT IDENTITY(1,1) PRIMARY KEY,
IdTK INT,
IdVC INT,
TrangThai INT  DEFAULT 0,
Ma VARCHAR(20) UNIQUE,
MaKH VARCHAR(20) UNIQUE,
HoTen NVARCHAR(50) DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
TongTien DECIMAL(20,0) DEFAULT 0,
Create_at DATE DEFAULT NULL,
Update_at DATE DEFAULT NULL,
Deleted bit default 0

)
GO

-- ChiTietSP
CREATE TABLE ChiTietSP(
Id INT IDENTITY(1,1) PRIMARY KEY,
IdSP INT,
IdNsx INT,
IdAnh INT,
IdLoaiSP INT,
HSD DATE DEFAULT NULL,
SoLuongTon INT,
DonGia DECIMAL(20,0) DEFAULT 0

)

GO
-- HoaDonChiTiet
CREATE TABLE HoaDonChiTiet(
IdHoaDon int,
IdChiTietSP int,
Ma VARCHAR(20) ,
Ten NVARCHAR(30),
SoLuong INT,
DonGia DECIMAL(20,0) DEFAULT 0,
TongTienSP DECIMAL(20,0) DEFAULT 0,
CONSTRAINT PK_HoaDonCT PRIMARY KEY (IdHoaDon,IdChiTietSP),
CONSTRAINT FK1 FOREIGN KEY(IdHoaDon) REFERENCES HoaDon(ID),
CONSTRAINT FK2 FOREIGN KEY(IdChiTietSP) REFERENCES ChiTietSP(Id),
)
GO


--T?O QUAN H? GI?A CÁC B?NG
--TaiKhoan - ChucVu
ALTER TABLE TaiKhoan ADD  FOREIGN KEY (IdCV) REFERENCES ChucVu(Id)

-- HoaDon - NhanVien
ALTER TABLE HoaDon  ADD FOREIGN KEY (IdTK) REFERENCES TaiKhoan(Id)
-- HoaDon - Voucher 
ALTER TABLE HoaDon  ADD  FOREIGN KEY (IdVC) REFERENCES Voucher(Id)

-- LoaiSP - ThanhPhan
ALTER TABLE LoaiSP  ADD FOREIGN KEY (IdTP) REFERENCES ThanhPhan(Id)

-- ChiTietSP - SanPham
ALTER TABLE ChiTietSP  ADD  FOREIGN KEY(IdSP) REFERENCES SanPham(Id)
-- ChiTietSP - NSX
ALTER TABLE ChiTietSP  ADD FOREIGN KEY(IdNsx) REFERENCES Nsx(Id)
-- ChiTietSP - Anh
ALTER TABLE ChiTietSP  ADD  FOREIGN KEY(IdAnh) REFERENCES Anh(Id)
-- ChiTietSP - LoaiSP
ALTER TABLE ChiTietSP  ADD FOREIGN KEY(IdLoaiSP) REFERENCES LoaiSP(Id)

CREATE TABLE KhoiLuong(
Id INT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),

)

GO
CREATE TABLE DonViTinh(
Id INT IDENTITY(1,1) PRIMARY KEY,
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
)
GO

alter table chiTietSP add created_at date 
alter table chiTietSP add idDV int 
alter table chiTietSP add  Create_at DATE DEFAULT NULL
alter table chiTietSP add Update_at DATE DEFAULT NULL
alter table chiTietSP add Deleted bit default 0

alter table chitietSP drop column idAnh


select * from ChiTietSP
select * from ctsp_khuyenmai
select * from SanPham
select * from khuyenmai
select * from DonViTinh


select * from HoaDonChiTiet

alter table HoaDonChiTiet add giaBan numeric

delete khuyenmai
 delete ctsp_khuyenmai
  delete SanPham where id between 413 and 10000 
 delete ChiTietSP where id =  500 between 415 and 10000 

 update HoaDonChiTiet set giaBan = DonGia

 update chiTietSP set IdLoaiSP = 1 where id = 437
 -- ChiTietSP - Anh
ALTER TABLE ChiTietSP  ADD  FOREIGN KEY(idKL) REFERENCES KhoiLuong(Id)
-- ChiTietSP - LoaiSP
ALTER TABLE ChiTietSP  ADD FOREIGN KEY(IdDV) REFERENCES DonViTinh(Id)

select * from don


select * from SanPham
insert into SanPham(Ma,Ten,Create_at) values 
('SP001','CanxiPro',CONVERT (date, GETDATE())),
('SP002','Abbott Grow',CONVERT (date, GETDATE())),
('SP003','Anmum vani',CONVERT (date, GETDATE())),
('SP004','EnPlus',CONVERT (date, GETDATE()))
go


insert into Anh(Ma,Ten,Cover) values('A001','Canxipro_900g',1)
insert into Anh(Ma,Ten,Cover) values('A002','AB_Grow_2_900g',1)
insert into Anh(Ma,Ten,Cover) values('A003','Anmum_vani_800g',1)
insert into Anh(Ma,Ten,Cover) values('A004','EnPlus_900g',1)
go

insert into NSX(Ma,Ten,QuocGia) values
('NSX001','VINAMILK',N'Vi?t Nam'),
('NSX002','Abbott',N'Hoa K?'),
('NSX003','Anmum ','New Zealand'),
('NSX004','NutiFood ',N'Vi?t Nam')
go



insert into ThanhPhan(Ma,Ten) values
('TP001','Ch?t ??m'),
('TP002','Canxi'),
('TP003','Vitamin')
go
select * from ThanhPhan

insert into LoaiSP(Ma,Ten,IdTP) values
('LSP001',N'S?a cho tr? em',1),
('LSP002',N'S?a cho ng??i l?n',2),
('LSP003',N'S?a cho ng??i già',3)



go 

select hd.Create_at as ngay,count(hd.Ma) as hoadon,SUM(TongTien) as tongtien,count(SoLuong) as SoluongSp from hoadon hd 
              join HoaDonChiTiet hdct on hd.Id = hdct.IdHoaDon 
                join ChiTietSP ctsp on hdct.IdChiTietSP = ctsp.Id 
              
                group by hd.Create_at



 insert into ChiTietSP (IdAnh,IdLoaiSP,IdNsx,IdSP,HSD,SoLuongTon,DonGia) values
 (1,1,1,1,'2023-11-20',20,350000),
 (2,2,2,2,'2023-11-20',30,360000),
 (3,3,3,3,'2022-11-20',24,350000)



select * from TaiKhoan
insert into ChucVu(Ma,Ten) values ('CV1','NhanVien1')
insert into TaiKhoan(Ma,HoTen,GioiTinh,NgaySinh,DiaChi,Sdt,email,MatKhau,IdCV) 
values('nv01','hiep','nam','01/10/2000','ha noi','09273313','hiepbhph27531@gmail.com','1',1)
select * from HoaDon
insert into HoaDon(IdTK,Ma,MaKH,HoTen,Sdt)
values(1,'hd01','kh01','huy','018383723')

insert into HoaDonChiTiet values(1,2,'hd01','anh',1,1,1111)
select * from HoaDonChiTiet
select * from HoaDon

delete from HoaDonChiTiet
delete TaiKhoan where id =20


select * from 