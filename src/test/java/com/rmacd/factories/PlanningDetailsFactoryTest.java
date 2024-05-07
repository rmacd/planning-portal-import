package com.rmacd.factories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanningDetailsFactoryTest {

    @Test
    public void testAssocDetails_00() {
        assertEquals(1, PlanningDetailsFactory.getAssociatedItemCount("There are 1 docum"));
        assertEquals(1, PlanningDetailsFactory.getAssociatedItemCount("There is 1 docum"));
        assertEquals(0, PlanningDetailsFactory.getAssociatedItemCount("There are 0 docum"));
        assertEquals(0, PlanningDetailsFactory.getAssociatedItemCount("there are 0 docum"));
        assertEquals(350, PlanningDetailsFactory.getAssociatedItemCount("there are 350"));
        assertNull(PlanningDetailsFactory.getAssociatedItemCount(null));
        assertNull(PlanningDetailsFactory.getAssociatedItemCount(""));
        assertNull(PlanningDetailsFactory.getAssociatedItemCount("test string"));
    }

}