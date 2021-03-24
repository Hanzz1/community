function like(btn, entityType, entityId , entityUserId, postId) {
    $.post(
        "http://localhost:8887/community/like",
        {"entityType":entityType,"entityId":entityId,"entityUserId":entityUserId,"postId":postId},
        function(data) {
            data = $.parseJSON(data);
            if(data.code == 0) {
                $(btn).children("i").text(data.likeCount);
                $(btn).children("b").text(data.likeStatus==1?'已赞':"赞");
            } else {
                alert(data.msg);
            }
        }
    );
}


// 删除
function setDelete(postId) {


    if(window.confirm('你确定要取消吗？')){
        $.post(
            "http://localhost:8887/community/delete",
            {"postId":postId},
            function(data) {
                location.href = "http://localhost:8887/community/index";
            }
        );
    }



}


