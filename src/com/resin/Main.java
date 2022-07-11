package com.resin;

import com.resin.service.InfoCollector;

public class Main {

    public static void main(String[] args) {
        InfoCollector collector = new InfoCollector();
        collector.parseDictionaryInfo();
    }
}
