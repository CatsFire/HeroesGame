import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class SoundGenerator {
    public static void main(String[] args) {
        generateSilentWAV("assets/sounds/battle.wav");
        generateSilentWAV("assets/sounds/win.wav");
        generateSilentWAV("assets/sounds/lose.wav");

        System.out.println("Файли звуків створено!");
    }

    private static void generateSilentWAV(String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            try (FileOutputStream out = new FileOutputStream(file)) {
                out.write(createWAVHeader(1, 44100, 16, 1)); // Порожній WAV
            }
            System.out.println("Створено: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] createWAVHeader(int channels, int sampleRate, int bitsPerSample, int seconds) {
        int dataSize = sampleRate * channels * bitsPerSample / 8 * seconds;
        ByteBuffer buffer = ByteBuffer.allocate(44);
        buffer.put("RIFF".getBytes());
        buffer.putInt(36 + dataSize);
        buffer.put("WAVE".getBytes());
        buffer.put("fmt ".getBytes());
        buffer.putInt(16);
        buffer.putShort((short) 1);
        buffer.putShort((short) channels);
        buffer.putInt(sampleRate);
        buffer.putInt(sampleRate * channels * bitsPerSample / 8);
        buffer.putShort((short) (channels * bitsPerSample / 8));
        buffer.putShort((short) bitsPerSample);
        buffer.put("data".getBytes());
        buffer.putInt(dataSize);
        return buffer.array();
    }
}
