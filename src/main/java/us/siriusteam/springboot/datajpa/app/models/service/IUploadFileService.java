package us.siriusteam.springboot.datajpa.app.models.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Upload file base service
 */
public interface IUploadFileService {
    /**
     * Load the provided file.
     * @param fileName the file name to load.
     * @return the loaded file.
     * @throws MalformedURLException if the given URI is not valid.
     */
    Resource load(String fileName) throws MalformedURLException;

    /**
     * Copy the provided file.
     * @param file the file to copy.
     * @return the name of the new copied file.
     * @throws IOException if an I/O error occurs when reading or writing.
     */
    String copy(MultipartFile file) throws IOException;

    /**
     * Delete a provided file.
     * @param fileName the file to delete.
     * @return true if the file is deleted, otherwise returns false.
     */
    boolean delete(String fileName);

    /**
     * Delete all files in the directory.
     * @return true if the files is deleted, otherwise returns false;
     */
    boolean deleteAll();

    boolean init() throws IOException;
}
