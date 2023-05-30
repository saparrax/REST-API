package com.mca.app.controller;

import com.mca.app.service.VisibilityService;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * REST controller for managing visibility
 */
@RestController
@RequestMapping("/api")
public class VisibilityController {

    private final Logger log = LoggerFactory.getLogger(VisibilityController.class);
    private final VisibilityService visibilityService;

    public VisibilityController(VisibilityService visibilityService){
        this.visibilityService = visibilityService;
    }

    /**
     *{GET /products}
     *
     * @return the List of visible Products ordered by sequence
     * @throws CsvValidationException
     * @throws IOException
     */
    @GetMapping("/products")
    public ResponseEntity<List<Long>> getVisibleProducts() throws CsvValidationException, IOException {
        log.debug("REST request to get visible products");
        List<Long> orderedProducts = visibilityService.getVisibleProductsOrdered();
        return ResponseEntity.ok(orderedProducts);
    }
}
