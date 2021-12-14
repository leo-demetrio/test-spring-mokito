package br.com.leopoldodev.api.services;

import br.com.leopoldodev.api.domain.User;

public interface UserService {
    User findById(Integer id);
}
