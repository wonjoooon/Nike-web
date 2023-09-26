package com.wonjun.controller;

import com.wonjun.model.ProductCopyForm;
import com.wonjun.model.entity.Product;
import com.wonjun.service.ProductCopyService;
import com.wonjun.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/stock")
public class ProductCopyController {
    @Autowired
    ProductCopyService productCopyService;
    @Autowired
    ProductService productService;
    String imagePath = "/images/";

    @GetMapping("/addPage")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String stockAddPage(Model model, @RequestParam(value = "p_code") String p_code, ProductCopyForm productCopyForm) {
        Product product = productService.getProduct(Long.parseLong(p_code));
        model.addAttribute("product", product);
        model.addAttribute("path", imagePath);
        return "stockAddPage";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String stockAdd(@Valid ProductCopyForm productCopyForm, BindingResult bindingResult,
                           @RequestParam(value = "p_code") String p_code) {
        if (bindingResult.hasErrors()) {
            return "redirect:/product/list";
        }
        Product product = productService.getProduct(Long.parseLong(p_code));
        productCopyService.addProductCopy(product, productCopyForm.getPc_size(), Integer.parseInt(productCopyForm.getPc_quantity()));
        return "redirect:/product/list";
    }
}
