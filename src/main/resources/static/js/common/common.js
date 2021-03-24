//查用户
var userName = window.sessionStorage.getItem("username");
var storage = window.sessionStorage;
var authorization = storage.getItem("token");

setTimeout(function () {
    //添加常用弹窗
    var div = document.createElement('div');
    div.innerHTML = '<div class="modal inmodal fade" id="tipsDialog" tabindex="-1" role="dialog"  aria-hidden="true"><div class="modal-dialog modal-sm"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><h4 class="modal-title"><i class="fa fa-info-circle mr-5"></i>提示信息</h4></div><div class="modal-body"><p></p></div><div class="modal-footer" style="text-align:center"><button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times mr-5"></i>关闭</button></div></div></div></div>' +
        '<div class="modal inmodal fade" id="resultDialog" tabindex="-1" role="dialog"  aria-hidden="true"><div class="modal-dialog modal-sm"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><h4 class="modal-title"><i class="fa fa-cog mr-5"></i>操作结果</h4></div><div class="modal-body"><p></p></div><div class="modal-footer" style="text-align:center"><button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times mr-5"></i>关闭</button></div></div></div></div>' +
        '<div class="modal inmodal fade" id="confirmDialog" tabindex="-1" role="dialog"  aria-hidden="true"><div class="modal-dialog modal-sm"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><h4 class="modal-title"><i class="fa fa-question-circle mr-5"></i>确认信息</h4></div><div class="modal-body"><p style="word-break: break-all;"></p></div><div class="modal-footer" style="text-align:center"><button type="button" id="confirm_btn" class="btn btn-primary" data-dismiss="modal"><i class="fa fa-check mr-5"></i>确定</button><button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times mr-5"></i>关闭</button></div></div></div></div>';
    document.body.appendChild(div);
}, 2000);

//加载中-样式1
function loadingText() {
    return '<div style="text-align:center;font-size:14px;"><img src="../../img/cmcc_loading.gif" style="margin-right:5px;padding-bottom:4px;" />努力加载中...</div>';
}

//加载中-样式2
function loadingText1(text) {
    return '<div style="text-align:center;font-size:14px;"><img src="../../img/cmcc_loading.gif" style="margin-right:5px;padding-bottom:4px;" />' + text + '</div>';
}

function loadingTextForIndex(text) {
    return '<div style="text-align:center;font-size:15px;color:#fff;margin-top:30px;">' + text + '<br><img src="img/loading.gif" /></div>';
}

function clearFile(){
    var file = document.getElementById('uploadImg');
    //IE，Safari，chrome
    if (file.outerHTML) {
        file.outerHTML = file.outerHTML;
    } else { //FF
        file.value = "";
    }
}

//校验输入的参数是否含有非法字符
function checkStr(str){
	str = str.replace(/\s/g,''); //替换空格
	//需校验的非法字符
	var arr = ["&", "<", ">", "'", "\"", "<img", "<script", "<style", "<link", "<iframe", "<frame", "onclick=", "onload=", "onerror="];
	for(var i=0;i<arr.length;i++){
		if(str.indexOf(arr[i]) != -1){
			return true;
		}
	}
	return false;
}

//编码功能的函数，将特殊字符转义
function html_encode(str){
    var s='';
    if(str.length==0) return ""
    // 替换 & 符号
    s=str.replace(/&/g,"&amp;");
    //替换 < 符号
    s=s.replace(/</g,"&lt;");
    //替换 > 符号
    s=s.replace(/>/g,"&gt;");
    //替换 空格 符号
    s=s.replace(/\s/g,"&nbsp;");
    //替换 ' 符号
    s=s.replace(/\'/g,"&#39;");
    //替换 " 符号
    s=s.replace(/\"/g,'&quot;');
    //替换 换行 符号
    s=s.replace(/\n/g,"<br>");
    return s
}

