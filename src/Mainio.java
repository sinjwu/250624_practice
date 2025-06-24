import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Mainio {
    public static void writefile(String filename, String content) {
        try(FileWriter wrtr = new FileWriter(filename, true)) {
            wrtr.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(String filename) {
        try (BufferedReader rdr = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = rdr.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        writefile("lunch_menu.txt", "\n 국밥");
        System.out.println("파일 생성 완료");
    }
}