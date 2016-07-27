package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.CommunicationService;
import calisthenics.todolist.service.IOService;
import calisthenics.todolist.service.command.CommandStrategy;

import java.io.IOException;

/**
 * Created by jcharlet on 27/07/16.
 */
public class ImportFileCommandStragegy implements CommandStrategy {
    private final CommunicationService communicationService;
    private final IOService ioService;

    public ImportFileCommandStragegy(CommunicationService communicationService, IOService ioService) {
        this.communicationService = communicationService;
        this.ioService = ioService;
    }

    @Override
    public void executeCommand(TodoList todoList) {
        communicationService.tellUser(new Message("please provide path of your file"));
        final Message userInputFilePath = communicationService.getUserInput();
        final String filePath = userInputFilePath.text;
        try {
            todoList = ioService.importTodoListFromFile(filePath);
        } catch (IOException e) {
            communicationService.tellUser(new Message("file with path " + filePath + " does not exist"));
            return;
        }

        communicationService.tellUser(new Message(todoList.toString()));

    }
}
