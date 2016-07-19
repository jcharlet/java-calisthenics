package calisthenics.todolist.service;

import calisthenics.todolist.model.IOMessage;

import java.util.Scanner;

/**
 * Created by jcharlet on 18/07/16.
 */
public class IOServiceImpl implements IOService {

    @Override
    public void writeToConsole(IOMessage message) {
        System.out.println(message.text);
    }

    @Override
    public IOMessage readFromConsole() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        final String text = reader.next();
        return new IOMessage(text); // Scans the next token of the input as an int.
    }

    @Override
    public void writeNewLineToConsole() {
        System.out.println();
    }
}
