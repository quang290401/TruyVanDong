package vn.com.t3h.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerEntity extends BaseEntity {

    private String address;
    private String bankName;
    private String bankNumber;
    private String email;
    private String name;
    private String phoneNumber;
}
