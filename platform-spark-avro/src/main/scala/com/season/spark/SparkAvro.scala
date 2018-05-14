package com.season.spark

import org.apache.avro.generic.GenericRecord
import org.apache.avro.mapred.{AvroInputFormat, AvroWrapper}
import org.apache.hadoop.io.NullWritable
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by yingchun on 2017/8/29.
  */
object SparkAvro {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("spark avro").setMaster("local")
    val sc = new SparkContext(conf)
    // import needed for the .avro method to be added
    import com.databricks.spark.avro._

    val spark = new SQLContext(sc)
   // val spark = SparkSession.builder().master("local").getOrCreate()

    val path = "/Users/teambition/jiyc/mongo_slave_teambition_users_avro_processed+0+0150518637+0150562265.avro"
    val df = spark.read.avro(path).filter("updated is not null").select("_id","updated","get_json_object(lastEntered,’$.web’)")
    //println(df.count())
    df.show(false)
    //df.registerTempTable("table01")
    //df.createOrReplaceTempView("tmp")

    //spark.sql(s"select _id,updated from table01")


    // The Avro records get converted to Spark types, filtered, and
    // then written back out as Avro records
    /*val df = sqlContext.read
      .format("com.databricks.spark.avro")
      .load("file/data/avro/input/episodes.avro")
    df.show

    val iString = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())

    df.filter("doctor > 5").write
      .format("com.databricks.spark.avro")
      .save("file/data/avro/output/episodes/avro" + iString)
    df.filter("doctor > 5").show*/
  }
}
