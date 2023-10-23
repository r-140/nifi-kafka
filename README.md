"# nifi-kafka" 

to create topic run
./create_topic.sh -t <topic_name> 
see help to see other parameters

to run producer:
change topic name on nifi_producer xml file
<entry>
<key>topic</key>
<value><topic name></value>
</entry>
apply this template to apache nifi and run

to run consumer:
java -jar bitstamp-kafka-consumer.jar <topic_name>
