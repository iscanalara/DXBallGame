package live.gameEngine.dxBall;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class DxBallSimpleTileGenerator implements DxBallTileGenerator {
    private int TileSpaceing = 30;

    @Override
    public List<DxBallTile> generate(DxBallArea dxBallArea) {

        double x = dxBallArea.topLeft.getX()+ dxBallArea.width*0.05;
        double y = dxBallArea.topLeft.getY()+ dxBallArea.height*0.2;

        List<DxBallTile> tiles = new ArrayList<>();

        while(y < dxBallArea.topLeft.getY()+ dxBallArea.height*0.5)
        {
            while (x <dxBallArea.topLeft.getX() + dxBallArea.width)
            {
                DxBallTile tile = new DxBallSimpleTile(new Point2D.Double(x,y));
                tiles.add(tile);

                x += DxBallSimpleTile.TileWidth+ TileSpaceing;

            }

            x = dxBallArea.topLeft.getX()+ dxBallArea.width*0.05;
            y +=   DxBallSimpleTile.TileHeight+ TileSpaceing;
        }


        return tiles;
    }
}
