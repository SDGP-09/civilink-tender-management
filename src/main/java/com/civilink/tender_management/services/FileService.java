package com.civilink.tender_management.services;

import java.io.File;

public interface FileService {
    String uploadFile(File file);
    File getFile(String url);
    void deleteFile(String url);
}
