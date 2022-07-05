import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import templates.TemplatesGenerator;

public class MyTest {

    public static void main(String[] args) throws Exception {
        TemplatesImpl t= TemplatesGenerator.getTemplatesImpl();
        t.newTransformer();
    }
}
