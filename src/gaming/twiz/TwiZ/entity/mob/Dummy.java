package gaming.twiz.TwiZ.entity.mob;

import gaming.twiz.TwiZ.graphics.AnimatedSprite;
import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.SpriteSheet;
import gaming.twiz.TwiZ.util.Hitbox;

import java.util.Random;

/**
 * Created by Johan Segerlund on 2014-06-22.
 */
public class Dummy extends Mob {

    private AnimatedSprite up_moving = new AnimatedSprite(SpriteSheet.player_black_Link_moving_up, 32,32,11);
    private AnimatedSprite down_moving = new AnimatedSprite(SpriteSheet.player_black_Link_moving_down, 32,32,11);
    private AnimatedSprite left_moving = new AnimatedSprite(SpriteSheet.player_black_Link_moving_left, 32,32,11);
    private AnimatedSprite right_moving = new AnimatedSprite(SpriteSheet.player_black_Link_moving_right, 32,32,11);
    private int counter = 0;
    private int time = 0;
    private int xa = 0;
    private int ya = 0;
    private AnimatedSprite animSprite = down_moving;
    Random random = new Random();

    /**
     *
     * @param x start x coordinate
     * @param y start y coordinate
     */
    public Dummy(int x, int y) {
        this.x = x << 4; // * 16
        this.y = y << 4; // * 16
        sprite = sprite.dummy;

        hitbox = new Hitbox(this);
        maxHealth = 5;
        health = 5;
  //      level.doesitwork();
    //    this.level.add(hitbox, 5);      // hitbox);//hitbox = new Hitbox(this);
        //         level.doesitwork();
    }
    private int canDropBombTimer = 120;
    public void update() {
        hitbox.collision(level);
 /*       if(canDropBombTimer < 120) canDropBombTimer++;
        else {
            System.out.println("BOMB DROPPED");
            //Projectile bomb = new Bomb(this.getX(),this.getY() );
            dropBomb();
            canDropBombTimer = 0;
        }*/

        time++;
        if(time % (random.nextInt(60) + 30 ) == 0){ //hur ofta gör han något
            xa = random.nextInt(3) -1;
            ya = random.nextInt(3) -1;
            if(random.nextInt(3) == 0){ //stillastående

                xa = 0;
                ya = 0;
            }
        }


        if(walking) animSprite.update();
        else animSprite.setFrame(10);
        if(ya < 0) {

            animSprite = up_moving;
            dir = Direction.UP;
        } else if (ya > 0) {
            animSprite = down_moving;
            dir = Direction.DOWN;
        }
        if(xa < 0) {
            animSprite = left_moving;
            dir = Direction.LEFT;
        }else if (xa > 0){
            animSprite = right_moving;
            dir = Direction.RIGHT;
            }
        if(xa != 0 || ya != 0){
            move(xa, ya);
            walking = true;
        }else {
            walking = false;

        }
    }

    public void render(Screen screen) {
        screen.drawHealthMeter((int)x, (int)y, getHpPercent());

        hitbox.render(screen);
        sprite = animSprite.getSprite();
        screen.renderMob((int)(x - mobMiddlePosition), (int)(y - mobMiddlePosition), sprite);
    }
}
