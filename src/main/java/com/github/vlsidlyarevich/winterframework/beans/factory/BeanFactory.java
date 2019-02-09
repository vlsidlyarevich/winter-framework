package com.github.vlsidlyarevich.winterframework.beans.factory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    //TODO maybe registry?
    private final Map<String, Object> singletons = new ConcurrentHashMap<>();

    //TODO separate class scan logic into scanner
    public void init(final String path) {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        try {
            var resources = classLoader.getResources(path.replace(".", "/"));
            final List<String> classNames = new ArrayList<>();
            while (resources.hasMoreElements()) {
                final URL resource = resources.nextElement();
                var fileVisitor = new FullClassPathMemorizingFileVisitor(path);
                Files.walkFileTree(Path.of(resource.toURI()), fileVisitor);
                classNames.addAll(fileVisitor.getFullClassPaths());
            }




        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }


    }

    public Object getBean(final String name) {
        return singletons.get(name);
    }
}
