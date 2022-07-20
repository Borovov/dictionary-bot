package com.resin.repository;

import java.io.*;

public class DictionaryRepository {

    public boolean readPaths(String[] donorPaths, String[] recipientPaths, String donorKey, String recipientKey)
    {
        for (byte i = 0; i < donorPaths.length; i++) {
            File file = new File(donorPaths[i]);

            try {
                // Чтение донорских файлов
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder builder = new StringBuilder();
                String row;
                while ((row = reader.readLine()) != null) {
                    builder.append(row).append("\n");
                }
                reader.close();
                //System.out.println(builder);

                // Замена упоминаний справочника на новые
                String fileBody = builder.toString().replaceAll(donorKey, recipientKey);
                fileBody = fileBody.replaceAll(getLowerKey(donorKey), getLowerKey(recipientKey));
                //System.out.println(fileBody);

                // Создание и запись нового файла
                createRecipientFile(recipientPaths[i], fileBody);
                //Runtime.getRuntime().exec("cls");
            } catch (Exception ex) {
                System.out.println("Check path error with " + donorPaths[i]);
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return true;
    }

    private String getLowerKey(String key) {
        char keyChar = key.charAt(0);
        String lowerKeyChar = String.valueOf(Character.toLowerCase(keyChar));
        return key.replaceFirst(String.valueOf(keyChar), lowerKeyChar);
    }

    private void createRecipientFile(String path, String fileBody) {
        try(FileWriter writer = new FileWriter(path, false))
        {
            writer.write(fileBody);
            writer.append('\n');
            writer.flush();
            writer.close();
        }
        catch(IOException ex) {
            System.out.println("Error create recipient file " + path);
            System.out.println(ex.getMessage());
        }
    }

}
