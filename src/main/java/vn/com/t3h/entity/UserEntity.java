package vn.com.t3h.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

// Đánh dấu class này là một entity ứng với một table trong database
@Entity
@Table(name = "users") // chỉ định UserEntity ứng với table users trong db
@Getter
@Setter
public class UserEntity extends BaseEntity{

    /**
     Khai báo các thuộc tính ứng với các cột trong bảng
     users
     */
    private String username;
    private String password;
    private String code;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    /**
     config fetch:
        FetchType.EAGER: khi query user trong database sẽ lấy ra hết
            danh sách quyền thuộc về user đấy và đưa vào thuộc tính roles
        FetchType.LAZY: khi query user trong database sẽ không lấy ra
            ngay danh sách quyền và đưa vào roles, mà sẽ chỉ lấy ra và
            đưa vào khi gọi tới hàm getRoles()
     cascade = CascadeType.ALL: khi tạo ra hoặc xóa đi một user
            -> sẽ có thể tạo ra hoặc xóa đi một role đính kèm
     */
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable( // tự động tạo ra bảng phụ user_roles
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"), /* Cấu hình để thuộc tính user_id trong bảng phụ user_roles
                                                           sẽ là khóa phụ tham chiếu tới cột id trong bảng users*/
            inverseJoinColumns = @JoinColumn(name = "role_id")/* Cấu hình để thuộc tính role_id trong bảng phụ user_roles
                                                           sẽ là khóa phụ tham chiếu tới cột id trong bảng roles*/
    )
    // 1 user có nhiều quyền
    private Set<RoleEntity> roleEntities = new HashSet<>();
}
