import java.awt.Color;
import java.awt.Graphics;

public class Shape extends Thread {
	
	private int x, y, r, speedX, speedY;
	private Color color;
	
	public Shape(int x, int y, int speedX, int speedY, Color color) {
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.color = color;
		this.r = 20;
		
	}
	
	public void reverseX() {
        speedX = -speedX;
    }

    public void reverseY() {
        speedY = -speedY;
    }
	
	public void move() {
		x += speedX;
		y += speedY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getR() {
		return r;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x - r, y - r, 2 * r, 2 * r);
	}
	
}