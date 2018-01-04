# Simple-Spacegame
/*Benard Ngaruiya
 *readme
 *Benard.Ngaruiya@tufts.edu
 */
 
 Aggregation and Inheritance:
 The inheritance hierarchy is as follows:
 	*Plane is a subclass that inherits from Vehicle
 	*Main inherits from JFrame and implements methods from the abstract classes KeyListener and MouseListener
 	
 	
 The Aggregate hierarchy for this assignment is as follows:
 	Main class (which contains the main driver for the assignment) owns:
 		*JButton
 		*ComboBox instance
 		*ComboBoxCars instance
 		*Map (which also has an instance of Main.
 		*Timer
 		
 	Map
 		*Owns an instance of Main Class (for access to the frame while calling time.
 		
 HOW THE PROGRAM WORKS
 In the newer version of the program. The program is run as a game of space craft, where the player is expected to dodge incoming planes.
 To play the game, the user uses the scrollbar on the EAST side of the canvas to control his vehicle through the sky and avoid oncoming traffic
 
 The Simulation version can also be run by tweaking input in the Main file. Newer inputs include allowing the user to pick individual cars to draw from.
 When run, the program displays an empty canvas screen, an action and a control bar. The user is then prompted
 to select from a drop box how many of three vehicles he would like on the canvas. After that, the user is also 
 given the choice to add more than the three selected vehicles on the screen. I have also implemented a speed selection
 prompt, giving the user the ability to decide what speed the simulation should move in. 
 Because some widgets are only required in specific circumstances, I have opted to set some options invisible while
 they are not in use. eg. widgets associated with adding vehicles to our simulation are only made visible when the user 
 presses the AddVehicle button. I have also utilized the Object.setEnabled() function on the speed button, because the 
 user should not be concerned with the speed of the simulation if the simulation is not running.
 
 COLLABORATIONS
 To achieve different tasks, different widgets work together towards such a goal.
 	*To allow the user to pick cars, a mouseListener passes information to the Map class which passes o_ordinates to the Vehicle class which then tries to find the selected vehicle.
 	* For accuracy reasons, I generated a square for drawn from the selected mouse position, and then used the rectangle intersects function to figure out the closest vehicle to the selected region during mouse selecting
 	*To add the zoom quality, I stored an extra array for each vehicle to store co_ordinates for an x and y scaling of factor 2 which is painted when zoom is selected.
 	*To include the add vehicle utility, VehicleSelect(JComboBox) VehicleSelectLabel(JLabel) xAddValue (JTextPane), yAddValue(JTextPane)
 	and EnterCo_ordinates(Button) and a MouseListener work together to direct the user towards giving suitable co_ordinates to add a vehicle.
 	The information is then sent to the vehicles constructor which creates a new vehicle and which is then added to an arrayList that keeps track
 	of the vehicles on the canvas. 
 	
 	*To manipulate speed, a speed button, and a JComboBox collect speed selections in the action bar of the interface
 	A setSpeed function then processes the collected information and determines the requested speed. It then changes the running
 	Timer's delay time accordingly.
 
 
 DESIGN DECISIONS
 For design operations, 
	Map is placed in charge of functionalities within the canvas. It's duties include: repainting the canvas, maintaining an arrayList of 
	the vehicles in the current simulation, choosing to paint them on the canvas or not,
	
	Vehicle class maintains the state and co_ordinates of its member objects.
	
	Each subclass of Vehicles decides what it should look like, from data taken from the main program and then decides how it should
	paint itself and what changes should occur to it every time it's called to paint.
	
	
	Main contains the major components of the interface. It houses instances of classes, canvas and pauseButton. It's functions
	primarily revolve around interacting with the user, collecting information on when the user wants the simulation to run and what vehicles the user
	wishes to see on the canvas. 
	
	In this assignment I got rid of Boats and cars since they are redundant and not needed in the game simulation.
	
	
	
CHALLENGES AND DISCOVERIES
	The biggest problem I had with this was figuring out how to handle collisions since in previous assignments I had simply stored and updated array co_ordinates in lieu of actual polygons, which I would just draw with the 
	draw function to draw. In this assignment, I stored a Polygon object which I then reinitialized in each repaint to take in newer co_ordinates. With that, the biggest problem was now figuring out collisions, since eclipse would not allow me to use the intersects function of Polygons. I then
	decided to use 2D rectangles which I would use to store the bounding box of the Polygons. Once I did that, I simply used the intersects function of the Rectangle class to handle collisions. 
	
