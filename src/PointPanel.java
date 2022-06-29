//import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;



public class PointPanel extends JPanel{
  static final long serialVersionUID = 1;
  private final int INITIAL_DELAY = 100;
  private final int PERIOD_INTERVAL = 1;
  static final int WIDTH = 700;
  static final int HEIGHT = 700;
  Timer timer;
  BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
  int Xc = 350;
  int Yc = 350;

  int vertices = 6;
  double scale = 1.5; //(5,1.618 GR), (8,2.414), 2 is generally good

  double angle = Math.PI*2/vertices;
  double[] xArr = new double[1000];
  double[] yArr = new double[1000];
  Point currentPoint;
  
  double currX;
  double currY;

  public PointPanel() {
    xArr[0] = 150;
    yArr[0] = 150;
    for (int i = 1; i < vertices; i++) {
      xArr[i] = (xArr[i-1]-Xc)*Math.cos(angle)-(yArr[i-1]-Yc)*Math.sin(angle)+Xc;
      yArr[i] = (xArr[i-1]-Xc)*Math.sin(angle)+(yArr[i-1]-Yc)*Math.cos(angle)+Yc;
    }
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
    int vertex = (int)(Math.random()*vertices);
    currX = (currX+scale*xArr[vertex])/(scale+1);
    currY = (currY+scale*yArr[vertex])/(scale+1);
    g.fillRect((int)currX, (int)currY, 1, 1);
  }

  public void initImage(BufferedImage img){
    Graphics g = img.getGraphics();
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    g.setColor(Color.BLUE);
    for (int i = 0; i < vertices; i++) {
      g.fillOval((int)xArr[i]-2, (int)yArr[i]-2, 5, 5);
    }
  }

  private class ScheduleTask extends TimerTask {
    @Override
    public void run() {
      updateImage(image);
      repaint();
    }
  }

}