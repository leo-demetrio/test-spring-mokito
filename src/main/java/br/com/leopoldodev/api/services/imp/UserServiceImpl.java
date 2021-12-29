package br.com.leopoldodev.api.services.imp;

import br.com.leopoldodev.api.domain.User;
import br.com.leopoldodev.api.domain.dto.UserDTO;
import br.com.leopoldodev.api.repositories.UserRepository;
import br.com.leopoldodev.api.services.UserService;
import br.com.leopoldodev.api.services.exceptions.DataIntegrityViolationException;
import br.com.leopoldodev.api.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private UserRepository repository;
    private ModelMapper modelMapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(modelMapper.map(userDTO, User.class));
    }

    @Override
    public User update(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(modelMapper.map(userDTO, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private void findByEmail(UserDTO obj) {
        Optional<User> user = repository.findByEmail(obj.getEmail());
        if(user.isPresent() && !user.get().getId().equals(obj.getId())) {
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
    }


}
