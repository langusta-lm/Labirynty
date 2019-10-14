package Lab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


public class Wall {
	private Point position;
	//private boolean horizontal;
	private boolean visible;
	private int size  = 10;
	private Rectangle rectangle;
	private boolean isFirstBlock = false;
	private boolean showCenter=false;
	
	public Wall() {
		
		/*
		if((position.x+position.y % 2)%2 == 1) visible=true;
		else visible = false;
		if(position.y %2 == 0) horizontal = true;
		else horizontal = false;
		if(horizontal)
			rectangle = new Rectangle(position.x-size.x, position.y-size.y, size.x+size.x, size.y);
		else rectangle = new Rectangle(position.x, position.y, size.y, size.x*2);
		System.out.println((position.x+position.y %2)%2);
		*/
	}
	public void init(Point position) {
		this.position = position;
		visible = true;
		rectangle = new Rectangle(position.x-size, position.y-size, size*2,size*2);
	}
	
	public void paint(Graphics g) {
		
		if(isGrid())
			g.setColor(Color.red);
		else 
			g.setColor(Color.green);  
		if(visible) 
			g.fillRect(rectangle.x+1, rectangle.y+1, rectangle.width-1, rectangle.height-1);
		else if(isGrid()){
			g.setColor(Color.yellow);
			g.drawRect(rectangle.x+5, rectangle.y+5, rectangle.width-10, rectangle.height-10);
		}
		if(showCenter) {
			g.setColor(Color.yellow);
			int r = 4;
			g.fillOval(position.x-r, position.y-r, r*2,r*2);
		}
		
		if(isFirstBlock) {
			g.setColor(Color.yellow);
			int r = 8;
			g.fillOval(position.x-r, position.y-r, r*2,r*2);
		}
	}
	@SuppressWarnings("deprecation")
	public boolean detect(Point p) {
		if(rectangle.inside(p.x, p.y)){
			return true;
		}
			
		else
			return false;
	}
	
	public void setVisible(boolean b) {
		//showCenter=true;
		visible=b;
	}
	
	public boolean isVisible() {return visible;}
	public Rectangle getRect() {return rectangle;}
	
	public boolean isGrid() {
		if(rectangle.y < 20)
			return true;
		else if(rectangle.y > 350)
			return true;
		else if(rectangle.x < 20)
			return true;
		else if(rectangle.x > 350)
			return true;
		else 
			return false;
	
	}
	public void setFirstBlock(boolean b) {isFirstBlock=b;}
	
	
}
