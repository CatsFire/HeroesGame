import java.awt.*;
import java.util.Random;

public class Map {
    private final int width, height;
    private final TileType[][] tiles;
    public enum TileType { GRASS, WATER, MOUNTAIN, CITY, ENEMY }

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new TileType[height][width];
        generateMap();
    }

    private void generateMap() {
        Random rand = new Random();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int chance = rand.nextInt(100);
                if (chance < 70) tiles[y][x] = TileType.GRASS;
                else if (chance < 80) tiles[y][x] = TileType.WATER;
                else if (chance < 85) tiles[y][x] = TileType.MOUNTAIN;
                else if (chance < 95) tiles[y][x] = TileType.CITY;
                else tiles[y][x] = TileType.ENEMY;
            }
        }
    }

    public void draw(Graphics g) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                g.setColor(getColor(tiles[y][x]));
                g.fillRect(x * 64, y * 64, 64, 64);
            }
        }
    }

    private Color getColor(TileType type) {
        return switch (type) {
            case GRASS -> Color.GREEN;
            case WATER -> Color.BLUE;
            case MOUNTAIN -> Color.DARK_GRAY;
            case CITY -> Color.ORANGE;
            case ENEMY -> Color.RED;
        };
    }

    public TileType getTile(int x, int y) {
        return tiles[y][x];
    }
}
