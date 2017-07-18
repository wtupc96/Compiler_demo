package front;

import javax.swing.*;

/**
 * Created by wtupc96 on 2017/7/18.
 */
public class InputPanel extends JPanel {
    private static final JTextArea inputArea = new JTextArea();
    private static final JScrollPane jScrollPane = new JScrollPane(inputArea);

    public InputPanel() {
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        inputArea.setSize(750, 500);
        inputArea.setLineWrap(true);
        inputArea.setRows(30);

        inputArea.setText("");
        add(jScrollPane);
    }

    public static JTextArea getInputArea() {
        return inputArea;
    }
}
