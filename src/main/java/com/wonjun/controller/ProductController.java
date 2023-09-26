package com.wonjun.controller;

import com.wonjun.model.ProductAddForm;
import com.wonjun.model.entity.Product;
import com.wonjun.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    String imagePath = "/images/";

    @GetMapping("/list")
    public String productList(Model model) {
        List<Product> productList = productService.getProductList();
        model.addAttribute("products", productList);
        model.addAttribute("path", imagePath);
        return "productList";
    }

    @GetMapping("/view")
    public String product(Model model, @RequestParam(value = "p_code") String p_code) {
        Product product = productService.getProduct(Long.parseLong(p_code));
        model.addAttribute("path", imagePath);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/productAddPage")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String productAddPage(ProductAddForm productAddForm) {
        return "productAddPage";
    }

    @PostMapping("/addProduct")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProduct(@Valid ProductAddForm productAddForm, BindingResult bindingResult,
                             @RequestPart(value = "p_img")MultipartFile image) throws IllegalStateException, IOException {

        String imageFileName = image.getOriginalFilename();
        String path = "C:/work/springBoot/shopping/src/main/resources/static/images/";
        Path imagePath = Paths.get(path + imageFileName);

        try { // 이미지 파일 저장
            Files.write(imagePath, image.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (bindingResult.hasErrors()) {
            return "productAddPage";
        }

        Product product = new Product();
        product.setP_name(productAddForm.getP_name());
        product.setP_category(productAddForm.getP_category());
        product.setP_img(imageFileName);
        product.setP_description(productAddForm.getP_description());
        product.setP_gender(productAddForm.getP_gender());
        product.setP_date(Date.valueOf(productAddForm.getP_date()));
        product.setP_location(productAddForm.getP_location());
        product.setP_price(productAddForm.getP_price());
        product.setP_color(productAddForm.getP_color());

        try {
            productService.addProduct(product);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("Add Product Failed", "이미 등록된 상품입니다.");
            return "productAddPage";
        }
        catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("Add Product Failed", e.getMessage());
            return "productAddPage";
        }

        return "redirect:/";
    }
}
