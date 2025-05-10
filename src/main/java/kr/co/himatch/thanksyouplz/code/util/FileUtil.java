package kr.co.himatch.thanksyouplz.code.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    // resource 폴더의 파일을 불러오는 함수
    public static String readResourceFile(String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }
}