package com.acme

import io.smallrye.common.annotation.Blocking
import io.quarkus.runtime.Startup
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.graalvm.polyglot.Context
import org.graalvm.polyglot.Value

@Blocking
@Startup
@ApplicationScoped
class GraalPyService @Inject constructor(
    private val provider: GraalPyContextProvider
) {
    private val pythonCtx: Context by lazy {
        provider.pythonContext().apply {
            eval("python", "from python_functions import *")
        }
    }

    fun greet(name: String): String =
        pythonCtx.getBindings("python").getMember("greet").execute(name).asString()
}
