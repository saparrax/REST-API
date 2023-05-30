package com.mca.app.service;

import com.mca.app.service.dto.Product;
import com.mca.app.service.dto.Size;
import com.mca.app.service.impl.CsvReaderServiceImpl;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvReaderServiceImplTest {
    private CsvReaderServiceImpl csvReaderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        csvReaderService = new CsvReaderServiceImpl();
    }

    @Test
    void testGetProductsFromCsv() throws IOException, CsvValidationException {
        //Arrange
        String path = "/data/csv/test/product-test.csv";
        List<Product> products = csvReaderService.getProductsFromCsv(path);

        //Assert
        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getId());
        assertEquals(10, products.get(0).getSequence());
        assertEquals(2L, products.get(1).getId());
        assertEquals(7, products.get(1).getSequence());
    }

    @Test
    void testGetSizeFromCsv() throws IOException, CsvValidationException {
        //Arrange
        String path = "/data/csv/test/size-test.csv";
        Map<Long,List<Size>> sizes = csvReaderService.getSizesFromCsv(path);

        //Assert
        assertEquals(1, sizes.size());
        assertEquals(2, sizes.get(1L).size());
        assertEquals(11L, sizes.get(1L).get(0).getId());
        assertEquals(true, sizes.get(1L).get(0).getBackSoon());
        assertEquals(false, sizes.get(1L).get(0).getSpecial());
        assertEquals(12L, sizes.get(1L).get(1).getId());
        assertEquals(false, sizes.get(1L).get(1).getBackSoon());
        assertEquals(false, sizes.get(1L).get(1).getSpecial());
    }

    @Test
    void testGetStockFromCsv() throws IOException, CsvValidationException {
        //Arrange
        String path = "/data/csv/test/stock-test.csv";
        Map<Long,Integer> stock = csvReaderService.getStockFromCsv(path);

        //Assert
        assertEquals(2, stock.size());
        assertEquals(0, stock.get(11L));
        assertEquals(0, stock.get(12L));
    }
}