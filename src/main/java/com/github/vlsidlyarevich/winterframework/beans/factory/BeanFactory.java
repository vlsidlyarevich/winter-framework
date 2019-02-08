package com.github.vlsidlyarevich.winterframework.beans.factory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    //TODO maybe registry?
    private final Map singletons = new ConcurrentHashMap<String, Object>();

    //TODO separate class scan logic into scanner
    public void init(final String path) {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        try {
            var resources = classLoader.getResources(path.replace(".", "/"));
            while (resources.hasMoreElements()) {
                final URL resource = resources.nextElement();
                File file = new File(resource.toURI());
                for (File classFile : Objects.requireNonNull(file.listFiles())) {
                    if (classFile.getName().endsWith(".class")) {
                        String className = classFile.getName().replace(".class", "");

                    }
                }
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }


    }

    public Object getBean(final String name) {
        return singletons.get(name);
    }
}
