package vn.com.t3h.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "claim")
@Getter
@Setter
public class ClaimEntity extends BaseEntity {

    private Double amount;
    private LocalDate claimDate;
    private String description;
    private String code;

    @ManyToOne// xác định mối quan hệ 1-n : insurance_product-claim (1-n)
    @JoinColumn(name = "product_id") // tạo ra cột product_id trong bảng claim và làm khóa ngoại tham chiếu tới bảng insurance_product
    private InsuranceProductEntity insuranceProductEntity;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ClaimStatusEntity claimStatusEntity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;


}
