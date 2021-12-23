package br.com.leopoldodev.api.services.imp;

import br.com.leopoldodev.api.domain.User;
import br.com.leopoldodev.api.domain.dto.UserDTO;
import br.com.leopoldodev.api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

//import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Leo";
    public static final String EMAIL = "leo@gmail.com";
    public static final String PASSWORD = "123";

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private ModelMapper modelMapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }


    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        User user = userServiceImpl.findById(ID);
        assertNotNull(user);
        assertEquals(User.class, user.getClass());
        assertEquals(ID, user.getId());
        assertEquals(NAME, user.getName());
        assertEquals(EMAIL, user.getEmail());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}