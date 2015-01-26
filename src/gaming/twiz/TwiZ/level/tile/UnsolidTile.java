package gaming.twiz.TwiZ.level.tile;

import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;

/**
 * Created by Johan Segerlund on 2014-04-11.
 */
public class UnsolidTile extends Tile{
    public UnsolidTile(Sprite sprite){
        super(sprite);
    }

    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4, y << 4, sprite);
    }
}
