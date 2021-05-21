package login.domain;

import org.junit.jupiter.api.Test;

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
        assertEquals("testName", p.getProject_name());
        assertEquals(10, p.getWeek_duration());
    }

    // test om et project bliver lagt i vores database. start med use case og så bryd den ned i testcases.
    // getProject ? en vigtig use case. test mysql ?script i klassen.

//    @Test
//    void

}