package gamebook.gamebook.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        List<String> fileNames = createStoreFileName(file);
        file.transferTo(new File(fileNames.get(0)));
        return fileNames.get(1);
    }

    private List<String> createStoreFileName(MultipartFile file) {
        List<String> fileNames = new ArrayList<>();
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(file.getOriginalFilename());
        fileNames.add(fileDir + uuid + "." + ext);
        fileNames.add(uuid + "." + ext);
        return fileNames;
    }

    private String extractExt(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1);
    }
}
