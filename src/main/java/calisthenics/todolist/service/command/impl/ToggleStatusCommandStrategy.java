package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.model.Task;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.CommunicationService;
import calisthenics.todolist.service.command.CommandStrategy;

/**
 * Created by jcharlet on 25/07/16.
 */
public class ToggleStatusCommandStrategy extends CommandStrategy {

    private final CommunicationService communicationService;

    public ToggleStatusCommandStrategy(TodoListDao todoListDao, CommunicationService communicationService) {
        super(todoListDao);
        this.communicationService = communicationService;
    }

    @Override
    public void executeCommand() {
        TodoList todoList = todoListDao.get();
        if (todoList == null || todoList.getTasks().isEmpty()) {
            this.communicationService.tellUser(new Message("No task for which to toggle the status"));
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("On which task do you want to toggle the status? \n");
        int taskIndex = 0;
        for (Task task : todoList.getTasks()) {
            stringBuffer.append("[")
                    .append(taskIndex)
                    .append("] ")
                    .append(task.toString())
                    .append("\n");
            taskIndex++;
        }
        communicationService.tellUser(new Message(stringBuffer.toString()));

        int selectedTaskNumber;
        do{
            communicationService.tellUser(new Message("Please provide the number of the task to modify \n"));
            Message selectedTaskNumberUserMessage = communicationService.getUserInput();
            selectedTaskNumber = Integer.valueOf(selectedTaskNumberUserMessage.text);

            if (selectedTaskNumber>=todoList.getTasksSize()){
                communicationService.tellUser(new Message("There is no such task. \n"));
            }
        } while(selectedTaskNumber>=todoList.getTasksSize());

        Task taskToModify = todoList.getTasks().get(selectedTaskNumber);
        taskToModify.toggleStatus();

        todoListDao.save(todoList);

        communicationService.tellUser(new Message(todoList.toString()));
    }
}
