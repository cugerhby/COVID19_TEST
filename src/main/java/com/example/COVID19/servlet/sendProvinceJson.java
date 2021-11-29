package com.example.COVID19.servlet;

import com.alibaba.fastjson.JSONArray;
import com.example.COVID19.utils.MysqlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//此类为发送省份数据的servlet
@WebServlet("/sendProvinceJson")
public class sendProvinceJson extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置返回值的类型
        resp.setContentType("application/json;charset=utf-8;");
        JSONArray js= MysqlUtils.getProvinceDataOnly();
        PrintWriter pw=resp.getWriter();
        pw.print(js);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
