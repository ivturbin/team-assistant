package com.example.greeting;

import java.math.BigDecimal;

public record Product(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String currency,
        String imageUrl,
        boolean available
) {
}
