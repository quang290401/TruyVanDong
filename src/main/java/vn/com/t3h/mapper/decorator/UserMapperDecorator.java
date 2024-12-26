package vn.com.t3h.mapper.decorator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import vn.com.t3h.dto.UserDTO;
import vn.com.t3h.entity.RoleEntity;
import vn.com.t3h.entity.UserEntity;
import vn.com.t3h.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapperDecorator implements UserMapper {

    @Autowired
    @Qualifier("delegate")
    private UserMapper delegate;

    @Override
    public UserDTO entityToDto(UserEntity userEntity) {
        UserDTO userDTO = delegate.entityToDto(userEntity);
        Set<RoleEntity> roleEntities = userEntity.getRoleEntities();
        // ghép tất cả role trong danh sách thành String
        String roleName = roleEntities.stream().map(RoleEntity::getName).collect(Collectors.joining(","));
        userDTO.setRoleName(roleName);
        return userDTO;
    }

    @Override
    public UserEntity dtoToEntity(UserDTO userDTO) {
        UserEntity userEntity = delegate.dtoToEntity(userDTO);
        LocalDateTime now = LocalDateTime.now();
        userEntity.setLastModifiedDate(now);
        return userEntity;
    }

    @Override
    public List<UserDTO> toDtos(List<UserEntity> userEntities) {
        if (userEntities == null || userEntities.isEmpty()) {
            return null;
        }
        // Sử dụng delegate để chuyển đổi từng phần tử của danh sách
        return userEntities.stream()
                .map(delegate::entityToDto) // Map từng phần tử từ entity sang DTO
                .collect(Collectors.toList()); // Thu thập thành danh sách
    }
}
