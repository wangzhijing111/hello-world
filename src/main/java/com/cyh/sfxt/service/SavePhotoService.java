package com.cyh.sfxt.service;

import com.cyh.sfxt.entirty.result.ResponseData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SavePhotoService {
    public ResponseData insertSelective(MultipartFile file) throws IOException;
}
