package com.github.vlsidlyarevich.winterframework.beans.factory;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class FullClassPathMemorizingFileVisitor extends SimpleFileVisitor<Path> {

    private final String basePackage;
    private final List<String> fullClassPaths;

    public FullClassPathMemorizingFileVisitor(final String basePackage) {
        super();
        this.fullClassPaths = new ArrayList<>();
        requireNonNull(basePackage);
        this.basePackage = basePackage.replaceAll("\\.", "/");
    }

    @Override
    public FileVisitResult visitFile(final Path path, final BasicFileAttributes attrs) {
        requireNonNull(path);
        requireNonNull(attrs);
        final String fileName = path.toFile().getName();
        if (!fileName.endsWith(".class")) {
            return FileVisitResult.CONTINUE;
        }

        String pathString = path.toString();
        String postfixedFullClassPath = path.toString().substring(pathString.indexOf(basePackage), pathString.length());
        this.fullClassPaths.add(postfixedFullClassPath);

        return FileVisitResult.CONTINUE;
    }

    public List<String> getFullClassPaths() {
        return fullClassPaths;
    }
}
