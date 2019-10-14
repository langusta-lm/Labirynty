package Lab;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Map extends Canvas  {  
	private static final long serialVersionUID = 1L;
	private WallManager wallM;
	private int minDistOf2Points = 20;
	private Point sizeXY = new Point(20, 20);
	private Point firstPositionOfRunner = new Point(4,7);
	private int sizeOfPoint = 5;
	private ArrayList<Runner> arrRunners;
	private boolean firstRun=true;
	Rectangle place;
	private int cyclon;
	private boolean complete;
	private long timeStart;
	
	private boolean generated;
	private Point pointOfStart;
	private FindWay findWay; 
	
    public Map() {  
    	
    	
    	init();
    	addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					//runer.move();
					repaint();
					//System.out.println("move");
				}
				if(e.getKeyCode() == KeyEvent.VK_W) {
					//runer.rotateRigth();
					repaint();
					//System.out.println("rotate");
				}
				
			}
		});
    	requestFocusInWindow();
    }
    public void init() {
    	findWay = null;
    	generated = false;
    
    	cyclon = 0;
    	complete = false;
    	timeStart = System.currentTimeMillis();
    	firstRun = true;
    	arrRunners = new ArrayList<>();
    	setBackground (Color.GRAY);  
    	setSize(minDistOf2Points*sizeXY.x, minDistOf2Points*sizeXY.y);  
    	wallM = new WallManager(sizeXY, minDistOf2Points);
    	place = new Rectangle(this.getSize());
    	pointOfStart = new Point(minDistOf2Points*(sizeXY.x/2),minDistOf2Points*(sizeXY.y/2));
    	Runner r = new Runner(pointOfStart, true);
    	randomRotate(r);
    	arrRunners.add(r);
    	wallM.findWall(pointOfStart).setFirstBlock(true);
    }
    
    public void paint(Graphics g) { 
    
    	wallM.paint(g);
    	if(!arrRunners.isEmpty() ){
    		for(Runner r:arrRunners) 
    			r.paint(g);
    	}
    	if(findWay != null)
    		findWay.paint(g);
    	
    	
    	
    	//System.out.println(arrRunners.size());
    } 
    
    public void update() {
    	if(findWay != null)
    		findWay.update();
    	
    	wallM.update();
    	for(int i=0;i<arrRunners.size();i++) {
    		arrRunners.get(i).update();
    		
 		   if(!arrRunners.get(i).isBlocked()) {
 			     Wall w = wallM.findWall(arrRunners.get(i).getPosition());
 			     if(w != null)  
 			    	 w.setVisible(false);
 		   }
 		   if(arrRunners.get(i).getLifeTime() < 0)
 			  arrRunners.remove(i);
 			   
 	   }
    	
    	if(!arrRunners.isEmpty()) {
    		if(firstRun) 
    			firstRun();
    		else 
    			secondRun();
    	}
    	
    	if(arrRunners.isEmpty() && complete ) {
    		if(!generated) {
    			long timeTemp = System.currentTimeMillis();
    			timeTemp -= timeStart;
    			generated = true;
    			System.out.println("Generowanie labiryntu zaje³o czasu: " + (double)timeTemp/1000 +"s");
    			
    			
    		}
    	}
    	
    	if(!complete && arrRunners.isEmpty()) 
    		init();
    	
    	
    	//System.out.println("Populacja: " + arrRunners.size());
    }
    
    private void firstRun() {
    	Wall w1,w2;
    	if(place.contains(arrRunners.get(0).getScannerPosition())) {
			w1 = wallM.findWall(arrRunners.get(0).getScannerPosition());
			w2 = wallM.findWall(arrRunners.get(0).getVirtualJump());
			if(w1 != null && w2 != null) 
				if(w1.isVisible() && w2.isVisible()) {
					
					w1.setVisible(false);
					w2.setVisible(false);
					
					arrRunners.get(0).move();
					randomRotate(arrRunners.get(0));
					//repaint();
					cyclon++;
					if(cyclon>0) {
						initMoreRunners(arrRunners.get(0).getScannerPosition());
						cyclon=0;
					}
				
						
				} else 
					randomRotate(arrRunners.get(0));
			if (w2 == null) {
				complete = true;
				w1.setVisible(false);
				//System.out.println("widze koniec mapy");
				arrRunners.remove(0);
				firstRun = false;
			//place.contains(p)
			}
		}
		else {
			firstRun = false;
			//arrRunners.remove(0);
			System.out.println("koniec");
		}
    	
    	for(int r=0;r<arrRunners.size();r++)
    		if(arrRunners.get(r).getLifeTime()<0)
    			arrRunners.remove(r);
    	
    }
    
    private void secondRun() {
    	Wall w1,w2;
    	for(int i=0;i<arrRunners.size();i++) {
    	try {
    		if(arrRunners.get(i).isBlocked()) {
    			arrRunners.remove(i);
    			continue;
    		}
			
			w1 = wallM.findWall(arrRunners.get(i).getScannerPosition());
			w2 = wallM.findWall(arrRunners.get(i).getVirtualJump());
			
			if(w1 != null && w2 != null) {
				if(w1.isVisible() && w2.isVisible()) {
					if(w1.isGrid() || w2.isGrid()) {
						arrRunners.remove(i);
						w1.setVisible(true);
						w2.setVisible(true);
						continue;
						
					}
					//cyclon++;
					//if(cyclon > 0) {
						initMoreRunners(arrRunners.get(i).getPosition());
					//	cyclon = 0;
					//}
					
						
					if(!w1.isGrid())
						w1.setVisible(false);
					if(!w2.isGrid())
						w2.setVisible(false);
					
					arrRunners.get(i).move();
					randomRotate(arrRunners.get(i));
					//repaint();
					cyclon++;
					
				}//else if ((!w1.isVisible()) && (!w2.isVisible())) 
					//arrRunners.get(i).move();
				
				else{
					arrRunners.get(i).rotateRigth();
					continue;
				}
			
			}else {
				arrRunners.remove(i);
			}
				
			
			//randomRotate(arrRunners.get(i));
		
		} catch (Exception e) {
			System.out.println("error in secondRun    " + e);
		}}
    	
    }
   
    private void randomRotate(Runner r) {
    	int angleTime = (int) (Math.random() * 4);
    	do {
    		r.rotateRigth();
    		angleTime--;
    	}while(angleTime>0);
    }
    
    private void initMoreRunners(Point position) {
    	Runner r = new Runner(position ,true);
    	randomRotate(r);
    	arrRunners.add(r);
    }
    
    public Point getPointOfStart() {return pointOfStart;}
    public WallManager getWallManager() {
    	if(wallM != null)
    		return wallM;
    	else 
    		return null;
    	}
	public void startFinderWay() {
		Point p = new Point((int)wallM.findWall(pointOfStart).getRect().getCenterX(), (int) wallM.findWall(pointOfStart).getRect().getCenterY());
		findWay = new FindWay(wallM, p);
		
	}
}
