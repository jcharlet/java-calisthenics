package stubs;

import calisthenics.todolist.model.communication.Message;
import calisthenics.todolist.service.communication.CommunicationService;

/**
 * Created by jcharlet on 19/07/16.
 */
public class CommunicationServiceStub implements CommunicationService {

    public String stubMessage ="";

    @Override
    public void tellUser(Message message) {

    }

    @Override
    public Message getUserInput() {
        return new Message(stubMessage);
    }

    @Override
    public void writeNewLineToConsole() {

    }
}
