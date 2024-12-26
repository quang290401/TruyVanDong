package vn.com.t3h.mapper;

import jakarta.persistence.ManyToOne;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.com.t3h.dto.ClaimDTO;
import vn.com.t3h.entity.ClaimEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClaimMapper {

    @Mapping(source = "code",target = "claimCode")
    @Mapping(source = "customerEntity.name",target = "customerName")
    @Mapping(source = "insuranceProductEntity.name",target = "insuranceProductName")
    @Mapping(source = "claimStatusEntity.code",target = "status")
    @Mapping(source = "claimStatusEntity.description",target = "statusDescription")
    ClaimDTO toDto(ClaimEntity claimEntity);

    List<ClaimDTO> toDtos(List<ClaimEntity> claimEntities);
}
