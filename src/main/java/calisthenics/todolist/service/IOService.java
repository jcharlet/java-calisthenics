package calisthenics.todolist.service;

import calisthenics.todolist.model.TodoList;

import java.io.IOException;

/**
 * Created by jcharlet on 19/07/16.
 */
public interface IOService {
    TodoList importTodoListFromFile(String filePath) throws IOException;

}
