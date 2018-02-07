package com.akvelon.server.controller;

import com.akvelon.server.domain.Product;
import com.akvelon.server.domain.ProductModel;
import com.akvelon.server.domain.ProductSubcategory;
import com.akvelon.server.domain.UnitMeasure;
import com.akvelon.server.service.api.ProductModelService;
import com.akvelon.server.service.api.ProductService;
import com.akvelon.server.service.api.ProductSubcategoryService;
import com.akvelon.server.service.api.UnitMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductRESTController {
    @Autowired
    private ProductService productService;

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/search/{searchRequest}/{count}")
    public List<Product> searchProduct(@PathVariable("searchRequest") String searchRequest, @PathVariable("count") Integer count) {
        return productService.searchProduct(searchRequest, count);
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/get_top_five")
    public List<Product> getTopFive() {
        return productService.getTopFive();
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/products/{productID}")
    public Product getProduct(@PathVariable("productID") Integer productID) {
        return productService.read(productID);
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/products/delete/{productID}")
    public Integer deleteProduct(@PathVariable("productID") Integer productID) {
        return productService.delete(productID);
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/products/create")
    public Integer createProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/products/update")
    public Integer updateProduct(@RequestBody Product product) {
        productService.update(product);
        return product.getId();
    }
}
