package com.akvelon.server.controller;

import com.akvelon.server.domain.Product;
import com.akvelon.server.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainRESTController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @Deprecated
    @RequestMapping(value = "/get_all")
    public List<Product> getAllProduct() {
        return productService.getAll();
    }

    @RequestMapping(value = "/search/{searchRequest}")
    public List<Product> searchProduct(@PathVariable("searchRequest") String searchRequest) {
        return productService.searchProduct(searchRequest);
    }

    @RequestMapping(value = "/get_top_five")
    public List<Product> getTopFive() {
        return productService.getTopFive();
    }
}
