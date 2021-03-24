//登录
function login(){
	var username = $('#username').val();
	var password = $('#password').val();
	var code = $('#code').val();
	$("#setInfo").html("");
	var result = test(username,password,code);
	if(result == false){
		return;
	}
	$("#setInfo").html("");
	var storage = window.sessionStorage;
	$.ajax({
		type :"POST",
		url:"http://localhost:8887/community/admin/login1",
		data: {"username":username,"password":password,"admintype":1},
		success: function (data) {
			sessionStorage.setItem("adminSUCCESSadmin", data);

			if (data!== "1"){
				window.location = "http://localhost:8887/community/admin/login";
			}
			else {
				window.location = "http://localhost:8887/community/admin/index";
			}
			/*if(data.object.rtnCode == '0'){*/
			// 	$("#setInfo").html("");
			// 	$('#password').val("");
			// 	$('#code').val("");
			// 	storage.setItem("username", username);
			// 	storage.setItem("token", "Bearer "+data.object.token);
			// 	storage.setItem("isSuperAdmin", data.object.isSuperAdmin);
			// 	storage.setItem("remark", data.object.remark);
			// 	storage.setItem("roleCode", data.object.roleCode );
			// }else{
			// 	$('#code').val("");
			// 	$("#setInfo").html("<font color='red'>用户名或者密码错误!请重新输入</font>");
			// }
		}
	});

//window.location = "http://localhost:8887/community/admin/index";
}
//简单的前端验证
function test(username,password,code){
	if(username == ""){
		$("#setInfo").html("<font color='red'>请输入用户名!</font>");
		return false;
	}else if(password == ""){
		$("#setInfo").html("<font color='red'>请输入密码!</font>");
		return false;
	}else if(code == ""){
		$("#setInfo").html("<font color='red'>请输入验证码!</font>");
		return false;
	}else if(code.toLocaleUpperCase() != $("#codeValidateImg").val()){
		$("#setInfo").html("<font color='red'>验证码输入错误，请重新输入!</font>");
		return false;
	}
	return true;
}


//获取验证码
function sendMsg(obj){
    var countdown=60;
    settime(obj);
    function settime(obj) {
        if (countdown == 0) {
            $(obj).attr("onclick","sendMsg(this)");
            $(obj).text("重新发送验证码");
            countdown = 60;
            return;
        } else {
        	$(obj).removeAttr("onclick");
            $(obj).text("请在" + countdown + "秒后再次获取");
            countdown--;
        }
        setTimeout(function() {
        	settime(obj) }
        ,1000)
    }
}

//js生成验证码
function flushValidateCode(){
	 var codeValidateImg = "";
     var codeLength = 4;//验证码的长度
     var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
     'S','T','U','V','W','X','Y','Z');//随机数
     for(var i = 0; i < codeLength; i++) {//循环操作
        var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）
        codeValidateImg += random[index];//根据索引取得随机数加到code上
    }
    $("#codeValidateImg").val(codeValidateImg);
}