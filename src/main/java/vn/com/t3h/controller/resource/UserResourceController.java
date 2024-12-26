package vn.com.t3h.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.t3h.dto.ClaimDTO;
import vn.com.t3h.dto.UserDTO;
import vn.com.t3h.dto.request.ClaimRequestFilter;
import vn.com.t3h.dto.request.UserResqestFiter;
import vn.com.t3h.dto.response.ResponsePage;
import vn.com.t3h.service.IUserService;

import java.util.List;

@RestController// khai báo đây là một restcontroller chuyên nhận request và trả về response
@RequestMapping("api/user/") // chỉ định url cho api bắt đầu bằng http://localhost:8080/api/user/
public class UserResourceController {

    @Autowired // Tiêm bean từ ngoài vào sử dụng design DI
    private IUserService iUserService; // khai báo tầng service

    // Sử dụng phương thức GET
    @PostMapping("/all-user")
    public ResponseEntity<ResponsePage<List<UserDTO>>> getAllClaims(
            @RequestBody UserResqestFiter requestFilter,
            Pageable pageable
    ) {
        ResponsePage<List<UserDTO>> responsePage = iUserService.getAllUser(requestFilter,pageable);
        return ResponseEntity.ok(responsePage);
    }
    // PathVariable truyền tham số trên url
    // viết api lấy ra thông tin user dự vào id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO userDTO = iUserService.findById(id);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        UserDTO responseUser = iUserService.updateUser(userDTO);
        return ResponseEntity.ok(responseUser);
    }
    @GetMapping("/all-user")
    public ResponseEntity<ResponsePage<List<UserDTO>>> getAllClaims(
            // cách khai báo một param là dữ liệu truyền vào từ claim vào back-end
            @RequestParam(value = "userName",required = false) String userName,// required = false không bắt buộc truyền vào param
            @RequestParam(value = "code",required = false) String code,
            @RequestParam(value = "toDate",required = false) String toDate,
            @RequestParam(value = "fromDate",required = false) String fromDate,
            Pageable pageable
    ) {
        UserResqestFiter filter = new UserResqestFiter();
        filter.setUserName(userName);
        filter.setCode(code);
        filter.setFromDate(fromDate);
        filter.setToDate(toDate);
        ResponsePage<List<UserDTO>> responsePage = iUserService.getAllUser(filter,pageable);
        return ResponseEntity.ok(responsePage);
    }
}
