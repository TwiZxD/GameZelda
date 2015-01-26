package gaming.twiz.TwiZ.entity.projectile;

import gaming.twiz.TwiZ.Sound.Sound;
import gaming.twiz.TwiZ.entity.Entity;
import gaming.twiz.TwiZ.entity.mob.Mob;
import gaming.twiz.TwiZ.entity.mob.Player;
import gaming.twiz.TwiZ.graphics.AnimatedSprite;
import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;
import gaming.twiz.TwiZ.graphics.SpriteSheet;
import gaming.twiz.TwiZ.util.Hitbox;

import java.util.List;

/**
 * Created by Johan Segerlund on 2014-06-29.
 */
public class ArrowProjectile extends Projectile {

    public static final int FIRE_RATE = 15;

    private static Sprite arrow_down = new Sprite(16,0,0,SpriteSheet.arrow_Down);
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.arrow_Down,16,16,4);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.arrow_Right,16,16,4);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.arrow_Up,16,16,4);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.arrow_Left,16,16,4);
    private AnimatedSprite down_HitTarget = new AnimatedSprite(SpriteSheet.arrow_Down, 16,16,3);
    private boolean hitTarget = false;
    private int hitTargetAnimationTime = 15;
    private int timeLife = 100;
    private Entity hitTargetEntity = null;
    private Entity source;


    private AnimatedSprite animSprite = null;
    public ArrowProjectile(double x, double y, double dir,Entity source) {
        super(x, y, dir);
        range = 800;
        speed = 5;
        damage = 1;
        this.source = source;
        Direction direction = getDirection();
        switch(direction){
            case UP: sprite = up.getSprite();
                animSprite = up;
                break;
            case DOWN: sprite = down.getSprite();
                animSprite = down;
                break;
            case LEFT: sprite = left.getSprite();
                animSprite = left;
                    break;
            case RIGHT: sprite = right.getSprite();
                animSprite = right;
                break;
            default: sprite = Sprite.projectile_wizard;

        }

        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
        hitbox = new Hitbox(this,2,2);
    }
    private boolean hitMob = false;
    private int deltaX, deltaY;

    public void update() { //4,14,14, storlek på pil, hur mycket tomspace på tile x, tomt space y

        //Check if object have hit a moving target

        if(hitTargetEntity == null){
            List<Entity> entities = hitbox.getCollisionHitList(level);
            for(int i = 0; i < entities.size(); i++){
                if(source instanceof Player){
                    if(entities.get(i) instanceof Player) continue;
                    else if(entities.get(i) instanceof Mob){
                        hitTargetEntity = entities.get(i);//.hit   hitbox.collision(level);
                    }
                }
                if(source instanceof Mob) {
                    if(entities.get(i) instanceof Mob) continue;
                    if(entities.get(i) instanceof Player){
                        hitTargetEntity = entities.get(i);//.hit   hitbox.collision(level);
                    }
                }

            }

        }

        //
        if(hitTargetEntity !=null && !hitTarget){ //om man inte har träffat något alls
            //System.err.println("Hit a mob!");

            //första gången, ta start positionen på mob och pil som ger delta
            deltaX = (int)(hitTargetEntity.getX() - getX());
            deltaY = (int)(hitTargetEntity.getY() - getY());
            hitMob = true;
            hitTarget();

        }

        //Check if object have hit a wall
        if(!hitTarget){
            if (level.tileCollision((int)(x + nx), (int)(ny + y), 4,2,2)) { //om man inte har träffa något alls
                nx = 0; ny = 0;
                hitTarget();
                Sound.sound_Arrow_Hit.play2();
            } else move();
        }

        //If object hit a moving target
        if(hitMob){
            if(hitTargetEntity.isRemoved()){
                remove();
                return;
            } else {
                x = hitTargetEntity.getX() - deltaX;
                y = hitTargetEntity.getY() - deltaY;
            }
        }

        if(hitTarget && hitTargetAnimationTime > 0){ // om man har träffat något
            --hitTargetAnimationTime;
            if(hitTargetAnimationTime <= 0){
                animSprite.setFrame(2);
            } else animSprite.update(1,4);
            sprite = animSprite.getSprite();
        }

        --timeLife;
        if(timeLife <= 0) remove();

    }

    private void hitTarget() {

        if(hitTargetEntity instanceof Mob){
            ((Mob) hitTargetEntity).takeDamage(damage);
        }
        hitTarget = true;
        animSprite.setFrame(2);
        //         level.add(new ParticleSpawner((int)x,(int) y, 44, 3, level));
        sprite = animSprite.getSprite();// down.getSprite();


    }

    private Direction getDirection() {
        double degrees = angle * (180 / Math.PI); //radiant to degrees
        //-90 is North, -180/180 West, 90 South
//       System.out.println("degrees: " + degrees);
        if (degrees > -45 && degrees <= 45){
   //         System.out.println("Enter RIGHT");
            return Direction.RIGHT;
        }
        if (degrees > 45 && degrees <= 135){
            return Direction.DOWN;
        }
         if (degrees > 135 || degrees <= -135) {
  //           System.out.println("Enter LEFT");
             return Direction.LEFT;
         }
        if (degrees > -135 && degrees <= -45){
   //         System.out.println("Enter UP");
            return Direction.UP;
        }
        System.err.println("Couldn't find angle");
        return null;
    }

    protected void move() {
        x += nx;
        y += ny;
        if (distance() > range) remove();

    }

    private double distance() {
        double dist = 0;
        dist = Math.sqrt(Math.abs((xOrigin - x)*(xOrigin - x) + (yOrigin - y) * (yOrigin - y)) );
        return dist;
    }

    public void render(Screen screen) {
        hitbox.render(screen);
        screen.renderProjectile((int)x - 8,(int)y - 8, this);
    }
        //screen.renderProjectile((int)x - xPos - 8,(int)y - yPos - 8, this);

}
