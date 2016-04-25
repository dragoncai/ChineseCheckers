package dragoncai.chinese.checkers.gui;

import org.junit.Test;
import sun.awt.WindowClosingListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GuiCheckerBoardTest {
    @Test
    public void name() throws Exception {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("TestGui");
        jFrame.setSize(new Dimension(600, 400));
        jFrame.setLocationRelativeTo(null);
        GuiCheckerBoard contentPane = new GuiCheckerBoard();
        contentPane.add(new JButton("Test0"));
        jFrame.setContentPane(contentPane);

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        jFrame.setVisible(true);
        while (true);
    }
}