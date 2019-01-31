package com.oilgascs.gps.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.oilgascs.gps.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.Nomination.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.Nomination.class.getName() + ".priorities", jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.ReductionReason.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.Activity.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.RateSched.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.RateSched.class.getName() + ".rtSchedValds", jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.RateSchedVald.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.NominationPriority.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.Contract.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.Contract.class.getName() + ".contrLocs", jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.ContrLoc.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.BusinessAssociate.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.BusinessAssociate.class.getName() + ".contracts", jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.BusinessAssociate.class.getName() + ".businessAssociateAddresses", jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.BusinessAssociateAddress.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.MeasurementStation.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.LocationBA.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.Contact.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.BusinessUnit.class.getName(), jcacheConfiguration);
            cm.createCache(com.oilgascs.gps.domain.BusinessAssociateContact.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
