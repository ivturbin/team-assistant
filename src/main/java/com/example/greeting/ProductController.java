package com.example.greeting;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final List<Product> PRODUCTS = List.of(
            new Product(1L, "Ноутбук Air", "Надёжный ноутбук для дома и работы", new BigDecimal("54990"), "RUB", "https://placehold.co/800x450?text=NocTIP", true),
            new Product(2L, "Монитор 27 дюймов", "Монитор для работы с кодом", new BigDecimal("21990"), "RUB", "https://placehold.co/800x450?text=NocTIP", true),
            new Product(3L, "Клавиатура механическая", "Механическая клавиатура для набора текста", new BigDecimal("8490"), "RUB", "https://placehold.co/800x450?text=NocTIP", true),
            new Product(4L, "Мышь беспроводная", "Беспроводная мышь для компьютера", new BigDecimal("1990"), "RUB", "https://placehold.co/800x450?text=NocTIP", false)
    );

    @GetMapping
    public List<Product> getProducts() {
        return PRODUCTS;
    }
}
