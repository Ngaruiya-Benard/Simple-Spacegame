



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JPanel;
/**
 * Plane is a subclass of vehicles that creates a plane object with behaviors
 * @author Benard Ngaruiya
 *
 */
public class Plane extends Vehicles {
	public boolean mirror;

	public Plane(int xLoc, int yLoc){
		yco_ordinates = new int[13];
		xco_ordinates = new int[13];
		zoomX = new int[13];
		zoomY = new int[13];
		speed = 15;
		co_ordinateCount = 13;
		isSelected = false;
		setInitValues(xLoc, yLoc);
	}
	
	public void paintMethod(Graphics g, Main pseudoFrame, JPanel canvas){
		super.paintMethod(g, pseudoFrame, canvas);
		if (onDisplay == false)
			return;
		super.paintMethod(g, pseudoFrame, canvas);
		int counter =0;
		for (int i = 0; i < co_ordinateCount; i ++)
		{
			if(xco_ordinates[i] < 100)
				counter++;
		}
		if (counter == co_ordinateCount)
			isEdge = false;
		
		//to get the vehicle to move and wrap around the edge
		if(mirror == false)
			init_x = (init_x + speed)%canvas.getWidth();
		
		if (mirror ==true){
			int	[]xValues = {init_x,init_x+20,init_x+18,init_x+24,init_x+28,init_x+54,init_x + 46,init_x+26,init_x + 22,init_x +16,init_x+20,init_x,init_x-24};
			
			int[]yValues = {init_y-12,init_y-6,init_y-6,init_y-8,init_y-8,init_y-6,init_y-6,init_y,init_y,init_y+4,init_y+4,init_y,init_y};
			
			setCo_ordinates(xValues, yValues);
		}
		else{
		int[]xValues = {init_x-24,init_x,init_x+20,init_x+16,init_x+22,init_x+26,init_x+46,init_x+54,init_x+28,init_x+24,init_x+18,init_x+20,init_x};
		int[]yValues = {init_y-12,init_y-6,init_y-6,init_y-8,init_y-8,init_y-6,init_y-6,init_y,init_y,init_y+4,init_y+4,init_y,init_y};
		setCo_ordinates(xValues,yValues);
		}
		
		setCo_ordinateCount(13);
		
		
		if (isEdge == true)
			return;
		if(zoom == false){
			g.drawPolygon(xco_ordinates,yco_ordinates, co_ordinateCount);
			if(isSelected == true){
				g.setColor(Color.RED);
				g.fillPolygon(xco_ordinates, yco_ordinates, co_ordinateCount);
				g.setColor(Color.BLACK);
			}
			else
				g.fillPolygon(xco_ordinates, yco_ordinates, co_ordinateCount);
		}
		
		else{
			g.drawPolygon(zoomX,zoomY, co_ordinateCount);
			if(isSelected == true){
				g.setColor(Color.RED);
				g.fillPolygon(zoomX, zoomY, co_ordinateCount);
				g.setColor(Color.BLACK);
			}
			else
				g.fillPolygon(zoomX, zoomY, co_ordinateCount);
			
		}
	}
	
	
}
