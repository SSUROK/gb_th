package ru.gb.gbthymeleaf.controller;

import org.springframework.stereotype.Component;
import ru.gb.gbthymeleaf.service.CartService;

public interface ShopController {
    CartService getCart();
}
