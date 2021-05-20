package login.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getEmail() {
        User u = new User("test@mail.com", "strongpassword");
        assertEquals("test@mail.com", u.getEmail());
    }

    @Test
    void setEmail() {
        User u = new User("test@mail.com", "strongpassword");
        u.setEmail("another@mail.com");
        assertEquals("another@mail.com", u.getEmail());
    }

    @Test
    void getPassword() {
        User u = new User("test@mail.com", "strongpassword");
        assertEquals("strongpassword", u.getPassword());
    }

    @Test
    void setPassword() {
        User u = new User("test@mail.com", "strongpassword");
        u.setPassword("strongerpassword");
        assertEquals("strongerpassword", u.getPassword());
    }

   @Test
    void testObjectCreation() {
        User u = new User ("test@mail.com", "strongpassword");
        assertEquals("test@mail.com", u.getEmail());
        assertEquals("strongpassword", u.getPassword());
   }




}