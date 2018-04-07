package ballTracker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BallsPanel extends JPanel {

	private int WIDTH,HEIGHT, TurnRadius;
	private Point mouseLocation;
	private Ball ball1, ball2;
	
	public BallsPanel(int Width, int Height) {
		WIDTH = Width;
		HEIGHT = Height;
		TurnRadius = 100;
		mouseLocation = new Point(Width/2,Height/2);
		
		ball1 = new Ball(new Point(200,380));
		ball2 = new Ball(new Point(600,380));
		
		addMouseMotionListener(new mouse());
		
		
		setPreferredSize(new Dimension(Width,Height));
		setFocusable(true);
		requestFocus();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ball1.drawBall(g);
		ball2.drawBall(g);
		g.setColor(Color.BLACK);
		g.fillOval(350,600,100,100);
		g.fillRect(25, 100, 350, 50);
		g.fillRect(425, 100, 350, 50);
	}
	
	private class mouse extends MouseAdapter{
		public void mouseMoved(MouseEvent e) {
			mouseLocation.x = e.getX();
			mouseLocation.y = e.getY();
			ball1.updateBall();
			ball2.updateBall();
			repaint();
		}
	}
	
	private class Ball{
		
		private final int RADIUS;
		private Point loc;
		private final Point MIDDLE;
		
		public Ball(Point middle) {
			RADIUS = 200;
			loc = middle;
			MIDDLE = middle;
		}
		public int getRadius() {
			return RADIUS;
		}
		public void setLocation(int x , int y) {
			loc = new Point(x,y);
		}
		public int getX() {
			return loc.x;
		}
		public int getY() {
			return loc.y;
		}
		public void drawBall(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillOval(MIDDLE.x-(TurnRadius*4/2), MIDDLE.y-(TurnRadius*4/2), (TurnRadius*4), (TurnRadius*4));
			g.setColor(Color.WHITE);
			g.fillOval(loc.x-RADIUS/2, loc.y-RADIUS/2, RADIUS, RADIUS);
		}
		public void updateBall() {
			double ydif = (mouseLocation.y - MIDDLE.y);
			double xdif = (mouseLocation.x - MIDDLE.x);
			double angle = Math.atan(ydif / xdif);
			if((xdif < 0 && ydif < 0) ||xdif < 0)
				angle += Math.PI;
			double hyp = Math.sqrt(xdif*xdif + ydif*ydif);
			int bX,bY;
			if(hyp < TurnRadius) {
				bX = (int)(Math.cos(angle)*hyp);
				bY = (int)(Math.sin(angle)*hyp);
			}else{
				bX = (int)(Math.cos(angle)*TurnRadius);
				bY = (int)(Math.sin(angle)*TurnRadius);
			}
			//System.out.println("Y Difference: "+ ydif +" X Difference: "+xdif +" angle: "+angle*180 /Math.PI +" Ball's X: "+ bX 
			//		+ " Ball's Y: "+bY);
			setLocation(MIDDLE.x + bX,MIDDLE.y + bY);
		}
	}
}
