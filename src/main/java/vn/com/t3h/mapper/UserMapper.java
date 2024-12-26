package vn.com.t3h.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.com.t3h.dto.ClaimDTO;
import vn.com.t3h.dto.UserDTO;
import vn.com.t3h.entity.ClaimEntity;
import vn.com.t3h.entity.UserEntity;
import vn.com.t3h.mapper.decorator.UserMapperDecorator;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(UserMapperDecorator.class) // cấu hình file decorator với mapper, được sử dụng
// để xử lý các logic mapping phức tạp từ entity -> dto
public interface UserMapper {

    @Mapping(ignore = true,target = "roleName") //ignore = true bỏ qua không tự động chuyển trường đó từ entity sang dto
    @Mapping(ignore = true,target = "avatar")
    UserDTO entityToDto(UserEntity userEntity);

    @Mapping(ignore = true,target = "createdDate")
    @Mapping(ignore = true,target = "createdBy")
    @Mapping(ignore = true,target = "lastModifiedDate")
    @Mapping(ignore = true,target = "lastModifiedBy")
    @Mapping(ignore = true,target = "id")
    UserEntity dtoToEntity(UserDTO userDTO);
    List<UserDTO> toDtos(List<UserEntity> userEntities);

}
