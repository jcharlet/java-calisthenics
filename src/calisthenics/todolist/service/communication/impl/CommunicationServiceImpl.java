package calisthenics.todolist.service.communication.impl;

import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.communication.CommunicationService;

import java.util.Scanner;

/**
 * Created by jcharlet on 18/07/16.
 */
public class CommunicationServiceImpl implements CommunicationService {

    @Override
    public void tellUser(Message message) {
        System.out.println(message.text);
        System.out.println();
    }

    @Override
    public Message getUserInput() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        final String text = reader.next();
        return new Message(text); // Scans the next token of the input as an int.
    }

    @Override
    public void writeNewLineToConsole() {
        System.out.println();
    }
}
