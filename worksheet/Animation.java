import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Animation extends JPanel implements ActionListener {
    private JButton btnBlue, btnRed, btnGreen, btnYellow, btnStart, btnStop;
    private JTextField xSpeed, ySpeed;
    private JLabel xAxisSpeed, yAxisSpeed;
    private int x, y;
    private int SpeedXaxis, SpeedYaxis; 
    private Timer timer;
    private Color selecColor;
    private int radius = 20; 

    public Animation() {
    timer = new Timer(50, this);

        btnGreen = new JButton("Green");
        btnRed = new JButton("Red");
        btnYellow = new JButton("Yellow");
        btnBlue = new JButton("Blue");
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        xSpeed = new JTextField(10);
        ySpeed = new JTextField(10);
        JLabel xAxisSpeed = new JLabel("Enter a value for x-axis speed:");
        JLabel yAxisSpeed = new JLabel("Enter a value for y-axis speed:");


    
        add(xAxisSpeed);
        add(xSpeed);
        add(yAxisSpeed);
        add(ySpeed);
        add(btnRed);
        add(btnBlue);
        add(btnYellow);
        add(btnGreen);
        add(btnStart);
        add(btnStop);

       
        btnGreen.addActionListener(this);
        btnYellow.addActionListener(this);
        btnRed.addActionListener(this);
        btnBlue.addActionListener(this);
        btnStart.addActionListener(this);
        btnStop.addActionListener(this);

        selecColor = Color.GREEN; //setting default 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the ball
        g.setColor(selecColor);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRed) {
            selecColor = Color.RED;
        } else if (e.getSource() == btnYellow) {
            selecColor = Color.YELLOW;
        } else if (e.getSource() == btnBlue) {
            selecColor = Color.BLUE;
        } else if (e.getSource() == btnGreen) {
            selecColor = Color.GREEN;
        } else if (e.getSource() == timer) {
            animation();
        } else if (e.getSource() == btnStart) {
            try {
            SpeedXaxis = Integer.parseInt(xSpeed.getText());//turning the inputs that we got from user
            SpeedYaxis = Integer.parseInt(ySpeed.getText());// string to text
            x = getWidth() / 2; 
            y = getHeight() / 2; 
            timer.start();
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        } else if (e.getSource() == btnStop) {
            timer.stop();
        }
        x += SpeedXaxis;
        y += SpeedYaxis;
        repaint();
    }

    private void animation() {
        if (x < getWidth() - radius && x > 0) {
            x += SpeedXaxis;
        } else {
            SpeedXaxis = -SpeedXaxis;
            x += SpeedXaxis;
        }
        if (y < getHeight() - radius && y > 0) {
            y += SpeedYaxis;
        } else {
            SpeedYaxis = -SpeedYaxis;
            y += SpeedYaxis;
        }
    }

    public static void main(String[] args) {
        JFrame myFrame = new JFrame();
        myFrame.setTitle("Moving Ball Animation");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Animation animation = new Animation();
        myFrame.add(animation);
        myFrame.pack();
        myFrame.setVisible(true);
    }
}
