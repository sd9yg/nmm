package model;

public class Operation {
public final static int PLACE=0;
public final static int MOVE=1;
public final static int EAT=2;

public final static int PLAYER_R=0;
public final static int PLAYER_B=1;
public int optype;

public int player;
public Location lstart;
public Location lfinish;
@Override
public String toString() {
	return "Operation [optype=" + optype + ", player=" + player + ", lstart=" + lstart + ", lfinish=" + lfinish + "]";
}
}
