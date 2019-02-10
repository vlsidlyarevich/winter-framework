package com.github.vlsidlyarevich.winterframework.beans.factory.support;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PathScanningClassesProvider {

    public List<Class<?>> provideClassesForPath(final String path) {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        final List<Class<?>> result = new ArrayList<>();
        assert classLoader != null;
        try {
            var resources = classLoader.getResources(path.replace(".", "/"));
            final List<String> fullClassPaths = new ArrayList<>();
            while (resources.hasMoreElements()) {
                final URL resource = resources.nextElement();
                var fileVisitor = new FullClassPathMemorizingFileVisitor(path);
                Files.walkFileTree(Path.of(resource.toURI()), fileVisitor);
                fullClassPaths.addAll(fileVisitor.getFullClassPaths());
            }

            for (String p : fullClassPaths) {
                Class<?> clazz = Class.forName(convertPathForClassFetch(p));
                result.add(clazz);
            }

        } catch (IOException | URISyntaxException | ClassNotFoundException e) {
            throw new ClassScanException("Exception during class scanning", e);
        }

        return result;
    }

    private String convertPathForClassFetch(final String fullClassPath) {
        String pathWithoutPostfix = ClassNameUtils.removePostfix(fullClassPath);
        return ClassNameUtils.convertToPackagedPath(pathWithoutPostfix);
    }
}
