package gaming.twiz.TwiZ.entity.spawner;

import gaming.twiz.TwiZ.entity.particle.Particle;
import gaming.twiz.TwiZ.level.Level;

/**
 * Created by Johan Segerlund on 2014-06-21.
 */
public class ParticleSpawner extends Spawner {
    private int life;

    /**
     *
     * @param x
     * @param y
     * @param life The duration of the particle
     * @param amount How many particles there will be
     * @param level
     */
    public ParticleSpawner(int x, int y, int life,int amount, Level level) {
        super(x, y, Type.PARTICLE, amount, level);
        this.life = life;
        for(int i = 0; i < amount; i++){
                level.add(new Particle(x,y,life));

        }
    }
}
