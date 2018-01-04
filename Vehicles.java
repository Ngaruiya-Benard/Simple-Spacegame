



import java.awt.Graphics;
import javafx.scene.shape.*;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * Vehicles is a parent class for all vehicles animated on the canvas
 * This class declares variables meant to store state variables of our vehicles
 * 
 * @ author Benard Ngaruiya
 */


public class Vehicles {
	protected int init_x;
	protected int init_y;
	protected int[]xco_ordinates;
	protected int co_ordinateCount;
	protected int[]yco_ordinates;
	protected int[]zoomX;
	protected int[]zoomY;
	protected boolean isEdge;
	protected boolean onDisplay;
	protected int speed;
	public Polygon vehicle;
	public boolean isSelected;
	protected boolean zoom;
	protected Rectangle2D vehicleRect;
	protected boolean isInitialized;
	
	public Vehicles(){}
	public Vehicles(int xLoc, int yLoc){
		setInitValues(xLoc, yLoc);
		onDisplay = false;
		zoom = false;
		isInitialized = false;
	}
	
	public void setInitValues(int x, int y){
		init_x = x;
		init_y = y;
	}
	
	public int getInit_x(){
		return init_x;
	}
	
	public int getInit_y(){
		return init_y;
	}
	
	
	public int[] getxCo_ordinates(){
		if(zoom == true)
			return zoomX;
		else
			return xco_ordinates;
	}
	
	public void setCo_ordinates(int[] tempx, int [] tempy){
		for(int i = 0; i < co_ordinateCount; i++){
			xco_ordinates[i] = tempx[i];
			zoomX[i] = tempx[i]*2;
		}
	
		for(int i = 0; i < co_ordinateCount; i++){
			yco_ordinates[i] = tempy[i];
			zoomY[i] = tempy[i]*2;
		}
		if (zoom == true)
			vehicle = new Polygon(zoomX,zoomY,co_ordinateCount);
		else
			vehicle = new Polygon(xco_ordinates, yco_ordinates, co_ordinateCount);
		vehicleRect = vehicle.getBounds2D();
		isInitialized = true;
	}
	
	public int[] getyCo_ordinates(){
		if(zoom ==true)
		return zoomY;
		else
			return yco_ordinates;
	}
	public void setCo_ordinateCount(int p){
		co_ordinateCount = p;
	}
	
	public int getCo_ordinateCount(){
		return co_ordinateCount;
	}
	//abstract method to draw my vehicles
	public void paintMethod(Graphics g, Main pseudoFrame, JPanel canvas){
		
	}
	
	public void setDisplay(String display){
		if (display.equals("on"))
		onDisplay = true;
		else if(display.equals("off"))
			onDisplay = false;
	}
	public boolean getDisplay(){
		return onDisplay;
	}
	public boolean getEdge(){
		return isEdge;
	}
	
	public void setSpeed(int tempSpeed){
		speed = tempSpeed;
	}
	
	public void setInit_y(double y){
		init_y = (int) y;
	}
	
	public void selectVehicle(int x, int y){
		Rectangle checkPoint = new Rectangle(x,y, 10,10);
		if(vehicleRect.intersects(checkPoint)){
			isSelected = true;
		}
	}
	
	public void setZoom(boolean set){
		zoom = set;
	}
	
}
