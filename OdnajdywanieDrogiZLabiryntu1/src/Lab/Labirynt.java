package Lab;

import java.awt.EventQueue;



public class Labirynt {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				new App();
			}
		});
		
	}
	
}
