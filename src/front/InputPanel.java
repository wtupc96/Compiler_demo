package front;

import javax.swing.*;

/**
 * Created by wtupc96 on 2017/7/18.
 */
public class InputPanel extends JPanel {
    private static final JTextArea inputArea = new JTextArea();
    private static final JTextArea textField = new JTextArea();
    private static final JScrollPane jScrollPane = new JScrollPane(inputArea);
    private static final JScrollPane jScrollPane2 = new JScrollPane(textField);

    public InputPanel() {
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        inputArea.setSize(375, 500);
        inputArea.setLineWrap(true);
        inputArea.setRows(32);
        textField.setSize(375, 500);
        textField.setLineWrap(true);
        textField.setRows(32);
        textField.setText("");
        add(jScrollPane);
        add(jScrollPane2);
    }

    public static JTextArea getTextField() {
        return textField;
    }

    public static JTextArea getInputArea() {
        return inputArea;
    }
}
