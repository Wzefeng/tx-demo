package demo.tx.staticproxy;

import demo.tx.service.ProductService;

/**
 * @author wangzefeng
 */
public class ProductServiceStaticProxy extends ProductService {

    @Override
    public void saveProduct() {
        super.saveProduct();
    }

    @Override
    public void doSave() {

        System.out.println("[Static Proxy] Begin transaction...");

        super.doSave();

        System.out.println("[Static Proxy] commit...");
    }
}
