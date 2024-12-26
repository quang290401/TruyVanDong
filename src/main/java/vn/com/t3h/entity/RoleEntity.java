package vn.com.t3h.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class RoleEntity extends BaseEntity {

    private String name;
    private String code;
    // Đánh dấu thuộc tính userEntities sẽ mapping với UserEntity
    // với mối quan hệ many to many thông qua thuộc tính roleEntities của UserEntity
    @ManyToMany(mappedBy = "roleEntities")
    private Set<UserEntity> userEntities = new HashSet<>();
}
