package gaming.twiz.TwiZ.level.tile;

import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;

/**
 * Created by Johan Segerlund on 2014-04-11.
 */
public class SolidTile extends Tile{
    public SolidTile(Sprite sprite){

        super(sprite);
    }

    public void render(int x, int y, Screen screen){
        if(!(this.sprite == null))screen.renderTile(x << 4, y << 4, sprite);
    }

    public boolean solid() { //Om tilen ska ha någon kollition, kan spelaren gå över objektet
        return true;
    }
}
