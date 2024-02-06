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

    private static final String JSON_PRICE_KEY = "$.price";
    private static final String JSON_PRODUCTID_KEY = "$.productId";
    private static final String JSON_PRICELIST_KEY = "$.priceList";
    private static final String JSON_BRANDID_KEY = "$.brandId";
    private static final String JSON_STARTDATE_KEY = "$.startDate";
    private static final String JSON_ENDDATE_KEY = "$.endDate";
    private static final int TEST_BRAND_ID = 1;
    private static final int TEST_PRODUCT_ID = 35455;
    private static final int TEST_PRICELIST_ID1 = 1;
    private static final int TEST_PRICELIST_ID2 = 2;
    private static final int TEST_PRICELIST_ID3 = 3;
    private static final int TEST_PRICELIST_ID4 = 4;

    private static final double PRICE_TEST1 = 35.50;
    private static final double PRICE_TEST2 = 25.45;
    private static final double PRICE_TEST4 = 30.5;
    private static final double PRICE_TEST5 = 38.95;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test 1: Request at 10:00 on 14th for product 35455 and brand 1 (ZARA)")
    public void testPriceListAt10On14th() throws Exception {
        mockMvc.perform(get("/pricelist/brands/"+TEST_BRAND_ID+"/products/"+TEST_PRODUCT_ID).queryParam("date", "2020-06-14-10.00.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PRICE_KEY, is(PRICE_TEST1)))
                .andExpect(jsonPath(JSON_PRODUCTID_KEY, is(TEST_PRODUCT_ID)))
                .andExpect(jsonPath(JSON_PRICELIST_KEY, is(TEST_PRICELIST_ID1)))
                .andExpect(jsonPath(JSON_BRANDID_KEY, is(TEST_BRAND_ID)))
                .andExpect(jsonPath(JSON_STARTDATE_KEY, is("2020-06-14T00:00:00")))
                .andExpect(jsonPath(JSON_ENDDATE_KEY, is("2020-12-31T23:59:59")));
    }

    @Test
    @DisplayName("Test 2: Request at 16:00 on 14th for product 35455 and brand 1 (ZARA)")
    public void testPriceListAt16On14th() throws Exception {
        mockMvc.perform(get("/pricelist/brands/"+TEST_BRAND_ID+"/products/"+TEST_PRODUCT_ID).queryParam("date", "2020-06-14-16.00.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PRICE_KEY, is(PRICE_TEST2)))
                .andExpect(jsonPath(JSON_PRODUCTID_KEY, is(TEST_PRODUCT_ID)))
                .andExpect(jsonPath(JSON_PRICELIST_KEY, is(TEST_PRICELIST_ID2)))
                .andExpect(jsonPath(JSON_BRANDID_KEY, is(TEST_BRAND_ID)))
                .andExpect(jsonPath(JSON_STARTDATE_KEY, is("2020-06-14T15:00:00")))
                .andExpect(jsonPath(JSON_ENDDATE_KEY, is("2020-06-14T18:30:00")));

    }

    @Test
    @DisplayName("Test 3: Request at 21:00 on 14th for product 35455 and brand 1 (ZARA)")
    public void testPriceListAt21On14th() throws Exception {
        mockMvc.perform(get("/pricelist/brands/"+TEST_BRAND_ID+"/products/"+TEST_PRODUCT_ID).queryParam("date", "2020-06-14-21.00.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PRICE_KEY, is(PRICE_TEST1)))
                .andExpect(jsonPath(JSON_PRODUCTID_KEY, is(TEST_PRODUCT_ID)))
                .andExpect(jsonPath(JSON_PRICELIST_KEY, is(TEST_PRICELIST_ID1)))
                .andExpect(jsonPath(JSON_BRANDID_KEY, is(TEST_BRAND_ID)))
                .andExpect(jsonPath(JSON_STARTDATE_KEY, is("2020-06-14T00:00:00")))
                .andExpect(jsonPath(JSON_ENDDATE_KEY, is("2020-12-31T23:59:59")));


    }

    @Test
    @DisplayName("Test 4: Request at 10:00 on 15th for product 35455 and brand 1 (ZARA)")
    public void testPriceListAt10On15th() throws Exception {
        mockMvc.perform(get("/pricelist/brands/"+TEST_BRAND_ID+"/products/"+TEST_PRODUCT_ID).queryParam("date", "2020-06-15-10.00.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PRICE_KEY, is(PRICE_TEST4)))
                .andExpect(jsonPath(JSON_PRODUCTID_KEY, is(TEST_PRODUCT_ID)))
                .andExpect(jsonPath(JSON_PRICELIST_KEY, is(TEST_PRICELIST_ID3)))
                .andExpect(jsonPath(JSON_BRANDID_KEY, is(TEST_BRAND_ID)))
                .andExpect(jsonPath(JSON_STARTDATE_KEY, is("2020-06-15T00:00:00")))
                .andExpect(jsonPath(JSON_ENDDATE_KEY, is("2020-06-15T11:00:00")));

    }

    @Test
    @DisplayName("Test 5: Request at 21:00 on 16th for product 35455 and brand 1 (ZARA)")
    public void testPriceListAt21On16th() throws Exception {
        mockMvc.perform(get("/pricelist/brands/"+TEST_BRAND_ID+"/products/"+TEST_PRODUCT_ID).queryParam("date", "2020-06-16-21.00.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(JSON_PRICE_KEY, is(PRICE_TEST5)))
                .andExpect(jsonPath(JSON_PRODUCTID_KEY, is(TEST_PRODUCT_ID)))
                .andExpect(jsonPath(JSON_PRICELIST_KEY, is(TEST_PRICELIST_ID4)))
                .andExpect(jsonPath(JSON_BRANDID_KEY, is(TEST_BRAND_ID)))
                .andExpect(jsonPath(JSON_STARTDATE_KEY, is("2020-06-15T16:00:00")))
                .andExpect(jsonPath(JSON_ENDDATE_KEY, is("2020-12-31T23:59:59")));

    }

    @Test
    @DisplayName("Test: Request with Invalid Argument Type")
    public void testMethodArgumentTypeMismatchException() throws Exception {
        mockMvc.perform(get("/pricelist/brands/"+TEST_BRAND_ID+"/products/invalidArgument"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.description", containsString("Argument type error")));
    }

    @Test
    @DisplayName("Test: Request with Invalid Date Format")
    public void testInvalidDateFormatException() throws Exception {
        mockMvc.perform(get("/pricelist/brands/"+TEST_BRAND_ID+"/products/"+TEST_PRODUCT_ID).queryParam("date", "invalid-date-format"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.description", containsString("Invalid date format")));
    }

    @Test
    @DisplayName("Test: Request Not price found")
    public void testNoPriceFound() throws Exception {
        mockMvc.perform(get("/pricelist/brands/"+TEST_BRAND_ID+"/products/99999").queryParam("date", "2020-06-16-21.00.00"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.description", containsString("Price not found")));
    }

}
