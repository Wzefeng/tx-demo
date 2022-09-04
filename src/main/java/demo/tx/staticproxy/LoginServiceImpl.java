package demo.tx.staticproxy;

public class LoginServiceImpl implements LoginService {

    @Override
    public void login() {
        System.out.println("登录中...");
        auth();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void auth() {
        System.out.println("认证中...");

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
