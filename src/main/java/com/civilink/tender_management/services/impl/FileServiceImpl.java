package com.civilink.tender_management.services.impl;

import com.civilink.tender_management.services.FileService;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private Storage storage;

    private String bucketName;

    @Override
    public String uploadFile(File file) {
        try {
            String fileName = UUID.randomUUID() + "-" + file.getName();
            BlobId blobId = BlobId.of(bucketName, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(Files.probeContentType(file.toPath())).build();

            storage.create(blobInfo, Files.readAllBytes(file.toPath()));

            return "https://storage.googleapis.com/" + bucketName + "/" + fileName;

        }catch (IOException e){
            throw new RuntimeException("File upload failed: " + e.getMessage(), e);
        }
    }

    @Override
    public File getFile(String url) {

        try {
            Blob blob = storage.get(BlobId.of(bucketName, url));
            if (blob == null) {
                throw new RuntimeException("File not found in GCP Storage: " + url);
            }

            File tempFile = File.createTempFile("download-", ".tmp");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(blob.getContent());
            }

            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException("Error downloading file: " + url, e);
        }
    }

    @Override
    public void deleteFile(String url) {
        BlobId blobId = BlobId.of(bucketName, url);
        boolean deleted = storage.delete(blobId);
        if (!deleted) {
            throw new RuntimeException("Failed to delete file: " + url);
        }
    }
}
