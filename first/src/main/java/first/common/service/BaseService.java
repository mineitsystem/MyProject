package first.common.service;

import javax.annotation.Resource;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

public class BaseService extends AbstractServiceImpl {

    @Resource(name = "txManager")
    protected PlatformTransactionManager transactionManager;

    /**
     * 개별 트랜잭션 처리를 위해 TransactionStatus 객체를 얻는다.
     * @return
     */
    protected TransactionStatus getTransaction(int propagation) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(propagation);
        return transactionManager.getTransaction(definition);
    }

    /**
     * 개별 트랜잭션 처리를 위해 TransactionStatus 객체를 얻는다.
     * @return
     */
    protected TransactionStatus getTransaction() {
        return getTransaction(TransactionDefinition.PROPAGATION_REQUIRED);
    }
}
