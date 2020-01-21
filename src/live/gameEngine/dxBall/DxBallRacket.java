package live.gameEngine.dxBall;

import live.util.GUIContext;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class DxBallRacket {

    private int RacketWidth = 80;
    private int RacketHeight = 10;

    Point2D topLeft;

    int height = RacketHeight;

    int width  =  RacketWidth ;

    DxBallBall ball;
    private Point2D FireSpeed = new Point2D.Double(0,-0.2);

    public DxBallRacket() {
        ball = new DxBallBall();

    }



    private Color RacketColor = Color.cyan;

    public void init(DxBallArea area) {
        double x =  (area.topLeft.getX() + area.width/2 - width/2);
        double y = (area.topLeft.getY() + area.height-height);

        topLeft = new Point2D.Double(x,y);

        attachBall();
    }

    public void attachBall() {

        adjustBallPosition();

        ball.setSpeed(new Point2D.Double(0,0));

        ball.setAttached(true);

    }

    private void adjustBallPosition() {

        double x = topLeft.getX()+ width/2;

        double y = topLeft.getY()-ball.radius;


        ball.setPosition(new Point2D.Double(x,y));

    }

    public void update(DxBallGame dxBallGame, DxBallArea area, int time) {
        ball.update(dxBallGame,area,time);
    }

    public void render(DxBallArea area, GUIContext container) {
        ball.render(area,container);

        Graphics g = container.getGraphics();

        g.setColor(RacketColor);

        g.fillRect((int)topLeft.getX(),(int)topLeft.getY(),width,height);

    }

    public void setXPosition(int x) {
        topLeft.setLocation(x,topLeft.getY());

        if (ball.isAttached)
        {
            adjustBallPosition();
        }
    }

    public void fire() {
        ball.setAttached(false);
        ball.speed.setLocation(FireSpeed); ;
    }

    public Rectangle2D boundingRectangle() {
        return new Rectangle2D.Double(topLeft.getX(),topLeft.getY(),width,height);
    }
}
