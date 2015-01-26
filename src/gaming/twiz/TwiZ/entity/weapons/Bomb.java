package gaming.twiz.TwiZ.entity.weapons;

import gaming.twiz.TwiZ.Sound.MakeSound;
import gaming.twiz.TwiZ.Sound.Sound;
import gaming.twiz.TwiZ.entity.Entity;
import gaming.twiz.TwiZ.entity.mob.Mob;
import gaming.twiz.TwiZ.entity.projectile.Projectile;
import gaming.twiz.TwiZ.graphics.AnimatedSprite;
import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.SpriteSheet;
import gaming.twiz.TwiZ.util.Hitbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johan Segerlund on 2014-07-02.
 */



public class Bomb extends Projectile {
    private AnimatedSprite anim_Bomb_Tick = new AnimatedSprite(SpriteSheet.bomb_Tick, 32,32,4);
    private AnimatedSprite anim_Bomb_Blue = new AnimatedSprite(SpriteSheet.bomb_Tick, 32,32,2);
    private AnimatedSprite anim_Explosion = new AnimatedSprite(SpriteSheet.bomb_Explosion, 32,32,8);
    private AnimatedSprite animSprite = null;

    private boolean bombExplode = false;


    public Bomb(double x, double y) {
        super(x,y,0);
        //animSprite = anim_Bomb_Tick;
        animSprite = anim_Bomb_Blue;
        sprite = animSprite.getSprite();
        hitbox = new Hitbox(this,32,32);
        damage = 2;
    }

    private int detonateTimer = 0;
    public void update() {

        if (detonateTimer++ < 120) {
            animSprite.update();

          //Change to warning bomb
        } else if (detonateTimer < 240) {
            animSprite = anim_Bomb_Tick;
            animSprite.update();
        } else {
            if(bombExplode == false){
                bombExplode = true;
                List<Entity> list = hitbox.getCollisionHitList(level);
                for(int i = 0; i < list.size(); i++){
                    if(list.get(i) instanceof Mob){
                        ((Mob) list.get(i)).takeDamage(damage);
                    }
                }
                Sound.sound_Bomb_Blow.play();
                //Do damage
            }
            animSprite = anim_Explosion;
            if (anim_Explosion.update(5)) {


                remove();
            }
        }

            sprite = animSprite.getSprite();
    }

    public void render(Screen screen) {
        screen.renderProjectile((int) x - 16, (int) y - 16, this);
        hitbox.render(screen);
    }

}

