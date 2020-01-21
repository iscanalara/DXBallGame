package live.util;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface GUIContext {
    void init();

    Graphics getGraphics();   // Gets the graphics object to render anything
    void disposeGraphics();   // Posts rendering request to the graphical output device

    void setTitle(String title);

    int getWidth();
    int getHeight();

    void addKeyListener(KeyListener keyListener);         // Enables listening Key events
    void addMouseListener(MouseListener mouseListener);     // Enables listening Mouse events
    void addMouseMotionListener(MouseMotionListener mouseMotionListener); // Enables listening Mouse events

    void removeKeyListener(KeyListener keyListener);
    void removeMouseListener(MouseListener mouseListener);
    void removeMouseMotionListener(MouseMotionListener mouseMotionListener);

}
