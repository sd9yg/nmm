package test.acceptancetests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gamelogic.ComputerComponent;
import gamelogic.Game;
import model.ChessBoard;
import model.Location;
import model.Operation;

class FlyingTest {

	Game game=new Game();

	@BeforeEach
	void setUp() throws Exception {
		
		
	}

	@Test
	void testFlyingCondition() {
		ChessBoard cs=new ChessBoard();
		Operation op= new Operation();
		op.lstart=Location.a1;
		op.lfinish=Location.g1;
		op.optype=Operation.MOVE;
		Operation op0=new Operation();
		op0.lstart=Location.a1;
		cs.place(op0);
		Operation op1=new Operation();
		op1.lstart=Location.a4;
		cs.place(op1);
		Operation op2=new Operation();

		op2.lstart=Location.a7;
		cs.place(op2);
		assertTrue(cs.flyable(op));
		
	}
	@Test
	void testIllegalStartingPoint() {
		ChessBoard cs=new ChessBoard();
		Operation op0=new Operation();
		op0.lstart=Location.a1;
		cs.place(op0);
		Operation op1=new Operation();
		op1.lstart=Location.a4;
		cs.place(op1);
		Operation op2=new Operation();
		op2.lstart=Location.a7;
		op2.lfinish=Location.g1;
		assertFalse(cs.movable(op2));
			
	}
	@Test
	void testIllegalFlyofOccupiedEndingPoint() {
		ChessBoard cs=new ChessBoard();
		Operation op0=new Operation();
		op0.lstart=Location.g1;
		cs.place(op0);
		Operation op1=new Operation();
		op1.lstart=Location.a4;
		cs.place(op1);
		Operation op2=new Operation();
		op2.lstart=Location.a4;
		op2.lfinish=Location.g1;
		assertFalse(cs.movable(op2));
		
	}
	@Test
	void testIllegalFlyOutsideIntersectionPoint() {
		Operation op0=new Operation();
		op0.lstart=Location.getL(8, 8);
		assertNull(op0.lstart);
	}
	
	@Test
	void testLegalFly() {
		ChessBoard cs= new ChessBoard();
	
	
		Operation op0=new Operation();
		op0.lstart=Location.a1;
		cs.place(op0);
		Operation op1=new Operation();
		op1.lstart=Location.a4;
		cs.place(op1);
		Operation op2=new Operation();

		op2.lstart=Location.a7;
		cs.place(op2);
		Operation op3=new Operation();
		op3.lstart=Location.a1;
		op3.lfinish=Location.g4;
		op3.optype=Operation.MOVE;
		assertTrue(cs.movable(op3));
		assertTrue(cs.flyable(op3));

		int size= cs.opList.size();
		cs.eat(op3);
		assertEquals(size+1,cs.opList.size());
		
	
	}

}
