"http://localhost:8887/community/follow";

$(function(){
    $(".follow-btn").click(follow);
});

function follow() {
    var btn = this;
    if($(btn).hasClass("btn-info")) {
        // 关注TA
        $.post(
            "http://localhost:8887/community/follow",
            {"entityType":3,"entityId":$(btn).prev().val()},
            function(data) {
                data = $.parseJSON(data);
                if(data.code == 0) {
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            }
        );
        // $(btn).text("已关注").removeClass("btn-info").addClass("btn-secondary");
    } else {
        // 取消关注
        $.post(
            "http://localhost:8887/community/unfollow",
            {"entityType":3,"entityId":$(btn).prev().val()},
            function(data) {
                data = $.parseJSON(data);
                if(data.code == 0) {
                    window.location.reload();
                } else {
                    alert(data.msg);
                }
            }
        );
        //$(btn).text("关注TA").removeClass("btn-secondary").addClass("btn-info");
    }
}