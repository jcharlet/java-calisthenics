package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.dao.TodoListImportInterface;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.CommunicationService;
import calisthenics.todolist.service.command.CommandStrategy;

/**
 * Created by jcharlet on 27/07/16.
 */
public class ImportFileCommandStragegy extends CommandStrategy {
    private final CommunicationService communicationService;
    private final TodoListImportInterface todoListImportInterface;

    public ImportFileCommandStragegy(TodoListDao todoListDao, CommunicationService communicationService, TodoListImportInterface todoListImportInterface) {
        super(todoListDao);
        this.communicationService = communicationService;
        this.todoListImportInterface = todoListImportInterface;
    }

    @Override
    public void executeCommand() {
        communicationService.tellUser(new Message("please provide path of your file"));
        final Message userInputFilePath = communicationService.getUserInput();
        final String filePath = userInputFilePath.text;
        final TodoList todoList;
        try {
            todoList = todoListImportInterface.importFromFile(filePath);
            todoListDao.save(todoList);
        } catch (RuntimeException e) {
            communicationService.tellUser(new Message("file with path " + filePath + " does not exist"));
            return;
        }

        communicationService.tellUser(new Message(todoList.toString()));

    }
}
