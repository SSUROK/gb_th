package ru.gb.gbthymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.gbthymeleaf.entity.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final Buyer buyer;

    @RequestMapping(value="/add")
    public String addToCartProductByID(Model model, @RequestParam(name = "id") Long id){
        buyer.purchase(id);
        return "redirect:/cart/all";
    }

    @RequestMapping(path = "/all")
    public String showCart(Model model){
        Set<Product> cart = buyer.showCart();
        model.addAttribute("cart", cart);
        return "cart";
    }

    @RequestMapping(value="/remove")
    public String removeToCartProductByID(Model model, @RequestParam(name = "id") Long id){
        buyer.removeProduct(id);
        return "redirect:/cart/all";
    }
}
