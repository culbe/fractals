import java.awt.Graphics;
import java.awt.Color;

public class VerticalSlider extends Button<Double>{
    
    double min, max;

    public VerticalSlider(){
        value = 0.0;
        bottomRight = topLeft = null;
    }
    public VerticalSlider(Point topleft, Point bottomright){
        topLeft = topleft;
        bottomRight = bottomright;
        value = 0.0;
        min = 0;
        max = bottomright.Y-topleft.Y;
    }
    public VerticalSlider(Point topleft, Point bottomright, double value){
        topLeft = topleft;
        bottomRight = bottomright;
        this.value = value;
        min = 0;
        max = bottomright.Y-topleft.Y;
    }
    public VerticalSlider(Point topleft, Point bottomright, double min, double max){
        topLeft = topleft;
        bottomRight = bottomright;
        value = min;
        this.min = min;
        this.max = max;
    }
    public VerticalSlider(Point topleft, Point bottomright, double min, double max, double value){
        topLeft = topleft;
        bottomRight = bottomright;
        this.min = min;
        this.max = max;    
        this.value = value;
    }
        
    public double setValue(Point p){
        double travel = bottomRight.Y-topLeft.Y;
        double range = max - min;
        double dy = bottomRight.Y - p.Y;
        double v = dy/travel*range+min;
        value = v;
        return v;
    }

    public void draw(Graphics g){
        int width = (int)(bottomRight.X - topLeft.X);
        int height = (int)(bottomRight.Y - topLeft.Y);
        double range = max - min;
        g.setColor(Color.WHITE);
        // g.drawLine(topLeft.X + width / 2, topLeft.Y, topLeft.X + width / 2, bottomRight.Y);
        // g.drawLine(topLeft.X + width / 2 - 1, topLeft.Y, topLeft.X + width / 2 - 1, bottomRight.Y);
        // g.drawLine(topLeft.X + width / 2 + 1, topLeft.Y, topLeft.X + width / 2 + 1, bottomRight.Y);
        g.fillRect((int)topLeft.X + width / 2-1, (int)topLeft.Y, 3, (int)(bottomRight.Y-topLeft.Y));
        g.setColor(Color.GRAY);
        g.fillOval((int)topLeft.X, (int) (bottomRight.Y - width / 2 - ((value-min) / range * height)), width,width);
    }
}
