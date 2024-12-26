package vn.com.t3h.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InsuranceProductDTO {

    private Long id;
    private String name;
    private String description;
    private String coverage;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;
    private Boolean deleted = false;
}
