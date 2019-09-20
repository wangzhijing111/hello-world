package com.cyh.sfxt.service;

import com.cyh.sfxt.entirty.Users;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResolveExcelService {
    public List<Users>resolveExcel(MultipartFile file)throws Exception;
}
