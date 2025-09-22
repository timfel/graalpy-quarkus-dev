package com.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.graalvm.polyglot.Context;
import org.graalvm.python.embedding.GraalPyResources;
import org.graalvm.python.embedding.VirtualFileSystem;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

@ApplicationScoped
public class GraalPyContextProvider {
    @Produces
    public Context pythonContext() {
        VirtualFileSystem vfs = VirtualFileSystem.newBuilder()
                .resourceLoadingClass(GraalPyContextProvider.class)
                .build();
        try {
            Path tmpdir = Files.createTempDirectory("graalpy-vfs");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    Files.walk(tmpdir)
                            .sorted(Comparator.reverseOrder())
                            .forEach(path -> {
                                try {
                                    Files.deleteIfExists(path);
                                } catch (Exception ignored) {}
                            });
                } catch (Exception ignored) {}
            }));
            GraalPyResources.extractVirtualFileSystemResources(vfs, tmpdir);
            return GraalPyResources.contextBuilder(tmpdir)
                    .allowNativeAccess(false)
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException("Could not initialize Python context", ex);
        }
    }
}
