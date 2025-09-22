package com.acme;

import io.smallrye.common.annotation.Blocking;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.graalvm.polyglot.Context;

@Blocking
@Startup
@ApplicationScoped
public class GraalPyService {
    private final GraalPyContextProvider provider;
    private Context pythonCtx;

    @Inject
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
