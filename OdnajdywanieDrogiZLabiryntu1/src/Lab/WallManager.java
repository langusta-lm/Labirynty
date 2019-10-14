package Lab;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class WallManager {

	//private ArrayList<Wall> arrWalls = new ArrayList<>();
	private Wall [][]map;
	private Point size;
	private int distance;
	private Rectangle place;
	
	
	public WallManager(Point size, int distance) {
		this.size = size;
		this.distance = distance;
		generateWalls();
		
		
	}
	
	private void generateWalls() {
		map = new Wall[size.x][size.y];
		for(int i=0;i<size.x;i++)
	    	for(int j=0;j<size.y;j++) {
	    		Wall w  = new Wall();
	    		w.init(new Point(i*distance, j*distance));
	    		map[i][j] = w;
	    	}
	    		
		
		place = new Rectangle(map[0][0].getRect().x, map[0][0].getRect().y,
					map[size.x-1][size.y-1].getRect().width, map[size.x-1][size.y-1].getRect().height);
	}
	
	
	
	
	public void paint(Graphics g) {
		for(int i=1;i<size.x;i++)
	    	for(int j=1;j<size.y;j++) 
	    		map[i][j].paint(g);
	}
	public void update() {
		;
	}
	
	
	
	
	public Wall findWall(Point point) {
		for(int i=1;i<size.x;i++)
	    	for(int j=1;j<size.y;j++) 
			if(map[i][j].detect(point)) {
				//w.setVisible(false);
				return map[i][j];
			}
		return null;
	}
	
	public boolean pointInPlace(Point p) {
		if(place.contains(p)) 
			return true; 
		else return false;
	}
	
	
}
