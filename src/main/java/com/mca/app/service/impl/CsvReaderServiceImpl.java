package com.mca.app.service.impl;

import com.mca.app.service.CsvReaderService;
import com.mca.app.service.dto.Product;
import com.mca.app.service.dto.Size;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service Implementation for managing CSV
 */
@Service
public class CsvReaderServiceImpl implements CsvReaderService {

    private final Logger log = LoggerFactory.getLogger(CsvReaderServiceImpl.class);
    private static final String CSV_ERROR_INVALID_DATA = "Invalid data format";
    private static final String CSV_ERROR_READING_DATA ="Error reading data";

    public CsvReaderServiceImpl() {}

    @Override
    public List<Product> getProductsFromCsv(String path) throws CsvValidationException, IOException {
        log.debug("Read Products from CSV");
        List<Product> products = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource(path);

        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            String[] nextLine;
            boolean skipFirst = true;
            while ((nextLine = reader.readNext()) != null) {

                if(skipFirst){
                    skipFirst = false;
                    continue;
                }

                if (nextLine.length >= 2) {
                    try {
                        Long id = Long.parseLong(nextLine[0]);
                        Integer sequence = Integer.parseInt(nextLine[1]);
                        products.add(new Product(id, sequence));
                    } catch (NumberFormatException e){
                        log.error(CSV_ERROR_INVALID_DATA, e);
                        throw new CsvValidationException();
                    }
                }
            }
        } catch (CsvValidationException e) {
            log.error(CSV_ERROR_READING_DATA, e);
            throw new CsvValidationException();
        } catch (IOException e) {
            log.error(CSV_ERROR_READING_DATA, e);
            throw new IOException();
        }
        return products;
    }

    @Override
    public Map<Long,List<Size>> getSizesFromCsv(String path) throws CsvValidationException, IOException {
        log.debug("Read Sizes from CSV");
        Map<Long,List<Size>> sizes = new HashMap<>();
        ClassPathResource resource = new ClassPathResource(path);

        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            String[] nextLine;
            boolean skipFirst = true;
            while ((nextLine = reader.readNext()) != null) {

                if(skipFirst){
                    skipFirst = false;
                    continue;
                }

                if (nextLine.length >= 4) {
                    try{
                        Long id = Long.parseLong(nextLine[0]);
                        Long productId = Long.parseLong(nextLine[1]);
                        Boolean backSoon = Boolean.valueOf(nextLine[2]);
                        Boolean special = Boolean.valueOf(nextLine[3]);
                        Size size = new Size(id, productId, backSoon, special);
                        sizes.computeIfAbsent(productId, k -> new ArrayList<>()).add(size);
                    } catch(NumberFormatException e){
                        log.error(CSV_ERROR_INVALID_DATA, e);
                        throw new CsvValidationException();
                    }
                }
            }
        } catch (CsvValidationException e) {
            log.error(CSV_ERROR_READING_DATA, e);
            throw new CsvValidationException();
        } catch (IOException e) {
            log.error(CSV_ERROR_READING_DATA, e);
            throw new IOException();
        }
        return sizes;
    }

    @Override
    public Map<Long, Integer> getStockFromCsv(String path) throws IOException, CsvValidationException {
        log.debug("Read Stocks from CSV");
        Map<Long, Integer> stocks = new HashMap<>();
        ClassPathResource resource = new ClassPathResource(path);

        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            String[] nextLine;
            boolean skipFirst = true;
            while ((nextLine = reader.readNext()) != null) {

                if(skipFirst){
                    skipFirst = false;
                    continue;
                }

                if (nextLine.length >= 2) {
                    try{
                        Long sizeId = Long.parseLong(nextLine[0]);
                        Integer quantity = Integer.parseInt(nextLine[1]);
                        stocks.put(sizeId, quantity);
                    } catch (NumberFormatException e){
                        log.error(CSV_ERROR_INVALID_DATA, e);
                        throw new CsvValidationException();
                    }
                }
            }
        } catch (CsvValidationException e) {
            log.error(CSV_ERROR_READING_DATA, e);
            throw new CsvValidationException();
        } catch (IOException e) {
            log.error(CSV_ERROR_READING_DATA, e);
            throw new IOException();
        }
        return stocks;
    }
}
