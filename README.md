
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

# RabbitMQ - websocket
- start management docker
- rabbitmq-plugins enable rabbitmq_stomp
- create virtualHost
- /etc/rabbitmq/conf.d/ for configurations
- https://www.rabbitmq.com/stomp.html
- it uses dot as delimiter in topics
# Grafana
# Loki
# Prometheus
- clients apps need change properties to
    ```
    eureka.instance.metadataMap.prometheus.path=/actuator/prometheus
    eureka:
      instance:
        metadataMap:
          "prometheus.path": "/actuator/prometheus"
    ```

- Prometheus server config
  
    ```
      scrape_configs:
      # The job name is added as a label `job=<job_name>` to any timeseries scraped from this config.
      - job_name: "prometheus"
    
        # metrics_path defaults to '/metrics'
        # scheme defaults to 'http'.
    
        static_configs:
          - targets: ["localhost:9090"]
      - job_name: 'hogar_eureka'
    
        # Scrape Eureka itself to discover new services.
        eureka_sd_configs:
          - server: http://host.docker.internal:8761/eureka
        relabel_configs:```
- watch out the host name it runs inside docker locally
