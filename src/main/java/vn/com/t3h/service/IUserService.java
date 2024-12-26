package vn.com.t3h.service;

import org.springframework.data.domain.Pageable;
import vn.com.t3h.dto.ClaimDTO;
import vn.com.t3h.dto.UserDTO;
import vn.com.t3h.dto.request.ClaimRequestFilter;
import vn.com.t3h.dto.request.UserResqestFiter;
import vn.com.t3h.dto.response.ResponsePage;

import java.util.List;

/**
 IUserService: Sử dụng để chuyên quản lý các hàm xử lý với thông tin
 user trong database được lấy lên từ UserRepository
 */
public interface IUserService {

    public List<UserDTO> getAllUser();

    UserDTO findById(Long id);

    UserDTO updateUser(UserDTO userDTO);
    ResponsePage<List<UserDTO>> getAllUser(UserResqestFiter filter, Pageable pageable);
}
