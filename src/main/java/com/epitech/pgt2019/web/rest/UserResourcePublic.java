package com.epitech.pgt2019.web.rest;

import com.epitech.pgt2019.service.UserService;
import com.epitech.pgt2019.service.dto.UserDTO;
import com.epitech.pgt2019.web.rest.errors.BadRequestAlertException;
import com.epitech.pgt2019.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Public REST controller for managing User.
 */
@RestController
@RequestMapping("/public")
public class UserResourcePublic {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private static final String ENTITY_NAME = "User";

    private final UserService userService;

    public UserResourcePublic(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST  /users : Create a new user.
     *
     * @param userDTO the userDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userDTO, or with status 400 (Bad Request) if the user has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);
        UserDTO result = userService.save(userDTO);
        return ResponseEntity.created(new URI("/api/users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
