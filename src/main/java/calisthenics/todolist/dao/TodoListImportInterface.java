package calisthenics.todolist.dao;

import calisthenics.todolist.model.TodoList;

/**
 * Created by jcharlet on 28/07/16.
 */
public interface TodoListImportInterface {
    TodoList importFromFile(String filePath);
}
