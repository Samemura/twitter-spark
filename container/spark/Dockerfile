FROM sequenceiq/spark:1.6.0

# Japanese
ENV LANG ja_JP.UTF-8
ENV LANGUAGE ja_JP.UTF-8
ENV LC_ALL ja_JP.UTF-8

# Plugin
ENV CLASSPATH=$CLASSPATH:$SPARK_HOME/lib/spark-examples-1.6.0-hadoop2.6.0.jar

ENV KUROMOJI_VERSION 0.7.7
RUN curl -SLO https://github.com/downloads/atilika/kuromoji/kuromoji-$KUROMOJI_VERSION.zip \
  && unzip kuromoji-$KUROMOJI_VERSION.zip \
  && cp -a kuromoji-$KUROMOJI_VERSION/lib/* $SPARK_HOME/lib \
  && rm -r kuromoji-$KUROMOJI_VERSION*
ENV CLASSPATH=$CLASSPATH:$SPARK_HOME/lib/kuromoji-$KUROMOJI_VERSION.jar

ENV ES_HADOOP_VERSION 2.4.0
RUN curl -SLO http://download.elastic.co/hadoop/elasticsearch-hadoop-$ES_HADOOP_VERSION.zip \
  && unzip elasticsearch-hadoop-$ES_HADOOP_VERSION.zip \
  && cp -a elasticsearch-hadoop-$ES_HADOOP_VERSION/dist/* $SPARK_HOME/lib \
  && rm -r elasticsearch-hadoop-$ES_HADOOP_VERSION*
ENV CLASSPATH=$CLASSPATH:$SPARK_HOME/lib/elasticsearch-spark_2.10-2.4.0.jar

# docker run -it -p 8088:8088 -p 8042:8042 -p 4040:4040 -h sandbox sequenceiq/spark:1.6.0 bash