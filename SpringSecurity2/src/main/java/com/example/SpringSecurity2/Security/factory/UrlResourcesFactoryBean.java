package com.example.SpringSecurity2.Security.factory;

import com.example.SpringSecurity2.Service.SecurityResourceService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.security.Security;
import java.util.Collection;
import java.util.LinkedHashMap;

@Component
public class UrlResourcesFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>> {

    private SecurityResourceService securityResourceService;
    private LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> resourceMap;

    @Override
    public LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> getObject() throws Exception {

        if(resourceMap == null) {
            init();
        }

        return resourceMap;
    }

    private void init() {
        resourceMap = securityResourceService.getResourceList();
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setSecurityResourceService(SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
    }
}
