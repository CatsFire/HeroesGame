import javax.swing.*;
import java.util.Random;

public class Battle {
    private int heroArmy;
    private int enemyArmy;
    private int heroHealth;
    private int enemyHealth;

    public Battle(int heroArmy, int enemyArmy) {
        this.heroArmy = heroArmy;
        this.enemyArmy = enemyArmy;
        this.heroHealth = heroArmy * 10;
        this.enemyHealth = enemyArmy * 10;
    }

    public void startBattle() {
        Random rand = new Random();
        while (heroHealth > 0 && enemyHealth > 0) {
            int heroDamage = rand.nextInt(20) + 10;
            int enemyDamage = rand.nextInt(20) + 10;
            enemyHealth -= heroDamage;
            heroHealth -= enemyDamage;
        }

        if (heroHealth > 0) {
            JOptionPane.showMessageDialog(null, "Ви перемогли ворога!");
        } else {
            JOptionPane.showMessageDialog(null, "Вас перемогли! Гра закінчена.");
            System.exit(0);
        }
    }
}
