package ru.gb.gbthymeleaf.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbthymeleaf.dao.CartDao;
import ru.gb.gbthymeleaf.dao.ProductDao;
import ru.gb.gbthymeleaf.entity.Cart;
import ru.gb.gbthymeleaf.entity.Product;

import java.util.*;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService implements Consumer<Product> {
//    private final HashMap<Product, Integer> cart = new HashMap<>();
    private final CartDao cartDao;
    private final Cart cart;


    public Cart add(Product product) {
        if (cart.getId() == null){
            createCart();
        }
        Optional<Cart> cartOptional = cartDao.findById(cart.getId());
        if (cartOptional.isPresent()){
            Cart cartFromDB = cartOptional.get();
            cartFromDB.addProduct(product);
            return cartDao.save(cartFromDB);
        }
        return null;
    }

    public void deleteByProductId(Product product) {
        Optional<Cart> cartOptional = cartDao.findById(cart.getId());
        if (cartOptional.isPresent()) {
            Cart cartFromDB = cartOptional.get();
            cartFromDB.deleteProduct(product);
            cartDao.save(cartFromDB);
        }
    }

    public Set<Product> showProductList() {
        if (cart.getId() == null){
            createCart();
        }
        Optional<Cart> cartOptional = cartDao.findById(cart.getId());
        Cart cartFromDB = cartOptional.get();
        return cartFromDB.getProducts();
    }

    private void createCart(){
        cartDao.save(cart);
    }


    @Override
    public void accept(Product product) {
        add(product);
    }
}
