package calisthenics.todolist.service.command;

import calisthenics.todolist.dao.TodoListDao;

/**
 * Created by jcharlet on 25/07/16.
 */
public abstract class CommandStrategy {
    protected final TodoListDao todoListDao;

    protected CommandStrategy(TodoListDao todoListDao) {
        this.todoListDao = todoListDao;
    }

    public abstract void executeCommand();
}
