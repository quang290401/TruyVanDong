const URL = 'http://localhost:8080/';
const PAGE = 0;
const SIZE = 3;

$(document).ready(function () {
    loadUsers(PAGE, SIZE);
});
function search(){
    console.log('bắt đầu hàm search')
    loadUsers();

}

function loadUsers(pageIndex, pageSize) {
    let lastname = $('#lastName').val() !== '' ? $('#lastName').val() : null;
    let code = $('#code').val() !== '' ? $('#code').val() : null;
    let toDate = $('#toDate').val() !== '' ? $('#toDate').val() : null;
    let fromDate = $('#fromDate').val() !== '' ? $('#fromDate').val() : null;


    let requestFilter = {
        lastname,
        code,
        fromDate,
        toDate
    };

    let jsonRequestBody = JSON.stringify(requestFilter);

    $.ajax({
        type: "POST",
        url: URL + 'api/user/all-user?page=' + pageIndex + '&size=' + pageSize, // Đổi thứ tự pageIndex và pageSize
        contentType: "application/json",
        data: jsonRequestBody,
        success: function (response) {
            let tbody = $("#tableData tbody");
            tbody.empty();

            // Hiển thị dữ liệu lên bảng
            $.each(response.content, function (index, user) {
                console.log("aa"+response.content)
                let row = `<tr>
                        <td><input type="checkbox"></td>
                        <td>${user.code}</td>
                        <td>${user.username}</td>
                        <td>${user.firstName}</td>
                        <td>${user.createdDate}</td>
                        <td>${user.email}</td>
                        <td>${user.address}</td>
                    </tr>`;
                tbody.append(row);
            });

            // Gọi hàm cập nhật phân trang
            updatePagination(response.totalPages, pageIndex);
        },
        error: function (err) {
            console.error("Error fetching users:", err);
        }
    });
}

function updatePagination(totalPages, currentPage) {
    let pagination = $("#paginationId");
    pagination.empty();

    // Tạo các trang phân trang
    for (let i = 0; i < totalPages; i++) {
        let activeClass = (i === currentPage) ? 'active' : '';
        pagination.append(`<li class="page-item ${activeClass}"><a class="page-link" href="#">${i + 1}</a></li>`);
    }

    // Gắn sự kiện click cho mỗi trang
    $(".page-link").click(function (e) {
        e.preventDefault();
        let pageNumber = parseInt($(this).text()) - 1; // Lấy trang hiện tại từ phân trang (trang 1 UI tương ứng với 0 backend)
        loadUsers(pageNumber, SIZE); // Truyền đúng pageNumber và kích thước trang
    });
}

// Gọi loadUsers khi trang được load lần đầu
loadUsers(PAGE, SIZE);

function addUser(){
    window.location.href = URL + "user/add";
}