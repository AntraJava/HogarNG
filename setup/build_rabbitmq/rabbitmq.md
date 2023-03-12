Run `docker build -t rabbitmq_iot .` (don't miss the tailing dot) to build the image with management plugin

-- [Optional]  Test it using `docker run -d  --name hogar_rabbitmq -p 15672:15672 -p 61613:61613 rabbitmq_iot`

Console link:
http://localhost:15672

username/pwd: guest/guest
