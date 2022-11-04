package com.jgr.cliente.feign.loadbalancer.feign.client;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * The Interface LoadBalancerFeignClient.
 * conecta con los metodos de aa-basico
 */
@FeignClient(name="aa-basico",url="localhost:8080/characters")
@LoadBalancerClient(name="aa-basico",configuration=Balanceador.class)
public interface LoadBalancerFeignClient {
	
	/**
	 * Gets the characters game of thrones.
	 *
	 * @return the characters game of thrones
	 */
	@GetMapping("/juegotronos")	
	public Iterable<String> getCharactersGameOfThrones();
	
	
	/**
	 * Gets the characters chuck.
	 *
	 * @return the characters chuck
	 */
	@GetMapping("/chucknorris")
	public Iterable<String> getCharactersChuck();
	
	
	/**
	 * Gets the characters yoda.
	 *
	 * @return the characters yoda
	 */
	@GetMapping("/yoda")
	public Iterable<String> getCharactersYoda();
	
	/**
	 * Gets the characters witcher.
	 *
	 * @return the characters witcher
	 */
	@GetMapping("/witcher")
	public Iterable<String> getCharactersWitcher();

}
