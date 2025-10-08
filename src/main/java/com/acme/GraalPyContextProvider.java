package com.acme;

import org.graalvm.polyglot.Context;
import org.graalvm.python.embedding.GraalPyResources;
import org.graalvm.python.embedding.VirtualFileSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

@Configuration
public class GraalPyContextProvider {
    @Bean
    public Context pythonContext() {
        VirtualFileSystem vfs = VirtualFileSystem.newBuilder()
                .resourceLoadingClass(GraalPyContextProvider.class)
                .build();
        try {
            return GraalPyResources.contextBuilder(vfs)
                    .allowNativeAccess(false)
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException("Could not initialize Python context", ex);
        }
    }
}
