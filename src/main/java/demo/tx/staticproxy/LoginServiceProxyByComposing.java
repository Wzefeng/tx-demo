package demo.tx.staticproxy;

import java.util.Date;

public class LoginServiceProxyByComposing implements LoginService {

    private final LoginService loginService;

    public LoginServiceProxyByComposing(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void login() {
        long start = System.currentTimeMillis();

        loginService.login();

        long end = System.currentTimeMillis();

        System.out.printf("登录耗时%d(ms)\n", end - start);
    }

    @Override
    public void auth() {
        long start = System.currentTimeMillis();

        loginService.auth();

        long end = System.currentTimeMillis();

        System.out.printf("登录耗时%d(ms)\n", end - start);
    }
}
