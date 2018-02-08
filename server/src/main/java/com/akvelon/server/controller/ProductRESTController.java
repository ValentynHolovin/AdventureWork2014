package com.akvelon.server.controller;

import com.akvelon.server.domain.Product;
import com.akvelon.server.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
    @ResponseBody
    public List<Product> searchProduct(@PathVariable("searchRequest") String searchRequest, @PathVariable("count") Integer count) {
        return productService.searchProduct(searchRequest, count);
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/get_top_five")
    @ResponseBody
    public List<Product> getTopFive() {
        return productService.getTopFive();
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/products/{productID}")
    @ResponseBody
    public Product getProduct(@PathVariable("productID") Integer productID) {
        return productService.read(productID);
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/products/delete/{productID}")
    @ResponseBody
    public Integer deleteProduct(@PathVariable("productID") Integer productID) {
        return productService.delete(productID);
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/products/create")
    @ResponseBody
    public Integer createProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/products/update")
    @ResponseBody
    public Integer updateProduct(@RequestBody Product product) {
        productService.update(product);
        return product.getId();
    }
}
