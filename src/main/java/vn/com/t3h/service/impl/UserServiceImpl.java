package vn.com.t3h.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.com.t3h.dto.ClaimDTO;
import vn.com.t3h.dto.UserDTO;
import vn.com.t3h.dto.request.UserResqestFiter;
import vn.com.t3h.dto.response.ResponsePage;
import vn.com.t3h.entity.ClaimEntity;
import vn.com.t3h.entity.UserEntity;
import vn.com.t3h.mapper.UserMapper;
import vn.com.t3h.repository.UserRepository;
import vn.com.t3h.service.IUserService;
import vn.com.t3h.utils.DateUtils;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 Được sử dụng để triển khai code xử lý các hàm được khai báo tại interface IUserService
 */
@Service// đánh dấu đây là tầng service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UserDTO> getAllUser() {
        // query lấy danh sách user từ database sử dụng repository
        List<UserEntity> userEntities = userRepository.findAll();
        // chuyển dữ liệu từ dang entity sang dạng DTO để trả về controller
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        for (UserEntity userEntity : userEntities) {
            UserDTO userDTO = new UserDTO();
            // Set các giá trị từ UserEntity sang UserDTO
            userDTO.setId(userEntity.getId());
            userDTO.setUsername(userEntity.getUsername());
            userDTO.setCode(userEntity.getCode());
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setFirstName(userEntity.getFirstName());
            userDTO.setLastName(userEntity.getLastName());
            userDTO.setPhone(userEntity.getPhone());
            userDTO.setAddress(userEntity.getAddress());
            // Các trường từ BaseEntity sang UserDTO
            if (userEntity.getCreatedDate() != null){
                userDTO.setCreatedDate(userEntity.getCreatedDate().toLocalDate());
            }
            userDTO.setCreatedBy(userEntity.getCreatedBy());
            if (userEntity.getLastModifiedDate() != null){
                userDTO.setLastModifiedDate(userEntity.getLastModifiedDate().toLocalDate());
            }
            userDTO.setLastModifiedBy(userEntity.getLastModifiedBy());
            userDTO.setDeleted(userEntity.getDeleted());
            userDTOs.add(userDTO);
        }
        // trả về danh sách user cho controller
        return userDTOs;
    }

    @Override
    public UserDTO findById(Long id) {
        if (id == null) {
            id = 1l;
        }
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()){
            throw  new RuntimeException("user not found");
        }

        UserDTO userDTO = userMapper.entityToDto(userEntity.get());

        return userDTO;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        // tìm kiếm user theo id để thực hiện update
        UserEntity userEntity = userRepository.findById(userDTO.getId()).orElse(null);
        if (userEntity == null) {
            throw  new RuntimeException("user not found");
        }
        // set các giá trị cho phép update thông tin profile
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setLastModifiedDate(LocalDateTime.now());
        // lưu dữ liệu được thay đổi vào database
        userEntity = userRepository.save(userEntity);

        UserDTO userResponse = userMapper.entityToDto(userEntity);
        return userResponse;
    }

    @Override
    public ResponsePage<List<UserDTO>> getAllUser(UserResqestFiter filter, Pageable pageable) {
        // Viết hàm xử lý tại đây
        // convert string data to LocalDate
        filter.setFromDateQuery(DateUtils.strToDate(filter.getFromDate()));
        filter.setToDateQuery(DateUtils.strToDate(filter.getToDate()));

        // query data base
        Page<UserEntity> page = userRepository.findAllByFilter(filter,pageable);

        List<UserEntity> userEntities = page.getContent();

        // convert entity to dto
        List<UserDTO> userDTOS = userMapper.toDtos(userEntities);

        // set data to response
        ResponsePage<List<UserDTO>> responsePage = new ResponsePage<>();
        responsePage.setContent(userDTOS);
        responsePage.setPageNumber(pageable.getPageNumber());
        responsePage.setPageSize(pageable.getPageSize());
        responsePage.setTotalPages(page.getTotalPages());
        responsePage.setTotalElements(page.getTotalElements());
        return responsePage;
    }
}
