package live.gameEngine.dxBall;

import live.util.GUIContext;

import java.util.ArrayList;
import java.util.List;

public class DxBallTileSet {
    List<DxBallTile> tileList;

    public DxBallTileSet( List<DxBallTile> tileList) {
        this.tileList = tileList ;
    }

    public void init(DxBallArea container) {
        for ( DxBallTile tile: tileList
             ) {
            tile.init(container);
        }
    }

    public void update(DxBallArea dxBallArea, GUIContext container, int time) {
        for ( DxBallTile tile: tileList
        ) {
            if (tile.isAlive)
                tile.update(dxBallArea,container,time);
        }
    }

    public void render(GUIContext container) {
        for ( DxBallTile tile: tileList
        ) {
            if (tile.isAlive)
                tile.render(container);
        }
    }
}
