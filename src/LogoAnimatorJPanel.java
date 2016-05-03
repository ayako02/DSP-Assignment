import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LogoAnimatorJPanel extends JPanel implements KeyListener{
  protected ImageIcon images[], images2[];    //array of images
  private int curImgBlue = 0;    //current image index
  private int curImgSil = 0;
  private final int ANIMATION_DELAY = 10;  //millisecond delay
  private int width, height;        //image width & height
  private Timer animationTimer;     //Timer drives animation
  
//variable used in for loop function for car movement counting
  private int x=425, y=500, xVel = 0, yVel = 0;					
  private int x1=425, y1=550, x1Vel = 0, y1Vel = 0;
  
  public LogoAnimatorJPanel ()
  {
    try 
    {   
      //create array to store ImageIcon reference
      images = new ImageIcon[16];
      images2 = new ImageIcon[16];
      
      //load the Light Blue car
      for (int c = 0; c < images.length; c++)
      {
        images[c] = new ImageIcon("LBlueCar/"+  (c+1) +".jpg" ); 

      }  //end for
      
      //load the silver car
      for (int a = 0; a < images.length; a++)
      {
        images2[a] = new ImageIcon("SilverCar/"+  (a+1) +".jpg" ); 

      }  //end for
      
      //this example assumes all images have the same width and height
      width = images[0].getIconWidth();
      height = images[0].getIconHeight();
    }  //end try
    
    catch (Exception e)
    {
      e.printStackTrace();
    }  //end catch
    
    
  }  //end constructor
  
  //display current image
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  //call superclass paintComponent
    images[curImgBlue].paintIcon(this, g, x, y);
    images2[curImgSil].paintIcon(this, g, x1, y1);
    
    //set next image to be drawn only if Timer is running
//    if(animationTimer.isRunning())
//    {
//    	curImgBlue= (curImgBlue + 1) % images.length;
//    	curImgSil= (curImgSil + 1) % images.length;
//    }
    //grass
    Color c1 = Color.green;
    g.setColor(c1);
    g.fillRect(150,200,550,300);
    
    //outer & inner edges
    Color c2 = Color.black;
    g.setColor(c2);
    g.drawRect(50, 100, 750, 500); 	//outer edge
    g.drawRect(150, 200, 550, 300); //inner edge
    
    //mid-line marker
    Color c3 = Color.yellow;
    g.setColor(c3);
    g.drawRect(100, 150, 650, 400); 
    
    //start line
    Color c4 = Color.white;
    g.setColor(c4);
    g.drawLine(425, 500, 425, 600);
    
  }  //end method
  
  //start animation, or restart if windows is redisplayed
  public void startAnimation()
  {
    if (animationTimer == null)
    {
    	curImgBlue = 12;    //display first image
    	curImgSil = 12;
      
      //create timer
      animationTimer = new Timer(ANIMATION_DELAY, new TimerHandler());
      animationTimer.start();    //start timer
    }  //end if
    
    else //animation already exists, restart animation
    {
      if(! animationTimer.isRunning())
        animationTimer.restart();
    }    //end else
  }    //end method
  
  //stop animation Timer
  public void stopAnimation()
  {
    animationTimer.stop();
  }
  
  //return min size of animation
  public Dimension getMinimumSize()
  {
    return getPreferredSize();
  }
  
  //return preferred size of animation
  public Dimension getPreferredSize()
  {
    return new Dimension(850,650);
  }
  
  //inner class of handle action events from timer
  private class TimerHandler implements ActionListener
  {
    //respond to timer's event
    public void actionPerformed(ActionEvent a)
    {
      
      //car 1
		if(x < 50 ) {
			xVel = 0;
			x = 50;	
		}
		
		if(x > 750) {
			xVel = 0;
			x = 750;	
		}
	
		if(y < 100) {
			yVel = 0;
			y = 100;	
		}
		
		//grass - bottom track
		if(x > 100 && x < 700) {
			if(y > 450 && y < 700) {
				if (y < 500) {
					yVel = 0;
					y = 500;		
				}
			}
		}
		
		//grass - upper track
		if(x > 100 && x < 700) {
			if(y > 100 && y < 200) {
				if(y > 150) {
					yVel = 0;
					y = 150;
				}
			}
		}
	
		//grass - right track
		
		x = x + xVel;
		y = y + yVel;
	
	//car 2
	if(x1 < 50) {
		x1Vel = 0;
		x1 = 50;	
	}
	
	if(x1 > 750) {
		x1Vel = 0;
		x1 = 750;	
	}
	
	if(y1 < 100) {
		y1Vel = 0;
		y1 = 100;
	}
	
	if(y1 > 550) {
		y1Vel = 0;
		y1 = 550;
    }
	
	x1 = x1 + x1Vel;
	y1 = y1 + y1Vel;
	repaint();
  }
  }

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
//	System.out.println(e.getKeyCode());
	System.out.println("X: "+ x + " Y: " + y);
	System.out.println("Img: " + curImgBlue);

	int z = e.getKeyCode();

    // Light Blue car
    if (z == KeyEvent.VK_LEFT) {
        xVel = -1;
        yVel = 0;
    }
    if (z == KeyEvent.VK_UP) {
        xVel= 0;
        yVel = -1;
    }
    if (z == KeyEvent.VK_RIGHT) {
        xVel = 1;
        yVel = 0;
        if(curImgBlue < 16)
        	curImgBlue += 1;
        if(curImgBlue == 16) 
        	curImgBlue = 0;
    }
    if (z == KeyEvent.VK_DOWN) {
        xVel = 0;
        yVel = 1;
    }

    // Blue box
    if (z == KeyEvent.VK_A) {
        x1Vel = -1;
        y1Vel = 0;
    }
    if (z == KeyEvent.VK_W) {
        x1Vel = 0;
        y1Vel = -1;
    }
    if (z == KeyEvent.VK_D) {
        x1Vel = 1;
        y1Vel = 0;
    }
    if (z == KeyEvent.VK_S) {
        x1Vel = 0;
        y1Vel = 1;
    }
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	xVel = 0;
	yVel = 0;
	
	x1Vel = 0;
	y1Vel = 0;
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}// end public class
