import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Vector;


public abstract class Sprite {

    /**
     * Tracks our current interaction mode after a mouse-down
     */
    protected enum InteractionMode {

        IDLE,
        DRAGGING,
        SCALING,
        ROTATING
    }
    private Vector<Sprite> children = new Vector<Sprite>();     // Holds all of our children
    private Sprite parent = null;                     // Pointer to our parent
    private Sprite neighbour = null;
    private AffineTransform transform = new AffineTransform();    // Our transformation matrix
    protected Point2D lastPoint = null;                     // Last mouse point
    protected InteractionMode interactionMode = InteractionMode.IDLE;     // Current interaction mode
    protected Point2D rotationaxis = new Point2D.Double(0, 0);
    double currentrotation = 0;

    protected abstract String getName();

    public Sprite() {
        ; // no-op
    }

    public Sprite(Sprite parent) {
        if (parent != null) {
            parent.addChild(this);
        }
    }

    public void setneighbour(Sprite s) {
        neighbour = s;
    }

    public void addChild(Sprite s) {
        children.add(s);
        s.setParent(this);
    }

    public Sprite getParent() {
        return parent;
    }

    private void setParent(Sprite s) {
        this.parent = s;
    }

    /**
     * Test whether a point, in world coordinates, is within our sprite.
     */
    public abstract boolean pointInside(Point2D p);

    /**
     * Handles a mouse down event, assuming that the event has already been tested to ensure the mouse point is within our sprite.
     */
    protected void handleMouseDownEvent(MouseEvent e) {
        lastPoint = e.getPoint();
        String name = getName();
        if (e.getButton() == MouseEvent.BUTTON1) {
            if(name == "torso"){
                interactionMode = InteractionMode.DRAGGING;
            } else {
                interactionMode = InteractionMode.ROTATING;
            }
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            interactionMode = InteractionMode.SCALING;
        }
        // Handle rotation, scaling mode depending on input
    }


    protected void handleMouseDragEvent(MouseEvent e) {

        Point2D oldPoint = lastPoint;
        Point2D newPoint = e.getPoint();
        switch (interactionMode) {
            case IDLE:
                ; // no-op (shouldn't get here)
                break;
            case DRAGGING:
                double x_diff = newPoint.getX() - oldPoint.getX();
                double y_diff = newPoint.getY() - oldPoint.getY();
                transform.translate(x_diff, y_diff);
                rotationaxis.setLocation(rotationaxis.getX() + x_diff, rotationaxis.getY() + y_diff);
                break;
            case ROTATING:
                double angleBig = Math.atan(Math.abs(newPoint.getX() - this.currentaxis().getX()) / Math.abs(newPoint.getY() - currentaxis().getY()));
                double angleSmall = Math.atan(Math.abs(oldPoint.getX() - this.currentaxis().getX()) / Math.abs(oldPoint.getY() - this.currentaxis().getY()));
                int quadrant = 5;
                if (getName() == "head") {
                    if (currentaxis().getY() > newPoint.getY()) {
                        if (currentaxis().getX() < newPoint.getX()) {
                            quadrant = 1;
                            transform.rotate(angleBig - angleSmall);
                            currentrotation += (angleBig - angleSmall);
                        } else {
                            quadrant = 2;
                            transform.rotate(angleSmall - angleBig);
                            currentrotation += (angleSmall - angleBig);
                        }
                    }
                } else if(getName() == "ruleg" || getName() == "luleg"){
                    if (currentaxis().getY() < newPoint.getY()) {
                        if (currentaxis().getX() < newPoint.getX()) {
                            quadrant = 4;
                            transform.rotate(angleSmall - angleBig);
                            currentrotation += (angleSmall - angleBig);
                        } else {
                            quadrant = 3;
                            transform.rotate(angleBig - angleSmall);
                            currentrotation += (angleBig - angleSmall);
                        }
                    }

                } else {

                    if (currentaxis().getY() < newPoint.getY()) {
                        if (currentaxis().getX() < newPoint.getX()) {
                            quadrant = 4;
                            transform.rotate(angleSmall - angleBig);
                            currentrotation += (angleSmall - angleBig);
                        } else {
                            quadrant = 3;
                            transform.rotate(angleBig - angleSmall);
                            currentrotation += (angleBig - angleSmall);
                        }
                    } else {
                        if (currentaxis().getX() < newPoint.getX()) {
                            quadrant = 1;
                            transform.rotate(angleBig - angleSmall);
                            currentrotation += (angleBig - angleSmall);
                        } else {
                            quadrant = 2;
                            transform.rotate(angleSmall - angleBig);
                            currentrotation += (angleSmall - angleBig);
                        }
                    }

                }

                ; // rotation code 
                break;
            case SCALING:
                double factor;
                if (getName() == "ruleg" || getName() == "luleg") {
                    factor = (newPoint.distance(currentaxis()) / oldPoint.distance(currentaxis()));
                } else {
                    factor = (newPoint.distance(this.parent.currentaxis()) / oldPoint.distance(this.parent.currentaxis()));
                }
                if (getName() == "ruleg" || getName() == "luleg") {
                    transform.scale(1, factor);
                    this.neighbour.transform(AffineTransform.getScaleInstance(1, factor));
                } else if (getName() == "rlleg" || getName() == "llleg") {
                    this.getParent().transform(AffineTransform.getScaleInstance(1, factor));
                    this.neighbour.transform(AffineTransform.getScaleInstance(1, factor));
                }
                break;

        }
        lastPoint = e.getPoint();
    }

    public Point2D currentaxis() {

        Point2D temp = new Point2D.Double(0, 0);
        if (this.parent != null) {
            temp.setLocation(rotationaxis.getX() + this.parent.currentaxis().getX(), rotationaxis.getY() + this.parent.currentaxis().getY());
        } else {
            temp.setLocation(rotationaxis.getX(), rotationaxis.getY());

        }

        return temp;
    }

    protected void handleMouseUp(MouseEvent e) {
        interactionMode = InteractionMode.IDLE;
    }

    /**
     * Locates the sprite that was hit by the given event. 
     *
     * @return The sprite that was hit, or null if no sprite was hit
     */
    public Sprite getSpriteHit(MouseEvent e) {
        for (Sprite sprite : children) {
            Sprite s = sprite.getSpriteHit(e);
            if (s != null) {
                return s;
            }
        }
        if (this.pointInside(e.getPoint())) {
            return this;
        }
        return null;
    }

    /**
     * Returns the full transform to this object from the root
     */
    public AffineTransform getFullTransform() {
        AffineTransform returnTransform = new AffineTransform();
        Sprite curSprite = this;
        while (curSprite != null) {
            returnTransform.preConcatenate(curSprite.getLocalTransform());
            curSprite = curSprite.getParent();
        }
        return returnTransform;
    }

    /**
     * Returns our local transform
     */
    public AffineTransform getLocalTransform() {
        return (AffineTransform) transform.clone();
    }

    /**
     * Performs an arbitrary transform on this sprite
     */
    public void transform(AffineTransform t) {
        if (t.TYPE_TRANSLATION == 1) {
            double xdif = t.getTranslateX();
            double ydif = t.getTranslateY();
            rotationaxis.setLocation(rotationaxis.getX() + xdif, rotationaxis.getY() + ydif);
        } else if (t.TYPE_MASK_ROTATION == 1) {
        }
        transform.concatenate(t);
    }

    /**
     * Draws the sprite. This method will call drawSprite after the transform has been set up for this sprite.
     */
    public void draw(Graphics2D g, Color choice) {
        AffineTransform oldTransform = g.getTransform();

        // Set to our transform
        g.setTransform(this.getFullTransform());

        // Draw the sprite (delegated to sub-classes)
        this.drawSprite(g, choice);

        // Restore original transform
        g.setTransform(oldTransform);

        // Draw children
        for (Sprite sprite : children) {
            sprite.draw(g, choice);
        }
    }

    /**
     * The method that actually does the sprite drawing. This method is called after the transform has been set up in the draw() method. Sub-classes should override this method to perform the drawing.
     */
    protected abstract void drawSprite(Graphics2D g, Color choice);
}
