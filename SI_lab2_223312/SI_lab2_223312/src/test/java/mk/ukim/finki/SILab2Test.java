package mk.ukim.finki;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {
    private List<Item> items(Item... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    @Test
    void checkEveryBranch() {
        RuntimeException ex;

        //TEST1
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 1));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));


        //TEST2
        assertTrue(SILab2.checkCart(
                new ArrayList<Item>(), 0)
        );


        //TEST3
        assertFalse(SILab2.checkCart(
                new ArrayList<Item>(), -1)
        );


        //TEST4
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items(
                new Item("", null, 20, 0.5f)), 1)
        );
        assertTrue(ex.getMessage().contains("No barcode!"));


        //TEST5
        assertFalse(SILab2.checkCart(items
                (new Item("", "02345", 500, 0.4f)), 2)
        );


        //TEST6
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items(
                new Item("TestName", "02a3456", 500, 0.4f)), 1)
        );
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));


        //TEST7
        assertFalse(SILab2.checkCart(items(
                new Item("TestName", "123456", 200, -1)), 2)
        );
    }

    @Test
    void MultipleCondition() {
        assertFalse(SILab2.checkCart(items(new Item("Condition1", "12345", 350, 0.2f)), 2));
        assertFalse(SILab2.checkCart(items(new Item("Condition2", "12345", 400, -2f)), 2));
        assertTrue(SILab2.checkCart(items(new Item("Condition3", "01234", 350, 0.2f)), 300));
        assertFalse(SILab2.checkCart(items(new Item("Condition4", "12345", 400, -2f)), 2));
    }
}