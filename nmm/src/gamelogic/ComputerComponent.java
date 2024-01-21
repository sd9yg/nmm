package gamelogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.tools.DocumentationTool.Location;

import model.Chess;
import model.ChessBoard;
import model.Operation;

public class ComputerComponent{
	
 public static void Operate(int player, Game game) {
	 if(Game.msgToOp(game.msg).player!=player) return;
	 List<Operation> oplist=allAvailableOperate(player,game);
	
	 if(Game.msgToOp(game.msg).optype==Operation.EAT) {game.toEat(optimizedOperation(oplist, game));}
	 game.toDo(optimizedOperation(oplist, game));
 }
 
 public static List<Operation> allAvailableOperate(int player, Game game) {
	 Operation op= Game.msgToOp(game.msg);
	 if(op.player!=player)return null;
	 ArrayList<Operation> opList = new ArrayList<Operation>();
	 
	 if(op.optype==Operation.PLACE) 
	   {
		 for(model.Location l:model.Location.values()) {
			 Operation op0=new Operation();
			 op0.optype=op.optype;
			 op0.player=op.player;
			 op0.lstart=l;
			 if(game.csbd.placable(op0)) {opList.add(op0);}
			 }
		 } 
	 if(op.optype==Operation.MOVE) {
		 for(Chess a: game.csbd.chessonboard) 
		 {
			 if(a.player!=player)continue;
			 for(model.Location l:model.Location.values()) {
				 Operation op0=new Operation();
				 op0.optype=op.optype;
				 op0.player=op.player;
				 op0.lstart=a.l;
				 op0.lfinish=l;
				 if(game.csbd.movable(op0)&&model.Location.isAdjacent(op0.lstart, op0.lfinish)) {opList.add(op0);}
				 if(game.csbd.movable(op0)&&game.csbd.flyable(op0)) {opList.add(op0);}
				 }
		 }
	     }
	 if(op.optype==Operation.EAT) 
	 {
		 for(Chess a: game.csbd.chessonboard) 
		 {
			 if(a.player==player)continue;
			 Operation op0=new Operation();
			 op0.optype=op.optype;
			 op0.player=op.player;
			 op0.lstart=a.l;
			 if(game.csbd.eatable(op0))opList.add(op0);
		 }
	 }
        return opList;
	 } 
 public static Operation randomOperation(List<Operation> opList) 
 {
	 Random rand = new Random();
	   return opList.get(rand.nextInt(opList.size()));
 }
 public static void computerPlay(Game game,int computerplayer) {
	  ComputerComponent.Operate(computerplayer,game);
	    Operation lstop=game.csbd.opList.get(game.csbd.opList.size()-1);
	    if(lstop.player==computerplayer)if(game.checkEatable(lstop))
	   {ComputerComponent.Operate(computerplayer,game);}
 }
public static Operation optimizedOperation(List<Operation> opList, Game game)
{   if(opList.size()==0)return null;
    for(Operation op:opList) {
    	if(op.optype!=Operation.PLACE) {if(game.csbd.isFinishByMove(op)==op.optype) return op; }
    }
	for(Operation op:opList) {
	if(op.optype==Operation.PLACE)if(game.csbd.hasMill(op.lstart, op.player)) return op;
	if(op.optype==Operation.MOVE)if(game.csbd.hasMill(op, op.player))return op;
    }
	for(int i=0;i<opList.size();i++) {
		Operation op=opList.get(i);
		if(op.optype==Operation.PLACE)if(game.csbd.hasMill(op.lstart, (op.player-1)*-1))return op;
		if(op.optype==Operation.MOVE) {
			int counta=0;
			int countb=0;
			 if(game.csbd.hasMill(op.lfinish, (op.player-1)*-1))
				for(Chess c:game.csbd.chessonboard) {
				    
			   
				 if(model.Location.isAdjacent(c.l, op.lfinish)&&c.player!=op.player) {counta++;}
				    if(counta>1)return op;}
			 
				  if(game.csbd.hasMill(op.lstart, (op.player-1)*-1))    
				for(Chess c:game.csbd.chessonboard) {
				  
				    	if(model.Location.isAdjacent(c.l, op.lstart)&&c.player!=op.player)countb++;
				       
				}
				  if(countb>1) {opList.remove(i);i--;}
			
			}
		
	}

	for(Operation op:opList) {
		if(op.optype!=Operation.EAT)break;
	    for(Chess c:game.csbd.chessonboard) 
	    {
	    	if(model.Location.isMill(op.lstart,c.l))return op;
	    }
	}
	
	Random rand = new Random();
	   return opList.get(rand.nextInt(opList.size()));
	
}

}
