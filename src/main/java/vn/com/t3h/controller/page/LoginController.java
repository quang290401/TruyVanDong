package vn.com.t3h.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vn.com.t3h.service.ILoginService;

@Controller
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @GetMapping(value = "/login")
    public String LoginController() {
        return "guest/loginmetlife";
    }

    // Thực hiện xử lý sau khi login thành công sẽ check quyền và tự động điều hướng với trang
    // phù hợp ứng với quyền User đã đăng nhập
    @GetMapping(value = "process-after-login")
    public String processAfterLoginController() {
        return loginService.processAfterLogin();
    }
}
