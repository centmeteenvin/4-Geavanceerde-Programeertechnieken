package J2DSpaceInvaders;

import SpaceInvaders.utilities.Input;
import SpaceInvaders.utilities.InputController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The implementation of {@link InputController}.
 */
public class J2DInputController extends InputController {


    /**
     * Default Constructor for J2DInputController.
     * @param graphicsContext {@link J2DFactory#graphicsContext}
     */
    public J2DInputController(GraphicsContext graphicsContext) {
        graphicsContext.getFrame().addKeyListener(new KeyInputAdapter());
//        shooting = Input.SHOOT;
    }

    /**
     * KeyInputAdapter class used to handle keyEvents and passing them to the {@link InputController}.
     */
    class KeyInputAdapter extends KeyAdapter {

        /**
         * Invoked when a key has been pressed.
         * See the class description for {@link KeyEvent} for a definition of
         * a key pressed event.
         *
         * @param e the event to be processed
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT -> direction = Input.LEFT;
                case KeyEvent.VK_RIGHT -> direction = Input.RIGHT;
                case KeyEvent.VK_SPACE -> shooting = Input.SHOOT;
            }
        }

        /**
         * Invoked when a key has been released.
         * See the class description for {@link KeyEvent} for a definition of
         * a key released event.
         *
         * @param e the event to be processed
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT: if (direction == Input.LEFT) direction = Input.NONE;
                case KeyEvent.VK_RIGHT: if (direction == Input.RIGHT) direction = Input.NONE;
            }
        }
    }
}
