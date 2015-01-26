package gaming.twiz.TwiZ.util;

import gaming.twiz.TwiZ.graphics.Screen;

/**
 * Created by Johan Segerlund on 2014-06-26.
 */

public class Debug {

    private Debug() { //private för att detta är en static class som aldrig kommer initieras

    }

    //Debug.drawRect(screen,-40,20,100,40,0xff00ff,true);
    public static void drawRect(Screen screen, int x, int y, int width, int height, boolean fixed){
        drawRect(screen,x,y,width,height, 0xff0000, fixed);
    }

    public static void drawRect(Screen screen, int x, int y, int width, int height,int col, boolean fixed) {
        screen.drawRect(x, y, width, height,col, fixed);
    }
}
