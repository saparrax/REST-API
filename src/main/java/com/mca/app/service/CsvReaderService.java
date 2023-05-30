package com.mca.app.service;

import com.mca.app.service.dto.Product;
import com.mca.app.service.dto.Size;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Service Interface for managing
 */
public interface CsvReaderService {

    /**
     * @return the List of Products from CSV
     * @throws CsvValidationException
     * @throws IOException
     */
    List<Product> getProductsFromCsv(String path) throws IOException, CsvValidationException;

    /**
     *
     * @return the Map of Sizes mapped by ProductId
     * @throws CsvValidationException
     * @throws IOException
     */
    Map<Long,List<Size>> getSizesFromCsv(String path) throws CsvValidationException, IOException;

    /**
     *
     * @return the Map of Stock by size
     * @throws CsvValidationException
     * @throws IOException
     */
    Map<Long,Integer> getStockFromCsv(String path) throws IOException, CsvValidationException;
}
