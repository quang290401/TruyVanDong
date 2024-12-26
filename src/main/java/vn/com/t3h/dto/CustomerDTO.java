package vn.com.t3h.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerDTO {

    private Long id;
    private String address;
    private String bankName;
    private String bankNumber;
    private String email;
    private String name;
    private String phoneNumber;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;
    private Boolean deleted = false;
}
