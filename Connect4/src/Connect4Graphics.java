import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.*;

public class Connect4Graphics {

    ConnectFourGame game;
    JFrame frame = new JFrame("Connect 4!");
    JPanel board;
    JDialog GAMEOVER = new JDialog();
    JButton RESTART = new JButton("Play Again?");
    ArrayList<Chip> holes = new ArrayList<>(42);

    private class Chip extends JPanel {
        public Color color;

        public Chip(Color color) {
            super();
            this.color = color;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            g.fillOval(4, 4, 92, 88);
        }
    }

    public Connect4Graphics(ConnectFourGame game) {
        this.game = game;
        initComponents();
    }
    

    private void initComponents() {
        // creates main window for game
        frame.setSize(new Dimension(700, 600));
        frame.setResizable(false);

        // makes the "close" button simply exit out of the application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // centers the frame on screen
        frame.setLocationRelativeTo(null);

        frame.setBackground(Color.BLUE);

        RESTART.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Connect4.game = new ConnectFourGame();
                Connect4.graphics = new Connect4Graphics(Connect4.game);
                GAMEOVER.setVisible(false);
            }
        });

        board = new JPanel(new GridLayout(6, 7));
        board.setBackground(Color.BLUE);

        for (int i = 0; i < 42; i++) {
            final int position = i;
            Chip hole = new Chip(Color.WHITE);
            hole.addMouseListener(new MouseListener() {

                int col = position % 7;

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (!game.isWon) {
                        game.makeMove(col);
                        updateGraphics();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                //     if (!game.isWon) {
                //         game.makeMove(col);
                //         updateGraphics();

                //         // int playerMove = game.playerMove;
                //         // game.makeMove(col);
                //         // int moveLocation = game.lastMove[0] * 7 + game.lastMove[1];
                //         // JPanel moveMade = (JPanel) board.getComponent(moveLocation);
                //         // Graphics moveGraphics = moveMade.getGraphics();
                //         // if (playerMove == 1) {
                //         //     moveGraphics.setColor(Color.YELLOW);
                //         // } else {
                //         //     moveGraphics.setColor(Color.RED);
                //         // }
                //         // moveGraphics.fillOval(2, 2, 96, 92);
                //         // if (game.isWon) {
                //         //     gameover();
                //         // }
                //         // System.out.println(game);
                //     }
                }
            });
            hole.setMinimumSize(new Dimension(100, 100));
            hole.setBackground(Color.BLUE);
            hole.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

            board.add(hole);
            holes.add(hole);
        }

        JLabel gameName = new JLabel("Connect 4!", JLabel.CENTER);
        gameName.setFont(new Font("Serif", Font.BOLD, 50));
        gameName.setBackground(Color.WHITE);
        JPanel gameNamePanel = new JPanel();
        gameNamePanel.setPreferredSize(new Dimension(100, 50));
        gameNamePanel.setBackground(Color.BLUE);
        gameNamePanel.add(gameName);

        JLabel myName = new JLabel("Made by Dennis Kats", JLabel.RIGHT);
        myName.setBackground(Color.WHITE);
        JPanel myNamePanel = new JPanel();
        myNamePanel.setPreferredSize(new Dimension(100, 12));
        myNamePanel.setBackground(Color.BLUE);
        myNamePanel.add(myName);


        //JPanel format = new JPanel();
        //format.setLayout(new BoxLayout(format, BoxLayout.Y_AXIS));
        //format.setBackground(Color.BLUE);
        //format.add(gameNamePanel);
        //format.add(board);
        //format.add(myNamePanel);

        frame.getContentPane().add(board);
        frame.setVisible(true);
    }
    
    public void gameover() {
        int winner = game.changePlayer();
        String message = "GAMEOVER: Player " + winner + " wins!";
        JLabel label = new JLabel(message, JLabel.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 50));
        JPanel screen = new JPanel(new GridLayout(2, 1));
        screen.add(label);
        screen.add(RESTART);
        GAMEOVER.setLocationRelativeTo(frame);
        GAMEOVER.add(screen);
        GAMEOVER.setSize(new Dimension(700, 120));
        GAMEOVER.setResizable(false);
        GAMEOVER.setVisible(true);
    }
    
    public void updateGraphics() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                int playerInHole = game.board[row][col];
                int gridIndex = row * 7 + col;
                Chip holeGraphics = holes.get(gridIndex);
                if (playerInHole == 0) {
                    holeGraphics.color = Color.WHITE;
                } else if (playerInHole == 1) {
                    holeGraphics.color = Color.RED;
                } else {
                    holeGraphics.color = Color.YELLOW;
                }
                holeGraphics.repaint();
            }
        }
        if (game.isWon) {
            gameover();
        }
        System.out.println(game);
    }

}