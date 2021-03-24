$(function () {
    $("#publishBtn").click(publish);
});

function publish() {
    $("#publishModal").modal("hide");

    // 获取标题和内容
    var title = $("#recipient-name").val();
    var content = $("#message-text").val();
    // 发送异步请求(POST)
    $.post(
        "http://localhost:8887/community/discuss/add",
        {"title": title, "content": content},
        function (data) {
            data = $.parseJSON(data);
            // 在提示框中显示返回消息
            $("#hintBody").text(data.msg);
            // 显示提示框
            $("#hintModal").modal("show");
            // 2秒后,自动隐藏提示框
            setTimeout(function () {
                $("#hintModal").modal("hide");
                // 刷新页面
                if (data.code == 0) {
                    window.location.reload();
                }
            }, 2000);
        }
    );
}

// 删除帖子
function deletepost(postId) {


    if (window.confirm('你确定要删除本条帖子吗？')) {
        $.post(
            "http://localhost:8887/community/delete",
            {"postId": postId},
            function (data) {
                location.href = "http://localhost:8887/community/admin/postdata";
            }
        );
    }
}


// 删除用户
function deleteuser(userId) {

    if (window.confirm('你确定要删除该用户吗？')) {
        $.post(
            "http://localhost:8887/community/admin/deleteuser",
            {"userId": userId},
            function (data) {
                location.href = "http://localhost:8887/community/admin/userdata";
            }
        );
    }
}


//查看帖子
function lookpost(id, userId) {
    //  "http://localhost:8887/community/admin/selectPostById",
    $('#hiddendiv').attr("class", " ");
    $.ajax({
        url:"http://localhost:8887/community/admin/selectPostById",
        data:{"id": id},
        type:"GET",
        success:function(data){
           //alert(JSON.stringify(data))
            $('#input1').attr("value", data.id);
            $('#input2').attr("value", data.userId);
            $('#input3').attr("value", data.title);
            $('#input4').attr("value", data.content);
            $('#input5').attr("value", data.createTime.toString().substr(0,19));
        }
    });
}



//更新某人的帖子和内容
function updatepost(){
    var id  = $('#input1').val();
    alert(id);
    // 获取标题和内容
    var title = $("#input3").val();
    var content = $("#input4").val();
    // 发送异步请求(POST)
    $.post(
        "http://localhost:8887/community/admin/updateTitleAndContent",
        {"id":id,"title": title, "content": content},
        function (data) {
            setTimeout(function () {
                window.location.reload();
            }, 2000);
        }
    );

}




//查看用户
function lookuser(id, userId) {

    $('#userhiddendiv').attr("class", " ");

    //  "http://localhost:8887/community/admin/selectUserById",

    $.ajax({
        url: "http://localhost:8887/community/admin/selectUserById",
        data: {"id": id},
        type: "GET",
        success: function (data) {
            alert(JSON.stringify(data))
            $('#input1').attr("value", data.id);
            $('#input2').attr("value", data.username);
            $('#input3').attr("value", data.email);
            $('#input4').attr("value", data.type);
            $('#input5').attr("value", data.status);
            $('#input6').attr("value", data.createTime.toString().substr(0,19));
        }
    });
}




//更新某人信息
function updateuser(){
    var id  = $('#input1').val();

    //获取标题和内容 username, email, type, status
    var username = $("#input2").val();
    var email = $("#input3").val();
    var type = $("#input4").val();
    var status = $("#input5").val();
    // 发送异步请求(POST)
    $.post(
        "http://localhost:8887/community/admin/updateUserData",
        {"id":id,"username": username, "email": email,"type": type, "status": status},
        function (data) {
            setTimeout(function () {
                window.location.reload();
            }, 1000);
        }
    );

}

//显示新增贴子
function showaddpost(userId) {

    $('#APinput1').attr("value",userId);
    $('#addposthiddendiv').attr("class", " ");
}

////新增帖子
function addpost() {


    var userId =$('#APinput1').val();
    var title =$('#APinput2').val();
    var content =$('#APinput3').val();
    //addPostByUserId

    // 发送异步请求(POST)
    $.post(
        "http://localhost:8887/community/admin/addPostByUserId",
        {"userId":userId,"title": title, "content": content},
        function (data) {
            alert("添加成功");
            setTimeout(function () {
                location.href = "http://localhost:8887/community/admin/postdata";
            }, 1000);
        }
    );

}



//显示新增用户
function showadduser() {
    $('#adduserhiddendiv').attr("class", " ");
}



////新增帖子
function adduser() {

    var username = $('#AUinput1').val();
    var password = $('#AUinput2').val();
    var email = $('#AUinput3').val();
    var type = $('#AUinput4').val();
    var status = $('#AUinput5').val();
    //addUser
    $.post(
        "http://localhost:8887/community/admin/addUser",
        {"username": username, "password": password, "email": email, "type": type, "status": status},
        function (data) {
            alert("添加成功");
            setTimeout(function () {
                location.href = "http://localhost:8887/community/admin/userdata";
            }, 1000);
        }
    );
}

//     // 发送异步请求(POST)
//     $.post(
//         "http://localhost:8887/community/admin/addPostByUserId",
//         {"username":username,"password": password, "email": email},
//         function (data) {
//             alert("添加成功");
//             setTimeout(function () {
//                 location.href = "http://localhost:8887/community/admin/postdata";
//             }, 1000);
//         }
//     );
//
// }
//



