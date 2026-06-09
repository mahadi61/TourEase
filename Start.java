import javax.swing.SwingUtilities;
import frames.LoginFrame;

public class Start {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}