package acsse.csc2a.fmb.gui;

import java.util.ArrayList;

import acsse.csc2a.fmb.model.Entity;
import acsse.csc2a.fmb.model.FireworkEntity;
import acsse.csc2a.fmb.pattern.EntityDrawingVisitor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FireworkDisplayCanvas extends Canvas
{
	private ArrayList<FireworkEntity> fireworkEntities = null;
	private final static int Size =15*50;

	public FireworkDisplayCanvas()
	{
		//set canvas width and height
		this.setWidth(Size);
		this.setHeight(Size);
	}

	public void redrawCanvas()
	{
		//get the graphics context of canvas
		GraphicsContext graphicsContext = this.getGraphicsContext2D();
		
		//color of the line
		graphicsContext.setStroke(Color.BLACK);
		
		//color of fill
		graphicsContext.setFill(Color.ANTIQUEWHITE);
		
		//fill a rect (size of the grid)
		graphicsContext.fillRect(0, 0, Size*15, Size*15);
		
		for(int r = 0; r <= 15; r++)
		{
			for (int c = 0; c <= 15; c++) {
				//draw grid
				graphicsContext.strokeRect(c*50, r*50, 50, 50);
			}
		}
		
		// Instantiate visitor
        EntityDrawingVisitor visitor = new EntityDrawingVisitor(graphicsContext);
        
		for (Entity entity : fireworkEntities) {
			//display the fireworks
			//goes to firework entity then to abstract visitor then to EntityDrawingVisitor to display
			entity.accept(visitor);
		}
	}
	
	/**
	 * @return the fireworkEntities
	 */
	public ArrayList<FireworkEntity> getFireworkEntities() {
		return this.fireworkEntities;
	}

	/**
	 * @param fireworkEntities the fireworkEntities to set
	 */
	public void setFireworkEntities(ArrayList<FireworkEntity> fireworkEntities) {
		this.fireworkEntities = fireworkEntities;
		//Redraw the canvas
		redrawCanvas();
	}
}