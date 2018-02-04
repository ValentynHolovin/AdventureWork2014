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
    @Autowired
    private ProductSubcategoryService productSubcategoryService;
    @Autowired
    private ProductModelService productModelService;
    @Autowired
    private UnitMeasureService unitMeasureService;

    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = "/search/{searchRequest}/{count}")
    public List<Product> searchProduct(@PathVariable("searchRequest") String searchRequest, @PathVariable("count") Integer count) {
        List<Product> products = productService.searchProduct(searchRequest);
        List<Product> result = new ArrayList<>();

        for (int i = 0; i < (count + 1) * 20; i++) {
            if (i < products.size()) {
                result.add(products.get(i));
            } else {
                break;
            }
        }

        return result;
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

    @RequestMapping(value = "/get_subcategories")
    public List<ProductSubcategory> getProductSubcategories() {
        return productSubcategoryService.getAll();
    }

    @RequestMapping(value = "/get_models")
    public List<ProductModel> getProductModels() {
        return productModelService.getAll();
    }

    @RequestMapping(value = "/get_unitmeasurecode")
    public List<UnitMeasure> getUnitMeasureCodes() {
        return unitMeasureService.getAll();
    }

}
