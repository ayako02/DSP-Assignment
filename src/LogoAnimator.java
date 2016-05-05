import javax.swing.JFrame;

import java.awt.event.KeyListener;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class LogoAnimator
{
	public static void main(String args[]) throws Exception
	{
		LogoAnimatorJPanel animation = new LogoAnimatorJPanel();
		
		JFrame window = new JFrame("Animation test");		//set up window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(animation);		//add panel to frame
		window.pack();				//make window just large enough for its GUI
		window.setVisible(true);	//display window
		window.addKeyListener(animation); 
		animation.startAnimation();	//begin animation
		
		 Container contentPane = window.getContentPane();

		 animation.connect2server();
		
	}
}