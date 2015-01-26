package gaming.twiz.TwiZ.level.tile;

import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;

/**
 * Created by Johan Segerlund on 2014-07-08.
 */
public class NormalTile extends Tile {
    public NormalTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen){
        if(!(sprite == null) ){
            screen.renderTile(x << 4, y << 4, sprite);
        }

    }

    public boolean solid() {
        return false;
    }
}
