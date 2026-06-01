- Figure out the internals of spring boot
- org.boot: this provides you with bootrun and packages everything into an executable .jar for you
- org.boot.autoconfigure: looks for your gradle file and configures everything accordingly
- @SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class})
    You can use this to exclude specific things from your redis 