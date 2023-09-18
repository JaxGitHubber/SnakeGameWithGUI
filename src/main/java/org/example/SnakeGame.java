package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private boolean gameOver = false;
    private int bordWidth;
    private int bordHeight;
    private JTextField textFieldForCount;
    private int countOfScore = 0;
    private Tile snakeHead;
    private ArrayList<Tile> snakeBody;
    private Tile food;
    private static Color bodyColor = BLUE;
    private static Color borderBodyColor = BLACK;
    private final int tileSize = 25;
    private int velocityX;
    private int velocityY;
    private Random random;
    private static int speed = 100;
    private Timer gameLoop;
    SnakeGame(int boardWidth, int boardHeight) {
        setConfigurations(boardWidth, boardHeight);
        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();
        food = new Tile(10, 10);
        createScoreCount();
        velocityX = 0;
        velocityY = 0;
        gameLoop = new Timer(speed, this);
        gameLoop.start();
    }

    SnakeGame(int speed) {
        this.speed = speed;
    }

    SnakeGame(Color bodyColor, Color borderBodyColor) {
        this.bodyColor = bodyColor;
        this.borderBodyColor = borderBodyColor;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawScore(g);
        drawSnakeHead(g);
        drawSnakePart(g);
        drawFood(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        eatFood();
        lengthenSnake();
        setDefaultStateOfSnakeHead();
        endGameIfSnakePartInSnakeHead();
        endGameIfSnakeOutOfField();
        repaint();
        if(gameOver) {
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if((e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if((e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        } else if((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }
    //no use
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void setConfigurations(int boardWidth, int boardHeight) {
        this.bordWidth = boardWidth;
        this.bordHeight = boardHeight;
        setPreferredSize(new Dimension(this.bordWidth, this.bordHeight));
        setBackground(new Color(325738));
        addKeyListener(this);
        setFocusable(true);
    }

    private void createScoreCount() {
        textFieldForCount = new JTextField(3);
        add(textFieldForCount);
        locateFood();
        textFieldForCount.setText(Integer.toString(countOfScore++));
    }

    private void locateFood() {
        random = new Random();
        placeFood();
    }

    private void drawScore(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.drawString("Score:", 225, 23);
    }

    private void drawSnakeHead(Graphics g) {
        g.setColor(bodyColor);
        g.fillRect(snakeHead.x  * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
        g.setColor(BLACK);
        g.fillRect(snakeHead.x  * tileSize+5, snakeHead.y * tileSize+5, 15, 15);
        g.setColor(Color.WHITE);
        g.fillRect(snakeHead.x  * tileSize+7, snakeHead.y * tileSize+7, 10, 10);
        g.setColor(Color.CYAN);
        g.fillRect(snakeHead.x  * tileSize+13, snakeHead.y * tileSize+9, 5, 5);
        g.setColor(borderBodyColor);
        g.fillRect(snakeHead.x  * tileSize, snakeHead.y * tileSize, 3, 25);
        g.fillRect(snakeHead.x  * tileSize, snakeHead.y * tileSize, 25, 3);
        g.fillRect(snakeHead.x  * tileSize, snakeHead.y * tileSize+22, 25, 3);
        g.fillRect(snakeHead.x  * tileSize+22, snakeHead.y * tileSize, 3, 25);
    }

    private void drawSnakePart(Graphics g) {
        for(Tile snakePart : snakeBody) {
            g.setColor(bodyColor);
            g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize);
            g.setColor(borderBodyColor);
            g.fillRect(snakePart.x  * tileSize, snakePart.y * tileSize, 3, 25);
            g.fillRect(snakePart.x  * tileSize, snakePart.y * tileSize, 25, 3);
            g.fillRect(snakePart.x  * tileSize, snakePart.y * tileSize+22, 25, 3);
            g.fillRect(snakePart.x  * tileSize+22, snakePart.y * tileSize, 3, 25);
        }
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(food.x  * tileSize, food.y * tileSize, tileSize, tileSize);
        g.setColor(new Color(325738));
        g.fillRect(food.x  * tileSize, food.y * tileSize, 4, 4);
        g.fillRect(food.x  * tileSize, food.y * tileSize+21, 4, 4);
        g.fillRect(food.x  * tileSize+21, food.y * tileSize+21, 4, 4);
        g.fillRect(food.x  * tileSize+21, food.y * tileSize, 4, 4);
        g.setColor(BLACK);
        g.fillRect(food.x  * tileSize+12, food.y * tileSize-4, 4, 10);
    }

    private void drawBorders(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for(int i = 0; i < bordWidth/tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, bordHeight);
            g.drawLine(0, i * tileSize, bordWidth, i * tileSize);
        }
    }

    private void eatFood() {
        if(collision(snakeHead, food)) {
            placeFood();
            snakeBody.add(new Tile(food.x, food.y));
            textFieldForCount.setText(Integer.toString(countOfScore++));
        }
    }

    private boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    private void placeFood() {
        food.x = random.nextInt(bordWidth/tileSize);
        food.y = random.nextInt(bordHeight/tileSize);
        for(Tile snakePart : snakeBody) {
            if(collision(snakePart, food)) {
                placeFood();
            }
        }
    }

    private void lengthenSnake() {
        for(int i = snakeBody.size()-1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if(i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else {
                Tile prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }
    }

    private void setDefaultStateOfSnakeHead() {
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
    }

    private void endGameIfSnakePartInSnakeHead() {
        for(Tile snakePart : snakeBody) {
            if(collision(snakePart, snakeHead)) {
                gameOver = true;
                break;
            }
        }
    }

    private void endGameIfSnakeOutOfField() {
        if(snakeHead.x >= bordWidth/tileSize || snakeHead.x < 0 || snakeHead.y >= bordHeight/tileSize || snakeHead.y < 0) {
            gameOver = true;
        }
    }
}