package chapter1;

public class ProduceClient {

    public static void main(String... args) throws Exception{
        Mqclient client = new Mqclient();
        client.produce("Hello World");
    }
}
