package UIDemo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class AlgoVisHelper {
	private AlgoVisHelper() {}

    public static void strokeCircle(Graphics2D g, int x, int y, int r){

        Ellipse2D circle = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
        g.draw(circle);
    }

    public static void fillCircle(Graphics2D g, int x, int y, int r){

        Ellipse2D circle = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
        g.fill(circle);
    }

    public static void strokeRectangle(Graphics2D g, int x, int y, int w, int h){

        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        g.draw(rectangle);
    }

    public static void fillRectangle(Graphics2D g, int x, int y, int w, int h){

        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        g.fill(rectangle);
    }

    public static void setColor(Graphics2D g, Color color){
        g.setColor(color);
    }

    public static void setStrokeWidth(Graphics2D g, int w){
        int strokeWidth = w;
        g.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public static void pause(int t) {
        try {
            Thread.sleep(t);
        }
        catch (InterruptedException e) {
            System.out.println("Error sleeping");
        }
    }

    public static void putImage(Graphics2D g, int x, int y, String imageURL){

        ImageIcon icon = new ImageIcon(imageURL);
        Image image = icon.getImage();

        g.drawImage(image, x, y, null);
    }

    public static void drawText(Graphics2D g, String text, int centerx, int centery){

        if(text == null)
            throw new IllegalArgumentException("Text is null in drawText function!");

        FontMetrics metrics = g.getFontMetrics();
        int w = metrics.stringWidth(text);
        int h = metrics.getDescent();
        g.drawString(text, centerx - w/2, centery + h);
    }
}

