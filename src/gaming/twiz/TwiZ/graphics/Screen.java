package gaming.twiz.TwiZ.graphics;

import gaming.twiz.TwiZ.entity.mob.Chaser;
import gaming.twiz.TwiZ.entity.mob.Mob;
import gaming.twiz.TwiZ.entity.mob.Star;
import gaming.twiz.TwiZ.entity.projectile.Projectile;
import gaming.twiz.TwiZ.level.tile.Tile;

import java.util.Random;

/**
 * Created by Johan Segerlund on 2014-01-10.
 */

/**
 * Screen class is made to manipulate pixels onto the screen
 */
public class Screen {

    public int width, height;
    public int[] pixels;
    public final int MAP_SIZE = 64;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int xOffset, yOffset;
    public int[] tiles = new int[64 * 64];
    private Random random = new Random();

    //Color that is not gonna be rendered
    private final int ALPHA_COLOR_1 = 0xFFff00ff;
    private final int ALPHA_COLOR_2 = 0xff7F007F;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff); //Ep 15
            tiles[0] = 0;
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
        if(fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for (int y = 0; y < sprite.getHeight(); y++){
            int ya = y + yp;
            for (int x = 0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                if (xa < 0  || xa >= width || ya < 0 || ya >= height) continue;
                int col = sprite.pixels[x + y * sprite.getWidth()];
                if (col != ALPHA_COLOR_1 && col != ALPHA_COLOR_2) {
                    pixels[xa + ya * width] = col; //sprite.pixels[x + y * sprite.getWidth()];
                }
            }
        }
    }

    public void renderTextCharacter(int xp, int yp, Sprite sprite, int color, boolean fixed) {
        if(fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for (int y = 0; y < sprite.getHeight(); y++){
            int ya = y + yp;
            for (int x = 0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                if (xa < 0  || xa >= width || ya < 0 || ya >= height) continue;
                int col = sprite.pixels[x + y * sprite.getWidth()];
                if (col != ALPHA_COLOR_1 && col != ALPHA_COLOR_2) {
                    pixels[xa + ya * width] = color; //sprite.pixels[x + y * sprite.getWidth()];
                }
            }
        }
    }


    public void renderTile(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;

        for (int y = 0; y < sprite.getHeight(); y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xa = x + xp;
                if (xa < -sprite.getWidth() || xa >= width || ya >= height || ya < 0) break;
                if (xa < 0) xa = 0;

                /*int col = tile.sprite.pixels[x+y*sprite];
                if (col != 0xffFF00FF) //Ritar inte ut om färgen är rosa FF00FF. ff för att vi kör rgb BufferedImage
                    pixels[xa + ya * width] = col;*/
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];

            }
        }
    }

    /*public void renderTile(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;

        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -sprite.SIZE || xa >= width || ya >= height || ya < 0) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];

            }
        }*/

    public void renderProjectile(int xp, int yp, Projectile p) {
        xp -= xOffset;
        yp -= yOffset;

        for (int y = 0; y < p.getSpriteSize(); y++) {
            int ya = y + yp;
            for (int x = 0; x < p.getSpriteSize(); x++) {
                int xa = x + xp;
                if (xa < -p.getSpriteSize() || xa >= width || ya >= height || ya < 0) break;
                if (xa < 0) xa = 0;
                int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
                if (col != ALPHA_COLOR_1 && col != ALPHA_COLOR_2) pixels[xa + ya * width] = col;

                //pixels[xa + ya * width] = p.getSprite().pixels[x + y * p.getSprite().SIZE]; //<-- inte p.getSpriteSize() ?

            }
        }


        /*        for (int y = 0; y < screen.sprite.SIZE; y++) {
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break; //Out of bound fel ?
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }*/
    }

    public void renderMob(int xp, int yp, Sprite sprite){
        xp -= xOffset;
        yp -= yOffset;

        int playersize = 32;
        for (int y = 0; y < playersize; y++) {
            int ya = y + yp;
            int ys = y;
            for (int x = 0; x < playersize; x++) {
                int xa = x + xp;
                int xs = x;
                if (xa < -playersize || xa >= width || ya < 0 || ya >= height) break; //Out of bound fel ?
                if (xa < 0) xa = 0;
                int col = sprite.pixels[xs + ys * playersize];
                if (col != ALPHA_COLOR_1 && col != ALPHA_COLOR_2) //Ritar inte ut om färgen är rosa FF00FF. ff för att vi kör rgb BufferedImage
                    pixels[xa + ya * width] = col;
            }
        }
    }


    public void renderMob(int xp, int yp, Sprite sprite,int SpriteSize){
        xp -= xOffset;
        yp -= yOffset;

        int playersize = SpriteSize;
        for (int y = 0; y < playersize; y++) {
            int ya = y + yp;
            int ys = y;
            for (int x = 0; x < playersize; x++) {
                int xa = x + xp;
                int xs = x;
                if (xa < -playersize || xa >= width || ya < 0 || ya >= height) break; //Out of bound fel ?
                if (xa < 0) xa = 0;
                int col = sprite.pixels[xs + ys * playersize];
                if (col != ALPHA_COLOR_1 && col != ALPHA_COLOR_2) //Ritar inte ut om färgen är rosa FF00FF. ff för att vi kör rgb BufferedImage
                    pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderMob(int xp, int yp, Mob mob){
        xp -= xOffset;
        yp -= yOffset;
        int playersize = 32;
        for (int y = 0; y < playersize; y++) {
            int ya = y + yp;
            int ys = y;
            for (int x = 0; x < playersize; x++) {
                int xa = x + xp;
                int xs = x;
                if (xa < -playersize || xa >= width || ya < 0 || ya >= height) break; //Out of bound fel ?
                if (xa < 0) xa = 0;
               // int col = mob.getSprite().pixels[x+y*playersize];
                int col = mob.getSprite().pixels[xs + ys * playersize];

                if((mob instanceof Chaser) && col == 0xff3151FF) col = 0xffBA0015; //Byter färgen på ögonen till röda
                if((mob instanceof Star) && col == 0xff3151FF) col = 0xff00FF10; //Byter färgen på ögonen till gröna

                if (col != ALPHA_COLOR_1 && col != ALPHA_COLOR_2) //Ritar inte ut om färgen är rosa FF00FF. ff för att vi kör rgb BufferedImage
                    pixels[xa + ya * width] = col;
            }
        }
    }

    public void drawRect(int xp, int yp, int width, int height,int color, boolean fixed) {
        if(fixed){
            xp -= xOffset;
            yp -= yOffset;
        }
        for (int x = xp; x < xp + width; x++) {
            if (x < 0 | x >= this.width || yp >= this.height) continue;
            if (yp > 0) pixels[x + yp * this.width] = color;
            if (yp + height >= this.height) continue;
            if (yp + height > 0) pixels[x + (yp + height) * this.width] = color;
        }
        for (int y = yp; y <= yp + height; y++) {
            if (xp >= this.width || y < 0 || y >= this.height) continue;
            if (xp > 0)pixels[xp + y * this.width] = color;
            if (xp + width >= this.width) continue;
            if (xp + width> 0) pixels[xp + width + y * this.width] = color;
        }

    }


    public void drawHealthMeter(int xp, int yp, int hp){
        if(hp >= 100) return;
        int hmetet = 0;
        int color;
        int xPixelPositon;
        int yPixelPosition;
        for(int x = 0; x < 20; x++){ //100% / 20 = 4
            xPixelPositon = -10 + x + xp -xOffset;
            yPixelPosition =(-16 +yp-yOffset);
            if(hp > hmetet) color = 0xFFff0000;
            else color = 0xFF000000;
            hmetet = hmetet + 5;

            if (xPixelPositon < 0 || xPixelPositon >= width) continue;
            if (yPixelPosition < 0 || yPixelPosition >= height ) continue;

            pixels[xPixelPositon + yPixelPosition*this.width] = color;

        }
    }


    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}


