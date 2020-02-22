package de.sollder1.chess.game.ai;

import java.util.ArrayList;
import java.util.List;

import de.sollder1.chess.game.chessfigures.Figure;
import de.sollder1.chess.game.gui.view.GameView;
import de.sollder1.chess.game.helpObjects.ArrayPoint;

public class SimpleAI {

	private static ArrayList<Figure> AI_Figures = new ArrayList<>();
	
	public static void performMove(int player) {
		
		updateList(player);
		
		int r1 = (int) (Math.random() * (AI_Figures.size()-1));
		Figure toMove = AI_Figures.get(r1);


		while(toMove.getPossibleCoordinates().isEmpty()) {
			
			r1 = (int) (Math.random() * AI_Figures.size());
			toMove = AI_Figures.get(r1);
			
		}

		List<ArrayPoint> posMoves = toMove.getPossibleCoordinates();
		int r2 = (int) (Math.random() * toMove.getPossibleCoordinates().size());

		if(posMoves.get(r2).getX() > 7 || posMoves.get(r2).getY() > 7){
			System.err.println("Fehelrhafte Implementierung der getPossibleCoordinates Methode!");
			System.err.println("Von Figur " + toMove);

		}

		toMove.moveFigure(posMoves.get(r2).getX(), posMoves.get(r2).getY());
		
		
	}
	
	private static void updateList(int player) {
		
		AI_Figures.clear();
		
		for(Figure[] f : GameView.cb.figuresOnTheField) {
			
			for(Figure fig : f) {
				
				if(fig != null && fig.player == player) {
					
					AI_Figures.add(fig);
					
				}
				
			}
			
		}
		
	}

}
