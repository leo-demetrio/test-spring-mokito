package br.com.leopoldodev.api.services.imp;

import br.com.leopoldodev.api.domain.User;
import br.com.leopoldodev.api.domain.dto.UserDTO;
import br.com.leopoldodev.api.repositories.UserRepository;
import br.com.leopoldodev.api.services.exceptions.DataIntegrityViolationException;
import br.com.leopoldodev.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Leo";
    public static final String EMAIL = "leo@gmail.com";
    public static final String PASSWORD = "123";
    public static final String MESSAGE = "Usuário não encontrado";
    public static final String EMAIL_JA_CADASTRADO = "Email já cadastrado";

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
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(MESSAGE));
        try {
            userServiceImpl.findById(ID);
        } catch(Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(MESSAGE, e.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));
        List<User> listUser = userServiceImpl.findAll();
        assertNotNull(listUser);
        assertEquals(1, listUser.size());
        assertEquals(User.class, listUser.get(0).getClass());
        assertEquals(ID, listUser.get(0).getId());
        assertEquals(NAME, listUser.get(0).getName());
        assertEquals(EMAIL, listUser.get(0).getEmail());

    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);
        User user = userServiceImpl.create(userDTO);
        assertNotNull(user);
        assertEquals(User.class, user.getClass());
        testeUser(user);
        assertEquals(ID, user.getId());
        assertEquals(NAME, user.getName());
        assertEquals(EMAIL, user.getEmail());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);
        try {
            optionalUser.get().setId(2);
            userServiceImpl.create(userDTO);
        }catch (Exception e) {
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals(EMAIL_JA_CADASTRADO,e.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);
        User user = userServiceImpl.update(userDTO);
        assertNotNull(user);
        assertEquals(User.class, user.getClass());
        testeUser(user);
        assertEquals(ID, user.getId());
        assertEquals(NAME, user.getName());
        assertEquals(EMAIL, user.getEmail());
    }
    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);
        try {
            optionalUser.get().setId(2);
            userServiceImpl.create(userDTO);
        }catch (Exception e) {
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals(EMAIL_JA_CADASTRADO,e.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyInt());
        userServiceImpl.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWhitObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
        try {
            userServiceImpl.delete(ID);
        } catch(Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Objeto não encontrado",e.getMessage());
        }
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
    private void testeUser(User user) {
        assertEquals(ID, user.getId());
        assertEquals(NAME, user.getName());
        assertEquals(EMAIL, user.getEmail());
    }
}