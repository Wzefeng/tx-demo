package demo.tx.service;

import demo.tx.MyTransactional;
import demo.tx.proxyfactory.IoCContainer;

public class ProductService {

    public void saveProduct() {
        System.out.println("ProductService#saveProduct()");

        // doSave();

        ProductService productService = IoCContainer.get(ProductService.class);
        productService.doSave();
    }

    @MyTransactional
    public void doSave() {
        System.out.println("ProductService#doSave()");
    }

}
