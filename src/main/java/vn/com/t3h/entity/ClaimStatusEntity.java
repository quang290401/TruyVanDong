package vn.com.t3h.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "claim_status")
@Getter
@Setter
public class ClaimStatusEntity extends BaseEntity{
    private String code;
    private String description;

}
