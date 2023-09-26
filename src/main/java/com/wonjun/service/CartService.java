package com.wonjun.service;

import com.wonjun.model.entity.Cart;
import com.wonjun.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public void add(Long p_code, String m_id, String pc_size, String p_name) {
        Cart cart = new Cart();
        cart.setP_code(p_code);
        cart.setMemberId(m_id);
        cart.setPc_size(pc_size);
        cart.setPc_quantity(1);
        cart.setP_name(p_name);

        cartRepository.save(cart);
    }

    public List<Cart> cartList(String m_id) {
        return cartRepository.findByMemberId(m_id);
    }

    public void remove(Long id){
        cartRepository.deleteById(id);
    }
}
