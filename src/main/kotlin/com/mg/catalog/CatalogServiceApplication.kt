package com.mg.catalog

import com.mg.eventbus.cache.redis.RedisUtil
import com.mg.eventbus.gateway.CommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Import
import org.springframework.context.event.EventListener

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableHystrix
@EnableFeignClients
@EnableCaching
@Import(CommandGateway::class, RedisUtil::class)
class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)

        }
    }

    @Autowired
    lateinit var commandGateway: CommandGateway

/*    @Bean
    fun categoryCache() = ExpirableCache<SimpleCategoryTree>()*/

    @EventListener(ApplicationReadyEvent::class)
    fun onApplicationReadyEvent() {
        commandGateway.onApplicationReadyEvent(this.javaClass.`package`.name)
    }

}
