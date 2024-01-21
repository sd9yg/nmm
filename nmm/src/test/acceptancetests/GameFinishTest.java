package test.acceptancetests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ChessBoard;
import model.Location;
import model.Operation;

class GameFinishTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGameFinishedByCount() {
		ChessBoard cs=new ChessBoard();
		Operation op0=new Operation();
		op0.lstart=Location.a1;
		cs.place(op0);
		Operation op1=new Operation();
		op1.lstart=Location.a4;
		cs.place(op1);
		Operation op2=new Operation();
		op2.lstart=Location.a7;
		cs.place(op2);
		assertEquals(0,cs.isFinishByCount());
		Operation op3=new Operation();
		op3.lstart=Location.g1;
		op3.player=1;
		cs.place(op3);
		Operation op4=new Operation();
		op4.lstart=Location.g4;
		op4.player=1;
		cs.place(op4);
		Operation op5=new Operation();
		op5.lstart=Location.g7;
		op5.player=1;
		cs.place(op5);
		assertEquals(-1,cs.isFinishByCount());
	}
	@Test
	void testGameFinishedByMove() {
		ChessBoard cs=new ChessBoard();
		Operation op0=new Operation();
		op0.lstart=Location.a1;
		cs.place(op0);
		Operation op1=new Operation();
		op1.lstart=Location.b4;
		cs.place(op1);
		Operation op3=new Operation();
		op3.lstart=Location.a4;
		op3.player=1;
		cs.place(op3);
		assertEquals(-1,cs.isFinishByMove());
		Operation op2=new Operation();
		op2.lstart=Location.a7;
		cs.place(op2);
		assertEquals(0,cs.isFinishByMove());
	}

}
