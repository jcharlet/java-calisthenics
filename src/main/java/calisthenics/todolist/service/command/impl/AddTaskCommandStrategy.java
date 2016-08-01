package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.model.ApplicationContext;
import calisthenics.todolist.model.Task;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.CommunicationService;
import calisthenics.todolist.service.command.CommandStrategy;

/**
 * Created by jcharlet on 25/07/16.
 */
public class AddTaskCommandStrategy extends CommandStrategy {

    private final CommunicationService communicationService;

    public AddTaskCommandStrategy(TodoListDao todoListDao, CommunicationService communicationService) {
        super(todoListDao);
        this.communicationService = communicationService;
    }

    @Override
    public void executeCommand() {
        if (ApplicationContext.todoList == null) {
            communicationService.tellUser(new Message("no todo list created yet"));
        }
        this.communicationService.tellUser(new Message("Which is the task name?"));
        Message taskName = communicationService.getUserInput();
        Task testTask = new Task(taskName.text);

        TodoList todoList = todoListDao.get();
        todoList.addTask(testTask);
        todoListDao.save(todoList);

        communicationService.tellUser(new Message(ApplicationContext.todoList.toString()));
    }
}
