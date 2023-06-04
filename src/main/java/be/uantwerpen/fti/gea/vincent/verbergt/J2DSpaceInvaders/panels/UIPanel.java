package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.panels;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Props;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Constants;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.GameState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The panel containing all UI element. Is displayed on top of {@link GamePanel}
 */
public class UIPanel extends JPanel {

    /**
     * The GraphicsContext we are working in.
     */
    private final GraphicsContext graphicsContext;

    /**
     * Shorthand for {@link GraphicsContext#getProps()}
     */
    private final Props props;

    /**
     * The main selector panel.
     */

    private final JPanel mainMenu = new JPanel(null, false);

    /**
     * The title for the {@link #mainMenu} panel.
     */
    private final JLabel title = new JLabel("", SwingConstants.CENTER);

    /**
     * The start button for the game.
     * This calls {@link GraphicsContext#start()}.
     */
    private final JButton startButton = new JButton("Start");

    /**
     * The quit button for the game.
     * This calls {@link GraphicsContext#end()}.
     */
    private final JButton quitButton = new JButton("Quit");

    /**
     * The button that opens the {@link #levelSelector}
     */
    private final JButton levelSelectorButton = new JButton("Select Level");



    /**
     * The level selector panel
     */
    private final JPanel levelSelector = new JPanel(null, false);

    /**
     * The title of the {@link #levelSelector} panel.
     */
    private final JLabel levelSelectorTitle = new JLabel("Select Level", SwingConstants.CENTER);

    /**
     * Return from {@link #levelSelector} to {@link #mainMenu}.
     */
    private final JButton levelSelectorReturnButton = new JButton();

    /**
     * An array containing all the levelSelect buttons.
     */
    private final JButton[] selectLevelButtons = new JButton[Constants.NUMBER_OF_LEVELS];


    /**
     * The default constructor
     *
     * @param layout           The layout manager
     * @param isDoubleBuffered is double buffered.
     * @param graphicsContext The {@link GraphicsContext} we are working in.
     */
    public UIPanel(LayoutManager layout, boolean isDoubleBuffered, GraphicsContext graphicsContext) {
        super(layout, isDoubleBuffered);
        this.graphicsContext = graphicsContext;
        this.props = graphicsContext.getProps();
        setOpaque(false);

        //Main Menu panel
        mainMenu.setOpaque(false);

        mainMenu.add(startButton);
        startButton.addActionListener(e -> {
            setVisible(false);
            graphicsContext.start();
        });

        mainMenu.add(quitButton);
        quitButton.addActionListener(e -> {
            graphicsContext.end();
        });

        mainMenu.add(levelSelectorButton);
        levelSelectorButton.addActionListener(e -> {
            mainMenu.setVisible(false);
            levelSelector.setVisible(true);
        });

        mainMenu.add(title);

        add(mainMenu);
        mainMenu.setVisible(true);

        // Level Selector panel
        levelSelector.setOpaque(false);

        levelSelector.add(levelSelectorTitle);

        levelSelector.add(levelSelectorReturnButton);
        levelSelectorReturnButton.addActionListener(e -> {
            mainMenu.setVisible(true);
            levelSelector.setVisible(false);
        });

        add(levelSelector);
        levelSelector.setVisible(false);

    }

    /**
     * Initialize {@link #getSize()} and its subcomponents' sizes after the frame size is set.
     */
    public void initialize() {
        setSize(graphicsContext.getFrame().getSize());

        //Main menu
        mainMenu.setSize(getSize());

        startButton.setSize(300, 100);
        startButton.setLocation(getWidth() / 2 - startButton.getWidth()/2, getHeight() / 2 - 150);
        startButton.setFont(new Font("Helvetica", Font.BOLD, 30));

        levelSelectorButton.setSize(startButton.getSize());
        levelSelectorButton.setLocation(getWidth() / 2 - levelSelectorButton.getWidth()/2, getHeight() / 2  + 20);
        levelSelectorButton.setFont(new Font("Helvetica", Font.BOLD, 30));

        title.setVisible(true);
        title.setFont(new Font("Helvetica", Font.BOLD, 80));
        title.setText(props.title);
        title.setForeground(Color.WHITE);
        title.setSize(getWidth(), 100);
        title.setLocation(getWidth()/2 - title.getWidth()/2, 100);

        quitButton.setSize(startButton.getSize());
        quitButton.setLocation(getWidth() / 2 - quitButton.getWidth()/2, getHeight() / 2  + 190);
        quitButton.setFont(new Font("Helvetica", Font.BOLD, 30));

        //Level selector
        levelSelector.setSize(getSize());

        levelSelectorTitle.setVisible(true);
        levelSelectorTitle.setFont(new Font("Helvetica", Font.BOLD, 80));
        levelSelectorTitle.setForeground(Color.WHITE);
        levelSelectorTitle.setSize(getWidth(), 100);
        levelSelectorTitle.setLocation(getWidth()/2 - title.getWidth()/2, 100);

        levelSelectorReturnButton.setSize(100, 100);
        levelSelectorReturnButton.setLocation(getWidth() - levelSelectorReturnButton.getWidth() - 50, getHeight() - levelSelectorReturnButton.getHeight() - 70);
        ImageIcon returnIcon = new ImageIcon(props.returnIcon);
        Image returnIconImage = returnIcon.getImage();
        returnIcon = new ImageIcon(returnIconImage.getScaledInstance(levelSelectorReturnButton.getWidth(), levelSelectorReturnButton.getHeight(), Image.SCALE_DEFAULT));
        levelSelectorReturnButton.setIcon(returnIcon);

        int buttonSize = 100;
        int buttonMargin= 50;
        int levels = Constants.NUMBER_OF_LEVELS;
        int maxButtonsPerRow = 5;
        int numberOfRows; int numberOfColumns;
        int x; int y;
        int currentLevelNumber = 1;
        y = levelSelectorTitle.getLocation().y + levelSelectorTitle.getHeight() +buttonMargin;
        if (levels <= maxButtonsPerRow) {
            numberOfRows = 1;
        } else {
            numberOfRows = (int) Math.ceil(1.0*levels/maxButtonsPerRow);
        }
        for (int row = 0; row < numberOfRows; row++) {
            if (levels < maxButtonsPerRow) {
                numberOfColumns = levels;
            } else if ((row + 1)*maxButtonsPerRow < levels) {
                numberOfColumns = maxButtonsPerRow;
            } else {
                numberOfColumns = levels - row*maxButtonsPerRow;
            }
            x = getWidth()/2 - ((numberOfColumns*buttonSize) + (numberOfColumns - 1) * buttonMargin)/2;
            for(int col = 0; col < numberOfColumns; col++) {
                JButton selectLevelButton = new JButton(String.valueOf(currentLevelNumber));
                selectLevelButton.setSize(buttonSize, buttonSize);
                selectLevelButton.setLocation(x, y);
                selectLevelButton.setFont(new Font("Helvetica", Font.BOLD, 20));
                final int currentLevel = Integer.valueOf(currentLevelNumber);
                selectLevelButton.addActionListener(e -> {
                    GameState gameState = graphicsContext.getGameState();
                    gameState.setCurrentLevel(currentLevel);
                    gameState.setPlaying(true);
                    levelSelector.setVisible(false);
                    mainMenu.setVisible(true);
                    setVisible(false);
                });
                selectLevelButtons[currentLevelNumber - 1] = selectLevelButton;
                levelSelector.add(selectLevelButton);
                x += buttonSize + buttonMargin;
                currentLevelNumber++;
            }
            y += buttonSize + buttonMargin;
        }

    }

}
