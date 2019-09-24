package com.cyh.sfxt.service.impl;


import com.cyh.sfxt.entirty.Users;
import com.cyh.sfxt.service.SavePhotoService;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public class SavePhotoServiceImpl implements SavePhotoService {
    @Override
    public Long insertSelective(MultipartFile file) throws IOException {
        Users users=new Users();
        String fileName=file.getOriginalFilename();
        users.setOther1(fileName);
        // 计算出文件的大小与现实单位(KB/MB)
        long bytes=file.getSize();// bytes
        byte[] photoByte=file.getBytes();


        return null;
    }
}
