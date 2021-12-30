package br.com.leopoldodev.api.resources;

import br.com.leopoldodev.api.domain.User;
import br.com.leopoldodev.api.domain.dto.UserDTO;
import br.com.leopoldodev.api.services.UserService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;



@AllArgsConstructor
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
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
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