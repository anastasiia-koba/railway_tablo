package system.mdb;

import com.google.gson.Gson;
import system.domain.JsonFromServer;
import system.ejb.InMemoryStorage;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "Consumer",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/topic/rw")
        })
public class NotificationBean implements MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String msg = textMessage.getText();
            JsonFromServer json = new Gson().fromJson(msg, JsonFromServer.class);
            putToStorage(json);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    protected void putToStorage(JsonFromServer message) {
        InMemoryStorage.add(message);
    }
}