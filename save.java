import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFileGenerator {
    public static void main(String[] args) {
        createSaveFile("assets/data/save.dat");
    }

    private static void createSaveFile(String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            writer.write("heroX=5\n");
            writer.write("heroY=5\n");
            writer.write("gold=1000\n");
            writer.write("army=50\n");
            writer.close();
            System.out.println("Файл збереження створено: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
