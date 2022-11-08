package com.jgr.micro.cursos.cache.redis.config;

import java.util.HashMap;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class CursosConfiguracionCache.
 * para la configuracion del cache
 * * INCLUIR EN EL POM 
 * <!-- https://mvnrepository.com/artifact/org.redisson/redisson -->
<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson</artifactId>
    <version>3.17.7</version>
</dependency>
**TB SE INSTALA CON DOCKER LA IMAGEN DE REDIS
 *
 */

@EnableCaching
@Configuration
public class AnCursosConfiguracionCacheRedis {
	

	/**
	 * Redisson.
	 * creamos el bean de redisson
	 *
	 * @return the redisson client
	 */
	@Bean(destroyMethod = "shutdown")
	public RedissonClient redisson() {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://127.0.0.1:6379");//ip:puerto donde esta levantado el servidor redis
		return Redisson.create(config);
	}

	/**
	 * Gets the manager.
	 *configuracion de redisson,se inyecta desde el bean que he definido aqui
	 *el nombre de configuracionRedis lo pongo en los metodos del servicio que necesite
	 * @param redissonClient the redisson client
	 * @return the manager
	 */
	@Bean
	public CacheManager getManager(RedissonClient redissonClient) {
		Map<String, CacheConfig> config = new HashMap<>();
		config.put("configuracionRedis", new CacheConfig());
		return new RedissonSpringCacheManager(redissonClient);

	}
	
	

}
