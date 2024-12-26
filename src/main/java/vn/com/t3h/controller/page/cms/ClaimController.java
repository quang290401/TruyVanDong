package vn.com.t3h.controller.page.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms")
public class ClaimController {

    // url: localhost:8080/cms/claim-manager
    @GetMapping("/claim-manager")
    public String claimManager() {
        return "cms/claim-manager";
    }

    // url: localhost:8080/cms/detail-claim -> 404
    // url: localhost:8080/cms/claim-detail -> 404
    @GetMapping("/claim-detail")
    public String claimDetail() {
        return "cms/detail-claim";
    }
}
