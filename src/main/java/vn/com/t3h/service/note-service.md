* Ý nghĩa và cấu trúc của tầng Service trong project Spring boot 

ý nghĩa: Tầng được sử dụng để xử lý logic
Luồng làm một api:
    - request được tiếp nhận bởi controller 
    - controller chuyển request tới tầng service 
    - service xử lý logic nghiệp vụ 
        vd: nghiệp vụ lấy ra dữ liệu user từ trong database thông qua tầng repository 
            ->b1) service phải thực hiện lấy ra dữ liệu từ trong database dạng entity 
            ->b2) chuyển dữ liệu từ dạng entity sang dạng dto( Data Transfer Object)
            ->b3) trả dữ liệu dạng dto cho controller 
    - controller nhận được dữ liệu dưới dạng dto và trả cho client(user, postman, view....)