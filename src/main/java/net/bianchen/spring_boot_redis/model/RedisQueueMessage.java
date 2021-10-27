package net.bianchen.spring_boot_redis.model;

public class RedisQueueMessage {
	private String content;
	
	public RedisQueueMessage() {
    }
	
	public RedisQueueMessage(String content) {
        this.content = content;
    }
}
