package model;

import java.io.IOException;
import java.io.InputStream;

import gamelogic.Game;

public class Test {

	public static void main(String[] args) {

 /* Operation op[]=new Operation [20];
  op[0]=new Operation();
  op[0].optype=Operation.PLACE;
  op[0].player=Chess.PLAYER_R;
  op[0].lstart=Location.getL(1,1);
  Game game=new Game();
  game.init();
  System.out.print(game.toDo(op[0])+'\n');
  System.out.print(game.msg+'\n');
  System.out.print(game.csbd.opList.toString()+'\n');
  System.out.print(game.csbd.chessonboard.toString()+'\n');
  op[1]=new Operation();
  op[1].optype=Operation.PLACE;
  op[1].player=Chess.PLAYER_B;
  op[1].lstart=Location.getL(1,4);

  System.out.print(game.toDo(op[1])+'\n');
  System.out.print(game.msg+'\n');
  System.out.print(game.csbd.opList.toString()+'\n');
  System.out.print(game.csbd.chessonboard.toString()+'\n');*/
		Game game=new Game();
			  game.init();
			  System.out.print("Enter Type,Player, startColumn and startRow,finishColummn and finishRow,such as 001100,006400"+'\n');
			  while(true) {
			  
			  System.out.print(game.msg+":\n");
			  
			  int optype=-1;
			  int player=-1;
			  int scolumn=0;
			  int srow=0;
			  int fcolumn=0;
			  int frow=0;
			try {
				InputStream in = System.in;
				
				optype=in.read()-48;
				player=in.read()-48;
				scolumn = in.read()-48;
				srow=in.read()-48;
				fcolumn = in.read()-48;
				frow=in.read()-48;
				in.read();
				in.read();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			 Location ls,lf;

			 ls=Location.getL(scolumn, srow);
			 lf=Location.getL(fcolumn, frow);
			 if(ls==null) {System.out.print("Wrong Location!");continue;}
			 if(lf==null&&optype % 2==1) {System.out.print("Wrong Location!");continue;}
			 Operation op= new Operation();
			 op.optype=optype;
			 op.player=player;
			 op.lstart=ls;
			 op.lfinish=lf;
			 System.out.print(game.toDo(op)+"\n");	 
			 System.out.print(game.csbd.chessonboard+"\n");
			 System.out.print(game.csbd.opList+"\n");
		
			 if(game.checkFinished()) {System.out.print(game.msg+"\n");break;}
			 
			 if(game.checkEatable(op))
			 {
				 System.out.print(game.msg+"\n"); 
				 try {
						InputStream in = System.in;
						scolumn = in.read()-48;
						srow=in.read()-48;
						in.read();
						in.read();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 System.out.print(scolumn+","+srow);
				 ls=Location.getL(scolumn, srow);
				 if(ls==null) {System.out.print("Wrong Location!");continue;}//Error here
				 Operation op0=new Operation();
				 op0.player=op.player;
				 op0.optype=Operation.EAT;
				 op0.lstart=ls;
				 while(game.toEat(op0)==false) {
					 System.out.print(game.msg+"\n"); 
					 try {
							InputStream in = System.in;
							scolumn = in.read()-48;
							srow=in.read()-48;
							in.read();
							in.read();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 System.out.print(scolumn+","+srow);
					 ls=Location.getL(scolumn, srow);
					 if(ls==null) {System.out.print("Wrong Location!");continue;}//Error here
					 op0=new Operation();
					 op0.player=op.player;
					 op0.optype=Operation.EAT;
					 op0.lstart=ls;
				 }	 
				 System.out.print(game.csbd.chessonboard+"\n");
				 System.out.print(game.csbd.opList+"\n");
				 game.msg=game.msgOneStepLater;
				 continue;
				 
			 }
			 
		}
  
	}

}
