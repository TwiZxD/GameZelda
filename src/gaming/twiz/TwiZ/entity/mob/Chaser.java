package gaming.twiz.TwiZ.entity.mob;

import gaming.twiz.TwiZ.graphics.AnimatedSprite;
import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;
import gaming.twiz.TwiZ.graphics.SpriteSheet;

import java.util.List;

/**
 * Created by Johan Segerlund on 2014-06-23.
 */
public class Chaser extends Mob {

    private AnimatedSprite up_moving = new AnimatedSprite(SpriteSheet.player_black_Link_moving_up, 32,32,11);
    private AnimatedSprite down_moving = new AnimatedSprite(SpriteSheet.player_black_Link_moving_down, 32,32,11);
    private AnimatedSprite left_moving = new AnimatedSprite(SpriteSheet.player_black_Link_moving_left, 32,32,11);
    private AnimatedSprite right_moving = new AnimatedSprite(SpriteSheet.player_black_Link_moving_right, 32,32,11);

    private AnimatedSprite animSprite = down_moving;

    private double xa = 0;
    private double ya = 0;
    private double speed = 0.8;

    public Chaser(int x, int y){
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummy;
    }

    private void move(){
        xa = 0;
        ya = 0;
        //Chasing
       // if (((Math.abs(player.getX() - x)) < 75) && (Math.abs(player.getY() - y)) < 75){
        List<Player> players = level.getPlayers(this,50);
        if(players.size() > 0){
            Player player = players.get(0);
            if (x < player.getX()) xa+= speed;
            if (x > player.getX()) xa-= speed;
            if (y < player.getY()) ya+= speed;
            if (y > player.getY()) ya-= speed;
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
        screen.renderMob((int)(x - 16),(int)(y - 16), this);

    }
}
