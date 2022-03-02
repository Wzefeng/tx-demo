package demo.tx.proxyfactory;

import java.util.function.Supplier;

public class ProxyFactory {

    public static <T> T createProxy(T targetProvider) {
        return createProxy(() -> targetProvider, null);
    }

    public static <T> T createProxy(Supplier<? extends T> targetProvider, Class<T> targetInterface) {

        T proxy;
        if (targetInterface != null) {
            T target = targetProvider.get();
            // Jdk Proxy
            proxy = JdkProxyFactory.createProxy(targetProvider, targetInterface);
            IoCContainer.put(targetInterface, proxy);
            IoCContainer.put(target.getClass(), proxy);
        } else {
            // Cglib Proxy
            T target = targetProvider.get();
            proxy = CglibProxyFactory.creatProxy(target);
            IoCContainer.put((Class<T>) target.getClass(), proxy);
        }

        return proxy;
    }

}
