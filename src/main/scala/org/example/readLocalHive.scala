package org.example

import java.io.{File, PrintWriter}
import java.sql.{Connection, DriverManager, ResultSet, Statement}

import org.json.{JSONArray, JSONObject}

object readLocalHive {


  def main(args: Array[String]): Unit = {

    val driverName:String = "org.apache.hive.jdbc.HiveDriver"
    try {
      Class.forName(driverName)
    } catch{
      case e: ClassNotFoundException =>
        println("Missing Class",e)
    }

    val con:Connection =DriverManager.getConnection("jdbc:hive2://localhost:10000/wu")
    val stmt:Statement = con.createStatement()
    val res:ResultSet = stmt.executeQuery("show tables")
    System.out.println("------"+res)

    while (res.next()) {
      System.out.println(res.getString(1))
    }

    val jsonArray:JSONArray=new JSONArray()
    val rs:ResultSet = stmt.executeQuery("select * from wu.test_tb")
    System.out.println(rs)

    while (rs.next()){
      System.out.println(rs.getString(1))
    }
  }

}
