package test.acceptancetests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gamelogic.ComputerComponent;
import gamelogic.Game;
import model.Operation;

class ComputerComponentTest {
	Game game=new Game();

	@BeforeEach
	void setUp() throws Exception {
		
		game.init();
	}

	@Test
	void testPlacing() {
		ComputerComponent.computerPlay(game, Operation.PLAYER_R);
		 assertTrue(game.csbd.opList.get(game.csbd.opList.size()-1).player==Operation.PLAYER_R);
		 assertTrue(game.csbd.opList.get(game.csbd.opList.size()-1).optype==Operation.PLACE);
	}

	@Test
	void testMoving() {
		while(Game.msgToOp(game.msg).optype==Operation.PLACE) {
			ComputerComponent.computerPlay(game, Game.msgToOp(game.msg).player);

		}
		ComputerComponent.computerPlay(game, Operation.PLAYER_R);
		 assertTrue(game.csbd.opList.get(game.csbd.opList.size()-1).player==Operation.PLAYER_R);
		 assertTrue(game.csbd.opList.get(game.csbd.opList.size()-1).optype==Operation.MOVE);
	}

	@Test
	void testFlying() {
		while(!game.checkFinished()) {
			ComputerComponent.computerPlay(game, Game.msgToOp(game.msg).player);
			if(game.csbd.flyable(game.csbd.opList.get(game.csbd.opList.size()-1)))assertTrue(true);
		}
		 
		 
	}
	@Test
	void testRemoving() {
	     
		while(!game.checkFinished()) {
			ComputerComponent.computerPlay(game, Game.msgToOp(game.msg).player);
			if(game.csbd.opList.get(game.csbd.opList.size()-1).optype==Operation.EAT)assertTrue(true);
		}
		 
	}
}
