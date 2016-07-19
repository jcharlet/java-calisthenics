package calisthenics.todolist;

import calisthenics.todolist.model.*;
import calisthenics.todolist.service.IOService;
import calisthenics.todolist.service.IOServiceImpl;

/**
 * write a CLI which enables you to deal with a todo list
 * <h2>rules</h2>
 * <ol>
 * <li>Only One Level Of Indentation Per Method</li>
 * <li>
 * Don’t Use The ELSE Keyword</li>
 * <li>
 * Wrap All Primitives And Strings</li>
 * <li>
 * First Class Collections</li>
 * <li>
 * One Dot Per Line</li>
 * <li>
 * Don’t Abbreviate</li>
 * <li>
 * Keep All Entities Small</li>
 * <li>
 * No Classes With More Than Two Instance Variables</li>
 * <li>
 * No Getters/Setters/Properties</li>
 * </ol>
 * <h2>US</h2>
 * <h3>Core User Stories</h3>
 * As a user I can create a todo list<br/>
 * As a user I can add tasks to the todo list<br/>
 * As a user I can see all the tasks in a list<br/>
 * As a user I can manipulate the todo list through a menu<br/>
 * <h3>Secondary User Stories</h3>
 * As a user I can open a list from a text file<br/>
 * As a user I can save a list to a text file<br/>
 * As a user I can delete a task<br/>
 * As a user I can update a task<br/>
 * <h3>Reach User Stories</h3>
 * As a user I can set a task status<br/>
 * As a user I can toggle a task status as complete or incomplete
 */
public class Main {

    private TodoList todoList;

    private IOService ioService;

    public Main() {
        this.ioService = new IOServiceImpl();
    }

    public Main(IOService ioService) {
        this.ioService = ioService;
    }

    public static void main(String[] args) {
        Main main = new Main();
        while (true) {
            main.runTodoListProgram();
        }
    }

    private void runTodoListProgram() {
        UserCommand command;
        try{
            command = askUser();
        }catch (IllegalArgumentException e){
            tellUserOutcome(new UserCommandOutput("unknown command"));
            return;
        }
        final UserCommandOutput userCommandOutput = executeUserCommand(command);
        tellUserOutcome(userCommandOutput);
    }

    private void tellUserOutcome(UserCommandOutput userCommandOutput) {
        final IOMessage message = new IOMessage(userCommandOutput.text);
        ioService.writeToConsole(message);
        ioService.writeNewLineToConsole();
    }

    private UserCommand askUser() {
        ioService.writeToConsole(new IOMessage("state your command"));
        IOMessage commandMessage = ioService.readFromConsole();
        return UserCommand.valueOf(commandMessage.text);
    }


    public UserCommandOutput executeUserCommand(UserCommand command) {
        if (command == UserCommand.create) {
            return executeCreateCommand();
        }
        if (command == UserCommand.addTask) {
            return executeAddTaskCommand();
        }
        if (command == UserCommand.help) {
            return executeHelpCommand();
        }
        throw new IllegalArgumentException("user command not recognized: " + command);
    }

    private UserCommandOutput executeCreateCommand() {
        todoList = new TodoList();
        return new UserCommandOutput(todoList.toString());
    }

    private UserCommandOutput executeAddTaskCommand() {
        if(todoList==null){
            return new UserCommandOutput("no todo list created yet");
        }
        ioService.writeToConsole(new IOMessage("Which is the task name?"));
        IOMessage taskName = ioService.readFromConsole();
        Task testTask = new Task(taskName.text);
        todoList.addTask(testTask);
        return new UserCommandOutput(todoList.toString());
    }

    private UserCommandOutput executeHelpCommand() {
        ioService.writeToConsole(new IOMessage("Here are the available commands:"));
        String listOfCommands="";
        for (UserCommand availableCommand:UserCommand.values()){
            listOfCommands+=availableCommand.name() + " ";
        }
        return new UserCommandOutput(listOfCommands);
    }

}
