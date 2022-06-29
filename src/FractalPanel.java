//import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;
// import java.util.Timer;
// import java.util.TimerTask;



public class FractalPanel extends JPanel{
  static final long serialVersionUID = 1;
  // private final int INITIAL_DELAY = 100;
  // private final int PERIOD_INTERVAL = 1;
  static final int WIDTH = 700;
  static final int HEIGHT = 700;
  // Timer timer;
  BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
  int Xc = 350;
  int Yc = 350;

  int vertices = 5;
  double scale = 1.31; //(5,1.618 GR), (8,2.414), 2 is generally good

  double angle = Math.PI*2/vertices;
  double[] xArr = new double[1000];
  double[] yArr = new double[1000];
  // Point currentPoint;
  
  // double currX;
  // double currY;

  public FractalPanel() {
    xArr[0] = 150;
    yArr[0] = 150;
    for (int i = 1; i < vertices; i++) {
      xArr[i] = (xArr[i-1]-Xc)*Math.cos(angle)-(yArr[i-1]-Yc)*Math.sin(angle)+Xc;
      yArr[i] = (xArr[i-1]-Xc)*Math.sin(angle)+(yArr[i-1]-Yc)*Math.cos(angle)+Yc;
    }
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.WHITE);
    initImage(image);
  }


  public void paintComponent(Graphics g){
    g.drawImage(image, 0, 0, null);
    drawFractal(g, new Point (Xc, Yc), new Point(150, 150));
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

  private void drawFractal(Graphics g, Point center, Point start){
    float r = 1;
    float gb = 0;
    float alpha = (float)0.3;
    g.setColor(new Color(r, gb, gb, alpha));
    double[] xPoints = new double[vertices];
    double[] yPoints = new double[vertices];
    xPoints[0] = start.X;
    yPoints[0] = start.Y;
    for (int i = 1; i < vertices; i++) {
      xPoints[i] = (xPoints[i-1]-center.X)*Math.cos(angle)-(yPoints[i-1]-center.Y)*Math.sin(angle)+center.X;
      yPoints[i] = (xPoints[i-1]-center.X)*Math.sin(angle)+(yPoints[i-1]-center.Y)*Math.cos(angle)+center.Y;
    }
    if(center.distance(start)<2){
      g.fillPolygon(intCastArr(xPoints), intCastArr(yPoints), vertices);
    }else{
      for (int i = 0; i < vertices; i++) {
        Point newStart = new Point(xPoints[i],yPoints[i]);
        Point newCenter = new Point((center.X + scale*xPoints[i])/(1+scale),(center.Y + scale*yPoints[i])/(1+scale));
        drawFractal(g, newCenter, newStart); 
      }
    }
  }

  public int[] intCastArr(double[] arr){
    int[] newArr = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      newArr[i] = (int)(arr[i]+0.5);
    }
    return newArr;
  }

}