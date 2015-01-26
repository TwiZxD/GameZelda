package gaming.twiz.TwiZ.level;

import gaming.twiz.TwiZ.entity.mob.Chaser;
import gaming.twiz.TwiZ.entity.mob.Dummy;
import gaming.twiz.TwiZ.entity.mob.Shooter;
import gaming.twiz.TwiZ.entity.mob.Star;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Johan Segerlund on 2014-04-11.
 */
public class SpawnLevel extends Level{


    public SpawnLevel(String path) {
        super(path);
    }

   protected void loadLevel (String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResourceAsStream(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w*h];
            image.getRGB(0,0,w,h, tiles,0,w); //converts the picture to pixels
        }catch (IOException e) {
            System.out.println("Exception! Could not load level file! class: SpawnLevel");
        }
        for(int i = 0; i < 1; i++){
            add(new Dummy(9,6));
            add(new Dummy(10,6));
            add(new Dummy(11,6));
            add(new Dummy(5,6));
     //      add(new Chaser(12,10));
        //   add(new Star(12,8));
          //  add(new Shooter(12,10));
        }
}


/*    protected void generateLevel() { //doesnt get used really...
        for (int y = 0; y < 64; y++) {
            for (int x = 0; x < 64; x++) {
          //      getTile(x, y);
            }

        }
        tile_size = 16;
    }*/



}

