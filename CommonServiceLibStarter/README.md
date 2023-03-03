# Setup

## EMQX
- the docker emqx has default password admin/public for the first time.
- change it to admin/test1234
- default publisher/subscriber client names is "${spring.application.name}_Subscriber_Client_random4digit"
  - override by ${hogarNG.mqtt.subscriber.name}
  - 4 digits in the end are auto generated
- _@EnableHogarMqtt_ will enable mqtt connection
  - setup property to subscribe to topics ```hogarNG.mqtt.subscriber.topics=topic1,topic2```
  - implement to handle messages 
  ``` java
   @Bean
    @ServiceActivator(inputChannel = HogarConstants.MQTT_COMMON_INPUT_CHANNEL)
    public MessageHandler handler2() {
        return message -> System.out.println(message.getPayload());
    }
  ```
## Redis Cache
- _@EnableHogarRedis_ will enable redis connection
- use 
``` java
    @Autowired
    HogarCacheUtil cacheUtil;
  ```
or
``` java
    @Autowired
    RedisTemplate template;
```
