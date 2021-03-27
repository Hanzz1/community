window.onload = function () {

    var adminSUCCESSadmin = sessionStorage.getItem("adminSUCCESSadmin");
    if (adminSUCCESSadmin == 1){
        //alert("aa")
     //   window.location = "http://localhost:8887/community/admin/index";
    }else {
        alert("bb")
        window.location = "http://localhost:8887/community/admin/login";
    }


}
