import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class TextureGenerator {
    public static void main(String[] args) {
        generateTexture("assets/textures/grass.png", Color.GREEN);
        generateTexture("assets/textures/water.png", Color.BLUE);
        generateTexture("assets/textures/mountain.png", Color.DARK_GRAY);
        generateTexture("assets/textures/city.png", Color.ORANGE);
        generateTexture("assets/textures/enemy.png", Color.RED);
        generateTexture("assets/textures/hero.png", Color.YELLOW);

        System.out.println("Текстури створено!");
    }

    private static void generateTexture(String filePath, Color color) {
        try {
            BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();
            g.setColor(color);
            g.fillRect(0, 0, 64, 64);
            g.dispose();
            File file = new File(filePath);
            file.getParentFile().mkdirs(); 
            ImageIO.write(image, "png", file);
            System.out.println("Створено: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
