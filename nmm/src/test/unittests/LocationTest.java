package test.unittests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Location;

class LocationTest {

	@BeforeEach
	void setUp() throws Exception {
	}
	@Test
	void testGetRow() {
		  assertEquals(1,Location.a1.getRow());
	}
	@Test
	void testGetColumn() {
		  assertEquals(1,Location.a1.getColumn());
	}
	@Test
	void testCheck() {
		  assertTrue(Location.check(1, 1));
		  assertFalse(Location.check(1, 2));
	}

	@Test
	void testGetL() {
		assertEquals(Location.a1,Location.getL(1, 1));
		assertEquals(Location.a4,Location.getL(1, 4));
		assertNull(Location.getL(2, 1));
	}

	@Test
	void testIsAdjacent() {
		  assertTrue(Location.isAdjacent(Location.getL(1, 1),Location.getL(1, 4)));
		  assertFalse(Location.isAdjacent(Location.getL(7, 7),Location.getL(1, 4)));
		  assertTrue(Location.isAdjacent(Location.getL(4, 1),Location.getL(1, 1)));
		  assertTrue(Location.isAdjacent(Location.getL(2, 2),Location.getL(2, 4)));
		  assertFalse(Location.isAdjacent(Location.getL(4, 1),Location.getL(4, 7)));
	}

}
