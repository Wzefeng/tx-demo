package demo.tx;

import demo.tx.proxyfactory.CglibProxyFactory;
import demo.tx.proxyfactory.IoCContainer;
import demo.tx.proxyfactory.JdkProxyFactory;
import demo.tx.proxyfactory.ProxyFactory;
import demo.tx.service.ProductService;
import demo.tx.service.UserService;
import demo.tx.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        testJdkProxy();

        testCglibProxy();

        testAutoProxy();
    }

    private static void testJdkProxy() {
        UserService userService = JdkProxyFactory.createProxy(UserServiceImpl::new, UserService.class);

        IoCContainer.put(UserServiceImpl.class, userService);

        userService.saveUser();

    }

    private static void testCglibProxy() {
        ProductService productService = CglibProxyFactory.creatProxy(new ProductService());

        IoCContainer.put(ProductService.class, productService);

        productService.saveProduct();
    }

    private static void testAutoProxy() {
        UserService jdkProxy = ProxyFactory.createProxy(UserServiceImpl::new, UserService.class);
        jdkProxy.saveUser();

        UserServiceImpl cglibProxy = ProxyFactory.createProxy(new UserServiceImpl());
        cglibProxy.saveUser();
    }
}
