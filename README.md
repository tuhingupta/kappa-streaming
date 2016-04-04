# kappa-streaming

Kappa Architecture streaming project, that is based on a immutable log appender model. This project provides an implementation of streaming a very large file using vert.x client to a vert.x server. Parsing data on server and sending the records to Kafka Queue.

Then records are read by Kafka queue and processed by Apache Flink framework.

It is a maven multi-module project. It contains following modules

*  vertx-reactive-client - reads a very large file and streams it to server
*  vertx-reactive-server - non-blocking server, that reads stream of data from client, parses data and sends it to Kafka queue.
*  
