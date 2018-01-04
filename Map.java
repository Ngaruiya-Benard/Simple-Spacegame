

/*
 * Benard Ngaruiya comp 86
 * Benard.Ngaruiya@tufts.edu
 */


/*
 * Class Map is charge of the drawing pane 
 * It implements repaint actions and the pausing action
 * 
 */



import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

import org.w3c.dom.css.Rect;
/**
 * This class contains my methods and variables responsible for creating a canvas and storing my vehicle objects
 * @author Benard Ngaruiya
 *
 */
public class Map extends JPanel{
    private Main parent;
    private boolean play;
    private boolean isGame;
    private boolean zoom;
    private int timer;
    protected boolean end;
    private ArrayList<Vehicles> myVehicles;
    
    public Map (Main parent, String mode) {
    	this.parent = parent;
    	myVehicles = new ArrayList<Vehicles>();
    	AdjustmentListener gameMover = new AdjustmentListener(){
    		@Override
    		public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent){
    			myVehicles.get(myVehicles.size()-1).setInit_y(adjustmentEvent.getValue());
    		}
    	};
    	this.setLayout(new BorderLayout());
    	if (mode.equals("car")){
    	isGame = true;
		Vehicles plane1 = new Plane(50,50);
		Vehicles plane2 = new Plane(100,100);
		Vehicles plane3 = new Plane(50,150);
		Vehicles plane4 = new Plane(100,200);
		Vehicles plane5 = new Plane(50, 50-100);
		Vehicles plane6 = new Plane(100, 0);
		Vehicles plane7 = new Plane(50, 50 - 200);
		Vehicles plane8 = new Plane(100-200, 100);
		Vehicles plane9 = new Plane(50-200, 200);
		Plane player = new Plane(780, 130);
		player.setSpeed(0);
		player.mirror = true;
		myVehicles.add(plane1);
		myVehicles.add(plane2);
		myVehicles.add(plane3);
		myVehicles.add(plane4);
		myVehicles.add(plane5);
		myVehicles.add(plane6);
		myVehicles.add(plane7);
		myVehicles.add(plane8);
		myVehicles.add(plane9);
		myVehicles.add(player);
		
		//playerBox = (Rect) myVehicles.get(myVehicles.size()-1).vehicle.getBounds2D();
		timer = 100;
		for(int i = 0; i < myVehicles.size(); i++){
			//setting every car's display on and setting their speed
			myVehicles.get(i).setDisplay("on");
			if(i!=myVehicles.size()-1)
				myVehicles.get(i).setSpeed(50);
		}
		
		JScrollBar gameScroll = new JScrollBar(JScrollBar.VERTICAL, 50,100,0,700);
		gameScroll.addAdjustmentListener(gameMover);
		gameScroll.setPreferredSize(new Dimension(20,100));
		gameScroll.setAlignmentX(RIGHT_ALIGNMENT);
		add(gameScroll, BorderLayout.EAST);
		end = false;
		
    	}
    	
    }
    public void setPlayer(boolean set){
    	play = set;
    }
    public void paintComponent (Graphics g) {
    	super.paintComponent(g);
    	   timer++;
    	Graphics2D g2 = (Graphics2D) g;
    	if(isGame == false){
    		//This part is never called for assignment 5 since we are using the game mode
    		//conditionals to tell whether the programming is paused or not
    		if (play == true)
    		{ 		
    			for(int i = 0; i < myVehicles.size(); i++)
    			{
    				myVehicles.get(i).paintMethod(g, parent, this);
    			}
    		    		
    		}
    		else
			{//when pause has been pressed
    			for(int i = 0; i < myVehicles.size(); i++){
    				if (myVehicles.get(i).getDisplay() == true)
    				{
    					if(myVehicles.get(i).getEdge() == false)
    					{
    						g.drawPolygon(myVehicles.get(i).getxCo_ordinates(),myVehicles.get(i).getyCo_ordinates(),
    								myVehicles.get(i).getCo_ordinateCount());
    						g.fillPolygon(myVehicles.get(i).getxCo_ordinates(),myVehicles.get(i).getyCo_ordinates(),
    								myVehicles.get(i).getCo_ordinateCount());
    					}
    				}
    			}
			}
    		g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
    		g.drawLine(getWidth()/4, getHeight(), getWidth()/3, getHeight()/2);
    		g.drawLine(3*getWidth()/4, getHeight(), 2*getWidth()/3, getHeight()/2);
    		  
    		float dash[] = { 10.0f };
    	        g2.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
    	        g2.drawLine(getWidth()/2, getHeight(), getWidth()/2, getHeight()/2);
    	}
    	else
    	{
			//The game leg of this assignment runs solely on this mode
			setUpGame("plane",g);
    	}
   
    }
    
    
    public ArrayList<Vehicles> getmyVehicles()
    {
    	return myVehicles;
    }
    
    
   	
	
	public void addVehicle(int xLoc, int yLoc, String type){
		if (type.equals("plane"))
			myVehicles.add(new Plane(xLoc, yLoc));
		else if(type.equals("boat"))
			myVehicles.add(new Boat(xLoc, yLoc));
		else if(type.equals("car"))
			myVehicles.add(new Car(xLoc, yLoc));
	}
	
	public void setUpGame(String mode, Graphics g){
		if (mode.equals("plane")){
			
				for(int i = 0; i < myVehicles.size(); i++){
					
					myVehicles.get(i).setDisplay("on");
					if (play == true){
						if(zoom == true){
							myVehicles.get(i).setZoom(true);
						}
						else
							myVehicles.get(i).setZoom(false);
						myVehicles.get(i).paintMethod(g, parent, this);
						
					}
					else{
						if (myVehicles.get(i).getDisplay() == true)
						{
							if(myVehicles.get(i).getEdge() == false)
							{
								if(zoom == true){
									myVehicles.get(i).setZoom(true);
								}
								else{
									myVehicles.get(i).setZoom(false);
								}
								g.drawPolygon(myVehicles.get(i).getxCo_ordinates(),myVehicles.get(i).getyCo_ordinates(),
    								myVehicles.get(i).getCo_ordinateCount());
								if(myVehicles.get(i).isSelected == true){
									g.setColor(Color.RED);//Painting selected vehicle
									g.fillPolygon(myVehicles.get(i).getxCo_ordinates(),myVehicles.get(i).getyCo_ordinates(),
	    								myVehicles.get(i).getCo_ordinateCount());
									g.setColor(Color.BLACK);
								}
								else{
								g.fillPolygon(myVehicles.get(i).getxCo_ordinates(),myVehicles.get(i).getyCo_ordinates(),
    								myVehicles.get(i).getCo_ordinateCount());
								}
							}
						}
					}
				}
				
				for(int i = 0;i < myVehicles.size();i++){
					if(i!=myVehicles.size()-1 && myVehicles.get(i).isInitialized == true && myVehicles.get(myVehicles.size()-1).isInitialized == true && end!= true){
						if(myVehicles.get(i).vehicleRect.intersects(myVehicles.get(myVehicles.size()-1).vehicleRect)){
								parent.playing = false;
								final int score = parent.getFrame();
								end = true;
								JOptionPane.showMessageDialog(null, "Game Over! Your Score is: " + score);
								System.exit(0);
								
								
						}
					}
				}
		}
	}
	
	public int getArraySize(){
		return myVehicles.size();
	}
	
	public void checkPlanes(int x, int y){
		for(int i = 0;i < myVehicles.size(); i++){
			myVehicles.get(i).selectVehicle(x,y);
		}
	}
	
	public boolean checkPlaying(){
		return play;
	}
	
	public boolean checkZoom(){
		return zoom;
	}
	
	public void setZoom(boolean set){
		zoom = set;
	}
}

