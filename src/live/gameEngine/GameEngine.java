package live.gameEngine;

import live.util.GUIContext;
import live.util.JFrameGUIContext;
import org.lwjgl.Sys;

public class GameEngine implements Runnable {

    Game game;
    GUIContext container;
    Thread     gameThread;


    int updateCounter;
    int renderCounter;

    int updateInterval = 10;

    long lastUpdateClock;

    long lastTitleUpdateClock;
    long elapsed=0;

    boolean gameIsOn = false;


    public GameEngine(Game game, GUIContext container) {
        this.game = game;
        this.container = container;
    }

    public void stop()
    {
        gameIsOn = false;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public void start()
    {
        gameIsOn = true;

        gameThread = new Thread(this);

        gameThread.start();
    }

    public void init()
    {
        container.init();
        game.init(container);
        lastUpdateClock = System.currentTimeMillis();
        lastTitleUpdateClock = System.currentTimeMillis();
        elapsed =0;


        updateCounter =0;
        renderCounter= 0;
    }

    @Override
    public void run() {

        init();

        while (gameIsOn)
        {
            update();
            render();
            updateTitleBar();
        }

    }

    private void updateTitleBar() {
        if ( System.currentTimeMillis()-lastTitleUpdateClock >1000) {
            container.setTitle(game.getTitle() + " UPS:" + updateCounter + " FPS:" + renderCounter);
            lastTitleUpdateClock = System.currentTimeMillis();
            updateCounter=0;
            renderCounter=0;
        }
    }

    private void render() {
        game.render(container);
        container.disposeGraphics();
        renderCounter++;

    }

    private void update() {
        elapsed += System.currentTimeMillis()- lastUpdateClock;
        for (int i = 0; elapsed > updateInterval  ; elapsed -= updateInterval) {
            game.update(container, updateInterval);
            updateCounter++;
        }
        lastUpdateClock = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        Game game = new SimpleGame();
        GUIContext container = new JFrameGUIContext(game.getTitle(),600,400);
        GameEngine engine = new GameEngine(game,container);

        engine.start();
    }
}
