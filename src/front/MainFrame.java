package front;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wtupc96 on 2017/7/18.
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        setSize(800, 600);
        setTitle("一个奇怪语言的半编译器");
        setVisible(true);
        setLayout(new BorderLayout(5, 5));
        add(new InputPanel(), BorderLayout.CENTER);
        add(new ButtonPanel(), BorderLayout.SOUTH);
        setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }

}