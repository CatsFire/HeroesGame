import java.util.Random;

public class AI {
    private int enemyX, enemyY;
    private final Map map;
    private final Hero hero;

    public AI(Map map, Hero hero) {
        this.map = map;
        this.hero = hero;
        spawnEnemy();
    }

    private void spawnEnemy() {
        Random rand = new Random();
        do {
            enemyX = rand.nextInt(map.tiles[0].length);
            enemyY = rand.nextInt(map.tiles.length);
        } while (map.getTile(enemyX, enemyY) == Map.TileType.WATER);
    }

    public void moveEnemy() {
        Random rand = new Random();
        int moveDirection = rand.nextInt(4);

        switch (moveDirection) {
            case 0 -> enemyY = Math.max(0, enemyY - 1); // Вгору
            case 1 -> enemyY = Math.min(map.tiles.length - 1, enemyY + 1); // Вниз
            case 2 -> enemyX = Math.max(0, enemyX - 1); // Вліво
            case 3 -> enemyX = Math.min(map.tiles[0].length - 1, enemyX + 1); // Вправо
        }

        if (enemyX == hero.getX() && enemyY == hero.getY()) {
            Battle battle = new Battle(hero.getArmy(), 20);
            battle.startBattle();
        }
    }

    public int getEnemyX() {
        return enemyX;
    }

    public int getEnemyY() {
        return enemyY;
    }
}
