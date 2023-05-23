package com.hexagonal.architecture.infrastructure.resources;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Stream;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.hexagonal.architecture.infrastructure.config.SpringBootService;
import com.hexagonal.architecture.infrastructure.entity.ProductEntity;
import com.hexagonal.architecture.infrastructure.adapters.SpringDataProductRepository;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootService.class})
class ProductResourcesIT {

    private static final String ENTITY_API_URL = "/api/products";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_API_URL_ID_BRAND_ID = ENTITY_API_URL_ID + "/brands/{brandId}";
    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;
    private static final Long PRODUCT_ID_NOT_FOUND = 99999L;
    private static final Long BRAND_ID_ID_NOT_FOUND = 9L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpringDataProductRepository productRepository;

    @ParameterizedTest
    @MethodSource("testProductsOk")
    @Transactional
    void getProductsByIdAndBrandId(List<Long> priceIds, String applicationDate, int size) throws Exception {
        ProductEntity productEntity = productRepository.findById(PRODUCT_ID).get();

        RequestBuilder request = MockMvcRequestBuilders
                .get(ENTITY_API_URL_ID_BRAND_ID, PRODUCT_ID, BRAND_ID)
                .param("applicationDate", applicationDate)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(productEntity.getId().toString()))
                .andExpect(jsonPath("$.price").isArray())
                .andExpect(jsonPath("$.price", Matchers.hasSize(size)))
                .andExpect(jsonPath("$.price.[*].id").value(Matchers.containsInAnyOrder(priceIds.toArray())))
                .andExpect(jsonPath("$.price.[0].brand.id").value(1))
                .andExpect(jsonPath("$.price.[0].brand.code").value("ZA"))
                .andExpect(jsonPath("$.price.[0].brand.name").value("ZARA"));
    }

    private static Stream<Arguments> testProductsOk() {
        return Stream.of(
                Arguments.of(List.of(1), "2020-06-14 10:00:00", 1),
                Arguments.of(List.of(1,2), "2020-06-14 16:00:00", 2),
                Arguments.of(List.of(1), "2020-06-14 21:00:00", 1),
                Arguments.of(List.of(1,3), "2020-06-15 10:00:00", 2),
                Arguments.of(List.of(1,4), "2020-06-16 21:00:00", 2)
        );
    }

    @ParameterizedTest
    @MethodSource("testProductsNoSuchElementException")
    @Transactional
    void getProductsThrowsNoSuchElementException_ApplicationDateNotFound(Long productId, Long brandId, String applicationDate) throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get(ENTITY_API_URL_ID_BRAND_ID, productId, brandId)
                .param("applicationDate", applicationDate)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    private static Stream<Arguments> testProductsNoSuchElementException() {
        return Stream.of(
                Arguments.of(PRODUCT_ID, BRAND_ID, "2099-06-14 10:00:00"),
                Arguments.of(PRODUCT_ID_NOT_FOUND, BRAND_ID, "2020-06-14 16:00:00"),
                Arguments.of(PRODUCT_ID, BRAND_ID_ID_NOT_FOUND, "2020-06-14 21:00:00"),
                Arguments.of(PRODUCT_ID_NOT_FOUND, BRAND_ID_ID_NOT_FOUND, "2020-06-15 10:00:00")
        );
    }



}