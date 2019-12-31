package com.guyang.spring.boot.config;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 声明式事务配置
 * @date 2019-12-19 17:04
 */
@Configuration
@SuppressWarnings(value = {"all"})
public class TransactionConfig {

    //切点表达式
    private final String transactionExecution = "execution(* com.guyang.spring.boot.service.impl..*.*(..))";

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * spring的类：ProxyTransactionManagementConfiguration 会默认生成name为transactionInterceptor。
     * 那么就需要换个名字以避免冲突。
     * @return
     */
    @Bean
    public TransactionInterceptor myTransactionInterceptor() {
        //只读
        DefaultTransactionAttribute tx_readOnly = new DefaultTransactionAttribute();
        tx_readOnly.setReadOnly(true);

        //用于设置以下事务属性
        //传播行为：默认为PROPAGATION_REQUIRED
        //隔离级别：默认为ISOLATION.DEFAULT,使用dataSource的隔离级别，mysql:ISOLATION_REPEATABLE_READ，oracle:ISOLATION_READ_COMMITTED
        //回滚规则: 默认为RuntimeException.class 及其子类
        //只读:默认为false
        //超时时间：秒、默认是-1，表示无超时时间。
        RuleBasedTransactionAttribute tx_default = new RuleBasedTransactionAttribute();

        //为指定的方法配置事务属性
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("get*", tx_readOnly);
        source.addTransactionalMethod("select*", tx_readOnly);
        source.addTransactionalMethod("find*", tx_readOnly);
        source.addTransactionalMethod("count*", tx_readOnly);

        source.addTransactionalMethod("save*", tx_default);
        source.addTransactionalMethod("insert*", tx_default);
        source.addTransactionalMethod("add*", tx_default);
        source.addTransactionalMethod("update*", tx_default);
        source.addTransactionalMethod("modify*", tx_default);
        source.addTransactionalMethod("del*", tx_default);

        /**
         * @see TransactionInterceptor
         */
        return new TransactionInterceptor(dataSourceTransactionManager, source);
    }

    /**
     * 切点
     * @return
     */
    @Bean
    public AspectJExpressionPointcut aspectJExpressionPointcut() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(transactionExecution);
        return pointcut;
    }

    /**
     * 将事务织入到切面中
     * @param aspectJExpressionPointcut
     * @return
     */
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(AspectJExpressionPointcut aspectJExpressionPointcut) {
        return new DefaultPointcutAdvisor(aspectJExpressionPointcut, myTransactionInterceptor());
    }


}
