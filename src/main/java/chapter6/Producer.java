package chapter6;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 消息生产者
 */
public class Producer {

    public static void main(String... args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        for (int i = 0; i < 100; i++) {
            Message msg = new Message(
                    "topic_example_java",
                    "TagA",
                    ("Hello Java Demo RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );

            SendResult sendResult = producer.send(msg);

            System.out.printf("%s%n", sendResult);
        }

        producer.shutdown();
    }
}
