# vertx-reactive-server 

Written in vert.x

Non-blocking server, that reads stream of data from client, parses data and sends it to Kafka queue.  


Kafka is running on spotify/kafka docker image. To create a local container to consume messages you can use following command:

`$docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 spotify/kafka &`

Look at code in `StreamingServer` on how to read record by record of streaming data and queue into Kafka.