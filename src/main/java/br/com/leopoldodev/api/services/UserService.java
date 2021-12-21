package br.com.leopoldodev.api.services;

import br.com.leopoldodev.api.domain.User;
import br.com.leopoldodev.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO obj);
    User update(UserDTO userDTO);
    void delete(Integer id);
}
