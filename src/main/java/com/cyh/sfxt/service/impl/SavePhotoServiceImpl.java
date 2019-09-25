package com.cyh.sfxt.service.impl;


import com.cyh.sfxt.entirty.Photo;
import com.cyh.sfxt.entirty.result.ExceptionMsg;
import com.cyh.sfxt.entirty.result.ResponseData;
import com.cyh.sfxt.mapper.SavePhotoMapper;
import com.cyh.sfxt.service.SavePhotoService;
import com.cyh.sfxt.util.FormatSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class SavePhotoServiceImpl implements SavePhotoService {
    @Autowired
    private SavePhotoMapper savePhotoMapper;
    @Override
    public ResponseData insertSelective(MultipartFile file) throws IOException {
        ResponseData responseData=null;
        int num = 0;
        Photo photo=new Photo();
        String fileName=file.getOriginalFilename();
        photo.setSrcName(fileName);
        // 计算出文件的大小与现实单位(KB/MB)
        long bytes=file.getSize();// bytes
        String[] transferedArr =FormatSize.getFormatSize(bytes);
        Double size=Double.valueOf(transferedArr[0]);
        String unit=transferedArr[1];
        photo.setSize(size);
        photo.setUnit(unit);
        //转换图片格式 :MultipartFile --> byte
        byte[] images=file.getBytes();
        photo.setImages(images);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        photo.setCreateTime(dateFormat.format(new Date()));
        num =savePhotoMapper.insert(photo);
        if(0 != num){
            responseData=new ResponseData(ExceptionMsg.SUCCESS);
        }else {
            responseData=new ResponseData(ExceptionMsg.FAILED);
        }
        return responseData;
    }
}
