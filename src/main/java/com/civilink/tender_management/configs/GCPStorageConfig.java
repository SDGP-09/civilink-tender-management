package com.civilink.tender_management.configs;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GCPStorageConfig {

    @Bean
    public Storage storage()throws IOException{
        return StorageOptions.getDefaultInstance().getService();
    }
}
