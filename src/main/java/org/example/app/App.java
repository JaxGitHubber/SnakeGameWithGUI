package org.example.app;

import org.example.snakegame.RecordScores;
import org.example.snakemenu.SnakeGameMenu;

public class App {
    public static void main(String[] args) {
        SnakeGameMenu snakeGameMenu = new SnakeGameMenu();
        snakeGameMenu.createMenu();

        /*RecordScores recordScores = new RecordScores();
        recordScores.addBestRecordOnEaseDifficult(0);
        recordScores.addBestRecordOnNormalDifficult(0);
        recordScores.addBestRecordOnHardDifficult(0);*/
    }
}

