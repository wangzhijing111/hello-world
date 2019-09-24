package com.cyh.sfxt.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SavePhotoService {
    public Long insertSelective(MultipartFile file) throws IOException;
}
