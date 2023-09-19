package org.example.snakegame;

import java.io.*;

public class RecordScores implements Serializable {
    public int bestRecord;
    transient private RecordScores recordScores;

    public RecordScores() {

    }

    public int getBestRecordOnEaseDifficult() {
        try(FileInputStream readRecord = new FileInputStream("./src/main/resources/bestRecordOnEaseDifficult.txt");
            ObjectInputStream readObject = new ObjectInputStream(readRecord)) {
            recordScores = (RecordScores) readObject.readObject();
            return recordScores.bestRecord;
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void addBestRecordOnEaseDifficult(int countOfScore) {
        try(FileOutputStream writeRecord = new FileOutputStream("./src/main/resources/bestRecordOnEaseDifficult.txt");
            ObjectOutputStream writeObject = new ObjectOutputStream(writeRecord)) {
            recordScores = new RecordScores();
            recordScores.bestRecord = countOfScore;
            writeObject.writeObject(recordScores);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public int getBestRecordOnNormalDifficult() {
        try(FileInputStream readRecord = new FileInputStream("./src/main/resources/bestRecordOnNormalDifficult.txt");
            ObjectInputStream readObject = new ObjectInputStream(readRecord)) {
            recordScores = (RecordScores) readObject.readObject();
            return recordScores.bestRecord;
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void addBestRecordOnNormalDifficult(int countOfScore) {
        try(FileOutputStream writeRecord = new FileOutputStream("./src/main/resources/bestRecordOnNormalDifficult.txt");
            ObjectOutputStream writeObject = new ObjectOutputStream(writeRecord)) {
            recordScores = new RecordScores();
            recordScores.bestRecord = countOfScore;
            writeObject.writeObject(recordScores);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public int getBestRecordOnHardDifficult() {
        try(FileInputStream readRecord = new FileInputStream("./src/main/resources/bestRecordOnHardDifficult.txt");
            ObjectInputStream readObject = new ObjectInputStream(readRecord)) {
            recordScores = (RecordScores) readObject.readObject();
            return recordScores.bestRecord;
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void addBestRecordOnHardDifficult(int countOfScore) {
        try(FileOutputStream writeRecord = new FileOutputStream("./src/main/resources/bestRecordOnHardDifficult.txt");
            ObjectOutputStream writeObject = new ObjectOutputStream(writeRecord)) {
            recordScores = new RecordScores();
            recordScores.bestRecord = countOfScore;
            writeObject.writeObject(recordScores);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}
