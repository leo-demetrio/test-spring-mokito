package br.com.leopoldodev.api.resources;

import br.com.leopoldodev.api.domain.User;
import br.com.leopoldodev.api.domain.dto.UserDTO;
import br.com.leopoldodev.api.services.imp.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;



@SpringBootTest
class UserResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Leo";
    public static final String EMAIL = "leo@gmail.com";
    public static final String PASSWORD = "123";
    public static final String MESSAGE = "Usuário não encontrado";
    public static final String EMAIL_JA_CADASTRADO = "Email já cadastrado";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";

    private User user;
    private UserDTO userDTO;


    @InjectMocks
    private UserResource userResource;

    @Mock
    private ModelMapper mapper;

    @Mock
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(userServiceImpl.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);
        ResponseEntity<UserDTO> userResponse = userResource.findById(ID);

        assertNotNull(userResponse);
        assertNotNull(userResponse.getBody());
        assertEquals(ResponseEntity.class, userResponse.getClass());
        assertEquals(UserDTO.class, userResponse.getBody().getClass());
        assertEquals(ID, userResponse.getBody().getId());
        assertEquals(NAME, userResponse.getBody().getName());
        assertEquals(EMAIL, userResponse.getBody().getEmail());
    }

    @Test
    void whenFindAllThenReturnAListUserDTO() {
        when(userServiceImpl.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDTO);
        ResponseEntity<List<UserDTO>> userResponse = userResource.findAll();

        assertNotNull(userResponse);
        assertNotNull(userResponse.getBody());
        assertEquals(HttpStatus.OK, userResponse.getStatusCode());
        assertEquals(ResponseEntity.class, userResponse.getClass());
        assertEquals(ArrayList.class, userResponse.getBody().getClass());
        assertEquals(UserDTO.class, userResponse.getBody().get(0).getClass());
        assertEquals(ID, userResponse.getBody().get(0).getId());
        assertEquals(NAME, userResponse.getBody().get(0).getName());
        assertEquals(EMAIL, userResponse.getBody().get(0).getEmail());
    }

    @Test
    void whenCreateThenReturnCreated() {
        when(userServiceImpl.create(any())).thenReturn(user);
        ResponseEntity<UserDTO> userResponse = userResource.create(userDTO);

        assertEquals(HttpStatus.CREATED, userResponse.getStatusCode());
        assertNotNull(userResponse.getHeaders().getLocation());
        assertNotNull(userResponse.getHeaders().get("Location"));
        assertEquals(ResponseEntity.class, userResponse.getClass());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(userServiceImpl.update(any())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);
        ResponseEntity<UserDTO> userResponse = userResource.update(ID, userDTO);

        assertNotNull(userResponse);
        assertNotNull(userResponse.getBody());
        assertEquals(HttpStatus.OK, userResponse.getStatusCode());
        assertEquals(ResponseEntity.class, userResponse.getClass());
        assertEquals(UserDTO.class, userResponse.getBody().getClass());
        assertEquals(ID, userResponse.getBody().getId());
        assertEquals(NAME, userResponse.getBody().getName());
        assertEquals(EMAIL, userResponse.getBody().getEmail());

    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}