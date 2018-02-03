package com.akvelon.server.controller;

import com.akvelon.server.domain.Product;
import com.akvelon.server.domain.ProductModel;
import com.akvelon.server.service.api.ProductModelService;
import com.akvelon.server.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRESTController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = "/search/{searchRequest}")
    public List<Product> searchProduct(@PathVariable("searchRequest") String searchRequest) {
        return productService.searchProduct(searchRequest);
    }

    @RequestMapping(value = "/get_top_five")
    public List<Product> getTopFive() {
        return productService.getTopFive();
    }

    @RequestMapping(value = "/products/{productID}")
    public Product getProduct(@PathVariable("productID") Integer productID) {
        return productService.read(productID);
    }

    @RequestMapping(value = "/products/delete/{productID}")
    public void deleteProduct(@PathVariable("productID") Integer productID) {
        productService.delete(productID);
    }

    @RequestMapping(value = "/products/create")
    public Integer createProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @RequestMapping(value = "/products/update")
    public void updateProduct(@RequestBody Product product) {
        productService.update(product);
    }

}
