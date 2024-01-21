package model;

import java.util.ArrayList;

public class ChessBoard {
   public ArrayList<Operation> opList;
   public ArrayList<Chess> chessonboard;

   public ChessBoard(){
	   opList=new ArrayList<Operation>();
	   chessonboard=new ArrayList<Chess>();
   }
   public boolean placable(Operation op) {//check the location is occupied
	   for(Chess c:chessonboard) {
		   if (c.l.getColumn()==op.lstart.getColumn()&&c.l.getRow()==op.lstart.getRow()) 
			   return false;
		   }
	return true;
	}
   public void place(Operation op) {  //place the chess
	   Chess c= new Chess();
	   c.l=op.lstart;
	   c.player=op.player;

	   chessonboard.add(c);
	   opList.add(op);
   }
   public boolean movable(Operation op) {// if the moving destination is occupied
	   boolean k=false,t=true;
	   for(Chess c:chessonboard) {
		   if (c.l.getColumn()==op.lstart.getColumn()&&c.l.getRow()==op.lstart.getRow()&&c.player==op.player) 
			   k=true;
		   }
	   for(Chess c:chessonboard) {
		   if (c.l.getColumn()==op.lfinish.getColumn()&&c.l.getRow()==op.lfinish.getRow()) 
			  t=false;
		   }
	return k&&t;
	}
   public boolean flyable(Operation op) {//check if it is legal to fly
	   int count=0;
	   for(Chess c:chessonboard) {if(c.player==op.player)count++;}
	   if(count==3)return true;
	   return false;
	}
   public void move(Operation op) {//move the chess
	   for(Chess c:chessonboard) 
	   {
		   if (c.l.getColumn()==op.lstart.getColumn()&&c.l.getRow()==op.lstart.getRow()) 
		   {
			   c.l=Location.getL(op.lfinish.getColumn(), op.lfinish.getRow());
			   opList.add(op);
		   }
	   }
   }

   public boolean hasMill(Location p, int playerid) {//check the position here is in a mill or not
	   int countr=0;
	   int countc=0;
	   for(Chess c:chessonboard)
		   {
		   if(playerid==c.player)
			  if(c.l.getColumn()!=p.getColumn()||c.l.getRow()!=p.getRow())
		    {
		     if(p.getColumn()==c.l.getColumn())
			 {
		    	if(p.getColumn()!=4)countc++;
		       else if(p.getRow()<4&&c.l.getRow()<4)countc++;
		       else if (p.getRow()>4&&c.l.getRow()>4)countc++;
		      }
		     else if(p.getRow()==c.l.getRow())
		     {
		       if(p.getRow()!=4)countr++;
		       else if(p.getColumn()<4&&c.l.getColumn()<4)countr++;
		       else if (p.getColumn()>4&&c.l.getColumn()>4)countr++;
		     }
		    }
		   }
		   if(countc==2||countr==2)return true;
	   return false;
	   
   }
   public boolean hasMill(Operation op, int playerid) {//check the position here is in a mill or not
	   int countr=0;
	   int countc=0;
	   for(Chess c:chessonboard)
		   {
		   if(playerid==c.player)
			  if(c.l.getColumn()!=op.lfinish.getColumn()||c.l.getRow()!=op.lfinish.getRow())
				  if(c.l.getColumn()!=op.lstart.getColumn()||c.l.getRow()!=op.lstart.getRow())  
		    {
		     if(op.lfinish.getColumn()==c.l.getColumn())
			 {
		    	if(op.lfinish.getColumn()!=4)countc++;
		       else if(op.lfinish.getRow()<4&&c.l.getRow()<4)countc++;
		       else if (op.lfinish.getRow()>4&&c.l.getRow()>4)countc++;
		      }
		     else if(op.lfinish.getRow()==c.l.getRow())
		     {
		       if(op.lfinish.getRow()!=4)countr++;
		       else if(op.lfinish.getColumn()<4&&c.l.getColumn()<4)countr++;
		       else if (op.lfinish.getColumn()>4&&c.l.getColumn()>4)countr++;
		     }
		    }
		   }
		   if(countc==2||countr==2)return true;
	   return false;
	   
   }
   public boolean eatable(Operation op) {// check if it is legal to eat
	   int count=0;
	   for(Chess c:chessonboard) 
	   {
		   if (c.l.getColumn()==op.lstart.getColumn()&&c.l.getRow()==op.lstart.getRow()&&c.player!=op.player) { count++;}
	         
	   }
	   if(count==0)return false;
	   if(!hasMill(op.lstart,1-op.player))return true;
	   for(Chess c:chessonboard) {
		   if(op.player!=c.player) 
		   {
			   if(!hasMill(c.l,1-op.player))return false;
		   }
	   }
		   
		return true;
		}
   public void eat(Operation op) //eat
   {
	   for(int i=0;i<chessonboard.size();i++) {
		   Chess c=chessonboard.get(i);
		   if (c.l.getColumn()==op.lstart.getColumn()&&c.l.getRow()==op.lstart.getRow()) 
		   {  
			 
			   c.l=null;
			   chessonboard.remove(i);
			   i--;
			   opList.add(op);
		   } 
	   }
   }
   public int isFinishByCount() {//check if it's finished by count the chess
	   int countb=0;
	   int countr=0;
	   for(Chess c:chessonboard)
		   {if(c.player==Chess.PLAYER_B)countb++;
	       if(c.player==Chess.PLAYER_R)countr++;}
	   if(countb<3)return Operation.PLAYER_R;
	   if(countr<3)return Operation.PLAYER_B;
	   return -1;
	   
   }

	   public int isFinishByMove(Operation p) {//check if it's finished by the moving space
	   int countb=0;
	   int countr=0;
	   
	   for(Chess c:chessonboard) {
		   Location l0=c.l;
		   if(p.optype==Operation.EAT&&c.l.getColumn()==p.lstart.getColumn()&&c.l.getRow()==p.lstart.getRow()) continue;
		   if(p.optype==Operation.MOVE&&c.l.getColumn()==p.lstart.getColumn()&&c.l.getRow()==p.lstart.getRow()) {c.l=p.lfinish;}
		   for(Location l:Location.values()) 
		   {  Operation op= new Operation();
		      op.player=c.player;
		      op.lstart=c.l;
		      op.lfinish=l;
		      op.optype=Operation.MOVE;
			   if(Location.isAdjacent(c.l, l)&&this.movable(op))
				   {if(c.player==Chess.PLAYER_B)countb++;
				   else countr++;
				   }
			}
		   c.l=l0;
		 
	   }
	   if(countb==0)return Operation.PLAYER_R;
	   if(countr==0)return Operation.PLAYER_B;
	   return -1;
	   
   }   
   public int isFinishByMove() {//check if it's finished by the moving space
	   int countb=0;
	   int countr=0;
	   
	   for(Chess c:chessonboard)
		   for(Location l:Location.values()) 
		   {  Operation op= new Operation();
		      op.player=c.player;
		      op.lstart=c.l;
		      op.lfinish=l;
		      op.optype=Operation.MOVE;
			   if(Location.isAdjacent(c.l, l)&&this.movable(op))
				   {if(c.player==Chess.PLAYER_B)countb++;
				   else countr++;
				   }
			}
	   if(countb==0)return Operation.PLAYER_R;
	   if(countr==0)return Operation.PLAYER_B;
	   return -1;
	   
   }
}
