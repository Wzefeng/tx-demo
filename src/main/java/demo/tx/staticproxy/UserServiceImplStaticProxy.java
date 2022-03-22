package demo.tx.staticproxy;

import demo.tx.service.UserService;

/**
 * @author wangzefeng
 */
public class UserServiceImplStaticProxy implements UserService {

    private final UserService target;

    public UserServiceImplStaticProxy(UserService target) {
        this.target = target;
    }

    @Override
    public void saveUser() {

        target.saveUser();

    }

    @Override
    public void doSave() {

        System.out.println("[Static Proxy] Begin transaction...");

        target.doSave();

        System.out.println("[Static Proxy] commit...");
    }
}
