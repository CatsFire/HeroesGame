import java.awt.*;

public class Hero {
    private int x, y;
    private final Map map;

    public Hero(int startX, int startY, Map map) {
        this.x = startX;
        this.y = startY;
        this.map = map;
    }

    public void move(int keyCode) {
        int newX = x, newY = y;
        switch (keyCode) {
            case 87 -> newY = Math.max(0, y - 1); // W
            case 83 -> newY = Math.min(map.tiles.length - 1, y + 1); // S
            case 65 -> newX = Math.max(0, x - 1); // A
            case 68 -> newX = Math.min(map.tiles[0].length - 1, x + 1); // D
        }

        if (map.getTile(newX, newY) != Map.TileType.WATER) {
            x = newX;
            y = newY;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x * 64 + 16, y * 64 + 16, 32, 32);
    }
}
