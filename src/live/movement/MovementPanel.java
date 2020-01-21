package live.movement;


import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MovementPanel extends BasicGameState {

    public static final float FRAME_WIDTH = 30;
    private static final Color FrameColor = Color.black;
    private static final Color PanelColor = Color.darkGray;

    private static final Color MeasureLineColor = Color.lightGray;
    private static final float MeasureLineLength = 3;

    List<GameEntity> entityList;
    private double width;
    private double height;


    public MovementPanel() {
        this.entityList = new ArrayList();
    }

    public void addEntity(GameEntity entity)
    {
        entityList.add(entity);
    }


    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        width = gameContainer.getWidth()-2*FRAME_WIDTH;
        height = gameContainer.getHeight()-2*FRAME_WIDTH;
        for (GameEntity e:entityList
             ) {
            e.init(gameContainer,stateBasedGame);
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        Color c = graphics.getColor();
        int w = gameContainer.getWidth();
        int h = gameContainer.getHeight();
        graphics.setColor(FrameColor);
        graphics.fillRect(0,0,w,h);
        graphics.setColor(PanelColor);
        graphics.fillRect(FRAME_WIDTH,FRAME_WIDTH,(float) width,(float)height);
        graphics.setColor(c);

        drawMeasureLines(graphics);

        graphics.translate(FRAME_WIDTH,(float) height+FRAME_WIDTH);
        graphics.scale(1,-1);

        renderEtities(gameContainer,stateBasedGame,graphics);
    }

    private void drawMeasureLines(Graphics graphics) {
        graphics.setColor(MeasureLineColor);

        for (int i = 0; i < width; i += 50) {
            drawXLine(graphics,i);
            drawYLine(graphics,i);
        }
    }

    private void drawYLine(Graphics graphics, int i) {
        graphics.drawLine((float)FRAME_WIDTH-MeasureLineLength, (float) height + FRAME_WIDTH-i ,(float) (FRAME_WIDTH+MeasureLineLength),(float) height + FRAME_WIDTH-i );
        graphics.drawString(""+i,FRAME_WIDTH-graphics.getFont().getWidth(""+i)-2*MeasureLineLength,(float) height + FRAME_WIDTH - i-graphics.getFont().getHeight("i")/2);
    }

    private void drawXLine(Graphics graphics, int i) {
        graphics.drawLine(i+ FRAME_WIDTH,(float) (height+FRAME_WIDTH-MeasureLineLength),i+FRAME_WIDTH,(float)(height+FRAME_WIDTH+MeasureLineLength));
        graphics.drawString(""+i,i+ FRAME_WIDTH-graphics.getFont().getWidth(""+i)/2,(float) height + FRAME_WIDTH + 2*MeasureLineLength);
    }



    private void renderEtities(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        for (GameEntity e : entityList
             ) {
            e.render(gameContainer,stateBasedGame,graphics);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int time) throws SlickException {
        for (GameEntity e : entityList
        ) {
            e.update(gameContainer,stateBasedGame,1); // set to 1 for debug purposes
        }
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(0,0,width,height);
    }
}
