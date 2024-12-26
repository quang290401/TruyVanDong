package vn.com.t3h.controller.page;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller: giúp tự động khởi tạo bean( khởi tạo đối tượng bằng từ khóa new)
@Controller
public class HomeGuestController {
    /**
     @GetMapping:
                chỉ định với đường dẫn localhost:8080/home-guest
                sẽ được controller HomeGuestController tiếp nhận và
                chỉ định hàm homeGuest này xử lý và tiếp nhận request đấy
            value =: khai báo đường dẫn để chỉ định ứng với đường dẫn
                    đó sẽ là hàm homeGuest tiếp nhận request và xử lý
     / : tương ứng với đường dẫn localhost:8080
     /home-guest => localhost:8080/home-guest
     */
    @GetMapping(value = {"/home-guest","/"})
    public String homeGuest() {
        // trả về đường dẫn dẫn đến tên file html là page trả về về cho font-end
        // đường dẫn tính từ thư mục templates trở đi
        return "guest/homeguest";
    }
}
