package calisthenics.todolist.service;

import calisthenics.todolist.model.IOMessage;

/**
 * Created by jcharlet on 19/07/16.
 */
public interface IOService {
    void writeToConsole(IOMessage message);

    IOMessage readFromConsole();

    void writeNewLineToConsole();
}
