import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game extends JPanel {
    private static final int TILE_SIZE = 64;
    private static final int MAP_WIDTH = 20, MAP_HEIGHT = 15;
    private enum TileType { GRASS, WATER, MOUNTAIN, CITY, ENEMY }

    private final TileType[][] map = new TileType[MAP_HEIGHT][MAP_WIDTH];
    private int heroX = 5, heroY = 5;
    private Image heroImg, grassImg, waterImg, mountainImg, cityImg, enemyImg;

    public Game() {
        loadImages();
        generateMap();
        setPreferredSize(new Dimension(MAP_WIDTH * TILE_SIZE, MAP_HEIGHT * TILE_SIZE));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                moveHero(e.getKeyCode());
                repaint();
            }
        });
        setFocusable(true);
    }

    private void loadImages() {
        heroImg = new ImageIcon("assets/textures/hero.png").getImage();
        grassImg = new ImageIcon("assets/textures/grass.png").getImage();
        waterImg = new ImageIcon("assets/textures/water.png").getImage();
        mountainImg = new ImageIcon("assets/textures/mountain.png").getImage();
        cityImg = new ImageIcon("assets/textures/city.png").getImage();
        enemyImg = new ImageIcon("assets/textures/enemy.png").getImage();
    }

    private void generateMap() {
        Random rand = new Random();
        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                int chance = rand.nextInt(100);
                if (chance < 70) map[y][x] = TileType.GRASS;
                else if (chance < 80) map[y][x] = TileType.WATER;
                else if (chance < 85) map[y][x] = TileType.MOUNTAIN;
                else if (chance < 95) map[y][x] = TileType.CITY;
                else map[y][x] = TileType.ENEMY;
            }
        }
    }

    private void moveHero(int keyCode) {
        int newX = heroX, newY = heroY;
        switch (keyCode) {
            case KeyEvent.VK_W -> newY = Math.max(0, heroY - 1);
            case KeyEvent.VK_S -> newY = Math.min(MAP_HEIGHT - 1, heroY + 1);
            case KeyEvent.VK_A -> newX = Math.max(0, heroX - 1);
            case KeyEvent.VK_D -> newX = Math.min(MAP_WIDTH - 1, heroX + 1);
        }

        if (map[newY][newX] != TileType.WATER) {
            heroX = newX;
            heroY = newY;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                Image img = switch (map[y][x]) {
                    case GRASS -> grassImg;
                    case WATER -> waterImg;
                    case MOUNTAIN -> mountainImg;
                    case CITY -> cityImg;
                    case ENEMY -> enemyImg;
                };
                g.drawImage(img, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
            }
        }

        g.drawImage(heroImg, heroX * TILE_SIZE, heroY * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
    }

    public void start() {
        JFrame frame = new JFrame("Герої: Меча та Магії");
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
