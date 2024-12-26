const defaultId = 1;
const URL='http://localhost:8080/'

$(document).ready(function () {
    console.log('start call api get user from server')
    // sử dụng method get
    $.ajax({
        url: URL + 'api/user/' + defaultId,
        type: 'GET',
        contentType: 'application/json',
        success: function (response){
            console.log(response);
            setValueUser(response);
        },
        error: function (error) {
            console.log(error)
        }
    })

    // disable submit form
    $('#formAccountSettings').submit(false);
})

function setValueUser(response){
    let userDto = response;
    // set dữ liệu từ response lên thẻ html sử dụng id của thẻ
    $('#username').val(userDto.username);
    $('#code').val(userDto.code);
    $('#email').val(userDto.email);
    $('#phone').val(userDto.phone);
    $('#address').val(userDto.address);
    $('#firstName').val(userDto.firstName);
    $('#lastName').val(userDto.lastName);
    $('#createdDate').val(userDto.createdDate);
    $('#createdBy').val(userDto.createdBy);
    $('#lastModifiedBy').val(userDto.lastModifiedBy);
    $('#lastModifiedDate').val(userDto.lastModifiedDate);
    $('#roleName').val(userDto.roleName);
    $('#id').val(userDto.id);
}

function updateUser() {
    console.log('start update user');
    let userDto = {};

    // Collecting values from the HTML elements
    userDto.username = $('#username').val();
    userDto.code = $('#code').val();
    userDto.email = $('#email').val();
    userDto.phone = $('#phone').val();
    userDto.address = $('#address').val();
    userDto.firstName = $('#firstName').val();
    userDto.lastName = $('#lastName').val();
    userDto.createdDate = $('#createdDate').val(); // Assuming you want to keep this unchanged
    userDto.createdBy = $('#createdBy').val(); // Assuming you want to keep this unchanged
    userDto.lastModifiedBy = $('#lastModifiedBy').val();
    userDto.lastModifiedDate = new Date().toISOString(); // Set current date as last modified
    userDto.roleName = $('#roleName').val();
    userDto.id=$('#id').val();


    // Assuming you want to send this data to a server or perform some action
    console.log('User data to update:', userDto);

    $.ajax({
        url: URL + 'api/user/update',
        type: 'PUT',
        data: JSON.stringify(userDto),
        contentType: 'application/json',
        success: function(response) {
            console.log('User updated successfully', response);
            setValueUser(response);
        },
        error: function(err) {
            console.error('Error updating user', err);
        }
    });
}
