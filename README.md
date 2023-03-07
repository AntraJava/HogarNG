# Grafana


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
