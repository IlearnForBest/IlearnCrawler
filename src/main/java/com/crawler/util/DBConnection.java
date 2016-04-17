package com.crawler.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sl on 16-4-2.
 */

public class DBConnection {

    private String dbDriver="com.mysql.jdbc.Driver";
    private String dbUrl="jdbc:mysql://localhost:3306/db_ilearn?useUnicode=true&characterEncoding=utf-8";//根据实际情况变化
    private String dbUser="root";
    private String dbPass="root";


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


    public void insertCate(String cate_name,String category_1,
                       String category_2) {
        int i=0;
        String sql="insert into ilearn_category(cate_name,category_1," +
                "category_2)  values(?,?,?)";
        Connection cnn=getConn();

        try{
            PreparedStatement preStmt =cnn.prepareStatement(sql);
            preStmt.setString(1,cate_name);
            preStmt.setString(2,category_1);
            preStmt.setString(3,category_2);
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
        String sql="update ilearn_it_com set imgurl=? where title=?";
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


    public void updateValue( String category_2 ,String cate_name){
        int i=0;
//<<<<<<< HEAD
//        String sql="update ilearn_resources set category_1=? where category_2=?";
//=======
        String sql="update ilearn_category set category_2=? where cate_name=?";
//>>>>>>> zqh/master
//        String sql="update ilearn_it_com set category_2=? where rid=?";

        Connection cnn=getConn();

        try{
            PreparedStatement preStmt =cnn.prepareStatement(sql);
            preStmt.setString(1,category_2);
            preStmt.setString(2,cate_name);//或者：preStmt.setInt(1,值);
            i=preStmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
//        return i;//返回影响的行数，1为执行成功
    }



    public void updateSelf( String key ,String oldValue , String newValue){
        int i=0;
//<<<<<<< HEAD
        String sql="update ilearn_resources set "+key+"=? where "+key+"=?";
//        String sql="update ilearn_resources set category_3='汉语' where category_2='汉语' ";

//        String sql="update ilearn_resources set "+key+"=? where "+key+" IS NULL and category_1='升学考研'";

//        String sql="update ilearn_it_com set category_2=? where rid=?";

        Connection cnn=getConn();

        try{
            PreparedStatement preStmt =cnn.prepareStatement(sql);
            preStmt.setString(1,newValue);
//            preStmt.setString(2,oldValue);//或者：preStmt.setInt(1,值);
            i=preStmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
//        return i;//返回影响的行数，1为执行成功
    }




   //查询
   public void Qurey(String result,String key,String value){
       String sql = "select  DISTINCT "+result+" from ilearn_resources where "+key+"='"+value+"'";

        Connection cnn = getConn();//此处为通过自己写的方法getConn()获得连接

       List<String> res = new ArrayList<>();

        try
        {
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
            {

                res.add(rs.getString("category_3"));
//                System.out.println(rs.getString("category_3"));
            }
            //可以将查找到的值写入类，然后返回相应的对象
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
//        return res;
    }




    //查询
    public String  QureyCate1(String value){
        String sql = "select  DISTINCT category_2 from ilearn_it_com " +
                "WHERE category_3='"+value+"'";
        Connection cnn = getConn();//此处为通过自己写的方法getConn()获得连接

        String res="";

        try
        {
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                res = rs.getString("category_2");
//                System.out.println(rs.getString("category_2"));
            }
            //可以将查找到的值写入类，然后返回相应的对象
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return res;
    }



    public void deal(){
        String sql = "update ilearn_resources set category_3 ='其他' where category_3 is null";
        Connection cnn = getConn();//此处为通过自己写的方法getConn()获得连接

    }


/*
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


 public void insertintoit(String title,String url,String imgurl,String category_1,
                      String category_2,int collection,int remark,int grade,double satisfaction,int join_number, String source_web) {
       int i=0;
       String sql="insert into ilearn_it_com(title,url,imgurl,category_1," +
               "category_2,collection,remark,grade,satisfaction,join_number,source_web)  values(?,?,?,?,?,?,?,?,?,?,?)";
       Connection cnn=getConn();

       try{
           PreparedStatement preStmt =cnn.prepareStatement(sql);
           preStmt.setString(1,title);
           preStmt.setString(2,url);
           preStmt.setString(3,imgurl);
           preStmt.setString(4,category_1);
           preStmt.setString(5,category_2);
           preStmt.setInt(6, collection);
           preStmt.setInt(7,remark);
           preStmt.setInt(8,grade);
           preStmt.setDouble(9, satisfaction);
           preStmt.setInt(10,join_number);
           preStmt.setString(11,source_web);
           i=preStmt.executeUpdate();
       }
       catch (SQLException e){
           e.printStackTrace();
       }
//        return i;//返回影响的行数，1为执行成功
   }

    public void temp(String url,String sourceweb) throws SQLException {
        String sql="insert into temp(url,source_web)  values(?,?)";
        Connection cnn=getConn();
        PreparedStatement preStmt =cnn.prepareStatement(sql);
        preStmt.setString(1,url);
        preStmt.setString(2,sourceweb);
        preStmt.executeUpdate();
    }

    public List<String> qureyFromTemp(){
        List<String> urls = new ArrayList<String>();
        String sql = "select * from temp limit 24384,28131";
        Connection cnn = getConn();//此处为通过自己写的方法getConn()获得连接
        try
        {
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                urls.add(rs.getString("url"));
                System.out.println(rs.getString("url"));
                //或者为rs.getString(1)，根据数据库中列的值类型确定，参数为第一列
            }
            //可以将查找到的值写入类，然后返回相应的对象
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return urls;
    }


    public void insertintoresource(String title,String url,String imgurl,String category_1,
                             String category_2,String category_3,int collection,int remark,int grade,double satisfaction,int join_number, String source_web) {
        int i=0;
        String sql="insert into ilearn_resources(title,url,imgurl,category_1," +
                "category_2,category_3,collection,remark,grade,satisfaction,join_number,source_web)  values(?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection cnn=getConn();

        try{
            PreparedStatement preStmt =cnn.prepareStatement(sql);
            preStmt.setString(1,title);
            preStmt.setString(2,url);
            preStmt.setString(3,imgurl);
            preStmt.setString(4,category_1);
            preStmt.setString(5,category_2);
            preStmt.setString(6,category_3);
            preStmt.setInt(7, collection);
            preStmt.setInt(8,remark);
            preStmt.setInt(9,grade);
            preStmt.setDouble(10, satisfaction);
            preStmt.setInt(11,join_number);
            preStmt.setString(12,source_web);
            i=preStmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
//        return i;//返回影响的行数，1为执行成功
    }


    public void qureyFromSql() {
        int id=0;
        List<String> c2 = new ArrayList<String>();
        try {
            String sql = "select DISTINCT cate_name from ilearn_category where category_1 is not null and category_2 is not null";
            Connection cnn=getConn();
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                c2.add(rs.getString("cate_name"));
            }
            for(String c:c2){
                String sql2 = "select cid from ilearn_category where cate_name = '"+c+"' and category_2 is not null and category_1 is not null";
                ResultSet rs1 = stmt.executeQuery(sql2);
                if(rs1.next()){
                    id = rs1.getInt("cid");
                }
                String sql3 = "update ilearn_resources set category_3_id =? where category_3=?";
                PreparedStatement preStmt =cnn.prepareStatement(sql3);
                preStmt.setInt(1,id);
                preStmt.setString(2,c);
                preStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertcategory12() {

        try {
            String sql = "select DISTINCT category_2,category_1 from ilearn_resources ";
            Connection cnn=getConn();
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                String sql2="insert into ilearn_category(cate_name,category_1)  values(?,?)";
                PreparedStatement preStmt =cnn.prepareStatement(sql2);
                preStmt.setString(1,rs.getString("category_2"));
                preStmt.setString(2,rs.getString("category_1"));
                preStmt.executeUpdate();
                System.out.println("666666666666666666666666666");
                //或者为rs.getString(1)，根据数据库中列的值类型确定，参数为第一列
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addKey() {
        List<String> key = new ArrayList<String>();
        try {
            String sql = "select  url,title,category_1,category_2,category_3 from ilearn_resources ";
            Connection cnn=getConn();
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                key.add(rs.getString("url")+"@"+rs.getString("title")+rs.getString("category_1")
                        +rs.getString("category_2")+rs.getString("category_3"));
            }
            for(String k:key){
//                String sql2 = "select cid from ilearn_category where cate_name = '"+c+"' and category_2 is not null and category_1 is not null";
//                ResultSet rs1 = stmt.executeQuery(sql2);
//                if(rs1.next()){
//                    id = rs1.getInt("cid");
//                }
                String sql3 = "update ilearn_resources set rkey =? where url=?";
                PreparedStatement preStmt =cnn.prepareStatement(sql3);
                preStmt.setString(1,k.substring(k.indexOf("@")+1));
                preStmt.setString(2,k.substring(0,k.indexOf("@")));
                preStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

