package demo.tx.staticproxy;

public class LoginServiceImpl implements LoginService {

    @Override
    public void login() {
        System.out.println("登录中...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
