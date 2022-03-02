package demo.tx.proxyfactory;

import demo.tx.MyTransactional;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

public class CglibProxyFactory {

    public static <T> T creatProxy(T target) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new TransactionMethodInterceptor(target));
        return (T) enhancer.create();
    }

    private static class TransactionMethodInterceptor implements MethodInterceptor {

        private final Object target;

        public TransactionMethodInterceptor(Object target) {
            this.target = target;
        }

        @Override
        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

            // 没有被标记 @MyTransactional 的方法不进行事务拦截
            if (AnnotationUtils.findAnnotation(method, MyTransactional.class) == null) {
                return methodProxy.invoke(target, args);
            }

            System.out.println("[Cglib Proxy] Begin transaction...");

            Object result = methodProxy.invoke(target, args);

            System.out.println("[Cglib Proxy] Commit...");

            return result;
        }
    }

}
