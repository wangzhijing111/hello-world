package com.cyh.sfxt.service.impl;

import cn.hutool.log.LogFactory;
import cn.hutool.poi.excel.WorkbookUtil;
import com.cyh.sfxt.entirty.Users;
import com.cyh.sfxt.service.ResolveExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ResolveExcelServiceImpl
 * @Author wangzhijing
 * @Date $ $
 * @Param $
 * @return $
 **/
@Service
@Transactional
public class ResolveExcelServiceImpl implements ResolveExcelService {
    /**
     * 注册url
     */
    private static final String SUFFIX_2003 = ".xls";
    private static final String SUFFIX_2007 = ".xlsx";
    /**
     * 电话的正则
     */
    public static final String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[01356789]|18[0-9]|19[89])\\d{8}$";
    /**
     * 密码长度
     */
    public static final int passWardLength = 6;

    @Override
    public List<Users> resolveExcel(MultipartFile file) throws Exception {
        List<Users> list = new ArrayList<>();
        //获取文件的名字
        String originalFilename = file.getOriginalFilename();
        Workbook workbook=null;
        try{
            if(originalFilename.endsWith(SUFFIX_2003)){
                workbook=new HSSFWorkbook(file.getInputStream());
            }else if(originalFilename.endsWith(SUFFIX_2007)){
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("格式错误");
        }
        if(null == workbook){
            throw new Exception("格式错误");
        }else{
            //获取所有的工作表的的数量
            int numOfSheet =workbook.getNumberOfSheets();
            //遍历这些表
            for(int i =0 ; i< numOfSheet;i++){
                //获取一个sheet也就是一个工作簿
                Sheet sheet=workbook.getSheetAt(i);
                int lastRowNum=sheet.getLastRowNum();
                //从第一行开始第一行一般是标题
                for(int j=1;j<=lastRowNum;j++){
                    Row row=sheet.getRow(j);
                    Users users=new Users();
                    if(null != row.getCell(0)){
                        row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                        String username=row.getCell(0).getStringCellValue();
                        users.setUsername(username);
                    }
                    if(null != row.getCell(1)){
                        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                        String sex=row.getCell(1).getStringCellValue();
                        users.setSex(sex);
                    }
                    if(null != row.getCell(2)){
                        row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                        String phone=row.getCell(2).getStringCellValue();
                        users.setPhone(phone);
                    }
                    list.add(users);
                }
            }
        }
        return list;
    }
}
