import java.io.*;

public class GameData {
    private int score;
    // 其他游戏进度数据...

    public GameData(int score) {
        this.score = score;
        // 其他游戏进度数据的初始化...
    }

    // 将游戏数据保存到文件
    public void saveToFile(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(this);
            System.out.println("游戏进度已保存到文件。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

