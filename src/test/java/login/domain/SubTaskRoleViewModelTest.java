package login.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskRoleViewModelTest {

    @Test
    void getRoleDescription() {
        SubTaskRoleViewModel s = new SubTaskRoleViewModel("Senior Developer",8,500, 4000);
        assertEquals("Senior Developer", s.getRoleDescription());
    }

    @Test
    void setRoleDescription() {
    }

    @Test
    void getHours() {
    }

    @Test
    void setHours() {
    }

    @Test
    void getPrice() {
    }

    @Test
    void setPrice() {
    }

    @Test
    void getFinalPrice() {
        SubTaskRoleViewModel s = new SubTaskRoleViewModel("Senior Developer", 8, 500, 4000);
        assertEquals(4000, s.getFinalPrice());

    }

    @Test
    void setFinalPrice() {
        SubTaskRoleViewModel s = new SubTaskRoleViewModel("Senior Developer", 8, 500, 5000);
        s.setFinalPrice(4000);
        assertEquals(4000, s.getFinalPrice());

    }
}