package live.util;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

public class JFrameGUIContext implements GUIContext {

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;
    String TITLE;
    JFrame frame;

    int width;
    int height;


    public JFrameGUIContext(String TITLE) {
        this.TITLE = TITLE;
        width = FRAME_WIDTH;
        height = FRAME_HEIGHT;
    }

    public JFrameGUIContext(String TITLE, int width, int height) {
        this.TITLE = TITLE;
        this.width = width;
        this.height = height;
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }



    public JFrame getFrame() {
        return frame;
    }



    public void init() {
        frame = new JFrame(TITLE);
        frame.setPreferredSize(new Dimension(width,height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.requestFocus();
        frame.setVisible(true);
        frame.setResizable(false);


        // To enable antialiasing getting Graphics2D object
        Graphics g = getGraphics();
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        RenderingHints rh2 = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        g2.setRenderingHints(rh2);

    }


    public Graphics getGraphics() {
        BufferStrategy bs = frame.getBufferStrategy();
        if (bs == null)
        {
            frame.createBufferStrategy(3);
            bs = frame.getBufferStrategy();
        }
        return bs.getDrawGraphics();

    }

    public void disposeGraphics() {
        BufferStrategy bs = frame.getBufferStrategy();
        if (bs!=null)
        {
            bs.getDrawGraphics().dispose();
            bs.show();
        }
    }


    @Override
    public void addKeyListener(KeyListener keyListener) {
        frame.addKeyListener(keyListener);
    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        frame.addMouseListener(mouseListener);
    }

    @Override
    public void addMouseMotionListener(MouseMotionListener mouseMotionListener) {
        frame.addMouseMotionListener(mouseMotionListener);
    }

    @Override
    public void removeKeyListener(KeyListener keyListener) {
        frame.removeKeyListener(keyListener);
    }

    @Override
    public void removeMouseListener(MouseListener mouseListener) {
        frame.removeMouseListener(mouseListener);
    }

    @Override
    public void removeMouseMotionListener(MouseMotionListener mouseMotionListener) {
        frame.removeMouseMotionListener(mouseMotionListener);
    }


}

