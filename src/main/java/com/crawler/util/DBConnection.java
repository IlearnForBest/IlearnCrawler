package com.crawler.util;

import java.sql.*;

/**
 * Created by sl on 16-4-2.
 */

public class DBConnection {

    private String dbDriver="com.mysql.jdbc.Driver";
    private String dbUrl="jdbc:mysql://localhost:3306/db_ilearn?useUnicode=true&characterEncoding=utf-8";//根据实际情况变化
    private String dbUser="root";
    private String dbPass="111111";


    /**
     * 连接
     * @return
     */
    public Connection getConn(){
        Connection conn=null;
        try{
            Class.forName(dbDriver);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            conn = DriverManager.getConnection(dbUrl,dbUser,dbPass);//注意是三个参数
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 增加
     * @param title
     * @param url
     * @param imgurl
     * @param category_1
     * @param category_2
     * @param category_3
     */
    public void insert(String title,String url,String imgurl,String category_1,
                       String category_2, String category_3) {
        int i=0;
        String sql="insert into ilearn_res(title,url,imgurl,category_1," +
                "category_2,category_3)  values(?,?,?,?,?,?)";
        Connection cnn=getConn();

        try{
            PreparedStatement preStmt =cnn.prepareStatement(sql);
            preStmt.setString(1,title);
            preStmt.setString(2,url);
            preStmt.setString(3,imgurl);
            preStmt.setString(4,category_1);
            preStmt.setString(5,category_2);
            preStmt.setString(6,category_3);
            i=preStmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
//        return i;//返回影响的行数，1为执行成功
    }


    /**
     * 更新
     * @param imgurl
     * @param title
     */
    public void update(String imgurl , String title){
        int i=0;
        String sql="update ilearn_res set imgurl=? where title=?";
        Connection cnn=getConn();

        try{
            PreparedStatement preStmt =cnn.prepareStatement(sql);
            preStmt.setString(1,imgurl);
            preStmt.setString(2,title);//或者：preStmt.setInt(1,值);
            i=preStmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
//        return i;//返回影响的行数，1为执行成功
    }



   /*
   //查询
   public String Qurey(String key){
        String sql = "select * from db_res where "+"key=?";
        Connection cnn = getConn();//此处为通过自己写的方法getConn()获得连接
        try
        {
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next())
            {
                int m1 = rs.getInt(1);//或者为rs.getString(1)，根据数据库中列的值类型确定，参数为第一列
                String m2 = rs.getString(2);
            }
            //可以将查找到的值写入类，然后返回相应的对象
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return (相应的值的变量);
    }


    //删除
        public int delete()  {
        String sql = "delete from (表名) where (列名)=(值)";
        int i=0;
        Connection conn = getConn();//此处为通过自己写的方法getConn()获得连接
        try
        {
            Statement stmt = conn.createStatement();
            i = stmt.executeUpdate(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return i;//如果返回的是1，则执行成功;
    }
    */



}

