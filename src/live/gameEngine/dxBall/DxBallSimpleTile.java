package live.gameEngine.dxBall;

import live.util.GUIContext;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class DxBallSimpleTile extends DxBallTile {
    public static final int TileWidth = 30;
    public static final int TileHeight= 10;
    private static final double TileSpeedX=0.1;

    private Color TileColor = Color.ORANGE;


    double speedX;
    private final int TileScore = 10;

    public DxBallSimpleTile(Point2D topLeft) {
        super(topLeft);
        width = TileWidth;
        height = TileHeight;
        speedX = TileSpeedX;


    }


    @Override
    public void render(GUIContext container) {
        Graphics g = container.getGraphics();

        g.setColor(TileColor);

        g.fillRect((int)topLeft.getX(),(int)topLeft.getY(),width,height);
    }

    @Override
    public void update(DxBallArea dxBallArea, GUIContext container, int time) {

        if ( (topLeft.getX()<dxBallArea.topLeft.getX() && speedX<0) ||
                (topLeft.getX()>dxBallArea.topLeft.getX()+ dxBallArea.width && speedX>0))
            speedX = -speedX;


        topLeft.setLocation(topLeft.getX()+ speedX*time,topLeft.getY());
    }

    @Override
    public Rectangle2D boundingRectangle() {
        return new Rectangle2D.Double(topLeft.getX(),topLeft.getY(),width,height);
    }

    @Override
    public int hit(DxBallGame dxBallGame) {

        isAlive = false;
        return TileScore;
    }
}
