package com.example.COVID19.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.COVID19.data.city;
import com.example.COVID19.data.province;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.COVID19.utils.JsonUtils.getJsonArrayFromHtml;

public class MysqlUtils {

    static private String url="jdbc:mysql://42.192.208.23:3306/covid19_2";
    static private String password="hby20010507";
    static private String root="covid19_2";

    //注册驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){
        upadtaData();
    }

    public static Connection getConnection() throws SQLException {


        return DriverManager.getConnection(url,root,password);//获取资源

    }

    @Test
    public void test2(){
        System.out.println(getProvince());

    }
    //用来缩小所传入的省份名称
    public static String cut(String provinceName){
        if(provinceName.length()==3||provinceName.length()==4){
            if(provinceName.equals("黑龙江省"))
                return provinceName.substring(0,3);
            else{
                return provinceName.substring(0,2);
            }
        }else if(provinceName.equals("内蒙古自治区")){
            return provinceName.substring(0,3);
        }else if(provinceName.equals("宁夏回族自治区")){
            return provinceName.substring(0,2);
        }else if(provinceName.equals("新疆维吾尔自治区")){
            return provinceName.substring(0,2);
        }else if(provinceName.equals("广西壮族自治区")){
            return provinceName.substring(0,2);
        }else if(provinceName.equals("西藏自治区")){
            return provinceName.substring(0,2);
        }else if(provinceName.length()==2){
            return provinceName;
        }
        return null;

    }
    @Test
    public void testcut(){
        System.out.println(cut("内蒙古自治区"));
    }

    @Test
    public void bb(){
        System.out.println(getProvince());
    }
    //获取数据库中的数据的操作,返回的是一个JsonArray数据，获取省份以及省份附属的city的数据
    public static JSONArray getProvince(){
        Connection conn=null;
        ResultSet res=null;
        Statement stmt=null;
        //要封装为一个province然后发送出去
        List<province> pro=null;
        try {
            String sql="select * from province";
            conn=getConnection();
            stmt=conn.createStatement();
            res=stmt.executeQuery(sql);
            pro=new ArrayList<>();
            while (res.next()){

                //此处获取到了城市的数据之后，将城市的数据添加到province中去
                List<city> li=getCities(conn,res.getString("provinceName"));
                pro.add(new province(res,li));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                res.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return (JSONArray) JSON.toJSON(pro);
    }


    @Test
    public void testgetProvinceData(){
        getProvinceDataOnly();
    }

    public static void main(String[] args) {
        upadtaData();
    }
    //此为仅获取省份数据的方法
    public static JSONArray getProvinceDataOnly(){
        Connection conn=null;
        ResultSet res=null;
        Statement stmt=null;
        //要封装为一个province然后发送出去
        List<province> pro=null;
        try {
            String sql="select * from province";
            conn=getConnection();
            stmt=conn.createStatement();
            res=stmt.executeQuery(sql);
            pro=new ArrayList<>();
            while (res.next()){
                //此处获取到了城市的数据之后，将城市的数据添加到province中去,没有city数据
                pro.add(new province(res,null));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                res.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return (JSONArray) JSON.toJSON(pro);
    }



    private static List<city> getCities(Connection conn,String provinceName){
        Statement stmt=null;
        ResultSet res=null;
        List<city> result=new ArrayList<>();
        try {
            stmt=conn.createStatement();
            String sql="select * from cities where provinceName='"+provinceName+"'";
            res=stmt.executeQuery(sql);
            while(res.next()){
                //添加城市的数据
                result.add(new city(res));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                res.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return result;
    }


    @Test
    public void testupadtaData(){
        upadtaData();//
    }
    //向数据库中更新疫情的数据
    public static void upadtaData(){
        JSONArray jsonArray=getJsonArrayFromHtml("https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline", "script#getAreaStat");
        province pro=null;
        Connection conn=null;
        try {
           conn=getConnection();
            for (int i=0;i<jsonArray.size();i++){
                //获取json数据，进行更新操作
                pro=sql.getProvinceFromJson(jsonArray,i);
                //传入conn
                upProvinceData(conn,pro);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }


    }

    //此函数为插入数据的具体操作
    private static void upProvinceData(Connection conn,province pro){
        PreparedStatement stmt=null;
        PreparedStatement stmt2=null;


        //以下是update数据的sql语句
        try {
            String sql="update province set provinceName=?,provinceShortName=?,currentConfirmedCount=?,confirmedCount=?," +
                    "suspectedCount=?,curedCount=?,deadCount=?,comment=?,locationId=?,highDangerCount=?," +
                    "midDangerCount=?,detectOrgCount=?,vaccinationOrgCount=? where locationId=?";

            //
            String sql2="update cities set cityName=?,provinceName=?,currentConfirmedCount=?,confirmedCount=?," +
                    "suspectedCount=?,curedCount=?,deadCount=?,highDangerCount=?," +
                    "midDangerCount=?,locationId=?,currentConfirmedCountStr=? where locationId=?";



            //初始化数据 有11个数据
            //String sql2="insert into cities values(?,?,?,?,?,?,?,?,?,?,?)";
            //13ge ?
            //sql3是插入数据初始化的sql语句
            /*String sql3="insert into province values(?,?,?,?,?,?,?,?,?,?,?,?,?)";*/
            stmt=conn.prepareStatement(sql);
            stmt2=conn.prepareStatement(sql2);

            stmt.setString(1,pro.getProvinceName());
            stmt.setString(2,pro.getProvinceShortName());
            stmt.setInt(3,pro.getCurrentConfirmedCount());
            stmt.setInt(4,pro.getConfirmedCount());
            stmt.setInt(5,pro.getSuspectedCount());
            stmt.setInt(6,pro.getCuredCount());
            stmt.setInt(7,pro.getDeadCount());
            stmt.setString(8,pro.getComment());
            stmt.setInt(9,pro.getLocationId());
            stmt.setInt(10,pro.getHighDangerCount());
            stmt.setInt(11,pro.getMidDangerCount());
            stmt.setInt(12,pro.getDetectOrgCount());
            stmt.setInt(13,pro.getVaccinationOrgCount());
            stmt.setInt(14,pro.getLocationId());

            //以下是upadte cities数据的函数，因为cities的数据都被封装到了province中
            //要提取出cities的数据，然后先执行插入到数据库中，然后在执行update操作
            //stmt2=conn.prepareStatement(sql2);
            for (city c:pro.getCitys()){
                //设置city的数据
                stmt2.setString(1,c.getCityName());
                stmt2.setString(2,c.getProvinceName());
                stmt2.setInt(3,c.getCurrentConfirmedCount());
                stmt2.setInt(4,c.getConfirmedCount());
                stmt2.setInt(5,c.getSuspectedCount());
                stmt2.setInt(6,c.getCuredCount());
                stmt2.setInt(7,c.getDeadCount());
                stmt2.setInt(8,c.getHighDangerCount());
                stmt2.setInt(9,c.getMidDangerCount());
                stmt2.setInt(10,c.getLocationId());
                stmt2.setString(11,c.getCurrentConfirmedCountStr());
                stmt2.setInt(12,c.getLocationId());
                stmt2.execute();
            }
            stmt.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }


    }
    //释放资源的函数




}
