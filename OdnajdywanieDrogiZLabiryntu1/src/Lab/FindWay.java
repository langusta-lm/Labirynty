package Lab;

import java.awt.Graphics;
import java.awt.Point;

public class FindWay  {

	private WallManager wallM;
	private Runner runner;
	private Point startPoint;
	private boolean canMove;
	private boolean complete;
	public FindWay(WallManager wallManager, Point startPoint) {
		complete = false;
		wallM = wallManager;
		canMove = false;
		this.startPoint = startPoint;
		runner = new Runner(new Point(startPoint.x, startPoint.y), false);
		//System.out.println(startPoint);
	}
	
	public void paint(Graphics g) {
		runner.paint(g);
	}
	
	public void update() {
		try {
			if(complete) return;
			runner.update();
			if(canMove) {
				runner.move();
				canMove = false;
			}
			runner.rotateRigth();
			runner.rotateRigth();
			runner.rotateRigth();
			runner.update();
			//runner.rotateRigth();
			Wall w1 = wallM.findWall(runner.getScannerPosition());
			Wall w2 = wallM.findWall(runner.getVirtualJump());
			
			if(!w1.isVisible() && w1.isGrid() && w2 == null) {
				complete = true;
				runner.rotateRigth();
				runner.rotateRigth();
				System.out.println("koniec");
			}
				
			
			
			if(w1 != null && w2 != null) {
				
				if(!w1.isVisible() && !w2.isVisible()) {
					canMove = true;
					return;
				}else {
					runner.rotateRigth();
					runner.update();
					w1 = wallM.findWall(runner.getScannerPosition());
					w2 = wallM.findWall(runner.getVirtualJump());
					if(!w1.isVisible() && !w2.isVisible()) {
						canMove = true;
						return;
					}else {
						runner.rotateRigth();
						runner.update();
						w1 = wallM.findWall(runner.getScannerPosition());
						w2 = wallM.findWall(runner.getVirtualJump());
						if(!w1.isVisible() && !w2.isVisible()) {
							canMove = true;
							return;
						}else {
							runner.rotateRigth();
							runner.update();
							w1 = wallM.findWall(runner.getScannerPosition());
							w2 = wallM.findWall(runner.getVirtualJump());
							if(!w1.isVisible() && !w2.isVisible()) {
								canMove = true;
								return;
							}
						}
					}
				}
				
				
			} else if(w2 == null && w1 != null && w1.isGrid()) {
				runner.rotateRigth();
				runner.rotateRigth();
				
				runner.update();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
		
		
		
		/*
		if(w1 != null && w2 != null) {
			
			if(w1.isVisible() || w2.isVisible()) {
				runner.rotateRigth();

	
				runner.update();
				w1 = wallM.findWall(runner.getScannerPosition());
				w2 = wallM.findWall(runner.getVirtualJump());
				if(w1.isVisible() || w2.isVisible()) {
					runner.rotateRigth();

					
					runner.update();
					
					w1 = wallM.findWall(runner.getScannerPosition());
					w2 = wallM.findWall(runner.getVirtualJump());
					if(w1.isVisible() || w2.isVisible()) {
						runner.rotateRigth();

						runner.update();
						
						
				}else canMove=true;
				
			}else canMove=true;
		}else canMove=true;
		}

		if(!w1.isVisible() && w1.isGrid()) {
				
			System.out.println("koniec");
			
		}*/
	}
}
