package test.acceptancetests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gamelogic.Game;
import model.ChessBoard;
import model.Location;
import model.Operation;

class RemovingTest {
 
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testRemovingCondition() {
		Game game=new Game();
	ChessBoard cs=game.csbd;
		
		Operation op0=new Operation();
		op0.lstart=Location.a1;
		cs.place(op0);
		Operation op1=new Operation();
		op1.lstart=Location.a4;
		cs.place(op1);
		assertFalse(game.checkEatable(op1));
		Operation op2=new Operation();
		op2.lstart=Location.a7;
		cs.place(op2);
		assertTrue(game.checkEatable(op2));
	}
	@Test
	void testIllegalRemoveOutsideIntersectionPoints() {
		Operation op0=new Operation();
		op0.lstart=Location.getL(8, 8);
		assertNull(op0.lstart);
	}
	@Test
	void testIllegalRemoveOnSelfsIntersectionPoints() {
		ChessBoard cs=new ChessBoard();
		Operation op0=new Operation();
		op0.lstart=Location.a1;
		cs.place(op0);
		
		Operation op4=new Operation();
		op4.optype=Operation.EAT;
		op4.lstart=Location.a1;
		assertFalse(cs.eatable(op4));
	}
	@Test
	void testIllegalRemoveOnOpponentsIntersectionPoints() {
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
		op3.lstart=Location.d1;
		cs.place(op3);
		Operation op4=new Operation();
		op4.player=1;
		op4.lstart=Location.a1;
		assertFalse(cs.eatable(op4));
	}
	@Test
	void testLegalRemove() {
		ChessBoard cs= new ChessBoard();
		Operation op0=new Operation();
		op0.lstart=Location.a1;
		cs.place(op0);
		Operation op4=new Operation();
		op4.player=1;
		op4.lstart=Location.a1;
		cs.eatable(op4);

		int size= cs.opList.size();
		cs.eat(op4);
		assertEquals(size+1,cs.opList.size());
	}
}
