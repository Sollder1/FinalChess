package de.sollder1.chess.game.ai;

import java.util.List;
import de.sollder1.chess.game.chessfigures.Figure;
import de.sollder1.chess.game.helpObjects.ArrayPoint;

public class SimpleAI extends AI{

	private SimpleAI(){}

	protected static AI instance;

	public static AI getInstance(){
		if(instance == null){
			instance = new SimpleAI();
		}
		return instance;
	}

	@Override
	public void performMove(int player) {
		
		updateList(player);
		
		int r1 = (int) (Math.random() * (aiFigures.size()-1));
		Figure toMove = aiFigures.get(r1);


		while(toMove.getPossibleCoordinates().isEmpty()) {
			
			r1 = (int) (Math.random() * aiFigures.size());
			toMove = aiFigures.get(r1);
			
		}

		List<ArrayPoint> posMoves = toMove.getPossibleCoordinates();
		int r2 = (int) (Math.random() * toMove.getPossibleCoordinates().size());

		if(posMoves.get(r2).getI() > 7 || posMoves.get(r2).getJ() > 7){
			System.err.println("Fehelrhafte Implementierung der getPossibleCoordinates Methode!");
			System.err.println("Von Figur " + toMove);

		}

		//toMove.moveFigure(posMoves.get(r2).getX(), posMoves.get(r2).getY());
		
		
	}

}
