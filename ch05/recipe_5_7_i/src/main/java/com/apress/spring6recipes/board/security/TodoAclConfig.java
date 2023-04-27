package com.apress.spring6recipes.board.security;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.acls.AclEntryVoter;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.AuditLogger;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy;
import org.springframework.security.acls.domain.SpringCacheBasedAclCache;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;
import java.time.Duration;

@Configuration
public class TodoAclConfig {

	private final DataSource dataSource;

	public TodoAclConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public AclEntryVoter aclEntryVoter(AclService aclService) {
		return new AclEntryVoter(aclService, "ACL_MESSAGE_DELETE",
				new Permission[] { BasePermission.ADMINISTRATION, BasePermission.DELETE });
	}

	@Bean
	public Caffeine<Object, Object> caffeine() {
		return Caffeine.newBuilder().expireAfterWrite(Duration.ofMinutes(15));
	}

	@Bean
	public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
		var cacheManager = new CaffeineCacheManager();
		cacheManager.setCaffeine(caffeine);
		return cacheManager;
	}

	@Bean
	public AuditLogger auditLogger() {
		return new ConsoleAuditLogger();
	}

	@Bean
	public PermissionGrantingStrategy permissionGrantingStrategy(AuditLogger auditLogger) {
		return new DefaultPermissionGrantingStrategy(auditLogger);
	}

	@Bean
	public AclAuthorizationStrategy aclAuthorizationStrategy() {
		return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ADMIN"));
	}

	@Bean
	public AclCache aclCache(CacheManager cacheManager,
													 PermissionGrantingStrategy permissionGrantingStrategy,
													 AclAuthorizationStrategy aclAuthorizationStrategy) {
		var aclCache = cacheManager.getCache("aclCache");
		return new SpringCacheBasedAclCache(aclCache, permissionGrantingStrategy,
				aclAuthorizationStrategy);
	}

	@Bean
	public LookupStrategy lookupStrategy(AclCache aclCache,
																			 PermissionGrantingStrategy permissionGrantingStrategy,
																			 AclAuthorizationStrategy aclAuthorizationStrategy) {
		return new BasicLookupStrategy(this.dataSource, aclCache, aclAuthorizationStrategy, permissionGrantingStrategy);
	}

	@Bean
	public AclService aclService(LookupStrategy lookupStrategy, AclCache aclCache) {
		return new JdbcMutableAclService(this.dataSource, lookupStrategy, aclCache);
	}

	@Bean
	public AclPermissionEvaluator permissionEvaluator(AclService aclService) {
		return new AclPermissionEvaluator(aclService);
	}
}
