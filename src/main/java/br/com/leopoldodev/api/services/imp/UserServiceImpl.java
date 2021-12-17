package br.com.leopoldodev.api.services.imp;

import br.com.leopoldodev.api.domain.User;
import br.com.leopoldodev.api.repositories.UserRepository;
import br.com.leopoldodev.api.services.UserService;
import br.com.leopoldodev.api.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private UserRepository repository;
    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }
}
