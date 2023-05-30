package com.mca.app.service.impl;

import com.mca.app.service.CsvReaderService;
import com.mca.app.service.VisibilityService;
import com.mca.app.service.dto.Product;
import com.mca.app.service.dto.Size;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing visibility
 */
@Service
public class VisibilityServiceImpl implements VisibilityService {

    private final Logger log = LoggerFactory.getLogger(VisibilityServiceImpl.class);

    @Value("${csv.files.path.product}")
    private String productFile;
    @Value("${csv.files.path.size}")
    private String sizeFile;
    @Value("${csv.files.path.stock}")
    private String stockFile;

    private final CsvReaderService csvReaderService;


    public VisibilityServiceImpl(CsvReaderService csvReaderService) {
        this.csvReaderService = csvReaderService;
    }

    public List<Product> getVisibleProducts() throws CsvValidationException, IOException {
        log.debug("Get visible Products from CSV");
        try {
            List<Product> products = csvReaderService.getProductsFromCsv(productFile);
            Map<Long, List<Size>> sizes = csvReaderService.getSizesFromCsv(sizeFile);
            Map<Long, Integer> stocks = csvReaderService.getStockFromCsv(stockFile);

            List<Product> visibleProducts = products.stream()
                    .filter(product -> sizes.containsKey(product.getId()))
                    .filter(product -> {
                        List<Size> sizesByProduct = sizes.get(product.getId());
                        return sizesByProduct.stream()
                                .anyMatch(size -> {
                                    Boolean backSoon = size.getBackSoon();
                                    Boolean special = size.getSpecial();
                                    Long sizeId = size.getId();
                                    Integer stock = stocks.getOrDefault(sizeId, 0);
                                    return backSoon && !special || (!backSoon && !special && stock > 0);
                                });
                    })
                    .collect(Collectors.toList());

            return visibleProducts;
        } catch (IOException | CsvValidationException ex) {
            log.error("Error occurred while retrieving visible products: {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public List<Long> getVisibleProductsOrdered() throws CsvValidationException, IOException {
        log.debug("Get visible Products from CSV ordered");
        try {
            List<Product> visibleProducts = getVisibleProducts();
            return sortProductsBySequence(visibleProducts);
        } catch (IOException | CsvValidationException ex) {
            log.error("Error occurred while retrieving and ordering visible products: {}", ex.getMessage());
            throw ex;
        }
    }

    public List<Long> sortProductsBySequence(List<Product>products){
        log.debug("Sorting Products");
        products.sort(Comparator.comparing(Product::getSequence));
        return products.stream().map(Product::getId).collect(Collectors.toList());
    }
}
