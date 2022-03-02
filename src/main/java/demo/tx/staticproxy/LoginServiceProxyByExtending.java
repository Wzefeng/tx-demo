package demo.tx.staticproxy;

import java.util.Date;

public class LoginServiceProxyByExtending extends LoginServiceImpl {

    @Override
    public void login() {
        long start = new Date().getTime();

        super.login();

        long end = new Date().getTime();

        System.out.printf("登录耗时%d(ms)\n", end - start);
    }
}
