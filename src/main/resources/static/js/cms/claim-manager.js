/*
- Cách hoạt động của javascript:
    + chạy tất cả các câu lệnh được khai báo, và các hàm được gọi tới một cách lần lượt từ trên xuống dưới
 */
console.log("hello t3h") // câu lệnh in ra dữ liệu màn hình console log
const PAGE=0; // khai báo biến bằng trong js
const SIZE = 3;
/*
- Các cách khai báo biến trong js
    1) sử dụng từ khóa var, sử dụng khi muốn khai báo biến globe dung chung cho nhiều nơi
    2) sử dụng từ khóa let, sử dụng khi muốn khai báo các biến local sử dụng dụng trong hàm
    3) sử dụng từ khóa const khi khai báo hằng số, tương tự final trong java
- khi khao báo biến cho js sẽ không cần định nghĩa trước kiểu dữ liệu, js sẽ tự động định nghĩa kiểu dữ liệu cho biến phụ thuộc vào
    giá trị gán cho biết đấy
- Các kiểu dữ liệu phổ biến trong js
    + number ( tương tự int, long, double.. trong java)
    + string
    + boolean
    + object
        + có thuộc tính
        + giá trị các thuộc tính
 */
console.log("page: " + PAGE + " number: " + SIZE)
// khai báo biến bằng từ khóa var
var a = 't3h';
var b = 10;
var c = true;
// Khai báo biến bằng từ khóa let, thường được sử dụng trong các hàm
// Khai báo function hello
/*
khi khai báo funtion trong js chỉ cần sử dụng cú pháp
    funtion ten_funtion(tham_so_truyen_vao){
        return;
    }
 - tham_so_truyen_vao: có thể có hoặc không, không cần định nghĩa kiểu dữ liệu, chỉ cần truyền vào tên của tham số
 - nếu có return => funtion sẽ tự động hiểu hàm đang trả về với kiểu dữ liệu tại return, nếu không có return funtion được
    hiểu là funtion void không trả về dữ liệu gì
 - sau khi khai báo funtion phải thực hiện gọi tới funtion đấy thì funtion mới được hoạt động
 */
function hello(name){
    // ví dụ khai báo biến let trong funtion
    console.log('hello bạn: ' + name)
    let demoLet = 10;
    console.log(demoLet);
    let objectClaim = {
        id:10,
        name: 'Bảo hiểm thất nghiệp'
    }
    console.log(objectClaim)
}
// gọi tới funtion hello(), nếu không funtion hello sẽ không hoạt động được
hello(a)

/*
Khai báo funtion gán cho sự khiện khi người dùng click và btn tìm kiếm trên màn hình
    - sử dụng cơ chế bắt event trên màn hình html của js và jquery cung cấp
        js:
        https://www.w3schools.com/js/js_events.asp
        https://viblo.asia/p/javascript-event-handlers-va-event-listeners-RQqKLb1rl7z
        jquery:
        https://www.w3schools.com/jquery/jquery_events.asp

 */
function search(){
    console.log('bắt đầu hàm search')
    // lấy các thông tin trên màn hình sử dụng jquery cú pháp: $('#id_element_html').val(); => lấy ra value của phần tử có id https://www.w3schools.com/jquery/jquery_ref_selectors.asp
    // hiển thị ra các thông tin đấy
    getClaimFromApiToView();

}


// đảm bảo chỉ khi toàn bộ file html được load xong mới thực thi đoạn code này, tương tự hàm main trong java
$(document).ready(function (){
    getClaimFromApiToView(PAGE,SIZE);
});

/*
tạo function để thực hiện các công việc
    1) lấy thông tin dữ liệu trên các ô input tại view
    2) call api http://localhost:8080/api/claim/all-claim lấy danh sách claim từ server
    3) Từ response json tại api thực hiện đưa lên view html
 */
function getClaimFromApiToView(pageSize,pageIndex){

    /*
    1.	Sử dụng java script và ajax lấy được thông tin về các trường trong phần tim kiếm của page claim-manager, các trường bao gồm:
        -	Mã yêu cầu bồi thường
        -	Từ ngày
        -	Đến ngày
        -	Trạng thái yêu cầu
     */
    let claimCode = $('#search-ma-yeu-cau').val() !== '' ? $('#search-ma-yeu-cau').val() : null ;
    let fromDate = $('#from-date').val() !== '' ? $('#from-date').val() : null;
    let toDate = $('#to-date').val() !== '' ? $('#to-date').val() : null;
    let statusCode = $('#trang-thai-yeu-cau').val() !== '' ? $('#trang-thai-yeu-cau').val() : null;
    // đưa vào object
    let requestClaimFilter = {
        claimCode,
        fromDate,
        toDate,
        statusCode
    }
    console.log(requestClaimFilter);
    /**
     2.	Sử dụng ajax và jquery thực hiện call api từ màn hình claim-manager
     để lấy ra được tất cả thông tin về claim trong service back-end,
     hiển thị tại console với câu lệnh console.log(responseClaim)
     */
    // cấu trúc để call api sử dụng ajax
    let jsonRequestBody = JSON.stringify(requestClaimFilter); // chuyển object js sang string json để truyền vào body
    $.ajax({
        url: 'http://localhost:8080/api/claim/all-claim?page='+pageSize+'&size='+pageIndex, // url và param truyền vào
        type:'POST', //  method sử dụng
        contentType: 'application/json', // kiểu dữ liệu trong request body và response
        data: jsonRequestBody, // truyền data dưới dạng json
        success: function (response){
            // hàm này sẽ được gọi vào khi call api thành công và nhận về response
            console.log(response)
            // gắn dữ liệu từ json vào table
            renderTable(response);
            handlePage(response);
        },
        error: function (responseError){
            // hàm này sẽ được gọi vào khi call api thất bạt
            console.log(responseError)
        }
    })

}

function renderTable(response){
    // lấy ra danh sách claim trong response
    let claims = response.content;
    // lấy ra table muốn đưa dữ liệu vào
    let tableBody = $('#tableData tbody'); // lấy ra body của table bằng id
    // reset table nếu có data
    tableBody.empty();
    // đưa dữ liệu vào tbody của table
    for (let i = 0; i < claims.length; i++) {
        // lấy ra phần tử claim trong response
        let claim = claims[i];
        // tạo ra các dòng trong table
        let rowTable = '<tr>\n' +
            '  <td>'+ claim.id +'</td>\n' + // đưa dữ liệu của claim vào từng dòng (row)
            '  <td>' + claim.claimCode + '</td>\n' +
            '  <td>'+ claim.customerName +'</td>\n' +
            '  <td>' + claim.insuranceProductName + '</td>\n' +
            '  <td>' + claim.claimDate + '</td>\n' +
            '  <td>' + claim.description + '</td>\n' +
            '  <td>' + claim.status +'</td>\n' +
            ' </tr>';
        tableBody.append(rowTable); // đưa các dòng(row) vào table
    }
}

function handlePage(response){
    // Xử lý phân trang
    let totalPage = response.totalPages;
    let pageNumber = response.pageNumber;
    // lấy ra thẻ hiển thị page
    let pageHtml = $('#paginationId');
    // reset page
    pageHtml.empty();
    // tạo ra page
    let pagination = '';
    // tạo ra icon << : khi click sẽ lùi về 1 page
    pagination = pagination + `    <li class="page-item" onclick='getClaimFromApiToView(${pageNumber - 1},SIZE)'>
                                        <a class="page-link" href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>`
    // tạo ra các icon index page
    for (let i = 0; i < totalPage; i++) {
        pagination = pagination + `<li class="page-item ${i === pageNumber ? 'active' : ''}" onclick='getClaimFromApiToView(${i},SIZE)'><a class="page-link" href="#"> ${i + 1}</a></li>`;
    }
    // tạo ra icon >> : khi click sẽ tăng thêm 1 page
    pagination = pagination + `<li class="page-item" onclick='getClaimFromApiToView(${pageNumber + 1},SIZE)'>
                                        <a class="page-link" href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>`
    // ghép vào giao diện
    pageHtml.append(pagination);
}

