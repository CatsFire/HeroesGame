import java.io.*;

public class SaveLoad {
    public static void saveGame(Hero hero, City city) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("assets/data/save.dat"))) {
            out.writeObject(hero);
            out.writeObject(city);
            System.out.println("Гру збережено.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadGame(Hero hero, City city) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("assets/data/save.dat"))) {
            Hero loadedHero = (Hero) in.readObject();
            City loadedCity = (City) in.readObject();

            hero.setX(loadedHero.getX());
            hero.setY(loadedHero.getY());
            city.setGold(loadedCity.getGold());
            city.setArmy(loadedCity.getArmy());

            System.out.println("Гру завантажено.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
