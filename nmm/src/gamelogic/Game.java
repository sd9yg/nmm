package gamelogic;

import java.util.ArrayList;

import model.Chess;
import model.ChessBoard;
import model.Location;
import model.Operation;

public class Game {
   public ChessBoard csbd=new ChessBoard();
   int rleftcount;
   int bleftcount;
   public String msg;
   public String msgOneStepLater;
   public final static String msg0="Red place your chess";
   public final static String msg1="Blue place your chess";
   public final static String msg2="Red move your chess";
   public final static String msg3="Blue move your chess";
   public final static String msg4="Red eat opponent's chess";
   public final static String msg5="Blue eat opponent's chess";
   public final static String msg6="Red Win!";
   public final static String msg7="Blue Win!";
   public void init(){
	    rleftcount=9;
	    bleftcount=9;
	    csbd.chessonboard.clear();
	    csbd.opList.clear();
	    msg=msg0;
   }   
   public int getRleftcount() {
	return rleftcount;
}
public void setRleftcount(int rleftcount) {
	this.rleftcount = rleftcount;
}
public int getBleftcount() {
	return bleftcount;
}
public void setBleftcount(int bleftcount) {
	this.bleftcount = bleftcount;
}
public static Operation msgToOp(String msg) {
	   Operation op = new Operation();
	   switch(msg) {
	   case msg0:
		   op.player=Chess.PLAYER_R;
		   op.optype=Operation.PLACE;
		   break;
	   case msg1:
		   op.player=Chess.PLAYER_B;
		   op.optype=Operation.PLACE;
		   break; 
	   case msg2:
		   op.player=Chess.PLAYER_R;
		   op.optype=Operation.MOVE;
		   break;
	   case msg3:
		   op.player=Chess.PLAYER_B;
		   op.optype=Operation.MOVE;
		   break;
	   case msg4:
		   op.player=Chess.PLAYER_R;
		   op.optype=Operation.EAT;
		   break;
	   case msg5:
		   op.player=Chess.PLAYER_B;
		   op.optype=Operation.EAT;
		   break;
	   }
	   return op;
	   
   }
   
   public String toDo(Operation op) {

	   String error="ERROR!";
	   switch (msg) {
	   case msg0:
		   if(op.player==Operation.PLAYER_R&&op.optype==Operation.PLACE&&csbd.placable(op))
				   {
				   csbd.place(op);
				   rleftcount--;
				   if(bleftcount>0)
				   msg=msg1;
				   else
				   msg=msg2;
				   }
		   else return error;
		   break;
	   case msg1:
		   if(op.player==Operation.PLAYER_B&&op.optype==Operation.PLACE&&csbd.placable(op))
		   {
			   csbd.place(op);
			   bleftcount--;
			   if(rleftcount>0)
			   msg=msg0;
			   else
			   msg=msg2;
			   }
	   else return error;
	   break;
	   case msg2:
		   if(op.player==Operation.PLAYER_R
		   &&Location.isAdjacent(op.lstart, op.lfinish)
		   &&csbd.movable(op))
		   {   
			   csbd.move(op);
			   msg=msg3;
		   }
		   else if(op.player==Operation.PLAYER_R&&csbd.flyable(op)&&csbd.movable(op))
		   {
			   csbd.move(op);
			   msg=msg3;
		   }
		   else return error;
		   break;
	   case msg3:
		   if(op.player==Operation.PLAYER_B
		   &&Location.isAdjacent(op.lstart, op.lfinish)
		   &&csbd.movable(op))
		   {
			   csbd.move(op);
			   msg=msg2;
		   }
		   else if(op.player==Operation.PLAYER_B&&csbd.flyable(op)&&csbd.movable(op))
		   {
			   csbd.move(op);
			   msg=msg2;
		   }
		   else return error;
		   break;
	   }
	    return msg;
   } 
   
   public boolean checkFinished() {
	   if(csbd.isFinishByCount()==Chess.PLAYER_R||csbd.isFinishByMove()==Chess.PLAYER_R)if(rleftcount==0&&bleftcount==0) {msg= msg6;return true;}
	   if(csbd.isFinishByCount()==Chess.PLAYER_B||csbd.isFinishByMove()==Chess.PLAYER_B) if(rleftcount==0&&bleftcount==0){msg= msg7;return true;}
	   return false;
   }
   
   public boolean checkEatable(Operation op) 
   {   if(op.optype==Operation.MOVE) {
	   if(csbd.hasMill(op.lfinish, op.player)&&op.player==Chess.PLAYER_R) {msgOneStepLater=msg;msg=msg4;return true;}
	   if(csbd.hasMill(op.lfinish, op.player)&&op.player==Chess.PLAYER_B){msgOneStepLater=msg;msg=msg5;return true;}}
   if(op.optype==Operation.PLACE) {
	   if(csbd.hasMill(op.lstart, op.player)&&op.player==Chess.PLAYER_R) {msgOneStepLater=msg;msg=msg4;return true;}
	   if(csbd.hasMill(op.lstart, op.player)&&op.player==Chess.PLAYER_B){msgOneStepLater=msg;msg=msg5;return true;}}
	   return false;
   }
   public boolean toEat(Operation op) {

	   switch (msg) {
	   case msg4:
		   if(op.player==Operation.PLAYER_R&&op.optype==Operation.EAT&&csbd.eatable(op))
		   {csbd.eat(op);
            msg=msgOneStepLater;
		   return true;}
		   
	    else return false;
	   
	   case msg5:
		   if(op.player==Operation.PLAYER_B&&op.optype==Operation.EAT&&csbd.eatable(op))
			   {csbd.eat(op);msg=msgOneStepLater;return true;}
	    else return false;
		 
   }
	   return false;
}

}