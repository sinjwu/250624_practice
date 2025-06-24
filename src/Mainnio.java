import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;


public class Mainnio {
    public static void writeFile(String filename, String content) {
        Path filePath = Paths.get(filename);
        try (FileChannel writeChannel = FileChannel.open(filePath,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.APPEND)) { //try 괄호 안은 finally와 동일한 역할
            ByteBuffer bffr = ByteBuffer.allocate(1024);
            bffr.put(content.getBytes());
            bffr.flip();
            writeChannel.write(bffr);
            System.out.println("파일을 성공적으로 작성 완료했습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readFile(String filename) {
        Path filePath = Paths.get(filename);
        try (FileChannel readChannel = FileChannel.open(filePath, StandardOpenOption.READ)) {
            ByteBuffer bffr = ByteBuffer.allocate(1024);
            int bytesRead = readChannel.read(bffr);
            while (bytesRead != -1) {
                bffr.flip();
                String chunk = StandardCharsets.UTF_8.decode(bffr).toString();
                System.out.println(chunk);
                bffr.clear();
                bytesRead = readChannel.read(bffr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        writeFile("dinner_menu.txt", "오늘 저녁은 뭐 먹지?");
    }
}
