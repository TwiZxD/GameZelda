package gaming.twiz.TwiZ.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Johan Segerlund on 2014-04-12.
 */
public class Mouse implements MouseListener, MouseMotionListener {

    //static pga att vi bara kan ha en mus
    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;

    public static int getX() {
        return mouseX;
    }

    public static int getY() {
        return mouseY;
    }

    public static int getButton() {
        return mouseB;
    }

    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mouseClicked(MouseEvent e) {
    }


    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        mouseB = e.getButton();

    }

    public void mouseReleased(MouseEvent e) {
        mouseB = -1;
    }







}
