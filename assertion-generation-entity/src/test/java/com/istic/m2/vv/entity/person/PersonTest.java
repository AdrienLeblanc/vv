package com.istic.m2.vv.entity.person;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

class PersonTest {

    @Test
    public void testAge() {
        Person p = new Person("Someone", 13);
        assertFalse(p.isAdult());
    }
}
