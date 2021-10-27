package net.bianchen.spring_boot_redis.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class Pub {
	@Autowired
    private RedisTemplate<String, Object> rt;
    
    
    public void sendMessage(String channel, String message) {  
        rt.convertAndSend(channel, message);  
    }
}
