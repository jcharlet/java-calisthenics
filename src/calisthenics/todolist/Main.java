package calisthenics.todolist;

import calisthenics.todolist.model.*;

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

    public static final String CREATE_TODO_LIST = "create";
    private TodoList todoList;

    public static void main(String[] args) {
        Main main = new Main();
        main.runTodoListProgram();
    }

    private void runTodoListProgram() {
        IOService.writeToConsole(new IOMessage("state your command"));
        TodoList todoList = null;
        while (true) {
            IOMessage commandMessage = IOService.readFromConsole();
            UserCommand command = UserCommand.valueOf(commandMessage.text);
            final UserCommandOutput userCommandOutput = executeUserCommand(command);
            final IOMessage message = new IOMessage(userCommandOutput.text);
            IOService.writeToConsole(message);

        }
    }


    public UserCommandOutput executeUserCommand(UserCommand command) {
        if (command == UserCommand.CREATE_TODO_LIST) {
            Task testTask = new Task("test");
            Task[] tasks = new Task[1];
            tasks[0] = testTask;
            todoList = new TodoList(tasks);

            return new UserCommandOutput(todoList.toString());
        }

        return new UserCommandOutput("unknown command");
    }

}
