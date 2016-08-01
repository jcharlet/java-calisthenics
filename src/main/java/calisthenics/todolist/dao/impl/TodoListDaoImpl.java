package calisthenics.todolist.dao.impl;

import calisthenics.todolist.dao.TodoListDao;
import calisthenics.todolist.model.TodoList;

/**
 * Created by jcharlet on 28/07/16.
 */
public class TodoListDaoImpl implements TodoListDao {

    static private TodoList todoList;

    @Override
    public TodoList get() {
        return todoList;
    }

    @Override
    public void save(TodoList todoList) {
        this.todoList=todoList;
    }
}
