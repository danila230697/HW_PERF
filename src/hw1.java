import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.implementation.DestinationImpl;
import ru.pflb.mq.dummy.implementation.ProducerImpl;
import ru.pflb.mq.dummy.implementation.SessionImpl;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//Ну вроде как задача вполнена
public class hw1 {
    public static void main(String[] args) throws DummyException, InterruptedException {
        List<String> messageList = Arrays.asList( "Раз", "Два", "Три");
        ConnectionImpl connection= new ConnectionImpl();
        SessionImpl session= (SessionImpl) connection.createSession(true);
        DestinationImpl destination = (DestinationImpl) session.createDestination("True");
        destination.getQueueName();
        ProducerImpl producer=new ProducerImpl(destination);
        Iterator<String> iter = messageList.iterator();
        //Решил посомтреть а вообще как это работает, прикольно
        //producer.send("РАЗ");
        //producer.send("ДВА");
        //producer.send("ТРИ");
        while (iter.hasNext()) {
            producer.send(iter.next());
            TimeUnit.SECONDS.sleep(2);
        }
        session.close();
        connection.close();
    }
}
