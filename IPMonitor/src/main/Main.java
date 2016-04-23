/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package main;

public class Main {

    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("service")) {
            new MainService(args);
        } else {
            new MainApplication();
        }
    }
}
