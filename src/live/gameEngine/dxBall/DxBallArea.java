package live.gameEngine.dxBall;

import live.util.GUIContext;
import org.newdawn.slick.tiled.TileSet;

import java.awt.*;
import java.awt.geom.Point2D;

public class DxBallArea {

    private final double HeaderHeight= 25;
    Point2D topLeft;

    int width;
    int height;

    DxBallTileGenerator tileGenerator;


    DxBallTileSet tileSet;
    private Color BackgroundColor = Color.black;


    public DxBallArea(DxBallTileGenerator tileGenerator) {
        topLeft = new Point2D.Double(0,HeaderHeight);
        this.tileGenerator = tileGenerator;

    }

    public void init(GUIContext container) {


        width = container.getWidth();
        height = (int) (container.getHeight()-HeaderHeight);

        tileSet = new DxBallTileSet(tileGenerator.generate(this));
        tileSet.init(this);


    }

    public void update(GUIContext container, int time) {
        tileSet.update(this,container,time);
    }

    public void render(GUIContext container) {
        drawBackground(container);
        tileSet.render(container);
    }

    private void drawBackground(GUIContext container) {
        Graphics g = container.getGraphics();

        g.setColor(BackgroundColor);

        g.fillRect((int)topLeft.getX(),(int)topLeft.getY(),width,height);
    }
}
