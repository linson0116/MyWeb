package com.linson.myweb.servlet;

import javax.servlet.ServletInputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by linson on 2017/3/9.
 */
public class ServletUpFile extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        String fileName = "";
        String dirName = "";
        fileName = request.getParameter("fileName");
        dirName = request.getParameter("dirName");
        String realPath = getServletContext().getRealPath(dirName);
        File fileDir = new File(realPath);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        ServletInputStream is = request.getInputStream();
        FileOutputStream fos = new FileOutputStream(new File(fileDir, fileName));
        byte[] buff = new byte[1024];
        int len = -1;
        while ((len = is.read(buff)) != -1) {
            fos.write(buff, 0, len);
        }
        fos.close();
        is.close();
        System.out.println("上传文件：" + realPath + " " + fileName);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        System.out.println("doGet");
    }
}
