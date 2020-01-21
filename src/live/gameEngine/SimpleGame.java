package live.gameEngine;

import live.util.GUIContext;

import java.awt.*;

public class SimpleGame implements Game {


    private final static int  FrameWidth = 10;
    private final static int HeaderWidth = 25;

    @Override
    public void init(GUIContext container) {

    }

    @Override
    public void update(GUIContext container, int time) {

    }

    @Override
    public void render(GUIContext container) {
        Graphics g = container.getGraphics();

        g.setColor(Color.darkGray);

        g.fillRect(0,HeaderWidth, container.getWidth(),container.getHeight()-HeaderWidth);

        g.setColor(Color.gray);


        int x = FrameWidth;
        int y = FrameWidth + HeaderWidth;


        g.fillRect(x,y, container.getWidth()-2*FrameWidth,container.getHeight()-2*FrameWidth-HeaderWidth);



    }

    @Override
    public String getTitle() {

        return "Simple Game 1.0";
    }
}
