package chapter4;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息生产者
 */
public class TopicPublisher {

    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String... args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        try {
            Connection connection = connectionFactory.createConnection();

            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic myTestTopic = session.createTopic("activemq-topic-test1");

            MessageProducer producer = session.createProducer(myTestTopic);

            for (int i = 1; i <= 3; i++) {
                TextMessage message = session.createTextMessage("发送消息" + i);
                producer.send(myTestTopic, message);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
