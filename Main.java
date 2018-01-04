
	


/**
 * Benard Ngaruiya comp 86
 * Benard.Ngaruiya@tufts.edu
 * 
 * 
 * 
 *
 * This file contains my Main driver
 * it contains the main window(JFrame) and its containers
 * it also keeps track of time, 
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Main extends JFrame implements ActionListener, KeyListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private int frame = 1;
	private Map canvas;
	protected boolean playing = true;
	private JButton speed;
	private JComboBox<String> speedSelect;
	private JButton addVehicle;
	private JComboBox<String> vehicleSelect;
	private JLabel  vehicleSelectLabel;
	private JTextPane xAddValue;
	private JTextPane yAddValue;
	private JLabel xValuesLabel;
	private JLabel yValuesLabel;
	private JLabel addValuePrompt;
	private JButton enterCo_ordinates;
	private JLabel mouseClickOption;
	private Timer time;
	protected JButton select_vehicle;
	private JLabel selectVehicleLabel;
	private JLabel progress;
	
	public static void main(String args[]){
		java.awt.EventQueue.invokeLater (new Runnable() {
            public void run() {
		new Main ();
            }
        });
	}
	
	public Main(){
		addMouseListener(this);
		
		//declaring a container to hold widgets
		setSize(1000,400);
		setResizable(true);
		Container contents = getContentPane();
		contents.setLayout(new BorderLayout());
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		setLocation(100,100);
		addKeyListener(this);
		
		//Adding canvas to my Window
		canvas = new Map(this, "car");
		canvas.setBorder(new LineBorder(Color.RED,1));
		contents.add(canvas);
		setVisible(true);

	//setting a titleBar
		JLabel title = new JLabel("SIMPLE ANIMATION");
		title.setFont(new Font("Serif", Font.BOLD,14));
		contents.add(title,BorderLayout.NORTH);
		
		//setting up a control Panel
		JPanel controls = new JPanel();
		controls.setLayout(new FlowLayout(FlowLayout.LEFT, 5,40));
		controls.setPreferredSize(new Dimension(147,10));
		controls.setBorder(new LineBorder(Color.GREEN,2));
		
		//setting ComboBox to pick cars added
		String [] vehicleSelection = {"plane"};
		vehicleSelect = new JComboBox<String>(vehicleSelection);
		vehicleSelectLabel = new JLabel("Select vehicle type ");

		//setting vehicle select options to invisible to start with
		vehicleSelectLabel.setVisible(false);
		vehicleSelect.setVisible(false);
		
		//Setting up Button to allow for zooming
				JButton zoomButton = new JButton("ZOOM");
				zoomButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(canvas.checkZoom()){
							canvas.setZoom(false);
						}
					
						else{
							canvas.setZoom(true);
						}
					}
				});
		
		//setting button to add vehicles
		addVehicle = new JButton("ADD VEHICLES");
		addVehicle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				vehicleSelectLabel.setVisible(true);
				vehicleSelect.setVisible(true);
			}
		});
		
		//setting up text Fields to enter x,y values of added vehicles
		addValuePrompt = new JLabel("Enter x,y values        ");
		mouseClickOption = new JLabel("or click in the canvas");
		xAddValue = new JTextPane();
		xAddValue.setPreferredSize(new Dimension(30,20));
		yAddValue = new JTextPane();
		yAddValue.setPreferredSize(new Dimension(30,20));
		xValuesLabel = new JLabel("X Value                    ");
		yValuesLabel = new JLabel("Y Value               ");
		enterCo_ordinates = new JButton("ENTER");
		addValuePrompt.setVisible(false);
		mouseClickOption.setVisible(false);
		xAddValue.setVisible(false);
		yAddValue.setVisible(false);
		xValuesLabel.setVisible(false);
		yValuesLabel.setVisible(false);
		enterCo_ordinates.setVisible(false);
		
		//Upon selecting a vehicle, making resultant components visible
		vehicleSelect.addActionListener(new ActionListener(){


			public void actionPerformed(ActionEvent e){
				addValuePrompt.setVisible(true);
				mouseClickOption.setVisible(true);
				xAddValue.setVisible(true);
				yAddValue.setVisible(true);
				xValuesLabel.setVisible(true);
				yValuesLabel.setVisible(true);
				enterCo_ordinates.setVisible(true);
				addVehicle.setVisible(false);
				select_vehicle.setVisible(false);
				zoomButton.setVisible(false);
				setSize(1000,700);
			}
		});
		
		enterCo_ordinates.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
					canvas.addVehicle(Integer.parseInt(xAddValue.getText()), Integer.parseInt(yAddValue.getText()), vehicleSelect.getSelectedItem().toString());
					canvas.getmyVehicles().get(canvas.getmyVehicles().size()-1).setDisplay("on");
					
					//To reset the values of the textpane and setting widgets no longer in use to invisible.
					xAddValue.setText("");
					yAddValue.setText("");
					addValuePrompt.setVisible(false);
					mouseClickOption.setVisible(false);
					xAddValue.setVisible(false);
					yAddValue.setVisible(false);
					xValuesLabel.setVisible(false);
					yValuesLabel.setVisible(false);
					enterCo_ordinates.setVisible(false);
					vehicleSelectLabel.setVisible(false);
					vehicleSelect.setVisible(false);
					addVehicle.setVisible(true);
					select_vehicle.setVisible(true);
					zoomButton.setVisible(true);
					setSize(1000,400);

			}
		});
		
		//setting a button whose function is to allow you to select a vehicle
		selectVehicleLabel = new JLabel("select vehicle using mouse");
		selectVehicleLabel.setVisible(false);
		select_vehicle = new JButton("Change color");
		select_vehicle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (playing == true){
					selectVehicleLabel.setVisible(true);
					playing = false;
				}
				else{
					selectVehicleLabel.setVisible(false);
					playing = true;
				}
			}
		});
		
		
		
		
		progress = new JLabel("Current Score: " + frame);
		
		controls.add(Box.createVerticalStrut(10));
		controls.add(addVehicle);
		controls.add(vehicleSelectLabel);
		controls.add(vehicleSelect);
		controls.add(addValuePrompt);
		controls.add(mouseClickOption);
		controls.add(xAddValue);
		controls.add(xValuesLabel);
		controls.add(yAddValue);
		controls.add(yValuesLabel);
		controls.add(enterCo_ordinates);
		controls.add(select_vehicle);
		controls.add(selectVehicleLabel);
		controls.add(zoomButton);
		controls.add(progress);
;		//adding control Panel to my window
		contents.add(controls, BorderLayout.EAST);
		
		//creating Action Panel
		JPanel actions = new JPanel();
		actions.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
		actions.setBorder(new LineBorder(Color.RED,2));
		
		//creating tools to add to my action Panel
		speed = new JButton("SET SPEED");
		String [] speedSelections = {"0.5x", "1.0x","2.0x", "4.0x"};
		speedSelect = new JComboBox<String>(speedSelections);
		speedSelect.setVisible(false);
		
		//setting an actionListener for the speedButton
		
		speed.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a){
				speedSelect.setVisible(true);
			}
		});
		
		
		//setting up an actionListener for the speed comboBox
		speedSelect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setSpeed(speedSelect.getSelectedItem().toString());
				speedSelect.setVisible(false);
			}
		});
		
		//Adding tools to my action Panel
		//actions.add(pause);
		actions.add(speed);
		actions.add(speedSelect);
	
		
		//adding actions bar to my window
		contents.add(actions, BorderLayout.SOUTH);

		
		//start timer
		
		time = new Timer(100, this);
		time.start();
		}
	
	//Function allows for user to set the speed of the simulation
	public void setSpeed(String speed){
		if (speed.equals("0.5x")){
			time.stop();
			time.setDelay(200);
			time.start();
		}
		else if(speed.equals("1.0x")){
			time.stop();
			time.setDelay(100);
			time.start();
		}
		else if(speed.equals("2.0x")){
			time.stop();
			time.setDelay(50);
			time.start();
		}
		else if(speed.equals("4.0x")){
			time.stop();
			time.setDelay(25);
			time.start();
		}
	}
	
	public int getFrame(){
		return frame;
		}
	
	public void actionPerformed(ActionEvent e) {
		frame++;
		//playing = select_vehicle.isPlaying();
		
		canvas.setPlayer(playing);
		if(playing == false)
			speed.setEnabled(false);
		else
			speed.setEnabled(true);
		canvas.repaint();
		if (canvas.end!= true)
		progress.setText("Current score: " + frame);
	}

//set it so escape terminates the program
	 public void keyPressed (KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ESCAPE) System.exit (0);
	 }

	   


	public void keyReleased(KeyEvent e) {

		
	}

	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (xAddValue.isVisible() == true && yAddValue.isVisible()){
			xAddValue.setText(Integer.toString(e.getX()));
			yAddValue.setText(Integer.toString(e.getY()));
		}
		
		if(!canvas.checkPlaying()){
						canvas.checkPlanes(e.getX(), e.getY()-60);
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

