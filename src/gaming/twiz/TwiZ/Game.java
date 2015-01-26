package gaming.twiz.TwiZ;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;
import javax.swing.JFrame;

import gaming.twiz.TwiZ.entity.mob.Player;
import gaming.twiz.TwiZ.graphics.Font;
import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.input.Keyboard;
import gaming.twiz.TwiZ.input.Mouse;
import gaming.twiz.TwiZ.level.Level;
import gaming.twiz.TwiZ.level.SpawnLevel;

/**
 * Created by Johan Segerlund on 2014-01-10.
 * Skippade 61-62
 * %%%%%%%% Game Programming %%%%%%%%%%
 * Episode 107
 * https://www.youtube.com/watch?v=7urP1gPoFgk&list=PLlrATfBNZ98eOOCk2fOFg7Qg5yoQfFAdf&index=107
 *
 * %Network Chat Programming %EP 12 https://www.youtube.com/watch?v=hJC6cjCp_YU&list=PLlrATfBNZ98cCvNtS7x4u1bZOS5FBwOSb&index=12
 */
public class Game extends Canvas implements Runnable {

    private static final int width = 300;
    private static final int height = 168; // 168;
    private static final int SCALE = 2;
    public static String title = "TwiZ";
    private String titleUpsFps = title;
    private String titleinfo;

    private Random random = new Random();
    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    private boolean running = false;

    private Font font;
    private Screen screen;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //create image (episode 7)
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer() ).getData(); //access the image (episode 7)


    public Game(){
        Dimension size = new Dimension(width* SCALE, height* SCALE);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        level = new SpawnLevel("/textures/levels/house_Stairs_down.png");
  //    TileCoordinate playerSpawn = new TileCoordinate(19,62); no idea when this was added
        player = new Player(60,60,key);
        level.add(player);
        font = new Font();


        addKeyListener(key);

        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public static int getWindowWidth() {
        return width * SCALE;
    }

    public static int getWindowHeight() {
        return height * SCALE;
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop(){
        running = false;
        try{
            thread.join(); //join throws exception
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0; //för fps counter
        double delta = 0;   //fps counter
        int frames = 0;
        int updates = 0;

        requestFocus();

        while(running){

            long now = System.nanoTime();
            delta += (now-lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
            //    System.out.println(updates + " ups, " + frames + " fps");
             //   frame.setTitle(titleinfo + " | " + updates + " ups, " + frames + " fps" + " | Mouse Coordinate: ");// + "(" + Mouse.getX() + "," + Mouse.getY() +")");
                titleUpsFps = title +  " | " + updates + " ups, " + frames + " fps";// + "(" + Mouse.getX() + "," + Mouse.getY() +")");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    //"logic" updates the game |
    // 60 times/sec
    //buffer strategy, the game shouldn't move faster because the computer is faster
    public void update() {
        key.update();
        level.update();
        if(player.getLevel() != null) level = player.getLevel();

    }

    //"visual" displays images to the game
    // as fast as the computer can do.
    public void render() {
       // titleinfo = title + " | Mouse Coordinate: " + "(" + Mouse.getX() + "," + Mouse.getY() +")";
        BufferStrategy bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(3);    // 3 multibuffering
         //   requestFocus();
            return;
        }

        screen.clear();
        double xScroll = player.getX()-screen.width / 2;
        double yScroll = player.getY()-screen.height / 2;
        frame.setTitle(titleUpsFps + " | Mouse: " + "(" + (((int)xScroll+Mouse.getX()/ SCALE)) + "," + ((int)yScroll + Mouse.getY()/ SCALE) +") Tile: " +
                (((int)xScroll+Mouse.getX()/(SCALE) )/16) + "," + ((int)yScroll + Mouse.getY()/ SCALE)/16 +")");
        level.render((int)xScroll,(int)yScroll, screen);
     //   font.render(1,1,"You found a quest! \n Will you be able to defeat\n Sir Dasselmassel? ", screen);
        for(int i = 0; i < pixels.length; i++){
            pixels[i] = screen.pixels[i];
        }
        Graphics g = bs.getDrawGraphics();
        //all graphics in here
        g.drawImage(image, 0 , 0, getWidth(), getHeight(), null);
        //g.fillRect(Mouse.getX(), Mouse.getY(), 64,64);
        //
        // g.drawString("X: " + player.x + "Y: " + player.y, 350, 300);
        //if(Mouse.getButton() != -1) g.drawString("Button: " + Mouse.getButton(), 80,80);
        g.dispose();
        bs.show();
    }

    private void tryStuff() {
        for(int i = 0; i < 100; i++){
        System.out.println(random.nextInt(8));
       //     System.out.println( ((i % 3)- 1) + " = x = ("+i + " % 3) - 1);");
       //     System.out.println( ((i / 3)- 1) + " = x = ("+i + " / 3) - 1);");
        }
    }

    public void setLevel(Level level){
        this.level = level;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(Game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();

    }
}
