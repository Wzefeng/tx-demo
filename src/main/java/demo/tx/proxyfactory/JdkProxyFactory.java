package demo.tx.proxyfactory;

import demo.tx.MyTransactional;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public class JdkProxyFactory {

    public static <T> T createProxy(Supplier<? extends T> targetProvider, Class<T> targetInterface) {

        T target = targetProvider.get();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?>[] interfaces = new Class[]{targetInterface};
        InvocationHandler h = new TransactionMethodInterceptor(target);

        return (T) Proxy.newProxyInstance(loader, interfaces, h);

    }

    private static class TransactionMethodInterceptor implements InvocationHandler {

        private final Object target;

        public TransactionMethodInterceptor(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            // 没有被标记 @MyTransactional 的方法不进行事务拦截
            Class<?>[] parameterTypes = Arrays.stream(Optional.ofNullable(args).orElse(new Object[0]))
                    .map(Object::getClass).toArray(Class[]::new);
            Method targetMethod = target.getClass().getMethod(method.getName(), parameterTypes);
            if (AnnotationUtils.findAnnotation(targetMethod, MyTransactional.class) == null) {
                return method.invoke(target, args);
            }

            System.out.println("[Jdk Proxy] Begin transaction...");

            Object result = method.invoke(target, args);

            System.out.println("[Jdk Proxy] Commit...");

            return result;
        }
    }

}
