package login.domain;

import login.data.UserMapper;
import org.junit.jupiter.api.Test;
import login.data.ProjectMapper;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {



    @Test
    void getProject_name() {
        Project p = new Project("coolname", 8);
        assertEquals("coolname", p.getProject_name());
    }

    @Test
    void setProject_name() {
        Project p = new Project("coolname", 8);
        p.setProject_name("uglyname");
        assertEquals("uglyname", p.getProject_name());
    }

    @Test
    void getWeek_duration() {
        Project p = new Project("coolname", 8);
        assertEquals(8, p.getWeek_duration());
    }

    @Test
    void setWeek_duration() {
        Project p = new Project("coolname", 8);
        p.setWeek_duration(13);
        assertEquals(13, p.getWeek_duration());
    }
    @Test
    void testObjectCreation() {
        Project p = new Project ("testName", 10);
        assertEquals("testName", p.getProject_name()); // vi forventer at getProject_name() bliver testname.
        assertEquals(10, p.getWeek_duration()); // samme her.
    }


    // test om et project bliver lagt i vores database. start med use case og så bryd den ned i testcases.
    // getProject ? en vigtig use case. test mysql ?script i klassen.
    // en test er ikke en god test hvis den har adgang til en database. en test skal være afgrænset.

    @Test
    void testProjectDatabase() throws LoginSampleException {
        ProjectMapper pm = new ProjectMapper();
        UserMapper um = new UserMapper();
        Project p = new Project("test", 15); // opret testprojekt.
        User u = new User("test@test.dk", "1234"); // opret testuser.

        um.deleteUser(u); // slet bruger hvis den findes i databasen.
        um.createUser(u); // opret testuser i databasen.
        pm.createProject(p, u); // opret testprojekt i databasen.
        int id = p.getProjectId(); // henter projektid fra testprojekt.

        Project result = pm.getProjectNew(id); // Hent projekt med testprojektets projektid.
        assertEquals("test", result.getProject_name()); // her laver vi sammenligning med testprojekt og projekt fra databasen.
        assertEquals(15, result.getWeek_duration()); // Og her sammenligner vi duration på samme måde.
    }

}