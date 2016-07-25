package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.model.Task;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.command.CommandStrategy;
import calisthenics.todolist.service.communication.CommunicationService;

/**
 * Created by jcharlet on 25/07/16.
 */
public class AddTaskCommandStrategy implements CommandStrategy {

    private final CommunicationService communicationService;

    public AddTaskCommandStrategy(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @Override
    public void executeCommand(TodoList todoList) {
        if (todoList == null) {
            communicationService.tellUser(new Message("no todo list created yet"));
        }
        this.communicationService.tellUser(new Message("Which is the task name?"));
        Message taskName = communicationService.getUserInput();
        Task testTask = new Task(taskName.text);
        todoList.addTask(testTask);
        communicationService.tellUser(new Message(todoList.toString()));
    }
}
