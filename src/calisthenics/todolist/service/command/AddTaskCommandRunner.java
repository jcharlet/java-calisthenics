package calisthenics.todolist.service.command;

import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.model.Task;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.model.command.UserCommandOutput;
import calisthenics.todolist.service.communication.CommunicationService;

/**
 * Created by jcharlet on 25/07/16.
 */
public class AddTaskCommandRunner extends CommandRunner {

    public AddTaskCommandRunner(CommunicationService communicationService) {
        super(communicationService);
    }

    @Override
    public UserCommandOutput executeCreateCommand(TodoList todoList) {
        if (todoList == null) {
            return new UserCommandOutput("no todo list created yet");
        }
        this.communicationService.tellUser(new Message("Which is the task name?"));
        Message taskName = communicationService.getUserInput();
        Task testTask = new Task(taskName.text);
        todoList.addTask(testTask);
        return new UserCommandOutput(todoList.toString());
    }
}
