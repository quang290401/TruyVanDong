package vn.com.t3h.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

// Đánh dấu đây là class cha cho tất cả các entity kế thừa
@MappedSuperclass
@Data // tự tạo getter, setter
public class BaseEntity {

    @Id // xác định đây là khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // config khóa chính tự động tăng
    public Long id;

    @Column(name = "created_date") // chỉ định thuộc tính này của
    // java sẽ mapping với cột created_date trong table ứng với entity
    private LocalDateTime createdDate;

    // khi không đánh dấu @Column thì tự động JPA sẽ hiểu thuộc tính
    // java này createdBy đang mapping với cột created_by trong table
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;
    private Boolean deleted = false;
}
