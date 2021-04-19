package model.score;

import java.lang.reflect.*;

public class AtmProxy implements InvocationHandler {
     private AtmInterface atmInterface;
     public AtmProxy (AtmInterface atmInterface) {
         this.atmInterface = atmInterface;
     }

    @Override
     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         Object result = null;
        long startTime = System.currentTimeMillis();
        result = method.invoke(atmInterface, args);
        if (System.currentTimeMillis() - startTime > 2000) {
            System.out.println("Too large delay in " + method.getName() + " : " + (System.currentTimeMillis() - startTime) + "ms.");
        }
         return result;
     }
}
