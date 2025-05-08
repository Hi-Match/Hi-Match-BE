package kr.co.himatch.thanksyouplz.code.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {
    // resource 폴더의 파일을 불러오는 함수
    public static String readResourceFile(String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        Path path = resource.getFile().toPath();
        return Files.readString(path);
    }
}