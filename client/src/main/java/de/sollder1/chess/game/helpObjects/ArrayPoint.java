package de.sollder1.chess.game.helpObjects;

public class ArrayPoint {
	
	private int x;
	private int y;
	private String colorOfTheTile = "green"; //Only for tiles
	  
	public ArrayPoint(int x, int y){
		  
		this.setX(x);
		this.setY(y);
		  
	}
	
	public ArrayPoint(int x, int y, String color){
		  
		this.setX(x);
		this.setY(y);
		colorOfTheTile = color;
		  
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getColorOfTheTile() {
		return colorOfTheTile;
	}

	public void setColorOfTheTile(String colorOfTheTile) {
		this.colorOfTheTile = colorOfTheTile;
	}

	@Override
	public String toString() {
		
		return "["+ x + "," + y + "]";
		
	}
	
}
