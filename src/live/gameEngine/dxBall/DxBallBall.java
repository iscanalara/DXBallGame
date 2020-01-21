package live.gameEngine.dxBall;

import live.util.GUIContext;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class DxBallBall {

    public static final int BallRadius= 5;
    Point2D center;
    int radius;

    Point2D speed;
    private Color BallColor = Color.white;

    public DxBallBall() {
        this.center = new Point2D.Double(0,0);
        this.speed = new Point2D.Double(0,0);

        radius = BallRadius;
    }

    public void setAttached(boolean attached) {
        isAttached = attached;
    }

    boolean isAttached = false;

    public void update(DxBallGame dxBallGame, DxBallArea area, int time) {

        handleCollisions(dxBallGame,area,time);


        double x = center.getX() + speed.getX()*time;
        double y = center.getY() + speed.getY()*time;
        center.setLocation(x,y);
    }

    private void handleCollisions(DxBallGame dxBallGame, DxBallArea area, int time) {

        handleWallCollisions(dxBallGame);
        handleRacketCollisions(dxBallGame);
        handleTileCollisions(dxBallGame);




    }

    private void handleTileCollisions(DxBallGame dxBallGame) {
        DxBallTileSet tileSet = dxBallGame.area.tileSet;

        for (DxBallTile tile : tileSet.tileList
                )
        {
            handleTileCollisions(dxBallGame,tile);
        }


    }

    private void handleTileCollisions(DxBallGame dxBallGame, DxBallTile tile) {
        if (!tile.isAlive)
            return;
        if (boundingRectangle().intersects(tile.boundingRectangle()))
        {
            if ( ( tile.topLeft.getY()> center.getY() && speed.getY()>0) ||
                    ( tile.topLeft.getY()< center.getY() && speed.getY()<0) )
            {
                speed.setLocation(speed.getX(), -speed.getY());
                dxBallGame.score += tile.hit(dxBallGame);
            }
        }
    }

    private void handleRacketCollisions(DxBallGame dxBallGame) {
        if (boundingRectangle().intersects(dxBallGame.racket.boundingRectangle()) && speed.getY()>0)
        {
            double racketMiddleX = dxBallGame.racket.topLeft.getX() + dxBallGame.racket.width/2;
            double speedX=0;
            if ( center.getX()< racketMiddleX)
            {
                speedX= -speed.getY()* (2*(racketMiddleX-center.getX())/dxBallGame.racket.width);
            }
            else if ( center.getX()> racketMiddleX)
            {
                speedX= speed.getY()* (2*(center.getX()-racketMiddleX)/dxBallGame.racket.width);
            }

            speed.setLocation(speedX,-speed.getY());

        }
    }

    private void handleWallCollisions(DxBallGame dxBallGame) {

        DxBallArea area= dxBallGame.area;
        if(   ( (speed.getX()<0) && ((center.getX()-radius)<=area.topLeft.getX())) ||
                ( (speed.getX()>0) && ((center.getX()+radius)>=area.topLeft.getX()+ area.width)))
            speed.setLocation(-speed.getX(),speed.getY());


        if(   (speed.getY()<0) && ((center.getY()-radius)<=area.topLeft.getY()))
            speed.setLocation(speed.getX(),-speed.getY());

        if(   (speed.getY()>0) && ((center.getY()+radius)>=area.topLeft.getY()+area.height))
            dxBallGame.loseOneBall();
    }

    public Rectangle2D boundingRectangle()
    {
        return new Rectangle2D.Double(center.getX()-radius,center.getY()-radius,2*radius,2*radius);
    }


    public void render(DxBallArea area, GUIContext container) {
        Graphics g = container.getGraphics();

        g.setColor(BallColor);

        g.fillOval((int)(center.getX()-radius), (int)(center.getY()-radius),2*radius,2*radius);
    }

    public void setSpeed(Point2D.Double speed) {
        this.speed.setLocation(speed);
    }


    public void setPosition(Point2D.Double pos) {
        center.setLocation(pos);
    }
}
