package demo.tx.staticproxy;

import java.util.Date;

public class LoginServiceProxyByComposing implements LoginService {

    private final LoginService loginService;

    public LoginServiceProxyByComposing(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void login() {
        long start = new Date().getTime();

        loginService.login();

        long end = new Date().getTime();

        System.out.printf("登录耗时%d(ms)\n", end - start);
    }
}
