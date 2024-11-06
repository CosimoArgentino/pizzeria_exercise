package awesome.pizzeria;

import awesome.pizzeria.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Order> orderRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Order> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}