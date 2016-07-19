package calisthenics.todolist.model;

import java.util.Scanner;

/**
 * Created by jcharlet on 18/07/16.
 */
public class IOService {

    public static void writeToConsole(IOMessage message) {
        System.out.println(message.text);
    }

    public static IOMessage readFromConsole() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        final String text = reader.next();
        return new IOMessage(text); // Scans the next token of the input as an int.
    }
}
