import java.awt.*;

public class City {
    private int x, y;
    private int gold = 1000;
    private int army = 10;

    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void generateResources() {
        gold += 50;
    }

    public void trainArmy() {
        if (gold >= 100) {
            gold -= 100;
            army += 5;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x * 64, y * 64, 64, 64);
    }

    public int getGold() {
        return gold;
    }

    public int getArmy() {
        return army;
    }
}
