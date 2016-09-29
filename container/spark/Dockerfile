FROM sequenceiq/spark:1.6.0

ENV KUROMOJI_VERSION 0.7.7
RUN curl -SLO "https://github.com/downloads/atilika/kuromoji/kuromoji-$KUROMOJI_VERSION.zip" \
  && unzip "kuromoji-$KUROMOJI_VERSION.zip" \
  && cp -a "kuromoji-$KUROMOJI_VERSION/lib" "$SPARK_HOME" \
  && chown 500:500 "$SPARK_HOME/lib" \
  && rm -r kuromoji-$KUROMOJI_VERSION*
ENV CLASSPATH=$CLASSPATH:$SPARK_HOME/lib/spark-examples-1.6.0-hadoop2.6.0.jar:$SPARK_HOME/lib/kuromoji-$KUROMOJI_VERSION.jar
