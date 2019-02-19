import java.util.ArrayList;
import java.util.List;

public class Cron {
    public static void main(String[] args) {
        List<String> fullPhrase = new ArrayList<>();
        CornGUI gui = new CornGUI(fullPhrase);
        gui.setVisible(true);
    }
}