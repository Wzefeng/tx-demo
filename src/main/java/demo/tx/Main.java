package demo.tx;

import demo.tx.proxyfactory.CglibProxyFactory;
import demo.tx.proxyfactory.IoCContainer;
import demo.tx.proxyfactory.JdkProxyFactory;
import demo.tx.proxyfactory.ProxyFactory;
import demo.tx.service.ProductService;
import demo.tx.service.UserService;
import demo.tx.service.UserServiceImpl;
import demo.tx.staticproxy.ProductServiceStaticProxy;
import demo.tx.staticproxy.UserServiceImplStaticProxy;

public class Main {

    public static void main(String[] args) {
        // testStaticProxyByComposing();

        testStaticProxyByExtending();

        // testJdkProxy();

        // testCglibProxy();

        // testAutoProxy();
    }

    private static void testStaticProxyByComposing() {
        UserServiceImpl target = new UserServiceImpl();
        UserService userService = new UserServiceImplStaticProxy(target);
        userService.saveUser();
    }

    private static void testStaticProxyByExtending() {
        ProductService productService = new ProductServiceStaticProxy();
        productService.saveProduct();
    }

    private static void testJdkProxy() {
        UserService userService = JdkProxyFactory.createProxy(UserServiceImpl::new, UserService.class);

        IoCContainer.put(UserService.class, userService);

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
