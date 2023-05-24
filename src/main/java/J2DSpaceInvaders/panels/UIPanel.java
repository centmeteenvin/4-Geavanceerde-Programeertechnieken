package J2DSpaceInvaders.panels;

import javax.swing.*;
import java.awt.*;

/**
 * The panel containing all UI element. Is displayed on top of {@link GamePanel}
 */
public class UIPanel extends JPanel {

    /**
     * The default constructor
     * @param layout The layout manager
     * @param isDoubleBuffered is double buffered.
     */
    public UIPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        setOpaque(false);
        setSize(200, 200);
        setBackground(Color.BLUE);
        System.out.println(isOpaque());
        JButton button = new JButton("Test");
//        button.setPreferredSize(new Dimension(10, 10));
        add(button, BorderLayout.CENTER);

    }
}
