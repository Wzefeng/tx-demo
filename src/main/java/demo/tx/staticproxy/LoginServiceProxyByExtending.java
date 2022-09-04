package demo.tx.staticproxy;

import java.util.Date;

public class LoginServiceProxyByExtending extends LoginServiceImpl {

    @Override
    public void login() {
        long start = System.currentTimeMillis();

        super.login();

        long end = System.currentTimeMillis();

        System.out.printf("登录耗时%d(ms)\n", end - start);
    }

    @Override
    public void auth() {
        long start = System.currentTimeMillis();

        super.auth();

        long end = System.currentTimeMillis();

        System.out.printf("认证耗时%d(ms)\n", end - start);
    }
}
