var app = (function () {
    function login() {
        var user = $("#inputuser").val();
        var password = $("#inputpassword").val();
        var data = {"user":user, "password": password}
        console.log(data);
        apiclient.login(data,redirect);
    }

    function redirect(res) {
        if (res != null) {
            alert("error en el usuario o contraseña");
            return;
        }
        location.href = "/user.html";
    }

    function functionverify(bool) {
        if (bool) {
            return;
        }
        location.href = "/index.html";
    }
    return {
        login: function() {
            login();
        },
        verifylogin : function() {
            apiclient.isLogin(functionverify);
        }
    }
})();