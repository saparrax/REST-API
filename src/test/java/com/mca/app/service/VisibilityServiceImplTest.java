package com.mca.app.service;

import com.mca.app.service.dto.Product;
import com.mca.app.service.dto.Size;
import com.mca.app.service.impl.CsvReaderServiceImpl;
import com.mca.app.service.impl.VisibilityServiceImpl;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.when;

class VisibilityServiceImplTest {

    private VisibilityServiceImpl visibilityService;

    @Mock
    private CsvReaderServiceImpl csvReaderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        visibilityService = new VisibilityServiceImpl(csvReaderService);
    }

    @Test
    void testSortProducts() {
        //Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, 10));
        products.add(new Product(2L, 7));
        products.add(new Product(3L, 15));
        List<Long> expected = Arrays.asList(2L, 1L, 3L);

        //Act
        List<Long> result = visibilityService.sortProductsBySequence(products);

        //Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testVisibleProducts() throws CsvValidationException, IOException {
        //Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, 10));
        products.add(new Product(2L, 7));
        products.add(new Product(6L, 1));

        Map<Long, List<Size>> sizes = new HashMap<>();
        List<Size> listSizes1 = Arrays.asList(new Size(11L, 1L, true, false));
        List<Size> listSizes2 = Arrays.asList(new Size(12L, 2L, true, true));
        List<Size> listSizes3 = Arrays.asList(new Size(13L, 6L, false, false));
        sizes.put(1L, listSizes1);
        sizes.put(2L, listSizes2);
        sizes.put(6L, listSizes3);
        Map<Long, Integer> stocks = new HashMap<>();
        stocks.put(13L, 10);

        when(csvReaderService.getProductsFromCsv(null)).thenReturn(products);
        when(csvReaderService.getSizesFromCsv(null)).thenReturn(sizes);
        when(csvReaderService.getStockFromCsv(null)).thenReturn(stocks);

        //Act
        List<Product> listProducts = visibilityService.getVisibleProducts();

        //Assert
        Assertions.assertEquals(products.get(0).getId(), listProducts.get(0).getId());
        Assertions.assertEquals(products.get(2).getId(), listProducts.get(1).getId());

    }
}
