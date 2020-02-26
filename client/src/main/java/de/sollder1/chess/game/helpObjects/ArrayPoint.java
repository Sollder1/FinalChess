package de.sollder1.chess.game.helpObjects;

import java.util.Objects;

public class ArrayPoint {
	
	private int i;
	private int j;
	private String colorOfTheTile = "green"; //Only for tiles
	private Rochade rochade;
	  
	public ArrayPoint(int i, int j){
		  
		this.setI(i);
		this.setJ(j);
		  
	}
	
	public ArrayPoint(int i, int j, String color){
		  
		this.setI(i);
		this.setJ(j);
		colorOfTheTile = color;
		  
	}

	public ArrayPoint(int i, int j, String colorOfTheTile, Rochade rochade) {
		this.i = i;
		this.j = j;
		this.colorOfTheTile = colorOfTheTile;
		this.rochade = rochade;
	}

	public Rochade getRochade() {
		return rochade;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public String getColorOfTheTile() {
		return colorOfTheTile;
	}

	public void setColorOfTheTile(String colorOfTheTile) {
		this.colorOfTheTile = colorOfTheTile;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ArrayPoint that = (ArrayPoint) o;
		return i == that.i &&
				j == that.j;
	}

	@Override
	public int hashCode() {
		return Objects.hash(i, j);
	}

	@Override
	public String toString() {
		return "["+ i + "," + j + "]";
	}
	
}
