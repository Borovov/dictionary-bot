package com.resin.service;

import com.resin.models.Dictionary;
import com.resin.models.DictionaryPurpose;
import com.resin.repository.DictionaryRepository;

import java.util.Scanner;

public class InfoCollector {

    public void parseDictionaryInfo() {
        Scanner scanner = new Scanner(System.in);
        Dictionary donor = getDictionaryInfo(scanner, DictionaryPurpose.DONOR);
        Dictionary recipient = getDictionaryInfo(scanner, DictionaryPurpose.RECIPIENT);

        System.out.println("Input absolute src path: ");
        String srcPath = scanner.nextLine();
        scanner.close();

        if (!readPaths(srcPath, donor.getKeyword(), recipient.getKeyword())) {
            return;
        }
    }

    private boolean readPaths(String srcPath, String donorKey, String recipientKey) {
        DictionaryRepository repository = new DictionaryRepository();
        PathInfo info = new PathInfo();
        String[] donorPaths = preparationPaths(info.getInfo(), srcPath, donorKey);
        String[] recipientPaths = preparationPaths(info.getInfo(), srcPath, recipientKey);
        return repository.readPaths(donorPaths, recipientPaths, donorKey, recipientKey);
    }

    private String[] preparationPaths(String[] info, String srcPath, String keyword) {
        String[] paths = new String[info.length];
        for (int i = 0; i < info.length; i++) {
            paths[i] = info[i];
        }

        for (byte i = 0; i < paths.length; i++) {
            paths[i] = srcPath + paths[i].replace("###", keyword);
        }

        return paths;
    }

    private Dictionary getDictionaryInfo(Scanner scanner, DictionaryPurpose purpose) {
        System.out.println("Input " + purpose.getCode() + " keyword: ");
        String keyword = getLine(scanner);

        //System.out.println("Input " + purpose.getCode() + " region: ");
        //String region = getLine(scanner);

        return new Dictionary(keyword);
    }

    private String getLine(Scanner scanner) {
        while(scanner.hasNextLine()){
            return scanner.nextLine();
        }
        return null;
    }

}
