package com.epitech.pgt2019.web.rest;
import com.epitech.pgt2019.service.CountryService;
import com.epitech.pgt2019.web.rest.errors.BadRequestAlertException;
import com.epitech.pgt2019.web.rest.util.HeaderUtil;
import com.epitech.pgt2019.service.dto.CountryDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Country.
 */
@RestController
@RequestMapping("/api")
public class CountryResource {

    private final Logger log = LoggerFactory.getLogger(CountryResource.class);

    private static final String ENTITY_NAME = "userAppMongoDbCountry";

    private final CountryService countryService;

    public CountryResource(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * POST  /countries : Create a new country.
     *
     * @param countryDTO the countryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new countryDTO, or with status 400 (Bad Request) if the country has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/countries")
    public ResponseEntity<CountryDTO> createCountry(@Valid @RequestBody CountryDTO countryDTO) throws URISyntaxException {
        log.debug("REST request to save Country : {}", countryDTO);
        if (countryDTO.getId() != null) {
            throw new BadRequestAlertException("A new country cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CountryDTO result = countryService.save(countryDTO);
        return ResponseEntity.created(new URI("/api/countries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /countries : Updates an existing country.
     *
     * @param countryDTO the countryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated countryDTO,
     * or with status 400 (Bad Request) if the countryDTO is not valid,
     * or with status 500 (Internal Server Error) if the countryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/countries")
    public ResponseEntity<CountryDTO> updateCountry(@Valid @RequestBody CountryDTO countryDTO) throws URISyntaxException {
        log.debug("REST request to update Country : {}", countryDTO);
        if (countryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CountryDTO result = countryService.save(countryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, countryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /countries : get all the countries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of countries in body
     */
    @GetMapping("/countries")
    public List<CountryDTO> getAllCountries() {
        log.debug("REST request to get all Countries");
        return countryService.findAll();
    }

    /**
     * GET  /countries/:id : get the "id" country.
     *
     * @param id the id of the countryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the countryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/countries/{id}")
    public ResponseEntity<CountryDTO> getCountry(@PathVariable String id) {
        log.debug("REST request to get Country : {}", id);
        Optional<CountryDTO> countryDTO = countryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(countryDTO);
    }

    /**
     * DELETE  /countries/:id : delete the "id" country.
     *
     * @param id the id of the countryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/countries/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable String id) {
        log.debug("REST request to delete Country : {}", id);
        countryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
