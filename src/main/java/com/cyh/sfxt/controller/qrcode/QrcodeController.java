package com.cyh.sfxt.controller.qrcode;

import com.cyh.sfxt.util.QRCodeUtil;
import com.google.zxing.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/Qrcode")
@RestController
public class QrcodeController {
    /**
     * 生成二维码
     */
    @GetMapping
    public void productcode() {
        QRCodeUtil.zxingCodeCreate("http://www.baidu.com", "D:/voice/picture/2018/",500,"C:\\Users\\86151\\Desktop\\绩效\\1.jpg");
    }

    /**
     * 解析二维码
     */
    @GetMapping("/test")
    public void analysiscode() {
        Result result = QRCodeUtil.zxingCodeAnalyze("D:\\voice\\picture\\2018\\766.jpg");
        System.err.println("二维码解析内容："+result.toString());
    }
}
