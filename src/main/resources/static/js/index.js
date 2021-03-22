$(function(){
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
        {"title":title,"content":content},
        function(data) {
            data = $.parseJSON(data);
            // 在提示框中显示返回消息
            $("#hintBody").text(data.msg);
            // 显示提示框
            $("#hintModal").modal("show");
            // 2秒后,自动隐藏提示框
            setTimeout(function(){
                $("#hintModal").modal("hide");
                // 刷新页面
                if(data.code == 0) {
                    window.location.reload();
                }
            }, 2000);
        }
    );

}
// 删除
function deletepost(postId) {


    if(window.confirm('你确定要删除本条帖子吗？')){
        $.post(
            "http://localhost:8887/community/delete",
            {"postId":postId},
            function(data) {
                location.href = "http://localhost:8887/community/admin/postdata";
            }
        );
    }
}


// 删除
function deleteuser(userId) {

    if(window.confirm('你确定要删除该用户吗？')){
        $.post(
            "http://localhost:8887/community/admin/deleteuser",
            {"userId":userId},
            function(data) {
                location.href = "http://localhost:8887/community/admin/userdata";
            }
        );
    }
}

