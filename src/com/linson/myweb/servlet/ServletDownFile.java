package com.linson.myweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by linson on 2017/3/10.
 */
public class ServletDownFile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fileName");
        String dirName = request.getParameter("dirName");
        System.out.println(fileName + " " + dirName);
        System.out.println(getServletContext().getRealPath(dirName));
        String filePath = getServletContext().getRealPath(dirName) + File.separator + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("需要下载的文件存在");
            FileInputStream fis = new FileInputStream(file);
            String filename = URLEncoder.encode(file.getName(), "utf-8"); //解决中文文件名下载后乱码的问题
            byte[] b = new byte[fis.available()];
            fis.read(b);
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename + "");
            //获取响应报文输出流对象
            ServletOutputStream out = response.getOutputStream();
            //输出
            out.write(b);
            out.flush();
            out.close();
            System.out.println("下载成功 " + file.getAbsolutePath());
        }
        System.out.println("请求结束");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
