package com.testemilio.pricelistservice.application.api;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PriceApiIntegrationTest {

    public static final String JSON_PRICE_KEY = "$.price";
    public static final String JSON_PRODUCTID_KEY = "$.productId";
    public static final String JSON_PRICELIST_KEY = "$.priceList";
    public static final String JSON_BRANDID_KEY = "$.brandId";
    public static final String JSON_STARTDATE_KEY = "$.startDate";
    public static final String JSON_ENDDATE_KEY = "$.endDate";
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test 1: Request at 10:00 on 14th for product 35455 and brand 1 (ZARA)")
    public void testPriceListAt10On14th() throws Exception {
        mockMvc.perform(get("/priceList/1/35455/2020-06-14-10.00.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PRICE_KEY, is(35.50)))
                .andExpect(jsonPath(JSON_PRODUCTID_KEY, is(35455)))
                .andExpect(jsonPath(JSON_PRICELIST_KEY, is(1)))
                .andExpect(jsonPath(JSON_BRANDID_KEY, is(1)))
                .andExpect(jsonPath(JSON_STARTDATE_KEY, is("2020-06-14T00:00:00")))
                .andExpect(jsonPath(JSON_ENDDATE_KEY, is("2020-12-31T23:59:59")));
    }

    @Test
    @DisplayName("Test 2: Request at 16:00 on 14th for product 35455 and brand 1 (ZARA)")
    public void testPriceListAt16On14th() throws Exception {
        mockMvc.perform(get("/priceList/1/35455/2020-06-14-16.00.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PRICE_KEY, is(25.45)))
                .andExpect(jsonPath(JSON_PRODUCTID_KEY, is(35455)))
                .andExpect(jsonPath(JSON_PRICELIST_KEY, is(2)))
                .andExpect(jsonPath(JSON_BRANDID_KEY, is(1)))
                .andExpect(jsonPath(JSON_STARTDATE_KEY, is("2020-06-14T15:00:00")))
                .andExpect(jsonPath(JSON_ENDDATE_KEY, is("2020-06-14T18:30:00")));

    }

    @Test
    @DisplayName("Test 3: Request at 21:00 on 14th for product 35455 and brand 1 (ZARA)")
    public void testPriceListAt21On14th() throws Exception {
        mockMvc.perform(get("/priceList/1/35455/2020-06-14-21.00.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PRICE_KEY, is(35.50)))
                .andExpect(jsonPath(JSON_PRODUCTID_KEY, is(35455)))
                .andExpect(jsonPath(JSON_PRICELIST_KEY, is(1)))
                .andExpect(jsonPath(JSON_BRANDID_KEY, is(1)))
                .andExpect(jsonPath(JSON_STARTDATE_KEY, is("2020-06-14T00:00:00")))
                .andExpect(jsonPath(JSON_ENDDATE_KEY, is("2020-12-31T23:59:59")));


    }

    @Test
    @DisplayName("Test 4: Request at 10:00 on 15th for product 35455 and brand 1 (ZARA)")
    public void testPriceListAt10On15th() throws Exception {
        mockMvc.perform(get("/priceList/1/35455/2020-06-15-10.00.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PRICE_KEY, is(30.5)))
                .andExpect(jsonPath(JSON_PRODUCTID_KEY, is(35455)))
                .andExpect(jsonPath(JSON_PRICELIST_KEY, is(3)))
                .andExpect(jsonPath(JSON_BRANDID_KEY, is(1)))
                .andExpect(jsonPath(JSON_STARTDATE_KEY, is("2020-06-15T00:00:00")))
                .andExpect(jsonPath(JSON_ENDDATE_KEY, is("2020-06-15T11:00:00")));

    }

    @Test
    @DisplayName("Test 5: Request at 21:00 on 16th for product 35455 and brand 1 (ZARA)")
    public void testPriceListAt21On16th() throws Exception {
        mockMvc.perform(get("/priceList/1/35455/2020-06-16-21.00.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PRICE_KEY, is(38.95)))
                .andExpect(jsonPath(JSON_PRODUCTID_KEY, is(35455)))
                .andExpect(jsonPath(JSON_PRICELIST_KEY, is(4)))
                .andExpect(jsonPath(JSON_BRANDID_KEY, is(1)))
                .andExpect(jsonPath(JSON_STARTDATE_KEY, is("2020-06-15T16:00:00")))
                .andExpect(jsonPath(JSON_ENDDATE_KEY, is("2020-12-31T23:59:59")));

    }
}
