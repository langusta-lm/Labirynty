package Lab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Runner {
	private Point position;
	private Color color;
	private int angle;
	private int size = 5;
	private int howLongCanSee = 20;
	private Rectangle scanner;
	private Rectangle rectRuner;
	private Point virtualJump;
	private int lifeTime;
	private boolean bot;
	private boolean blocked;
	public Runner(Point p, boolean bot) {
		blocked = true;
		if(testOfCompatibility(p)) blocked = false;
		
		
		this.bot=bot;
		if(!bot) lifeTime = 1000;
		else  lifeTime = 20;
		rectRuner = new Rectangle(p.x-size, p.y-size, size*2,size*2);
		virtualJump = new Point(20,20);
		color = Color.BLUE;
		angle = 90;
		
		//size = howLongCanSee/3;
		position = p;
		update();
	}
	private boolean testOfCompatibility(Point p) {
		//
		for(int i=0; i<=500 ; i+=40)
			for(int j=20; j<=500; j+=40) 
				if((p.x >= i && p.x <= i+20) && (p.y >= j && p.y <= j+20))
				{
					//System.out.println(p + " dobre dziecko");
					return true;
				}
					
			
		//System.out.println(p + " Zle dziecko");
		return false;
	}
	@SuppressWarnings("deprecation")
	public void update() {
		if(!blocked) {
			switch(angle) {
		case 0:
			scanner = new Rectangle(rectRuner.width, rectRuner.height);
			scanner.move(rectRuner.x, rectRuner.y-howLongCanSee);
			this.virtualJump = new Point( (int)rectRuner.getCenterX() ,  (int)rectRuner.getCenterY() - howLongCanSee*2);
			//System.out.println(virtualJump);
			break;
		case 90:
			scanner = new Rectangle(rectRuner.width, rectRuner.height);
			scanner.move(rectRuner.x+howLongCanSee, rectRuner.y);
			this.virtualJump = new Point( (int)rectRuner.getCenterX() + howLongCanSee*2,  (int)rectRuner.getCenterY());
			break;
		case 180:
			scanner = new Rectangle(rectRuner.width, rectRuner.height);
			scanner.move(rectRuner.x, rectRuner.y+howLongCanSee);
			this.virtualJump = new Point( (int)rectRuner.getCenterX() ,  (int)rectRuner.getCenterY() + howLongCanSee*2);
			break;
		case 270:
			scanner = new Rectangle(rectRuner.width, rectRuner.height);
			scanner.move(rectRuner.x-howLongCanSee, rectRuner.y);
			this.virtualJump = new Point( (int)rectRuner.getCenterX() -(howLongCanSee*2), (int) rectRuner.getCenterY() );
			break;
		default:
			scanner = new Rectangle(rectRuner.width, rectRuner.height);
			scanner.move(rectRuner.x, rectRuner.y-howLongCanSee);
			this.virtualJump = new Point( (int)rectRuner.getCenterX() ,  (int)rectRuner.getCenterY() -howLongCanSee*2);
			break;
		}
		}
		


		if(!bot) lifeTime--;
		//System.out.println(lifeTime);
		
		
	}
	public void paint(Graphics g) {
		if(blocked) {
			g.setColor(Color.black);
			g.fillRect(rectRuner.x, rectRuner.y, 10, 10);
		}else {
			if(lifeTime >= 0) {
				g.setColor(Color.blue);
				g.fillRect(rectRuner.x, rectRuner.y, rectRuner.height, rectRuner.width);
				update();
				g.setColor(new Color(255,255,0,180));
				g.fillRect(scanner.x, scanner.y, scanner.width, scanner.height);
				g.fillRect(virtualJump.x-2, virtualJump.y-2, 4, 4);
			}
		}
		//System.out.println(lifeTime);
		
		
		
	}
	@SuppressWarnings("deprecation")
	public void move() {
		if(!blocked) {
			if(lifeTime < 0 )
				return;
			rectRuner.setRect(scanner);
			update();
			rectRuner.setRect(scanner);
			lifeTime++;
		}
		//System.out.println("X: "+rectRuner.getCenterX() +" Y: " + rectRuner.getCenterY());
		//System.out.println("X: "+scanner.getCenterX() +" Y: " + scanner.getCenterY());
		
	}
	public void rotateRigth() {
		angle += 90;
		if (angle == 360) angle = 0;
		lifeTime--;
	}
	
	public Point getPosition() {return new Point((int) rectRuner.getCenterX()-1, (int) rectRuner.getCenterY()-1);}
	public Point getScannerPosition() {return new Point((int) scanner.getCenterX(), (int) scanner.getCenterY());}
	public Point getVirtualJump() {return virtualJump;}
	public void start() {bot=false;}
	public int getLifeTime() {return lifeTime;}
	public boolean isBlocked() {return blocked;}
}
