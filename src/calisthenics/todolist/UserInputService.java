package calisthenics.todolist;

import java.util.Scanner;

/**
 * Created by jcharlet on 18/07/16.
 */
public class UserInputService {
    public static void log(String message) {
        System.out.println(message);
    }

    public static String readUserInput() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        return reader.next(); // Scans the next token of the input as an int.
    }
}
