package com.mca.app.service;

import com.mca.app.service.dto.Product;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;

/**
 * Service Interface for managing
 */
public interface VisibilityService {

    /**
     *
     * @return the List of visible Products
     * @throws CsvValidationException
     * @throws IOException
     */
    List<Product> getVisibleProducts() throws CsvValidationException, IOException;

    /**
     *
     * @return the List of visible products ordered
     * @throws CsvValidationException
     * @throws IOException
     */
    List<Long> getVisibleProductsOrdered() throws CsvValidationException, IOException;

    /**
     *
     * @param products
     * @return sort a List of products by sequence
     */
    List<Long> sortProductsBySequence(List<Product>products);
}
