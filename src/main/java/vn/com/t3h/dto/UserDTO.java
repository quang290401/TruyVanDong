package vn.com.t3h.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 "id": 1,
 "username": "admin",
 "password": "$2a$10$f6p5TLa9sDZzuTzRgnQAFewEMqU843QaSQqjjy56Fmk.UjgNkqTs2",
 "code": "A0001",
 "email": "admin@gmail.com",
 "firstName": "Nguyen",
 "lastName": "Admin",
 "phone": "0925334772",
 "address": "HaNoi",
 "roles": [
 {
 "code": "ADMIN",
 "name": "Quản trị viên"
 }
 ],
 "createdDate": null,
 "createdBy": null,
 "lastModifiedDate": null,
 "lastModifiedBy": "2024-07-27 12:25:58.000000 +00:00",
 "deleted": false
 */
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String username;
    private String code;
    private String email;
    private String phone;
    private String address;
    private String firstName;
    private String lastName;

    private LocalDate createdDate;
    private String createdBy;
    private LocalDate lastModifiedDate;
    private String lastModifiedBy;
    private Boolean deleted;
    private String avatar;
    private String roleName;

}
