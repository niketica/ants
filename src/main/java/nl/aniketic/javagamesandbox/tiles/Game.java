package nl.aniketic.javagamesandbox.tiles;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Game extends JFrame {

    public static final String TITLE = "Tiles Sandbox";

    public Game() {
        initUI();
    }

    private void initUI() {
        add(new GameFrame());
        setResizable(false);
        pack();

        setTitle(TITLE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Game();
            ex.setVisible(true);
        });
    }
}
