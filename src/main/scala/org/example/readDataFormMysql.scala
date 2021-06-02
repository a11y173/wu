package org.example

import org.apache.spark.sql.SparkSession

object readDataFormMysql {
  def main(args:Array[String]): Unit ={
    val spark = SparkSession.builder().appName("readDataFromMysql").master("local").getOrCreate()
    val jdbc = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/hive?useUnicode=true&characterEncoding=utf-8")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("dbtable", "employees.titles")
      .option("user", "root")
      .option("password", "root")
      .load()
    jdbc.show()
  }
}
