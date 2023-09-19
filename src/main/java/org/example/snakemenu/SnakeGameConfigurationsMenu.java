package org.example.snakemenu;

import org.example.snakegame.Difficult;
import org.example.snakegame.RecordScores;
import org.example.snakegame.SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SnakeGameConfigurationsMenu {
    public void createConfigurationsMenu() {
        JFrame configurationsMenu = new JFrame("Snake game -configurations");
        configurationsMenu.setVisible(true);
        configurationsMenu.setSize(250, 400);
        configurationsMenu.setLocationRelativeTo(null);
        configurationsMenu.setResizable(false);
        configurationsMenu.setBackground(Color.DARK_GRAY);
        JPanel menuButtons = new JPanel(new GridLayout(8, 1));
        menuButtons.setBackground(Color.WHITE);
        configurationsMenu.add(menuButtons);
        addDifficultConfigurations(menuButtons);
        addSkinsConfigurations(menuButtons);
    }

    private static void addDifficultConfigurations(JPanel menuButtons) {
        JLabel setDifficult = new JLabel("> set difficult <");
        JButton difficultEase = new JButton(new DifficultGameAction());
        difficultEase.setText("ease");
        JButton difficultNormal = new JButton(new DifficultGameAction());
        difficultNormal.setText("normal");
        JButton difficultHard = new JButton(new DifficultGameAction());
        difficultHard.setText("hard");
        menuButtons.add(setDifficult);
        menuButtons.add(difficultEase);
        menuButtons.add(difficultNormal);
        menuButtons.add(difficultHard);
    }

    private static void addSkinsConfigurations(JPanel menuButtons) {
        JLabel setSkin = new JLabel("> set skin <");
        JButton skinBlueSnake = new JButton(new SkinSetterAction());
        skinBlueSnake.setText("blue snake");
        JButton skinPoisonCaterpillar = new JButton(new SkinSetterAction());
        skinPoisonCaterpillar.setText("poison caterpillar");
        JButton skinWorm = new JButton(new SkinSetterAction());
        skinWorm.setText("worm");
        menuButtons.add(setSkin);
        menuButtons.add(skinBlueSnake);
        menuButtons.add(skinPoisonCaterpillar);
        menuButtons.add(skinWorm);
    }

    static class DifficultGameAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            String difficult = ((JButton)e.getSource()).getText();
            SnakeGame snakeGame;
            RecordScores recordScores = new RecordScores();
            switch(difficult) {
                case "ease" -> {snakeGame = new SnakeGame(140);
                                SnakeGame.difficult = Difficult.EASE;}
                case "normal" -> {snakeGame = new SnakeGame(100);
                                  SnakeGame.difficult = Difficult.NORMAL;}
                case "hard" -> {snakeGame = new SnakeGame(50);
                                SnakeGame.difficult = Difficult.HARD;}
                default -> {}
            }
        }
    }

    static class SkinSetterAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            String difficult = ((JButton)e.getSource()).getText();
            SnakeGame snakeGame;
            switch(difficult) {
                case "blue snake" -> snakeGame = new SnakeGame(Color.BLUE, Color.BLACK);
                case "poison caterpillar" -> snakeGame = new SnakeGame(Color.ORANGE, Color.RED);
                case "worm" -> snakeGame = new SnakeGame(Color.PINK, Color.DARK_GRAY);
                default -> {}
            }
        }
    }
}
