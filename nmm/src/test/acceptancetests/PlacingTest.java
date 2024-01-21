package test.acceptancetests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gamelogic.Game;
import model.Location;
import model.Operation;

class PlacingTest {
    Game game=new Game();
    Operation op= new Operation();
	@BeforeEach
	void setUp() throws Exception {
		game.init();
		op.optype=Operation.PLACE;
		op.player=Operation.PLAYER_R;
	}

	@Test
	void testIllegalPlaceOutsideIntersectionPoints() {
		op.lstart=Location.getL(8, 8);
		assertNull(op.lstart);
	}
	
	@Test
	void testIllegalPlaceOnAnOccupiedPoints() {
		op.lstart=Location.a1;
		game.toDo(op);
		Operation op1=new Operation();
		op1.optype=Operation.PLACE;
		op1.player=Operation.PLAYER_B;
		op1.lstart=Location.a1;
		assertEquals("ERROR!",game.toDo(op1));
	}
	@Test
	void testLegalPlace() {
		op.lstart=Location.a1;
		game.toDo(op);
		assertEquals(Game.msgToOp(game.msg).player,Operation.PLAYER_B);
		assertEquals(1,game.csbd.opList.size());
	}

}
