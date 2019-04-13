package com.epitech.pgt2019.service;

import com.epitech.pgt2019.domain.User;
import com.epitech.pgt2019.repository.UserRepository;
import com.epitech.pgt2019.service.dto.UserDTO;
import com.epitech.pgt2019.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing User.
 */
@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Save a user.
     *
     * @param userDTO the entity to save
     * @return the persisted entity
     */
    public UserDTO save(UserDTO userDTO) {
        log.debug("Request to save User : {}", userDTO);
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    /**
     * Get all the users.
     *
     * @return the list of entities
     */
    public List<UserDTO> findAll() {
        log.debug("Request to get all Users");
        return userRepository.findAll().stream()
            .map(userMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one user by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Optional<UserDTO> findOne(String id) {
        log.debug("Request to get User : {}", id);
        return userRepository.findById(id)
            .map(userMapper::toDto);
    }

    /**
     * Delete the user by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete User : {}", id);
        userRepository.deleteById(id);
    }
}
