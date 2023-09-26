package com.wonjun.controller;

import com.wonjun.model.entity.Cart;
import com.wonjun.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    @PostAuthorize("isAuthenticated()")
    public String addCart(@RequestParam (value = "p_code") String p_code,
                          @RequestParam (value = "pc_size") String pc_size,
                          @RequestParam (value = "p_name") String p_name,
                          Principal principal) {

        String m_id = principal.getName();
        cartService.add(Long.parseLong(p_code), m_id, pc_size, p_name);
        return "redirect:/cart/list";
    }

    @GetMapping("/list")
    @PostAuthorize("isAuthenticated()")
    public String cartList(Model model, Principal principal) {
        List<Cart> cartList = cartService.cartList(principal.getName());
        model.addAttribute("cartList", cartList);

        return "cartList";
    }

    @PostMapping("remove")
    @PostAuthorize("isAuthenticated()")
    public String remove(@RequestParam (value = "c_id") String id){
        cartService.remove(Long.parseLong(id));
        return "redirect:/cart/list";
    }
}
