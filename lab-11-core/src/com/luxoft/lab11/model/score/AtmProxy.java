package com.luxoft.lab11.model.score;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AtmProxy implements InvocationHandler {
    private final AtmInterface atm;

    public AtmProxy(AtmInterface atm) {
        this.atm = atm;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = method.invoke(atm, args);
        if (System.currentTimeMillis() - startTime > 2000) {
            System.out.println("Too large delay in " + method.getName() + " : " + (System.currentTimeMillis() - startTime) + "ms.");
        }
        return result;
    }
}
