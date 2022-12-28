package com.toy.cosmos.api.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@Slf4j
@EntityScan(basePackages = {"com.toy.cosmos"})
@SpringBootTest
@ActiveProfiles("test")
public class AttachedFileServiceTest {

    @Autowired
    AttachedFileService attachedFileService;

    @Test
    public void fileUploadTest() {
        MultipartFile file = new MockMultipartFile("file", "test.image".getBytes(StandardCharsets.UTF_8));


    }
}
