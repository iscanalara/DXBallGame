package live.gameEngine.dxBall;

import live.util.GUIContext;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public  abstract class DxBallTile {

    int width;
    int height;

    Point2D topLeft;

    boolean isAlive;

    public DxBallTile(Point2D topLeft) {
        this.topLeft = topLeft;
    }

    public void init(DxBallArea container) {
        isAlive = true;
    }

    public abstract void render(GUIContext container);

    public abstract void update(DxBallArea dxBallArea, GUIContext container, int time);

    public abstract Rectangle2D boundingRectangle();

    public abstract int hit(DxBallGame dxBallGame);
}
