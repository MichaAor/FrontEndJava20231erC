package com.cybrixsystems.TestRepository;

import com.cybrixsystems.backjsp.Model.User;
import com.cybrixsystems.backjsp.Repository.UserDAO;
import com.cybrixsystems.backjsp.Repository.UserDAOIMP;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {
    private UserDAO ud;

    @BeforeEach
    void setUp(){
        ud = new UserDAOIMP();
    }

    @Test
    @Order(1)
    void findAll_test(){
        List<User> users = new ArrayList<>();

        users = ud.findAllUsers();

        assertFalse(users.isEmpty());
        assertTrue(users.stream().allMatch(Objects::nonNull));
        users.forEach(System.out :: println);
    }

    @Test
    @Order(2)
    void findUserById_test(){
        Long idFind = 1L;

        Optional<User> userOpt = ud.findUserById(idFind);

        assertFalse(userOpt.isEmpty());
        assertNotNull(userOpt.orElseThrow());
        assertEquals(1,userOpt.orElseThrow().getId().intValue());
        assertEquals("Carlos",userOpt.orElseThrow().getName());
        assertEquals("Rodriguez",userOpt.orElseThrow().getSurname());
        assertEquals("45789123",userOpt.orElseThrow().getDni());
        assertEquals("carlos@gmail.com",userOpt.orElseThrow().getEmail());
        assertEquals("12345678mxeA",userOpt.orElseThrow().getPassword());
    }

    @Test
    @Order(3)
    void findByUserEmail_test(){
        String email = "mary@gmail.com";

        Optional<User> userOpt = ud.findUserByEmail(email);

        assertFalse(userOpt.isEmpty());
        assertNotNull(userOpt.orElseThrow());
        assertEquals(2,userOpt.orElseThrow().getId().intValue());
        assertEquals("Maria",userOpt.orElseThrow().getName());
        assertEquals("Miraflor",userOpt.orElseThrow().getSurname());
        assertEquals("37895212",userOpt.orElseThrow().getDni());
        assertEquals("mary@gmail.com",userOpt.orElseThrow().getEmail());
        assertEquals("248975100eer0",userOpt.orElseThrow().getPassword());
    }

    @Test
    @Order(4)
    void saveUser_test(){
        User userToSave = new User("Quiet","Wolf","42756891"
                                    ,"mgsV@gmail.com","147541754617");
        User userSaved  = ud.saveUser(userToSave);

        assertNotNull(userSaved);
        assertNotNull(userSaved.getId());
        assertEquals(userSaved.getName(),userToSave.getName());
        assertEquals(userSaved.getSurname(),userToSave.getSurname());
        assertEquals(userSaved.getDni(),userToSave.getDni());
        assertEquals(userSaved.getEmail(),userToSave.getEmail());
        assertEquals(userSaved.getPassword(),userToSave.getPassword());
    }

    @Test
    @Order(5)
    void updateUserNoEmail_test(){
        User userToSave = new User("Quiet","Snake","22479614"
                ,"mgsV@gmail.com","1189145189");
        String emailActualOrNew = "mgsV@gmail.com";
        boolean rta = ud.updateUser(userToSave,emailActualOrNew);

        assertTrue(rta);

        Optional <User> userSaved = ud.findUserByEmail("mgsV@gmail.com");

        assertTrue(userSaved.isPresent());
        assertEquals(userSaved.orElseThrow().getName(),userToSave.getName());
        assertEquals(userSaved.orElseThrow().getSurname(),userToSave.getSurname());
        assertEquals(userSaved.orElseThrow().getDni(),userToSave.getDni());
        assertEquals(userSaved.orElseThrow().getEmail(),userToSave.getEmail());
        assertEquals(userSaved.orElseThrow().getPassword(),userToSave.getPassword());
    }

    @Test
    @Order(6)
    void deleteUserByEmail_test(){
        String email = "mgsV@gmail.com";

        ud.deleteUserByEmail(email);

        Optional<User> userOpt = ud.findUserByEmail("mgsV@gmail.com");

        assertTrue(userOpt.isEmpty());
    }
}
