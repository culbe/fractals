//import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;



public class MyPanel extends JPanel{
  static final long serialVersionUID = 1;
  private final int INITIAL_DELAY = 100;
  private final int PERIOD_INTERVAL = 10;
  static final int WIDTH = 600;
  static final int HEIGHT = 600;
  Timer timer;
  BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
  Point currentPoint;
  Point P1 = new Point(20, 500);
  Point P2 = new Point(520, 500);
  Point P3 = new Point(270, 67);
  double currX;
  double currY;

  public MyPanel() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.WHITE);
    initImage(image);
    currentPoint = new Point(200,400);
    timer = new Timer();
    timer.scheduleAtFixedRate(new ScheduleTask(), INITIAL_DELAY, PERIOD_INTERVAL);   
  }


  public void paintComponent(Graphics g){
    g.drawImage(image, 0, 0, null);
    g.setColor(Color.RED);
    g.fillOval((int)currX-2, (int)currY-2, 5, 5);
  }

  public void updateImage(BufferedImage img){
    Graphics g = img.getGraphics();
    g.setColor(Color.BLACK);
    int vertex = (int)(Math.random()*3+1);
    if(vertex == 1){
      currX = (currX+P1.X)/2;
      currY = (currY+P1.Y)/2;
    }else if(vertex==2){
      currX = (currX+P2.X)/2;
      currY = (currY+P2.Y)/2;
    }else if(vertex==3){
      currX = (currX+P3.X)/2;
      currY = (currY+P3.Y)/2;
    }
    g.fillRect((int)currX, (int)currY, 2, 2);
  }

  public void initImage(BufferedImage img){
    Graphics g = img.getGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    g.setColor(Color.BLUE);
    drawPointBold(P1, g);
    drawPointBold(P2, g);
    drawPointBold(P3, g);
  }

  private class ScheduleTask extends TimerTask {
    @Override
    public void run() {
      updateImage(image);
      repaint();
    }
  }

  public static void drawPoint(Point p, Graphics g){
    g.fillRect((int)p.X, (int)p.Y, 1, 1);
  }

  public static void drawPointBold(Point p, Graphics g){
    g.fillRect((int)p.X-2, (int)p.Y-2, 5, 5);
  }

}