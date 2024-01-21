package model;

public enum Location {
   a1(1,1),d1(4,1),g1(7,1),
   b2(2,2),d2(4,2),f2(6,2),
   c3(3,3),d3(4,3),e3(5,3),
   a4(1,4),b4(2,4),c4(3,4),
   e4(5,4),f4(6,4),g4(7,4),
   c5(3,5),d5(4,5),e5(5,5),
   b6(2,6),d6(4,6),f6(6,6),
   a7(1,7),d7(4,7),g7(7,7);

private int column;
private int row;

Location(int column, int row) {
	this.column=column;
	this.row=row;
}

public int getColumn() {
	return column;
}

public int getRow() {
	return row;
}

public static boolean check(int column,int row) {//check if this location exists
	for(Location p:Location.values()) 
	{
		if(p.column==column&&p.row==row)return true;
	}
	return false;
    
}
public static Location getL(int column,int row) {//get location class by column and row
	for(Location l:Location.values()) 
	{
		if(l.column==column&&l.row==row)return l;
	}
	return null;}

/*public static boolean checkmill(Location l1,Location l2, Location l3) {//check if the three locations are in a mill
	if(l1.row==l2.row&&l2.row ==l3.row)
		{if(l1.row!=4)return true;
		else if(l1.column<4&&l2.column<4&&l3.column<4) return true;
		else if(l1.column>4&&l2.column>4&&l3.column>4) return true;
	    return false;}
	if(l1.column==l2.column&&l2.column ==l3.column)
	{if(l1.column!=4)return true;
	else if(l1.row<4&&l2.row<4&&l3.row<4) return true;
	else if(l1.row>4&&l2.row>4&&l3.row>4) return true;
    return false;}
	return false;     
}*/

public static boolean isAdjacent(Location l1,Location l2) {//check it two location is adjacent

	if(l1.column==l2.column) {
		if(l1.column==4) {if(l2.row-l1.row==1||l1.row-l2.row==1) return true;}
		else if(l2.row-l1.row==l1.column-4||l2.row-l1.row==4-l1.column) return true;
		}
	if(l1.row ==l2.row) {
		if(l1.row==4) {if(l2.column-l1.column==1||l1.column-l2.column==1) return true;}
		else if(l2.column-l1.column==l1.row-4||l2.column-l1.column==4-l1.row) return true;
		}
	
	return false;
	
	     
}
public static boolean isMill(Location l1,Location l2) {//check it two location is in a mill

	if(l1.column==l2.column) {
		if(l1.column==4) {if(l2.row-l1.row<3||l1.row-l2.row<3)if(l1.row+l2.row!=8) return true;}
		else if(l1.row!=l2.row) return true;
		}
	if(l1.row ==l2.row) {
		if(l1.row==4) {if(l2.column-l1.column<3||l1.column-l2.column<3)if(l1.column+l2.column!=8) return true;}
		else if(l2.column!=l1.row) return true;
		}
	
	return false;
	
	     
}
public static boolean isMill(Location l1,Location l2,Location l3) {//check it two location is in a mill

	if(l1.column==l2.column&&l2.column==l3.column) {
		if(l1.column==4) {if(l1.row+l2.row+l3.row==6||l1.row+l2.row+l3.row==18) return true;}
		else if(l1.row!=l2.row&&l1.row!=l3.row) return true;
		}
	if(l1.row ==l2.row&&l2.row==l3.row) {
		if(l1.row==4){if(l1.column+l2.column+l3.column==6||l1.column+l2.column+l3.column==18) return true;}
		else if(l1.column!=l2.column&&l1.column!=l3.column) return true;
		}
	
	return false;
	
	     
}
}
