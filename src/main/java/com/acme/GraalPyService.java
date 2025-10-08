package com.acme;

import org.graalvm.polyglot.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GraalPyService {
    private final GraalPyContextProvider provider;
    private Context pythonCtx;

    @Autowired
    public GraalPyService(GraalPyContextProvider provider) {
        this.provider = provider;
    }

    private Context getPythonCtx() {
        if (pythonCtx == null) {
            pythonCtx = provider.pythonContext();
            pythonCtx.eval("python", "from python_functions import *");
        }
        return pythonCtx;
    }

    public String greet(String name) {
        return getPythonCtx().getBindings("python")
            .getMember("greet")
            .execute(name)
            .asString();
    }
}
