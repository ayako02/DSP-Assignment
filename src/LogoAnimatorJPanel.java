import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LogoAnimatorJPanel extends JPanel implements KeyListener{
  protected ImageIcon images[], images2[];   //array of images
  private int curImgGreen = 0, curImgYel = 0; //current image index
  private final int ANIMATION_DELAY = 10;  	//millisecond delay
  private int width, height;        		//image width & height
  private Timer animationTimer;     		//Timer drives animation
  
//variable used in for loop function for car movement counting
  private int x=425, y=500, xVel = 0, yVel = 0;					
  private int x1=425, y1=550, x1Vel = 0, y1Vel = 0;
 
  public JFrame f = new JFrame("Car Game Client");	 //Server choosing
  private BufferedReader in;	//server communication
  private PrintWriter out;
  
  public LogoAnimatorJPanel ()
  {
    try 
    {   
      //create array to store ImageIcon reference
      images = new ImageIcon[16];
      images2 = new ImageIcon[16];
      
      //load the Green car
      for (int c = 0; c < images.length; c++)
      {
        images[c] = new ImageIcon("GreenCar/"+  (c+1) +".png" ); 

      }  //end for
      
      //load the yellow car
      for (int a = 0; a < images.length; a++)
      {
        images2[a] = new ImageIcon("YellowCar/"+  (a+1) +".png" ); 

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
    images[curImgGreen].paintIcon(this, g, x, y);
    images2[curImgYel].paintIcon(this, g, x1, y1);
    
    //set next image to be drawn only if Timer is running
//    if(animationTimer.isRunning())
//    {
//    	curImgGreen= (curImgGreen + 1) % images.length;
//    	curImgYel= (curImgYel + 1) % images.length;
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
    	curImgGreen = 12;    //display first image
    	curImgYel = 12;
      
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
		
		if (y> 550) {
			yVel = 0;
			y = 550;
		}
		
		//grass - bottom track
		if(x > 100 && x < 700) {
			if(y > 450 && y < 700) {
				if (y < 500) {
					yVel = 0;
					xVel = 0;
					y = 500;		
				}
			}
		}
		
		//grass - upper track
		if(x > 100 && x < 700) {
			if(y > 100 && y < 200) {
				if(y > 150) {
					yVel = 0;
					xVel = 0;
					y = 150;
				}
			}
		}
	
		//grass - right track
		if (y > 150 && y < 500) {
			if (x > 650 && x < 750) {
				if (x < 700) {
					x = 700;
					xVel = 0;
					yVel = 0;
				}
			}
		}
		
		//grass - left track
		if(y > 150 && y < 500) {
			if(x > 50 && x < 110) {
				if(x >= 100) {
					xVel = 0;
					yVel = 0;
					x = 99;
				}
			}
		}	
		
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
		
		//grass - bottom track
		if(x1 < 700 && x1 > 100) {
			if(y1 > 400 && y1 < 700) {
				if(y1 < 500) {
					y1Vel = 0;
					x1Vel = 0;
					y1 = 500;
				}
			}
		}
		
		//grass - upper track
		if(x1 < 700 && x1 > 100) {
			if(y1 > 100 && y1 < 200) {
				if(y1 > 150) {
					y1Vel = 0;
					x1Vel = 0;
					y1 = 150;
				}
			}
		}
		
		//grass - right track
		if(y1 > 150 && y1 < 500) {
			if(x1 > 650 && x1 < 750) { 
				if(x1 < 702) {
					x1Vel = 0;
					y1Vel = 0;
					x1 = 702;
				}
			}
		}
		
		//grass - left track
		if(y1 > 150 && y1 < 500) {
			if(x1> 50 && x1 < 110) {
				if(x1 >= 100) {
					x1Vel = 0;
					y1Vel = 0;
					x1 = 99;
				}
			}
		}
		
		x1 = x1 + x1Vel;
		y1 = y1 + y1Vel;
		repaint();
    }
  }

@Override
public void keyPressed(KeyEvent e) {
	System.out.println("X1: "+ x1 + " Y1: " + y1);
	System.out.println("Img: " + curImgGreen);

	int z = e.getKeyCode();

    // Green car
    if (z == KeyEvent.VK_LEFT) {
        xVel = -1;
        yVel = 0;
        if(curImgGreen > 0)
        	curImgGreen -= 1;
        if(curImgGreen == 0)
        	curImgGreen = 15;
        carDirB(curImgGreen);
    }
    
    if (z == KeyEvent.VK_UP) {
        xVel= 0;
        yVel = -1;
        carDirB(curImgGreen);
        acceleration();
    }
    if (z == KeyEvent.VK_RIGHT) {
        xVel = 1;
        yVel = 0;
        if(curImgGreen < 16)
        	curImgGreen += 1;
        if(curImgGreen == 16) 
        	curImgGreen = 0;
        carDirB(curImgGreen);
    }
    if (z == KeyEvent.VK_DOWN) {
        xVel = 0;
        yVel = 1;
        carDirB(curImgGreen);
        deceleration();
    }

    // Yellow Car
    if (z == KeyEvent.VK_A) {
        x1Vel = -1;
        y1Vel = 0;
        if(curImgYel > 0)
        	curImgYel -= 1;
        if(curImgYel == 0)
        	curImgYel = 15;
        carDirS(curImgYel);
    }
    if (z == KeyEvent.VK_W) {
        x1Vel = 0;
        y1Vel = -1;
        carDirS(curImgYel);
        acceleration();
    }
    if (z == KeyEvent.VK_D) {
        x1Vel = 1;
        if(curImgYel < 16)
        	curImgYel += 1;
        if(curImgYel == 16) 
        	curImgYel = 0;
        carDirS(curImgYel);
    }
    if (z == KeyEvent.VK_S) {
        x1Vel = 0;
        y1Vel = 1;
        carDirS(curImgYel);
        deceleration();
    }
}

@Override
public void keyReleased(KeyEvent e) {
	xVel = 0;
	yVel = 0;
	
	x1Vel = 0;
	y1Vel = 0;
}

//car direction of Green car
public void carDirB(int carD) {
    switch (carD) {
        case 0:
            xVel = 0;
            yVel = -10;
            break;
        case 1:
        	xVel = 3;
        	yVel = -7;
            break;
        case 2:
        	xVel = 5;
        	yVel = -5;
            break;
        case 3:
        	xVel = 7;
        	yVel = -3;
            break;
        case 4:
        	xVel = 10;
        	yVel = 0;
            break;
        case 5:
        	xVel = 7;
        	yVel = 3;
            break;
        case 6:
        	xVel = 5;
        	yVel = 5;
            break;
        case 7:
        	xVel = 3;
        	yVel = 7;
            break;
        case 8:
        	xVel = 0;
        	yVel = 10;
            break;
        case 9:
        	xVel = -3;
        	yVel = 7;
            break;
        case 10:
        	xVel = -5;
        	yVel = 5;
            break;
        case 11:
        	xVel = -7;
        	yVel = 3;
            break;
        case 12:
        	xVel = -10;
        	yVel = 0;
            break;
        case 13:
        	xVel = -7;
        	yVel = -3;
            break;
        case 14:
        	xVel = -5;
        	yVel = -5;
            break;
        case 15:
        	xVel = -3;
            yVel = -7;
            break;
    }
}

//car direction for yellow car
public void carDirS(int carD) {
    switch (carD) {
        case 0:
            x1Vel = 0;
            y1Vel = -10;
            break;
        case 1:
        	x1Vel = 3;
        	y1Vel = -7;
            break;
        case 2:
        	x1Vel = 5;
        	y1Vel = -5;
            break;
        case 3:
        	x1Vel = 7;
        	y1Vel = -3;
            break;
        case 4:
        	x1Vel = 10;
        	y1Vel = 0;
            break;
        case 5:
        	x1Vel = 7;
        	y1Vel = 3;
            break;
        case 6:
        	x1Vel = 5;
        	y1Vel = 5;
            break;
        case 7:
        	x1Vel = 3;
        	y1Vel = 7;
            break;
        case 8:
        	x1Vel = 0;
        	y1Vel = 10;
            break;
        case 9:
        	x1Vel = -3;
        	y1Vel = 7;
            break;
        case 10:
        	x1Vel = -5;
        	y1Vel = 5;
            break;
        case 11:
        	x1Vel = -7;
        	y1Vel = 3;
            break;
        case 12:
        	x1Vel = -10;
        	y1Vel = 0;
            break;
        case 13:
        	x1Vel = -7;
        	y1Vel = -3;
            break;
        case 14:
        	x1Vel = -5;
        	y1Vel = -5;
            break;
        case 15:
        	x1Vel = -3;
            y1Vel = -7;
            break;
    }
}
	public void acceleration() {
		xVel += xVel;
	}
	
	public void deceleration() {
		yVel -= yVel;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void connect2server() throws IOException {
		 
        String serverAddress = JOptionPane.showInputDialog(
        		f,
                "Enter IP Address of the Server: ",
                "Welcome to the Server",
                JOptionPane.QUESTION_MESSAGE);
        try {
            Socket s = new Socket(serverAddress, 1234);

            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
            
            for (int i = 0; i < 3; i++) {
                System.out.println(in.readLine());
            }
        } catch (Exception e) {
            System.out.println("Unable to connect to game server: " + e);
            System.exit(0);
        }
	}
}// end public class
