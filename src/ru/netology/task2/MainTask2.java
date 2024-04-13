package ru.netology.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MainTask2 {
    public static void main(String[] args) {
        GameProgress firstGame = new GameProgress(100, 5, 3, 15.20);
        GameProgress secondGame = new GameProgress(75, 7, 10, 19.70);
        GameProgress finalGame = new GameProgress(25, 10, 15, 23.50);

        saveGame("C://JAVA//GAMES//savegames//firstGame.dat", firstGame);
        saveGame("C://JAVA//GAMES//savegames//secondGame.dat", secondGame);
        saveGame("C://JAVA//GAMES//savegames//finalGame.dat", finalGame);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("C://JAVA//GAMES//savegames//firstGame.dat");
        arrayList.add("C://JAVA//GAMES//savegames//secondGame.dat");
        arrayList.add("C://JAVA//GAMES//savegames//finalGame.dat");
        zipFiles("C://JAVA//GAMES//savegames//zip.zip", arrayList);

        File firstGameDat = new File("C://JAVA//GAMES//savegames//firstGame.dat");
        File secondGameDat = new File("C://JAVA//GAMES//savegames//secondGame.dat");
        File finalGameDat = new File("C://JAVA//GAMES//savegames//finalGame.dat");
        if (firstGameDat.delete()) System.out.println("Файл \"firstGame.dat\" удален");
        if (secondGameDat.delete()) System.out.println("Файл \"secondGame.dat\" удален");
        if (finalGameDat.delete()) System.out.println("Файл \"finalGame.dat\" удален");

    }

    public static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zos.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zos.write(fis.read());
                    }
                    zos.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
