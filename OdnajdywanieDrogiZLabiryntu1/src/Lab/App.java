package Lab;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class App extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int FPSR = 2;
	private static  int FPS =10;
	private Map map;
	private JButton butRestart = new JButton("Restart");
	private JButton butStop = new JButton("Stop");
	private JButton butFindWay = new JButton("FindWay");
	private FindWay fWay;
	boolean b=true;
	//boolean fway=false;

	public App() {
		super();
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(3,1));
		
		
		map = new Map();
		setLayout(new BorderLayout());
		add(map, BorderLayout.WEST); 
	    setSize(500, 500); 
	    setTitle("Odnajdywanie drogi w labirynt_2Dp");
	    Timer timer = new Timer(1000/FPS, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				map.update();
			}
		});
       
	  
		Timer timerRepaint = new Timer(1000/FPSR, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				map.repaint();
				
			}
		});
		timer.start();
		timerRepaint.start();
        butRestart.addActionListener(new ActionListener() {		// RESTART
			
			@Override
			public void actionPerformed(ActionEvent e) {
				map.init();
				FPS = 60;
			}
		});
        butStop.addActionListener(new ActionListener() {		//STOP
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(b) {
					timer.stop();
					timerRepaint.stop();
				}
				else {
					timer.start();
					timerRepaint.start();
				}
				b=!b;
			}
		});
        butFindWay.addActionListener(new ActionListener() {			//FINDWAY
			
			@Override
			public void actionPerformed(ActionEvent e) {
				map.startFinderWay();
				FPS = 2;
			}
		});
        addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					System.out.println(e.getX() + " : "+e.getY());
					testOfCompatibility(new Point(e.getX(), e.getY()));
				}
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        jpanel.add(butRestart);
        jpanel.add(butStop);
        jpanel.add(butFindWay);
        
        add(jpanel, BorderLayout.EAST);
        
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true); 
	    repaint();
		//requestFocusInWindow();
		setAlwaysOnTop(false);
		//pack();
	}
	private boolean testOfCompatibility(Point p) {
		//
		for(int i=0; i<=1000 ; i+=40)
			for(int j=20; j<=1000; j+=40) 
				if((p.x >= i && p.x <= i+20) && (p.y >= j && p.y <= j+20))
				{
					
					return true;
				}
					
			
		System.out.println(p + " Zle dziecko");
		return false;
	}
	

}
