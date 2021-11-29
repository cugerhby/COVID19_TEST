package com.example.COVID19.servlet;

import com.alibaba.fastjson.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import static com.example.COVID19.utils.MysqlUtils.getProvince;


//向前端传输数据的
@WebServlet("/sendJson")
public class sendJson extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //向前端发送疫情数据
        //直接调用封装好的方法，获取json数据，发送到前端去
        resp.setContentType("application/json;charset=utf-8;");
        PrintWriter pw=resp.getWriter();
        JSONArray js=getProvince();
        pw.print(js);
        pw.close();
        System.out.println("sendJSON1运行");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
