Package entity: 
là package chứa các class được ánh xạ lại giống với các table trong database 
để đóng vai trò chứa dữ liệu khi query dữ liệu ra từ trong database 

1) Thêm các thư viện vào file pom.xml
để có thể kết nối được tới mysql và sử dụng các
annotation của JPA 
   <dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>
   <dependency>
   <groupId>com.mysql</groupId>
   <artifactId>mysql-connector-j</artifactId>
   <scope>runtime</scope>
   </dependency>
2) Tạo các class Entity để ứng với các table trong
database 
* bảng users và roles được mapping qua 
UserEntity và RoleEntity trên Java 
mối quan hệ: nhiều - nhiều : n - n => tạo bảng phụ user_roles
1 user có nhiều quyền
  - Tạo UserEntity và cấu hình ManyToMany 
1 quyền cũng dành cho nhiều user
  - Tạo Role Entity và cấu hình ManyToMany

* Bảng insurance_product
   - tạo entity InsuranceProductEntity 
   - Kế thừa class BasicEntity để có khóa chính 
      và các thuộc tính dùng chung 
   - Tạo các thuộc tính ứng với các cột trong databas e

* Tạo bảng claims
   - tạo entity ClaimEntity
   - Kế thừa class BasicEntity để có khóa chính
       và các thuộc tính dùng chung
   - Tạo các thuộc tính ứng với các cột trong databas e
   - các mối quan hệ của bảng claim với các bảng khác 
     +  insurance_product-claim (1-n)

* Bài tập về nhà 
  - Dựa vào file diagram insuranceclaim.png tạo ra database mới và tạo ra các entity ứng với 
  các table để khi run project spring boot sẽ có thể tự động tạo ra các table và mối quan hệ 
  y hệt hình insuranceclaim.png, ví dụ dựa và các entity demo đã có 
  - tóm lại nhiệm vụ:
    1) tạo ra database mới 
    2) tạo ra các entity ứng với mô hình các table trong ảnh insuranceclaim.png với các mối quan 
    hệ y hệt, tham khảo các entity đã có 