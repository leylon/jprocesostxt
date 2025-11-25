FROM openjdk:8-jre-alpine

ARG DYNAMIC_DB=test-firebird
RUN apk update
RUN apk add vim
RUN apk add tzdata
RUN cp /usr/share/zoneinfo/America/Lima /etc/localtime

RUN echo "America/Lima" >  /etc/timezone
RUN rm -r /usr/share/zoneinfo/Africa && \
    rm -r /usr/share/zoneinfo/Antarctica && \
    rm -r /usr/share/zoneinfo/Arctic && \
    rm -r /usr/share/zoneinfo/Asia && \
    rm -r /usr/share/zoneinfo/Atlantic && \
    rm -r /usr/share/zoneinfo/Australia && \
    rm -r /usr/share/zoneinfo/Europe  && \
    rm -r /usr/share/zoneinfo/Indian && \
    rm -r /usr/share/zoneinfo/Mexico && \
    rm -r /usr/share/zoneinfo/Pacific && \
    rm -r /usr/share/zoneinfo/Chile && \
    rm -r /usr/share/zoneinfo/Canada


ENV BD_ENV=${DYNAMIC_DB}
ENV BD_ENCODING=ISO-8859-1
ENV TZ=America/Lima
ENV LANG=es_ES.UTF-8 \
    LANGUAGE=es_ES.UTF-8
ENV LC_ALL=es_ES.UTF-8


WORKDIR /app
COPY dist/ .
CMD java -Dfile.encoding=$BD_ENCODING -Dsun.jnu.encoding=$BD_ENCODING -jar JProcesoTXTSeres.jar $BD_ENV