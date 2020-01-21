package live.gameEngine;

import live.util.GUIContext;

public interface Game {
    void init(GUIContext container);

    void update(GUIContext container, int time);

    void render(GUIContext container);

    String getTitle();
}
