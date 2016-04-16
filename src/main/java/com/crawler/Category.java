package com.crawler;

import com.crawler.util.DBConnection;

import java.util.List;

/**
 * Created by sl on 16-4-12.
 */
public class Category {

    public static void main(String args[]){

        DBConnection db = new DBConnection();

//        List<String> cate3 = db.Qurey("");
//
//        String cate2;
//
//        for(String c : cate3){
//            System.out.println("cate3 = "+c);
//            cate2 = db.QureyCate1(c);
//            System.out.println("cate2 = "+cate2);
////            db.updateValue(cate2,c);
//            db.insertCate(c,"IT/互联网",cate2);
//        }

        db.updateSelf("category_1",null,"IT/互联网");

    }
}
