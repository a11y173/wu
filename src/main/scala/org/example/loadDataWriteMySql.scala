package org.example

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame


object loadDataWriteMySql {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local").appName("loadDataToMySql").getOrCreate()

    def readExcel(file: String): DataFrame = spark.read
      .format("com.crealytics.spark.excel")
      .option("location", file)
      .option("header", "true")
      .option("treatEmptyValuesAsNulls", "true")
      .option("inferSchema", "true")
      .option("addColorColumns", "False")
      .load(file)

    val me_address = readExcel("/home/dunalang/duanlang/data/me_url_address.xlsx")
    me_address.show()
  }
}