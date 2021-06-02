package org.example

import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types._

case class titles(emp_no:BigInt,title:String,from_date:String,to_date:String)

object mysql2Hive{
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("loadDataFromMysql2Hive")
      .config("spark.sql.warehouse.dir", "/home/dunalang/duanlang/plat/warehourse")
      .master("local")
      .enableHiveSupport()
      .getOrCreate()

    val title = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/employees?useUnicode=true&characterEncoding=utf-8")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("dbtable", "titles")
      .option("user", "root")
      .option("password", "root")
      .load()

//    title.write
//      .format("jdbc")
//      .option("url", "jdbc:hive2://localhost:10000/wu")
//      .option("dbtable", "wu.titles")
//      .mode(SaveMode.Append)
//      .save()
    title.createOrReplaceTempView("title")

    spark.sql("insert into wu.titles select emp_no,title,from_date,to_date from title")
  }
}