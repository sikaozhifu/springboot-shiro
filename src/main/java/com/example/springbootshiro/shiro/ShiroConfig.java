package com.example.springbootshiro.shiro;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Log4j2
public class ShiroConfig {
    /**
     * ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager){
        log.info("进入【shiroFilter】");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/logout", "logout");
        //根据用户的角色赋予相应的权限
        filterChainDefinitionMap.put("/add", "roles[admin]");
        filterChainDefinitionMap.put("/delete", "roles[admin]");
        filterChainDefinitionMap.put("/delete", "roles[author]");
        filterChainDefinitionMap.put("/add", "perms[user:add]");
        filterChainDefinitionMap.put("/delete", "perms[user:delete]");
        filterChainDefinitionMap.put("/userList", "perms[user:list]");
        //   /** 匹配所有的路径
        //  通过Map集合组成了一个拦截器链 ，自顶向下过滤，一旦匹配，则不再执行下面的过滤
        //  如果下面的定义与上面冲突，那按照了谁先定义谁说了算
        //  /** 一定要配置在最后
        filterChainDefinitionMap.put("/**", "authc");
        // 将拦截器链设置到shiro中
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        return shiroFilterFactoryBean;
    }


    /**
     * securityManager
     */

    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm,@Qualifier("rememberMeManager")CookieRememberMeManager rememberMeManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setRealm(myShiroRealm);
        return securityManager;
    }

    /**
     * shiroRealm
     */

    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return new MyShiroRealm();
    }

    /**
     * 密码匹配凭证管理器
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        log.info("hashedCredentialsMatcher()");
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);// 散列的次数，比如散列两次，相当于md5(md5(""));

        return hashedCredentialsMatcher;
    }

    /**
     * cookie对象
     * @return
     */
    @Bean(name = "rememberMeCookie")
    public SimpleCookie rememberMeCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");

        simpleCookie.setHttpOnly(true);
        //设置有效期时间30天
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager(@Qualifier("rememberMeCookie")SimpleCookie rememberMeCookie){

        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie);
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    /**
     * 开启shiro aop注解支持
     * 使用代理方式;所以需要开启代码支持
     * @param securityManager
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * 开启cglib代理
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
