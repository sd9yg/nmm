package test.unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gamelogic.Game;
import model.ChessBoard;
import model.Location;
import model.Operation;

class GameTest {
Game game=new Game();
	@BeforeEach
	void setUp() throws Exception {
		
		
	}

	@Test
	void testInit() {
		
		game.init();
		assertTrue(game.csbd.opList.size()==0);
		assertTrue(game.csbd.chessonboard.size()==0);
		assertTrue(game.msg==Game.msg0);
	}

	@Test
	void testMsgToOp() {
		assertTrue(Game.msgToOp(Game.msg0).player==0&&Game.msgToOp(Game.msg0).optype==Operation.PLACE);
		assertTrue(Game.msgToOp(Game.msg1).player==1&&Game.msgToOp(Game.msg1).optype==Operation.PLACE);
		assertTrue(Game.msgToOp(Game.msg2).player==0&&Game.msgToOp(Game.msg2).optype==Operation.MOVE);
		assertTrue(Game.msgToOp(Game.msg3).player==1&&Game.msgToOp(Game.msg3).optype==Operation.MOVE);
		assertTrue(Game.msgToOp(Game.msg4).player==0&&Game.msgToOp(Game.msg4).optype==Operation.EAT);
		assertTrue(Game.msgToOp(Game.msg5).player==1&&Game.msgToOp(Game.msg5).optype==Operation.EAT);
	}

	@Test
	void testToDo() {
		game.init();
		Operation op= Game.msgToOp(Game.msg1);
		op.lstart=Location.a1;
		
		assertEquals("ERROR!",game.toDo(op));
		Operation op1= Game.msgToOp(Game.msg0);
		op1.lstart=Location.a1;
		assertEquals(Game.msg1,game.toDo(op1));
	}

	@Test
	void testCheckFinished() {
		ChessBoard cs=game.csbd;
		
		Operation op0=new Operation();
		op0.lstart=Location.a1;
		cs.place(op0);
		Operation op1=new Operation();
		op1.lstart=Location.a4;
		cs.place(op1);
		Operation op2=new Operation();
		op2.lstart=Location.a7;
		cs.place(op2);
		assertEquals(true,game.checkFinished());
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
		assertEquals(false,game.checkFinished());
		
		
	}

	@Test
	void testCheckEatable() {
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
	void testToEat() {
		ChessBoard cs=game.csbd;
		Operation op0=new Operation();
		op0.lstart=Location.a1;
		cs.place(op0);
		Operation op4=new Operation();
		op4.player=1;
		op4.lstart=Location.a1;
		op4.optype=Operation.EAT;
		game.msg=Game.msg5;
		game.toEat(op4);
		assertEquals(0, game.csbd.chessonboard.size());
	}

}
