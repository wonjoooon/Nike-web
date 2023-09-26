package com.wonjun.service;

import com.wonjun.model.entity.Product;
import com.wonjun.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getProductList() throws DataAccessException {
        List<Product> productList = productRepository.findAll();
        return productList;
    }
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProduct(Long p_code) {
        return productRepository.getReferenceById(p_code);
    }
}
