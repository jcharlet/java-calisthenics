package calisthenics.todolist.service.impl;

import calisthenics.todolist.model.Task;
import calisthenics.todolist.model.TodoList;
import calisthenics.todolist.service.IOService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by jcharlet on 27/07/16.
 */
public class IOServiceImpl implements IOService {
    @Override
    public TodoList importTodoListFromFile(String filePath) throws IOException {
        final List<String> fileLines;
        fileLines = Files.readAllLines(Paths.get(filePath));

        TodoList importedTodoList = new TodoList();
        for (String line : fileLines) {
            importedTodoList.addTask(new Task(line));
        }
        return importedTodoList;
    }
}
