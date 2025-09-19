package com.acme

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import org.graalvm.polyglot.Context
import org.graalvm.python.embedding.GraalPyResources
import org.graalvm.python.embedding.VirtualFileSystem
import java.nio.file.Paths
import java.nio.file.Files

@ApplicationScoped
class GraalPyContextProvider {
    @Produces
    fun pythonContext(): Context {
        val vfs = VirtualFileSystem.newBuilder()
            .resourceLoadingClass(GraalPyContextProvider::class.java)
            .build()
        var tmpdir = Files.createTempDirectory("graalpy-vfs")
        Runtime.getRuntime().addShutdownHook(Thread {
            Files.walk(tmpdir)
                .sorted(Comparator.reverseOrder())
                .forEach { Files.deleteIfExists(it) }
        })
        GraalPyResources.extractVirtualFileSystemResources(vfs, tmpdir)
        return GraalPyResources.contextBuilder(tmpdir)
            .allowNativeAccess(false)
            .build()
    }
}
