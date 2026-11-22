package com.example.greeting;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTests {

    private static final int EXPECTED_PRODUCT_COUNT = 4;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void productsShouldReturnOkJsonArray() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(EXPECTED_PRODUCT_COUNT)));
    }

    @Test
    void productShouldContainAllFieldsWithValidTypes() throws Exception {
        JsonNode catalog = objectMapper.readTree(getCatalogResponse());

        Assertions.assertEquals(EXPECTED_PRODUCT_COUNT, catalog.size());

        Set<Long> ids = new HashSet<>();
        for (JsonNode product : catalog) {
            Assertions.assertTrue(product.isObject());
            Assertions.assertEquals(7, product.size());

            Assertions.assertTrue(product.get("id").isNumber());
            long id = product.get("id").asLong();
            Assertions.assertTrue(id > 0);
            Assertions.assertTrue(ids.add(id));

            Assertions.assertTrue(product.get("name").isTextual());
            Assertions.assertTrue(product.get("description").isTextual());
            Assertions.assertTrue(product.get("price").isNumber());
            Assertions.assertTrue(product.get("currency").isTextual());
            Assertions.assertTrue(product.get("imageUrl").isTextual());
            Assertions.assertTrue(product.get("available").isBoolean());

            Assertions.assertEquals("RUB", product.get("currency").asText());
            Assertions.assertTrue(product.get("price").decimalValue().compareTo(BigDecimal.ZERO) > 0);
        }
    }

    @Test
    void productCatalogShouldBeStableBetweenRequests() throws Exception {
        String first = getCatalogResponse();
        String second = getCatalogResponse();

        Assertions.assertEquals(first, second);
    }

    private String getCatalogResponse() throws Exception {
        return mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
    }
}
