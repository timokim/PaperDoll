import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;

/**
 * A simple demo of how to create a elliptical sprite.
 *
 * Michael Terry
 */
public class EllipseSprite extends Sprite {

    private Ellipse2D ellip = null;
    String name;

    protected String getName() {
        return name;
    }

    /**
     * Creates a ellipse based at the origin with the specified width and height
     */
    public EllipseSprite(String aname, int width, int height) {
        super();
        name = aname;
        this.initialize(width, height);
    }

    /**
     * Creates a ellipse based at the origin with the specified width, height, and parent
     */
    public EllipseSprite(int width, int height, Sprite parentSprite) {
        super(parentSprite);
        this.initialize(width, height);
    }

    private void initialize(int width, int height) {
        ellip = new Ellipse2D.Double(-width / 2, 0, width, height);
    }

    /**
     * Test if our ellipse contains the point specified.
     */
    public boolean pointInside(Point2D p) {
        AffineTransform fullTransform = this.getFullTransform();
        AffineTransform inverseTransform = null;
        try {
            inverseTransform = fullTransform.createInverse();
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }
        Point2D newPoint = (Point2D) p.clone();
        inverseTransform.transform(newPoint, newPoint);
        return ellip.contains(newPoint);
    }

    protected void drawSprite(Graphics2D g, Color choice) {
        g.setColor(choice);
        g.draw(ellip);
    }

    public String toString() {
        return "EllipseSprite: " + ellip;
    }
}
