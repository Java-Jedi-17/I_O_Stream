package ru.netology.task1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MainTask1 {
    static String path = "C:\\JAVA\\GAMES\\";

    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>(10);
        list1.add(0, path + "savegames\\");
        list1.add(1, path + "src\\test\\");
        list1.add(2, path + "src\\main\\");
        list1.add(3, "MainTask1.java");
        list1.add(4, "Utils.java");
        list1.add(5, path + "res\\drawables\\");
        list1.add(6, path + "res\\vectors\\");
        list1.add(7, path + "res\\icons\\");
        list1.add(8, path + "temp\\");
        list1.add(9, "temp.txt");

        StringBuilder logFile = new StringBuilder("Журнал создания фалов и папок");

        Iterator<String> it = list1.iterator();
        String files = "";
        String directory = "";

        while (it.hasNext()) {
            files = it.next();
            File file = new File(files);

            logFile.append(System.lineSeparator() + "создание ");
            if (files.contains(".")) {
                File file1 = new File(directory, files);

                logFile.append("файла " + files + ": ");
                try {
                    logFile.append(file1.createNewFile());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                logFile.append("папки " + files + ": ");
                logFile.append(file.mkdir() ? file.exists() : file.mkdirs());
                directory = files;
            }
        }

        try (BufferedWriter logFileWriter = new BufferedWriter(
                new FileWriter(list1.get(8) + list1.get(9)))) {

            logFileWriter.write(logFile.toString());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


