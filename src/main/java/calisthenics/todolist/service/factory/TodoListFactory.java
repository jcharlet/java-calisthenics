package calisthenics.todolist.service.factory;

import calisthenics.todolist.model.Task;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.service.command.impl.CreateCommandStrategy;

/**
 * Created by jcharlet on 16/08/16.
 */
public class TodoListFactory {
    public static TodoList create(CreateCommandStrategy.TodoListCreationType userChoice) {
        TodoList todoList = new TodoList();
        switch (userChoice) {
            case house_chores:
                todoList.addTask(new Task("remove the dust"));
                todoList.addTask(new Task("Vacuum the floor"));
                todoList.addTask(new Task("wash the floor"));
                break;
            case website:
                todoList.addTask(new Task("design"));
                todoList.addTask(new Task("implement"));
                todoList.addTask(new Task("test"));
                break;
            default:
            case empty:
                break;

        }
        return todoList;
    }
}
