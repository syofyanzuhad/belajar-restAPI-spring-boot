package com.domain.controllers;

import javax.validation.Valid;

import com.domain.dto.ResponseData;
import com.domain.model.entities.Product;
import com.domain.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseData> create(@Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            responseData.setSuccess(false);
            responseData.setMessages(errors.getAllErrors().stream().map(x -> x.getDefaultMessage())
                    .collect(java.util.stream.Collectors.toList()));
            return ResponseEntity.badRequest().body(responseData);
        }
        
        responseData.setSuccess(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }
    
    @GetMapping()
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable Long id) {
        return productService.findOne(id);
    }

    @PutMapping()
    public ResponseEntity<ResponseData> update(@Valid @RequestBody Product product, Errors errors) {

        if (errors.hasErrors()) {
            ResponseData<Product> responseData = new ResponseData<>();
            responseData.setSuccess(false);
            responseData.setMessages(errors.getAllErrors().stream().map(x -> x.getDefaultMessage())
                    .collect(java.util.stream.Collectors.toList()));
            return ResponseEntity.badRequest().body(responseData);
        }

        ResponseData<Product> responseData = new ResponseData<>();
        responseData.setSuccess(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.removeOne(id);
    }
}
