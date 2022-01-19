package com.nik.code.ecom.controllers;

import com.nik.code.ecom.dto.product.ProductDTO;
import com.nik.code.ecom.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("search")
@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping
    public List<ProductDTO> findProductsByKeyword(@RequestParam String keyword) {
        List<ProductDTO> productDTOS = searchService.searchProducts(keyword);
        return productDTOS;
    }
}
