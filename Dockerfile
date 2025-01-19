FROM hseeberger/scala-sbt:8u222_1.3.5_2.13.1

WORKDIR /app
COPY . .
ENV SBT_OPTS="-Xms512M -Xmx2048M"
RUN sbt clean compile
EXPOSE 9000
CMD ["sbt", "run"]
