package gamebook.gamebook.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        String storeFileName = createStoreFileName(file);
        file.transferTo(new File(storeFileName));
        return storeFileName;
    }

    private String createStoreFileName(MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(file.getOriginalFilename());
        return fileDir + uuid + "." + ext;
    }

    private String extractExt(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1);
    }
}
