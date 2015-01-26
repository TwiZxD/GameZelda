package gaming.twiz.TwiZ.level.tile;

import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;

/**
 * Created by Johan Segerlund on 2014-07-08.
 */
public class NullSolidTile extends Tile {
    public NullSolidTile() {
        super(null);
        //   super(sprite);
    }
    public void render(int x, int y, Screen screen){

    }

    public boolean shouldItRender(){
        return false;
    }
    public boolean solid() {
        return true;
    }
}
