package gaming.twiz.TwiZ.level.tile;

import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;

/**
 * Created by Johan Segerlund on 2014-04-11.
 */
public class RockTile extends Tile{
    public RockTile(Sprite sprite){
        super(sprite);
    }

    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4, y << 4, sprite);
    }

    public boolean solid() { //Om tilen ska ha någon kollition, kan spelaren gå över objektet
        return true;
    }
}
