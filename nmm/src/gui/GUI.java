package gui;


	import javax.imageio.ImageIO;
	import javax.swing.*;

import gamelogic.ComputerComponent;
import gamelogic.Game;
import model.Chess;
import model.Location;
import model.Operation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;
import java.io.InputStream;

	public class GUI extends JFrame implements MouseListener,MouseMotionListener {
	boolean start=false;//if a piece is being dragged flag turns to true
	int computerplayer=-1;//-1 means human-to-human mode
    Game game=new Game();
    Operation op0=new Operation();
    Chess origin;
    boolean flag=false;
    int player=-1;
    int lx;
    int ly;
    
   
    
	


	BufferedImage image;

	 {
	 try {
	 image = ImageIO.read(new File("./img/background.jpeg"));
	 } catch (IOException e) {
	  e.printStackTrace();
	 }
	 }

	 class myJPanel extends JPanel{


     @Override
	 public void paint(Graphics g) {
	
	  Graphics2D g2 = (Graphics2D)g;

	  g2.drawImage(image,0,0,1000,750,null);
	  g2.setColor(Color.CYAN);

	  g2.fill3DRect(50, 50, 600, 600, true);


	  g2.setColor(Color.black);
	
	  g2.setStroke(new BasicStroke(3));
	  

	  for(int i=1;i<8;i++) 
	  { if(i==4)continue; 
	  g2.drawLine(350-Math.abs(4-i)*100,100*i-50,350+Math.abs(4-i)*100,100*i-50 );
	  }
	  for(int i=1;i<8;i++) 
	  { if(i==4)continue; 
	  g2.drawLine(100*i-50,350+Math.abs(4-i)*100,100*i-50,350-Math.abs(4-i)*100 );
	  }
	  g2.drawLine(50,350,250,350);
	  g2.drawLine(350,250,350,50);
	  g2.drawLine(450,350,650,350);
	  g2.drawLine(350,650,350,450);

	  g2.setColor(new Color(176, 166, 100, 254));
	  g2.fillRect(26, 26, 649, 23);
	  g2.fillRect(26, 49, 23, 625);
	  g2.fillRect(49, 652, 625, 23);
	  g2.fillRect(652, 49, 23, 603);
	  g2.setColor(Color.black);
	  for(Location l:Location.values()) 
	  {
		  g2.fillOval(l.getColumn()*100-50-5, l.getRow()*100-50-5, 10, 10);
	  }

	g2.setStroke(new BasicStroke(5));
	  g2.drawLine(25,25,675, 25);
	  g2.drawLine(25,675,675, 675);
	  g2.drawLine(25,25,25, 675);
	  g2.drawLine(675,25,675, 675);
      g2.setColor(Color.white);
	  g2.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
      g2.drawString(game.msg, 700, 100);
      
      for(Chess c:game.csbd.chessonboard) {
    	 if(flag&&c.l.getColumn()==origin.l.getColumn()&&c.l.getRow()==origin.l.getRow())continue;
    	 if(c.player==Chess.PLAYER_B)
    	  g2.setColor(Color.BLUE);
    	 else if(c.player==Chess.PLAYER_R)
       	  g2.setColor(Color.RED);
    	 g2.fillOval(c.l.getColumn()*100-50-20, c.l.getRow()*100-50-20, 40, 40);
    	 
      }
    if(flag) {
    	if(op0.player==Chess.PLAYER_B)
      	  g2.setColor(Color.BLUE);
      	 else if(op0.player==Chess.PLAYER_R)
         	  g2.setColor(Color.RED);
      	 g2.fillOval(lx-40, ly-40, 40, 40);
    }
	 }
	 }

    
  
	 public void init(){
		
		 myJPanel canvas =new myJPanel();
	  canvas.setEnabled(false);
	  
		 addMouseListener(this);
		 addMouseMotionListener(this);
		 JMenuBar bar = new JMenuBar();
	 	 JMenu menu = new JMenu("Mode:Human to Human(Default)");
	 	JMenuItem item1= new JMenuItem("Human to Human(Default)");
	 	item1.addActionListener(new ActionListener(){  
	   	  public void actionPerformed(ActionEvent e){  
	            computerplayer=-1;
	            game.init();
	            menu.setText("Mode:Human to Human");
	          repaint();
	           
	         }  
	     });
	 	JMenuItem item2= new JMenuItem("Automated Red Player");
		item2.addActionListener(new ActionListener(){  
		   	  public void actionPerformed(ActionEvent e){  
		   		computerplayer=Operation.PLAYER_R;
		   		   game.init();
		            menu.setText("Mode:Automated Red Player");
		            ComputerComponent.Operate(computerplayer,game);
		            repaint();
		         
		         }  
		     });
	 	JMenuItem item3= new JMenuItem("Automated Blue Player");
		item3.addActionListener(new ActionListener(){  
		   	  public void actionPerformed(ActionEvent e){  
		   		computerplayer=Operation.PLAYER_B;
		   		game.init();
		            menu.setText("Mode:Automated Blue Player");
		           repaint();
		         }  
		     });
	     menu.add(item1);
	     menu.add(item2);
	     menu.add(item3);
	     bar.setBounds(700, 150, 200, 50);
	     bar.add(menu);
	     

      JButton b_reset=new JButton("Reset"); 
      b_reset.setBounds(700, 250, 200, 50);
      

 	 b_reset.addActionListener(new ActionListener(){  
   	  public void actionPerformed(ActionEvent e){
   		computerplayer=-1;
   	  menu.setText("Mode:Human to Human");
             game.init();
             repaint();
             
             
             
             
            
         }  
     }); 

 	  
     add(bar);

      add(b_reset);
       add(canvas);
      
       
	 setResizable(false);
	
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	 setBounds(0,0,1000,750);
	
	 setVisible(true);  
     
	 }


	 public static void main(String[] args) {
	 GUI d=new GUI();
     
   

      d.init();   
     d.game.init();
 
             
           
            
         
	 }


	@Override
	public void mouseClicked(MouseEvent e) {
		
		int x=e.getX();
		int y=e.getY();

		Location lc=null;
		for(Location l:Location.values()) {
			if(x>l.getColumn()*100-50-40&&x<l.getColumn()*100-50+40&&y>l.getRow()*100-20-40&&y<l.getRow()*100-20+40)
				{lc=l;break;}
		}
		if(lc==null)return;
		Operation op=Game.msgToOp(game.msg);
		op.lstart=lc;
		System.out.print(op+"\n");
		if(op.optype==Operation.MOVE)return;
		if(op.optype==Operation.PLACE)game.toDo(op);
		if(op.optype==Operation.EAT) {game.toEat(op);}
       if(op.optype!=Operation.EAT) game.checkEatable(op);
   	 game.checkFinished();
    ComputerComponent.computerPlay(game,computerplayer);
	 game.checkFinished();
   	repaint();

     

		  
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if(Game.msgToOp(game.msg).optype!=Operation.MOVE)return;
		int x=e.getX();
		int y=e.getY();
		 int count=0;
		Location lc=null;
		for(Location l:Location.values()) {
			if(x>l.getColumn()*100-50-40&&x<l.getColumn()*100-50+40&&y>l.getRow()*100-20-40&&y<l.getRow()*100-20+40)
				{lc=l;count++;break;}
		}
		if(count==0) {flag=false;op0=new Operation();repaint();return;}
		for(Chess c: game.csbd.chessonboard) {
			if(c.l.getColumn()==lc.getColumn()&&c.l.getRow()==lc.getRow())
			{  
				origin=c;
				count++;
				if(Game.msgToOp(game.msg).player!=c.player){flag=false;op0=new Operation();repaint();return;}
			}
		}
		if(count==1){flag=false;op0=new Operation();repaint();return;}
		  op0.player=Game.msgToOp(game.msg).player;
		  op0.optype=Operation.MOVE;
		op0.lstart=lc;
		flag=true;
	
		
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		if(!flag)return;
		if(Game.msgToOp(game.msg).optype!=Operation.MOVE)return;
		int x=e.getX();
		int y=e.getY();
       int count=0;
		Location lc=null;
		for(Location l:Location.values()) {
			if(x>l.getColumn()*100-50-40&&x<l.getColumn()*100-50+40&&y>l.getRow()*100-20-40&&y<l.getRow()*100-20+40)
				{lc=l;count++;break;}
		}
		if(count==0) {flag=false;op0=new Operation();repaint();return;}
		op0.lfinish=lc;

		String outcome=game.toDo(op0);
          
		
		if(outcome=="ERROR!") {flag=false;op0=new Operation();repaint();return;};
		System.out.print(op0+"\n");
		flag=false;
		game.checkFinished();
		game.checkEatable(op0);


	    ComputerComponent.computerPlay(game,computerplayer);
  	 game.checkFinished();
		repaint();
		
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
	lx=e.getX();
	ly=e.getY();
	
	repaint();

	}


	@Override
	public void mouseMoved(MouseEvent e) {
		//lx=e.getX();
		//ly=e.getY();
	
		//repaint();
		
	}
	}

