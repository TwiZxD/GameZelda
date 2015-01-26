package gaming.twiz.TwiZ.entity.mob;

import gaming.twiz.TwiZ.graphics.AnimatedSprite;
import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;
import gaming.twiz.TwiZ.graphics.SpriteSheet;
import gaming.twiz.TwiZ.level.Node;
import gaming.twiz.TwiZ.util.Vector2i;

import java.util.List;

/**
 * Created by Johan Segerlund on 2014-06-24.
 */
public class Star extends Mob {

    private AnimatedSprite up_moving = new AnimatedSprite(SpriteSheet.player_black_Link_moving_up, 32,32,11);
    private AnimatedSprite down_moving = new AnimatedSprite(SpriteSheet.player_black_Link_moving_down, 32,32,11);
    private AnimatedSprite left_moving = new AnimatedSprite(SpriteSheet.player_black_Link_moving_left, 32,32,11);
    private AnimatedSprite right_moving = new AnimatedSprite(SpriteSheet.player_black_Link_moving_right, 32,32,11);

    private AnimatedSprite animSprite = down_moving;

    private double xa = 0;
    private double ya = 0;
    private List<Node> path = null;
    private int time = 0;

    private double speed = 0.8;

    public Star(int x, int y){
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummy;
    }

    private void move(){
        time++;
        xa = 0;
        ya = 0;
        int px = (int)level.getPlayerAt(0).getX();
        int py = (int)level.getPlayerAt(0).getY();
        Vector2i start = new Vector2i ((int)getX() >> 4, (int)getY() >> 4 );
        Vector2i destination = new Vector2i(px >> 4, py >> 4);
        if (time % 3 == 0) path = level.findPath(start,destination);
        if(path != null){
            if(path.size() > 0){
                Vector2i vec = path.get(path.size() - 1).tile;
                if (x < vec.getX() << 4) xa++;
                if (x > vec.getX() << 4) xa--;
                if (y < vec.getY() << 4) ya++;
                if (y > vec.getY() << 4) ya--;
            }
        }
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
    }

    public void update() {
        move();
        if (walking) animSprite.update();
        else animSprite.setFrame(0);
        if (ya < 0) {
            animSprite = up_moving;
            dir = Direction.UP;
        } else if (ya > 0) {
            animSprite = down_moving;
            dir = Direction.DOWN;
        }
        if (xa < 0) {
            animSprite = left_moving;
            dir = Direction.LEFT;
        } else if (xa > 0) {
            animSprite = right_moving;
            dir = Direction.RIGHT;
        }

    }

    public void render(Screen screen) {
        sprite = animSprite.getSprite();
        // - 16 pga 32 pixel
        screen.renderMob((int)(x - mobMiddlePosition),(int)(y - mobMiddlePosition), this);

    }
}


