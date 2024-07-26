import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AnimatedShapes extends JPanel implements ActionListener {
	
	private JButton btnStart, btnStop;
	private JLabel lblNum;
	private JTextField txtNumObj;
	private int numObj;
	private Shape[] shapes; 
	private Animation animation;
	private Random ran;
	public boolean isAnimationRunning = false; //will be used in Animation class that's why it's public
	
	public AnimatedShapes() {
		btnStart = new JButton("Start");
		btnStop = new JButton("Stop");
		txtNumObj = new JTextField(5);
		lblNum = new JLabel("Number of Objects: ");

		
		//designing gui
		add(lblNum);
		add(txtNumObj);
		add(btnStart);
		add(btnStop);
		
		btnStart.addActionListener(this);
		btnStop.addActionListener(this);
		shapes = new Shape[0];
		ran = new Random(); 
		
	}
	
	//drawing shapes 
	public void paint(Graphics g) {
		super.paint(g);
		for (Shape shape : shapes) {
			shape.draw(g);
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
			if (e.getSource() == btnStart) {
			try {
			numObj = Integer.parseInt(txtNumObj.getText());
			Shapes();
			startAnimation();
			} catch(NumberFormatException ex){
				ex.printStackTrace();				
			}
		} else if (e.getSource() == btnStop) {
			stopAnimation();
		}	
	}

	public void Shapes() {
		shapes = new Shape[numObj];
		for(int i = 0; i < numObj; i++) {
			int x = ran.nextInt(getWidth());
			int y = ran.nextInt(getHeight());
			int speedX = (int) (Math.random() * 15); //random x-axis speed value
			int speedY = (int) (Math.random() * 15); //random y-axis speed value
			Color color = new Color(ran.nextInt(255),ran.nextInt(255), ran.nextInt(255)); //random colors
			shapes[i] = new Shape(x, y, speedX, speedY, color); //using Shape class which extends Thread
		}
	}
	
	//startAnimation method implemented for using in actionPerformed --- ==btnStart
	private void startAnimation() {
		if(!isAnimationRunning) {
			animation = new Animation(shapes, this);
			animation.start();
			isAnimationRunning = true;
		}
		
	}
	
	//stopAnimation method implemented for using in actionPerformed --- ==btnStop
	private void stopAnimation() {
		if(isAnimationRunning) {
			animation.interrupt();
			isAnimationRunning = false;
		}
	}

	//MAIN METHOD

	public static void main(String[] args) {
		JFrame Frame = new JFrame(); //creating Frame
		Frame.setTitle("Moving Shapes Animation");
		Frame.setVisible(true);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		AnimatedShapes animatedShapes = new AnimatedShapes();
		Frame.setSize(800, 600);
		Frame.add(animatedShapes);

	} 
}





















