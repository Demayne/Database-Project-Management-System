package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class ProjectTest {

    @Test
    void testOutstandingAmount() {
        Project p = new Project("123","Test", LocalDate.now().plusDays(10), "House", "Addr", "ERF1", 1000, 250, "ARC001","CON001","CUS001");
        assertEquals(750, p.getOutstandingAmount());
    }

    @Test
    void testOverdueLogic() {
        Project p = new Project("124","Old", LocalDate.now().minusDays(1), "House", "Addr", "ERF1", 1000, 250, "ARC001","CON001","CUS001");
        assertTrue(p.isOverdue());
        p.setFinalised("Yes");
        assertFalse(p.isOverdue());
    }

    @Test
    void testCompletionStatus() {
        Project p = new Project("125","Final", LocalDate.now(), "House", "Addr", "ERF1", 1000, 1000, "ARC001","CON001","CUS001");
        assertFalse(p.isCompleted());
        p.setFinalised("Yes");
        assertTrue(p.isCompleted());
    }
}
