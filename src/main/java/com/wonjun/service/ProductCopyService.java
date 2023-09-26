package com.wonjun.service;

import com.wonjun.model.entity.Product;
import com.wonjun.model.entity.Product_copy;
import com.wonjun.repository.ProductCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCopyService {
    @Autowired
    ProductCopyRepository productCopyRepository;

    public void addProductCopy(Product p_code, String pc_size, int pc_quantity) {
        Product_copy productCopy = new Product_copy();
        productCopy.setP_code(p_code);
        productCopy.setPc_size(pc_size);
        productCopy.setPc_quantity(pc_quantity);

        productCopyRepository.save(productCopy);
    }

}
