import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming.dstream._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import org.elasticsearch.spark._
import org.elasticsearch.spark.rdd.EsSpark

// Twitterへのアクセスアカウント情報を定義する
System.setProperty("twitter4j.oauth.consumerKey", "xxxxxxxxxxxxxxxxxxxx")
System.setProperty("twitter4j.oauth.consumerSecret", "xxxxxxxxxxxxxxxxxxxx")
System.setProperty("twitter4j.oauth.accessToken", "xxxxxxxxxxxxxxxxxxxx")
System.setProperty("twitter4j.oauth.accessTokenSecret", "xxxxxxxxxxxxxxxxxxxx")

// sc is automatically created by spark-shell
// http://stackoverflow.com/questions/30662084/fail-to-create-sparkcontext
// https://spark.apache.org/docs/latest/api/java/org/apache/spark/SparkContext.html#setLocalProperty(java.lang.String,%20java.lang.String)
// SparkContext.getConf is returned just
// sc shall be stopped then created again.
// http://developer.couchbase.com/documentation/server/current/connectors/spark-1.0/spark-shell.html

sc.stop()
val sc = new SparkContext(
  new SparkConf().
    setAppName("Spark Streaming Twitter to Elasticsearch").
    setMaster("local[*]").
    set("es.nodes", "elasticsearch").
    set("es.index.auto.create", "true")
)

val ssc = new StreamingContext(sc, Seconds(2))
val index_name = "twitter"
val type_name = "iPad" // Search Word

TwitterUtils.createStream(ssc, None, Array(type_name)).
  // TwitterのデータをJSON化
  map ( status => {
     var lonlat : Array[Double] = Array()
     val hashmap = new java.util.HashMap[String, Object]()
     hashmap.put("user_name", status.getUser().getName())
     hashmap.put("user_lang", status.getUser().getLang())
     hashmap.put("text", status.getText())
     hashmap.put("@create_at", new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").format(status.getCreatedAt()))
     if(status.getGeoLocation() != null) {
         lonlat = Array(status.getGeoLocation().getLongitude(), status.getGeoLocation().getLatitude())
     }
     hashmap.put("location", lonlat)

    (new org.json.JSONObject(hashmap).toString())
  }).
  // elasticsearchへデータを投入
  foreachRDD(jsonRDD => {
     EsSpark.saveJsonToEs(jsonRDD, index_name+"/"+type_name)
  }
)

ssc.start()
