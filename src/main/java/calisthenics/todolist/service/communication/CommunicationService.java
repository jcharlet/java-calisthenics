package calisthenics.todolist.service.communication;

import calisthenics.todolist.model.communication.Message;

/**
 * Created by jcharlet on 19/07/16.
 */
public interface CommunicationService {
    void tellUser(Message message);

    Message getUserInput();

    void writeNewLineToConsole();
}
