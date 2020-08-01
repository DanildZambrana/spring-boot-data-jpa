package us.siriusteam.springboot.datajpa.app.models.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service("images_uploadService")
public class UploadFileServiceImplement implements IUploadFileService {
    private static final Logger LOG = LoggerFactory.getLogger(UploadFileServiceImplement.class);
    private static final String UPLOADS_FOLDER = "uploads";

    @Override
    public Resource load(String fileName) throws MalformedURLException {
        Path pathFoto = getPath(fileName);

        LOG.info("pathFoto: " + pathFoto);

        Resource resource = new UrlResource(pathFoto.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());
        }

        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString();
        Path rootPath = getPath(uniqueFileName);

        Files.copy(file.getInputStream(), rootPath);

        return uniqueFileName;
    }

    @Override
    public boolean delete(String fileName) {
        Path rootPath = getPath(fileName);
        File file = rootPath.toFile();

        if (file.exists() && file.canRead()) {
            return file.delete();
        }

        return false;
    }

    @Override
    public boolean deleteAll() {
        return FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
    }

    @Override
    public boolean init() throws IOException {
         return Files.createDirectories(Paths.get(UPLOADS_FOLDER)) != null;
    }

    private Path getPath(String fileName) {
        return Paths.get(UPLOADS_FOLDER).resolve(fileName).toAbsolutePath();
    }
}
