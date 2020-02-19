package com.cardrandomizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AppFrame implements ActionListener {

    final int DECK_SIZE = 52;

    private JFrame frame;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton button;
    private ImageIcon imageIcon;
    private Image image;
    private Image modifiedImage;
    private JLabel label;
    private GridLayout gridLayout;

    private Card[] deck = new Card[DECK_SIZE];

    public AppFrame() {
        gui();
    }

    private void gui() {
        initDeck();

        frame = new JFrame("Card Randomizer");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(10, 108, 3));

        gridLayout = new GridLayout(4, 13, 5, 10);
        topPanel.setLayout(gridLayout);

        bottomPanel = new JPanel(new BorderLayout());

        button = new JButton("Shuffle");
        button.setPreferredSize(new Dimension(0, 50));
        button.addActionListener(this);

        bottomPanel.add(button);
        frame.add(topPanel);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        displayDeck();

        frame.setVisible(true);
    }

    private void initDeck() {
        int suiteCount = 0;
        for(int currentCard = 0; currentCard < DECK_SIZE; currentCard++) { // Initialize all SPADES
            suiteCount = (currentCard % 13) + 1;
            if(currentCard <= 12) {
                deck[currentCard] = new Card(suiteCount, "S"); // Spades
            } else if(currentCard > 12 && currentCard <= 25) {
                deck[currentCard] = new Card(suiteCount, "H"); // Hearts
            } else if(currentCard > 25 && currentCard <= 38) {
                deck[currentCard] = new Card(suiteCount, "C"); // Clubs
            } else {
                deck[currentCard] = new Card(suiteCount, "D"); // Diamonds
            }
        }
    }

    private void displayDeck() {
        int newWidth = (int) (691 * .15);
        int newHeight = (int) (1056 * .15);

        for(int currentCard = 0; currentCard < DECK_SIZE; currentCard++) {
            imageIcon = new ImageIcon(this.getClass().getResource(deck[currentCard].getImagePath()));
            image = imageIcon.getImage();
            modifiedImage = image.getScaledInstance(newWidth, newHeight, 0);
            imageIcon = new ImageIcon(modifiedImage);
            label = new JLabel(imageIcon);
            topPanel.add(label);
        }
    }

    private void testDeck() {
        for(int currentCard = 0; currentCard < DECK_SIZE; currentCard++) {
            System.out.println("Face: " + deck[currentCard].getFace());
            System.out.println("Suite: " + deck[currentCard].getSuite());
            System.out.println();
        }
    }

    private void shuffleDeck() {
        Random rand = new Random();
        for(int currentCard = 0; currentCard < DECK_SIZE; currentCard++) {
            // Random from remaining positions
            int r = currentCard + rand.nextInt(DECK_SIZE - currentCard);

            // Temp variables for a card object
            int tempFace = deck[r].getFace();
            String tempSuite = deck[r].getSuite();

            deck[r].setFace(deck[currentCard].getFace());
            deck[r].setSuite(deck[currentCard].getSuite());
            deck[currentCard].setFace(tempFace);
            deck[currentCard].setSuite(tempSuite);

            deck[r].regenerateImagePath();
            deck[currentCard].regenerateImagePath();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            topPanel.removeAll();

            shuffleDeck();
            displayDeck();

            topPanel.revalidate();
        }
    }
}
