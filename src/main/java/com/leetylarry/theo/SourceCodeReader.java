package com.leetylarry.theo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class SourceCodeReader {
    public static String readFileToSourceCode(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        return new String(bytes, Charset.defaultCharset());
    }
}
