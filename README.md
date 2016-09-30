# This repo
This is trial codes to work with Apache Spark, Elasticsearch and Kibana on Docker.

Refer to:

http://www.intellilink.co.jp/article/column/bigdata-kk01.html

http://www.intellilink.co.jp/article/column/bigdata-kk02.html

http://d.hatena.ne.jp/Kazuhira/20160611/1465633327

## Installation

1. git clone this repo
1. docker-compose build
1. docker-compose run --rm spark bash
1. spark-shell
1. replace your twitter credentials first in below files.
1. copy and paste either below files.

## files
### twitter_streaming.scala
fetch twitter streaming and separate to word. Result will be shown on terminal.

### twitter_toelasticsearch.scala
fetch twitter streaming and save to elasctisearch. Result will be shown on Kibana.
http://localhost:5601