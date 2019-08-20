package com.cyh.sfxt.util;

import java.io.IOException;

public class test {
        public static void main(String[] args) throws IOException {
            String imageStr = Base64Utils.GetImageStr("F://img/1.jpg");
            System.out.println(imageStr);
            Base64Utils.GenerateImage(imageStr, "F://photos/1.jpg");
        }
}
