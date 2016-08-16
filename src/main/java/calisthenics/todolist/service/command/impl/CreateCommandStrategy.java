package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.CommunicationService;
import calisthenics.todolist.service.command.CommandStrategy;
import calisthenics.todolist.service.factory.TodoListFactory;

import java.util.Arrays;

/**
 * Created by jcharlet on 25/07/16.
 */
public class CreateCommandStrategy extends CommandStrategy {

    private final CommunicationService communicationService;

    public CreateCommandStrategy(TodoListDao todoListDao, CommunicationService communicationService) {
        super(todoListDao);
        this.communicationService = communicationService;
    }

    @Override
    public void executeCommand() {
        this.communicationService.tellUser(new Message("Todo lists available: " + Arrays.toString(TodoListCreationType.values())));
        this.communicationService.tellUser(new Message("Which todo list you want to create?"));
        Message todoListName = communicationService.getUserInput();
        TodoListCreationType userChoice = null;
        try {
            userChoice = TodoListCreationType.valueOf(todoListName.text);
        } catch (IllegalArgumentException e) {
            this.communicationService.tellUser(new Message(todoListName.text + " not handled"));
            return;
        }

        TodoList todoList = TodoListFactory.create(userChoice);

        todoListDao.save(todoList);
    }

    public enum TodoListCreationType {
        empty, house_chores, website
    }
}
