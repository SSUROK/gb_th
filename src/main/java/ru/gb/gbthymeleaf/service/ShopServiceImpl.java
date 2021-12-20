package ru.gb.gbthymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.gbthymeleaf.controller.ShopController;
import ru.gb.gbthymeleaf.dao.CartDao;
import ru.gb.gbthymeleaf.dao.ProductDao;
import ru.gb.gbthymeleaf.entity.Product;

import java.util.HashMap;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ShopServiceImpl implements ShopController {
    private final ProductDao productDao;
    private final CartDao cartDao;
    private CartService cart;

    @Lookup
    @Override
    public CartService getCart() {
        return null;
    }

    public void showAssortment() {
        System.out.println(productDao.findAll());
    }

    public void addProductFromCartById(Long id) {
         productDao.findById(id).ifPresent(cart);
    }

    public void deleteProductFromCartById(Long id) {
        System.out.println(productDao.getById(id));
        cart.deleteByProductId(productDao.getById(id));
    }

    public Set<Product> showProductsInCart() {
        return cart.showProductList();
    }

    public void enterToShop() {
        this.cart = getCart();
    }
}