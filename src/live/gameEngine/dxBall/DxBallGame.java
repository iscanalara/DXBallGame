package live.gameEngine.dxBall;

import live.gameEngine.Game;
import live.gameEngine.GameEngine;
import live.util.GUIContext;
import live.util.JFrameGUIContext;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DxBallGame implements Game , MouseListener, MouseMotionListener {

    public int score;
    DxBallArea area;
    DxBallRacket racket;


    private final static int InitialBallCount= 3;

    int ballCount = InitialBallCount;

    boolean gameOver= false;
    private Color GameOverColor = Color.pink;


    public DxBallGame(DxBallTileGenerator tileGenerator) {
        area = new DxBallArea(tileGenerator);
        racket = new DxBallRacket();



    }

    @Override
    public void init(GUIContext container) {
        area.init(container);
        racket.init(area);

        ballCount = InitialBallCount;
        score =0;
        container.addMouseListener(this);
        container.addMouseMotionListener(this);

    }

    @Override
    public void update(GUIContext container, int time) {
        if (!gameOver) {
            area.update(container, time);
            racket.update(this, area, time);
        }
    }

    @Override
    public void render(GUIContext container) {
        if (! gameOver) {
            area.render(container);
            racket.render(area, container);
            renderHeader(area,container);
        }
        else renderGameOver(container);
    }

    private void renderHeader(DxBallArea area, GUIContext container) {
        Graphics g = container.getGraphics();

        int x = (int) (area.topLeft.getX()+ 20);
        int y = (int) (area.topLeft.getY()+ 20);
        g.setColor(Color.red);
        for (int i = 0; i < ballCount; i++) {
            g.fillOval(x,y,2*DxBallBall.BallRadius,2*DxBallBall.BallRadius);
            x += DxBallBall.BallRadius+ 15;
        }

        x = (int) (area.topLeft.getX()+ area.width-200);

        g.setColor(Color.white);
        g.setFont(new Font("Dialog",Font.PLAIN,20));
        g.drawString("Score:"+ score, x,y+ 20);

    }

    private void renderGameOver(GUIContext container) {
        Graphics g = container.getGraphics();

        g.setColor(GameOverColor);
        g.fillRect((int)area.topLeft.getX(),(int)area.topLeft.getY(),area.width,area.height);

        g.setFont(new Font("Dialog",Font.PLAIN,40));
        g.setColor(Color.white);
        g.drawString("GAME OVER!",area.width/2-100,area.height/2);

    }

    @Override
    public String getTitle() {
        return "DXBALL 0.1";
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton()== MouseEvent.BUTTON1 && racket.ball.isAttached)
        {
            racket.fire();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x= e.getX();

        racket.setXPosition(x);
    }




    public void loseOneBall() {
        racket.attachBall();
        ballCount--;

        if(ballCount<0)
            gameOver= true;
    }


    public static void main(String[] args) {
        Game dxBall = new DxBallGame(new DxBallSimpleTileGenerator());
        GUIContext container = new JFrameGUIContext( dxBall.getTitle(),600,400);



        GameEngine engine = new GameEngine(dxBall,container);

        engine.start();

    }
}
