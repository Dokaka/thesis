# ThesisProject
Project mô phỏng app bán giày

## Contents
- [Giới thiệu](#Giới-thiệu)
- [Thành viên](#Thành-viên)
- [Tính năng](#Tính-năng)
- [Cấu trúc Database](#Cấu-trúc-Database)
- [Công cụ](#Công-cụ)
- [Kiến trúc](#Kiến-trúc)
- [Cài đặt](#Cài-đặt)
- [API Documentation](#API-Documentation)


## Giới thiệu
Server API của project mô phỏng app bán giày online. Chức năng của app bao gồm:
- Xem thông tin sản phẩm.
- Mua sản phẩm giày.

Hiện tại, app chỉ mô phỏng chức năng cho người tiêu dùng.

## Thành viên
Thành viên của project bao gồm:
- Phan Doãn Đô: Phụ trách Backend, bao gồm thiết kế database, thiết kế các REST API để liên kết database với bên FrontEnd.
- Nguyễn Văn Liệu: Phụ trách Frontend, xây dựng application trên mobile android.

Các công việc khác như làm documentation, tạo mockup data, viết test, làm presentation sẽ được thực hiện đồng thời từ cả hai phía.

## Tính Năng
User story của người dùng.

<img align="center|left" src="https://i.imgur.com/LxBsygQ.png">

## Cấu trúc Database
Cấu trúc các bảng trong database của project.

<img align="center|left" src="https://i.imgur.com/M8AlOKG.png">

## Công cụ , thư viện
Cấu trúc và các công cụ được sử dụng của project:
- Version control: git
- Database: mysql, phpmyadmin
- BackEnd: Java
  - IDE : IntelliJ
  - Swagger: API documentation
  - JWT : Authentication mechanism for REST APIs
- Testing: Postman: https://www.getpostman.com/
- FrontEnd:
- Api documentation: https://anderadored.herokuapp.com/swagger-ui.html

## Kiến trúc
Repositories này chứa phần BackEnd của project bao gồm các Rest API, swagger documentation:

main:
- API declaration
- Database initialization
- Set listening port (default 8080) 

controller/
- API endpoint ứng với mỗi api.

service/
- API definition, mỗi file tương ứng với một chức năng chính của app

repository/
- tương tác với database

dto/
- Chứa các struct model cần thiết và gửi JSON về FrontEnd

entity/
- Chứa struct model của các bảng trong database

request/
- Chứa struct model nhận từ front-end

response/
- Chứa struct model trả về front-end

security/
- gen token, authentication API.


## Cài đặt
1.Từ thư mục làm việc kéo repositories về
```sh
$ git clone https://github.com/Dokaka/thesis.git
```

2. Chạy ứng dụng
```sh
run IntelliJ, open shoes_shopping project
```

## API Documentation
Swagger link: https://anderadored.herokuapp.com/swagger-ui.html
