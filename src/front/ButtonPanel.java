package front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by wtupc96 on 2017/7/18.
 */
public class ButtonPanel extends JPanel {
    private static Button readFromFile = new Button("Read from file");
    private static Button lexicalButton = new Button("Lexical Analysis");
    private static Button grammarButton = new Button("Grammar Analysis");
    private static Button semanticButton = new Button("Semantic Analysis");
    private static JTextArea area;
    private static JFileChooser jFileChooser = new JFileChooser();
    private static String path = "";

    public ButtonPanel() {
        area = InputPanel.getInputArea();
        setLayout(new GridLayout(1, 3, 5, 5));
        readFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = jFileChooser.showOpenDialog(ButtonPanel.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    path = jFileChooser.getSelectedFile().getPath();
                    try {
                        File file = new File(path);
                        FileReader fileReader = new FileReader(file);
                        char[] chars = new char[1024000];
                        fileReader.read(chars);
                        area.setText("");
                        area.append(String.valueOf(chars));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        add(readFromFile);
        add(lexicalButton);
        add(grammarButton);
        add(semanticButton);
    }
}
