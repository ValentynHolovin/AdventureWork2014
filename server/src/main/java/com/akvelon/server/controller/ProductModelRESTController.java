package com.akvelon.server.controller;

import com.akvelon.server.domain.ProductModel;
import com.akvelon.server.service.api.ProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductModelRESTController {
    @Autowired
    private ProductModelService productModelService;

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/get_models")
    public List<ProductModel> getProductModels() {
        return productModelService.getAll();
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/models/{modelID}")
    public ProductModel getProductModel(@PathVariable("modelID") Integer modelID) {
        return productModelService.read(modelID);
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/models/create")
    public Integer createProductModel(@RequestBody ProductModel productModel) {
        return productModelService.create(productModel);
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/models/update")
    public void updateProductModel(@RequestBody ProductModel productModel) {
        productModelService.update(productModel);
    }
}
