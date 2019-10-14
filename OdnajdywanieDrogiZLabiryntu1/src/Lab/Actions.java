package Lab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Actions implements ActionListener{
	private Map map;
	
	public Actions(Map map) {
		this.map = map;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		map.update();
		//System.out.println("actions");
		
	}
	
	

}
