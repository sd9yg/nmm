package test.acceptancetests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gamelogic.ComputerComponent;
import gamelogic.Game;
import model.Chess;
import model.ChessBoard;
import model.Location;
import model.Operation;

class MovingTest {
Game game=new Game();

	@BeforeEach
	void setUp() throws Exception {
		
		
	}

	@Test
	void testMovingCondition() {
	
	game.init();	
		for(int i=0;i<18;i++) {
			ComputerComponent.Operate(i % 2, game);
		}
		assertEquals(Operation.MOVE,Game.msgToOp(game.msg).optype);
		
	}
	@Test
	void testIllegalStartingPoint() {
		ChessBoard cs=new ChessBoard();
		Operation op0=new Operation();
		op0.player=Operation.PLAYER_B;
		op0.lstart=Location.a1;
		cs.place(op0);
		Operation op1=new Operation();
		op1.player=Operation.PLAYER_B;
		op1.lstart=Location.a4;
		cs.place(op1);
		Operation op2=new Operation();
		op2.player=Operation.PLAYER_R;
		op2.lstart=Location.a1;
		op2.lfinish=Location.d1;
		assertFalse(cs.movable(op2));
		op2.lstart=Location.g1;
		assertFalse(cs.movable(op2));
			
	}
	@Test
	void testIllegalMoveofOccupiedEndingPoint() {
		ChessBoard cs=new ChessBoard();
		Operation op0=new Operation();
		op0.lstart=Location.a1;
		cs.place(op0);
		Operation op1=new Operation();
		op1.lstart=Location.a4;
		cs.place(op1);
		Operation op2=new Operation();
		op2.lstart=Location.a4;
		op2.lfinish=Location.a1;
		assertFalse(cs.movable(op2));
		
	}
	@Test
	void testIllegalMoveOutsideIntersectionPoint() {
		Operation op0=new Operation();
		op0.lstart=Location.getL(8, 8);
		assertNull(op0.lstart);
	}
	@Test
	void testIllegalMoveBeingTooFar() {
		Operation op0=new Operation();
		op0.lstart=Location.a1;
		op0.lfinish=Location.a7;
		assertFalse(Location.isAdjacent(op0.lstart, op0.lfinish));
		
	}
	@Test
	void testLegalMove() {
		ChessBoard cs= new ChessBoard();
	
		Operation op0=new Operation();
		op0.lstart=Location.a1;
		cs.place(op0);
		Operation op1=new Operation();
		op1.lstart=Location.a1;
		op1.lfinish=Location.a4;
		
		
		assertTrue(cs.movable(op1) && Location.isAdjacent(op1.lstart, op1.lfinish));
		int size= cs.opList.size();
		cs.move(op1);
		assertEquals(size+1,cs.opList.size());
	
	}
}
