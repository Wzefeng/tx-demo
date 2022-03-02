package demo.tx.staticproxy;

public class LoginController {

    public static void main(String[] args) {
        // 组合模式代理
        compose();

        // 继承模式代理
        extend();
    }


    private static void compose() {
        LoginService loginService = new LoginServiceProxyByComposing(new LoginServiceImpl());

        loginService.login();
    }

    private static void extend() {
        LoginService loginService = new LoginServiceProxyByExtending();

        loginService.login();
    }
}
