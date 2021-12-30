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
import org.springframework.http.ResponseEntity;


import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
    }
}