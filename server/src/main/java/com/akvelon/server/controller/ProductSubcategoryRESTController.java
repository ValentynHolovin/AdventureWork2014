package com.akvelon.server.controller;

import com.akvelon.server.domain.ProductSubcategory;
import com.akvelon.server.service.api.ProductSubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductSubcategoryRESTController {
    @Autowired
    private ProductSubcategoryService productSubcategoryService;

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/get_subcategories")
    public List<ProductSubcategory> getProductSubcategories() {
        return productSubcategoryService.getAll();
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/subcategories/{subcategoryID}")
    public ProductSubcategory getProductSubcategory(@PathVariable("subcategoryID") Integer subcategoryID) {
        return productSubcategoryService.read(subcategoryID);
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/subcategories/create")
    public Integer createProductSubcategory(@RequestBody ProductSubcategory productSubcategory) {
        return productSubcategoryService.create(productSubcategory);
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/subcategories/update")
    public void updateProductSubcategory(@RequestBody ProductSubcategory productSubcategory) {
        productSubcategoryService.update(productSubcategory);
    }
}
