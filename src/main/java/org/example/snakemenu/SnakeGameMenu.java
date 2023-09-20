package org.example.snakemenu;

import org.example.snakegame.SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SnakeGameMenu {
    public static int borderWidth = 600;
    public static int borderHeight = 600;

    public void createMenu() {
        JFrame menu = new JFrame("Snake game - menu");
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(250, 300);
        menu.setLocationRelativeTo(null);
        menu.setResizable(false);
        menu.setBackground(Color.DARK_GRAY);
        JPanel menuButtons = new JPanel(new GridLayout(3, 1));
        addButtons(menuButtons);
        menuButtons.setBackground(Color.BLACK);
        menu.add(menuButtons);
    }

    private void addButtons(JPanel menuButtons) {
        JButton startGame = new JButton(new StartSnakeGameAction());
        startGame.setText("> start game <");
        menuButtons.add(startGame);
        JButton configurations = new JButton(new GameConfigurationsAction());
        configurations.setText("> configurations <");
        menuButtons.add(configurations);
        JButton exit = new JButton(new ExitAction());
        exit.setText("> exit <");
        menuButtons.add(exit);
    }

    private static void startSnakeGame() {
    JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(borderWidth,borderHeight);
        frame.setBackground(Color.GREEN);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        SnakeGame snakeGame = new SnakeGame(borderWidth, borderHeight);
        frame.add(snakeGame);
        frame.pack();
        snakeGame.requestFocus();
    }

    static class StartSnakeGameAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            startSnakeGame();
        }
    }

    static class GameConfigurationsAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            SnakeGameConfigurationsMenu snakeGameConfigurationsMenu = new SnakeGameConfigurationsMenu();
            snakeGameConfigurationsMenu.createConfigurationsMenu();
        }
    }

    static class ExitAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
