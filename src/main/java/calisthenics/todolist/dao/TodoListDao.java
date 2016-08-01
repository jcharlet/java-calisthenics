package calisthenics.todolist.dao;

import calisthenics.todolist.model.TodoList;

/**
 * Created by jcharlet on 28/07/16.
 */
public interface TodoListDao {
    TodoList get();
    void save(TodoList todoList);
}
