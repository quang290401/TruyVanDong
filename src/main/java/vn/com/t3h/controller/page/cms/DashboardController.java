package vn.com.t3h.controller.page.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// tạo ra gốc của tất cả các method trong controller
@RequestMapping("/cms")
public class DashboardController {

    // url: localhost:8080/cms/dashboard
    @GetMapping("/dashboard")
    public String dashboard() {
        return "cms/dashboard";
    }

    // url: localhost:8080/cms/report
    @GetMapping("/report")
    public String report() {
        return "cms/report";
    }
}
