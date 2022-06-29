import javax.swing.JFrame;

public class PointRunner {

  public static void main(String[] args) {
    JFrame f = new JFrame("Sierpinski n-flakes"); 
    PointPanel p = new PointPanel();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.add(p);
    f.pack();
    f.setVisible(true);
  }
}