package calisthenics.todolist.service.command.impl;

import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.model.ApplicationContext;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.service.command.CommandStrategy;

/**
 * Created by jcharlet on 25/07/16.
 */
public class CreateCommandStrategy extends CommandStrategy {

    public CreateCommandStrategy(TodoListDao todoListDao) {
        super(todoListDao);
    }

    @Override
    public void executeCommand() {
        if (ApplicationContext.todoList == null) {
            ApplicationContext.todoList = new TodoList();
        }

        TodoList todoList = todoListDao.get();
        todoList.emptyTasks();
        todoListDao.save(todoList);

    }
}
