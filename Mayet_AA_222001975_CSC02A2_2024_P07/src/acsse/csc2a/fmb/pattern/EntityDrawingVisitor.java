package acsse.csc2a.fmb.pattern;

import acsse.csc2a.fmb.model.E_COLOUR;
import acsse.csc2a.fmb.model.Firework;
import acsse.csc2a.fmb.model.FireworkEntity;
import acsse.csc2a.fmb.model.FountainFirework;
import acsse.csc2a.fmb.model.RocketFirework;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EntityDrawingVisitor implements AbstractVisitor{

	private GraphicsContext graphicsContext = null;
	
	/**
	 * @param graphicsContext
	 */
	public EntityDrawingVisitor(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
	}
	
	//if the visitor is FireworkEntity the implement this method
	@Override
	public void visit(FireworkEntity fireworkEntity) {
		//get the firework
		Firework firework = fireworkEntity.getFirework();
		
		//get the color of the firework and set the fill color
		graphicsContext.setFill(getFireworkColor(firework.getColour()));
		
		//if the firework is a rocket
		if(firework instanceof RocketFirework) {
			//rect height bigger than width
			//fireworkEntity.getXLocation()*50 + 10 - to shift rect right, 5 - to shift rect down, 30 - to leave gap,40 - to leave gap
			graphicsContext.fillRect((fireworkEntity.getXLocation()*50) + 10, 5, 30,40 );
			
		//if the firework is a fountain
		}else if (firework instanceof FountainFirework) {
			graphicsContext.fillOval(fireworkEntity.getXLocation()*50 + 10, 10, 30, 30);
		}

	}

	public Color getFireworkColor(E_COLOUR colour) {
		Color c = Color.BLACK;

		switch (colour){
		case RED:
			c = Color.RED;
			break;
		case GREEN: 
			c = Color.GREEN;
			break;
		case BLUE:
			c = Color.BLUE;
			break;
		case YELLOW: 
			c = Color.YELLOW;
			break;
		case MAGENTA:
			c = Color.MAGENTA;
			break;
		case WHITE:
			c = Color.WHITE;
			break;
		case ORANGE:
			c = Color.ORANGE;
			break;
		case CYAN:
			c = Color.CYAN;
			break;
		}
		return c;
	}

}
