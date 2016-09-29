FROM elasticsearch:2.3.5
ENV ELASTICSEARCH_HOME /usr/share/elasticsearch

# Plugin
RUN $ELASTICSEARCH_HOME/bin/plugin install analysis-kuromoji


# Japanese
ENV LANG ja_JP.UTF-8
ENV LANGUAGE ja_JP.UTF-8
ENV LC_ALL C.UTF-8

