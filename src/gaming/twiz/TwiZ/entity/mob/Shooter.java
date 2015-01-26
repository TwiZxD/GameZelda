package gaming.twiz.TwiZ.entity.mob;

import gaming.twiz.TwiZ.entity.Entity;
import gaming.twiz.TwiZ.entity.projectile.WizardProjectile;
import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;
import gaming.twiz.TwiZ.util.Debug;
import gaming.twiz.TwiZ.util.Vector2i;

import java.util.Collections;
import java.util.List;

/**
 * Created by Johan Segerlund on 2014-06-26.
 */
public class Shooter extends Mob { //EP 103

    private Entity rand = null;
    private int time = 0;
    private int fireRate = 0;

    public Shooter(int x, int y){
        this.x = x  << 4;
        this.y = y << 4;
        sprite = Sprite.dummy;
    }

    public void update(){
      shootRandom();
    }

    private void shootRandom() {
        List<Entity> entities = level.getEntities(this, 10);
        entities.add(level.getClientPlayer());
        if (time % (30 + random.nextInt(91)) == 0) {
      //      Collections.shuffle(entities);
            int index = random.nextInt(entities.size());
            rand = entities.get(index);
        }
      //  System.out.println(entities.size());

        if (entities.size() > 0) {
            rand = entities.get(0);
            double dx = rand.getX() - x;
            double dy = rand.getY() - y;
            double dir = Math.atan2(dy, dx);
    //       if( fireRate <= 0) {
                shoot(x, y, dir);
     //           fireRate = WizardProjectile.FIRE_RATE;
     //       }
        }

     /*   if (rand != null) {
            double dx = rand.getX() - x;
            double dy = rand.getY() - y;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
        }*/
    }

    private void shootClosest() {
        List<Entity> entities = level.getEntities(this, 500);
        entities.add(level.getClientPlayer());
        double min = 0;
        Entity closest = null;
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            double distance = Vector2i.getDistance(new Vector2i((int)x,(int)y), new Vector2i((int)e.getX(),(int)e.getY()) );
            if (i == 0 || distance < min) {

                min = distance;
                closest = e;
            }
        }
        if (closest != null) {
            double dx = closest.getX() - x;
            double dy = closest.getY() - y;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
        }
    }
    public void render(Screen screen) {
        screen.renderMob((int)x - mobMiddlePosition, (int)y - mobMiddlePosition, this);

    }

}
