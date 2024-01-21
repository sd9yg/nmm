package test.acceptancetests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gamelogic.Game;
import gui.GUI;
import gamelogic.ComputerComponent;
import model.Location;
import model.Operation;

class GameSettingUpTest {
Game game=new Game();

		
	@BeforeEach
	void setUp() throws Exception {
		game.init();
		
	}

	@Test
	void testModelChoosing() {
	
	 ComputerComponent.computerPlay(game, Operation.PLAYER_R);
	 assertTrue(game.csbd.opList.get(game.csbd.opList.size()-1).player==Operation.PLAYER_R);
	 
	    
	}
	void testResetingGame() {
		game.init();
		assertEquals(Operation.PLAYER_R,Game.msgToOp(game.msg).player);
	}
	@Test
	void testGameStarting() {
	
		assertEquals(Operation.PLAYER_R,Game.msgToOp(game.msg).player);
	    
	}
	
	@Test
	void testEmptyBoard()
	{
		
		assertEquals(0,game.csbd.opList.size());
		assertEquals(0,game.csbd.chessonboard.size());
	}
	
	@Test
	void testPieceNumber()
	{
		assertEquals(9,game.getBleftcount());
		assertEquals(9,game.getRleftcount());
	}
	@Test
	void testIllegalLocation()
	{   
	    assertTrue(Location.check(1, 1));
		assertFalse(Location.check(1, 2));
		assertEquals(Location.a1,Location.getL(1, 1));
		assertEquals(Location.a4,Location.getL(1, 4));
		assertNull(Location.getL(2, 1));
	}

}
