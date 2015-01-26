package gaming.twiz.TwiZ.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Johan Segerlund on 2014-04-06.
 * Episode 17
 */
public class Keyboard implements KeyListener{

    private boolean[] keys = new boolean[120];
    public boolean up, down, left, right, b;

    public void update(){
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        b = keys[KeyEvent.VK_B];
       // System.out.println(up);
    }
    public void keyTyped(KeyEvent e) {
    }


    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
