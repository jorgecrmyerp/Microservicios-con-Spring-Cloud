package com.jgr.micro.cursos.cache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class CursosConfiguracionCache.
 * para la configuracion del cache
 */

@EnableCaching
@Configuration
public class CursosConfiguracionCache {
	
	/**
	 * Gets the cache manager.
	 *para el tratamiento del cache.
	 *En el metodo que lo va a utilizar tengo que poner 
	 *"@Cacheable(nombre que le he dado aqui configuracionCache)"	 
	 *
	 * @return the cache manager
	 */
	@Bean
	public CacheManager getCacheManager() {
		return new ConcurrentMapCacheManager("configuracionCache");
	}

}
