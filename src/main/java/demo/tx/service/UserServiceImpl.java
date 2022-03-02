package demo.tx.service;

import demo.tx.MyTransactional;
import demo.tx.proxyfactory.IoCContainer;

public class UserServiceImpl implements UserService {

    @Override
    public void saveUser() {
        System.out.println("UserService#saveUser()");

        // doSave();

        UserService userService = IoCContainer.get(UserServiceImpl.class);
        userService.doSave();
    }

    @Override
    @MyTransactional
    public void doSave() {
        System.out.println("UserService#doSave()");
    }

}
