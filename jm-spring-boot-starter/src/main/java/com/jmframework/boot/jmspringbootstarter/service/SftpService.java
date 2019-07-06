package com.jmframework.boot.jmspringbootstarter.service;

import com.jmframework.boot.jmspringbootstarterdomain.common.SftpUploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <h1>SftpService</h1>
 * <p>SFTP service interface</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-07-04 20:35
 **/
public interface SftpService {
    /**
     * List all files under the full path
     *
     * @param fullPath directory full path
     * @return file names
     */
    List<String> listFiles(String fullPath);

    /**
     * Check whether file exists according to file path
     *
     * @param fileFullPath file's full path
     * @return true - file exists; false - file not exists
     */
    boolean exist(String fileFullPath);

    /**
     * Get file size
     *
     * @param fileFullPath file's full path
     * @return file size (size unit: byte). Null if the file does not exist or path refers to a directory
     * @throws IllegalArgumentException when file does not exist
     */
    Long getFileSize(String fileFullPath) throws IllegalArgumentException;

    /**
     * Upload single file
     *
     * @param sftpUploadFile encapsulated object
     * @return file's full path if successful, else null
     */
    String upload(SftpUploadFile sftpUploadFile);

    /**
     * Upload files
     *
     * @param files        file list
     * @param deleteSource true - delete source file; false - not delete source file
     * @throws IOException IO exception
     */
    void upload(List<MultipartFile> files, boolean deleteSource) throws IOException;

    /**
     * Upload files
     *
     * @param files file list
     * @throws IOException IO exception
     */
    void upload(List<MultipartFile> files) throws IOException;

    /**
     * Upload file
     *
     * @param multipartFile multipart file
     * @throws IOException IO exception
     */
    void upload(MultipartFile multipartFile) throws IOException;

    /**
     * Download file
     *
     * @param fileFullPath file's full path
     * @return file
     * @throws IllegalArgumentException when file does not exist
     */
    File download(String fileFullPath) throws IllegalArgumentException;

    /**
     * Delete file according to file path
     *
     * @param fileFullPath file's full path
     * @return true - file deleted; false - file not deleted
     */
    boolean delete(String fileFullPath);
}
