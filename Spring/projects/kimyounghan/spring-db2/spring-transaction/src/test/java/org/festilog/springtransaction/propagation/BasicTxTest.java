package org.festilog.springtransaction.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
public class BasicTxTest {

    @Autowired
    PlatformTransactionManager txManager;

    @TestConfiguration
    static class Config {

        /**
         * 원래는 스프링 부트가 트랜잭션 매니저도 자동으로 등록해주는데,
         * 이렇게 직접 빈을 등록하면 그 대신 이것을 사용하게 된다.
         */
        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }

    /**
     * o.f.s.propagation.BasicTxTest            : 트랜잭션 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@918922423 wrapping conn0: url=jdbc:h2:mem:7acbda96-0ea2-4d0a-a6be-420f047a52cb user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@918922423 wrapping conn0: url=jdbc:h2:mem:7acbda96-0ea2-4d0a-a6be-420f047a52cb user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : 트랜잭션 커밋 시작
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction commit
     * o.s.j.d.DataSourceTransactionManager     : Committing JDBC transaction on Connection [HikariProxyConnection@918922423 wrapping conn0: url=jdbc:h2:mem:7acbda96-0ea2-4d0a-a6be-420f047a52cb user=SA]
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@918922423 wrapping conn0: url=jdbc:h2:mem:7acbda96-0ea2-4d0a-a6be-420f047a52cb user=SA] after transaction
     * o.f.s.propagation.BasicTxTest            : 트랜잭션 커밋 완료
     */
    @Test
    void commit() {
        log.info("트랜잭션 시작");
        final TransactionStatus transactionStatus = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 커밋 시작");
        txManager.commit(transactionStatus);
        log.info("트랜잭션 커밋 완료");
    }

    /**
     * o.f.s.propagation.BasicTxTest            : 트랜잭션 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@117556243 wrapping conn0: url=jdbc:h2:mem:933cf4b3-b3ef-48e4-87e8-c8bae8492ca1 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@117556243 wrapping conn0: url=jdbc:h2:mem:933cf4b3-b3ef-48e4-87e8-c8bae8492ca1 user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : 트랜잭션 롤백 시작
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction rollback
     * o.s.j.d.DataSourceTransactionManager     : Rolling back JDBC transaction on Connection [HikariProxyConnection@117556243 wrapping conn0: url=jdbc:h2:mem:933cf4b3-b3ef-48e4-87e8-c8bae8492ca1 user=SA]
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@117556243 wrapping conn0: url=jdbc:h2:mem:933cf4b3-b3ef-48e4-87e8-c8bae8492ca1 user=SA] after transaction
     * o.f.s.propagation.BasicTxTest            : 트랜잭션 롤백 완료
     */
    @Test
    void rollback() {
        log.info("트랜잭션 시작");
        final TransactionStatus transactionStatus = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 롤백 시작");
        txManager.rollback(transactionStatus);
        log.info("트랜잭션 롤백 완료");
    }

    /**
     * o.f.s.propagation.BasicTxTest            : 트랜잭션1 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * <p>
     * 👉 'conn0' 커넥션 획득
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@1114712230 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@1114712230 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : 트랜잭션1 커밋
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction commit
     * o.s.j.d.DataSourceTransactionManager     : Committing JDBC transaction on Connection [HikariProxyConnection@1114712230 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA]
     * <p>
     * 👉 'conn0' 커넥션 반납
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@1114712230 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA] after transaction
     * <p>
     * <p>
     * <p>
     * o.f.s.propagation.BasicTxTest            : 트랜잭션2 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * <p>
     * 👉 'conn0' 커넥션 획득(HikariProxyConnection@12345678 처럼 커넥션은 다르지만, 실제로는 같은 물리 커넥션인 'conn0'을 사용.
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@940656203 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@940656203 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : 트랜잭션2 커밋
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction commit
     * o.s.j.d.DataSourceTransactionManager     : Committing JDBC transaction on Connection [HikariProxyConnection@940656203 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA]
     * <p>
     * 👉 'conn0' 커넥션 반납
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@940656203 wrapping conn0: url=jdbc:h2:mem:e3caf10b-873a-4f65-b1f4-d921e9604470 user=SA] after transaction
     */
    @Test
    void double_commit() {
        log.info("트랜잭션1 시작");
        final TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션1 커밋");
        txManager.commit(tx1);

        log.info("트랜잭션2 시작");
        final TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션2 커밋");
        txManager.commit(tx2);
    }

    /**
     * o.f.s.propagation.BasicTxTest            : 트랜잭션1 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@1612491156 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@1612491156 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : 트랜잭션1 커밋
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction commit
     * o.s.j.d.DataSourceTransactionManager     : Committing JDBC transaction on Connection [HikariProxyConnection@1612491156 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA]
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@1612491156 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA] after transaction
     * <p>
     * o.f.s.propagation.BasicTxTest            : 트랜잭션2 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@1770893302 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@1770893302 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : 트랜잭션2 롤백
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction rollback
     * o.s.j.d.DataSourceTransactionManager     : Rolling back JDBC transaction on Connection [HikariProxyConnection@1770893302 wrapping conn0: url=jdbc:h2:mem:e9b33176-c1ea-49d0-9cfa-259c057beac3 user=SA]
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@1770893302 wrapping conn0:
     */
    @Test
    void double_commit_rollback() { // 👉 트랜잭션 2개가 각각 수행
        log.info("트랜잭션1 시작");
        final TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션1 커밋");
        txManager.commit(tx1);

        log.info("트랜잭션2 시작");
        final TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션2 롤백");
        txManager.rollback(tx2);
    }

    /**
     * o.f.s.propagation.BasicTxTest            : 외부 트랜잭션 시작 👈
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@629230908 wrapping conn0: url=jdbc:h2:mem:6dc24db1-a471-4342-91d0-1fe3c1ff985e user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@629230908 wrapping conn0: url=jdbc:h2:mem:6dc24db1-a471-4342-91d0-1fe3c1ff985e user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : outer.isNewTransaction()=true
     * <p>
     * o.f.s.propagation.BasicTxTest            : 내부 트랜잭션 시작 👈
     * o.s.j.d.DataSourceTransactionManager     : Participating in existing transaction
     * o.f.s.propagation.BasicTxTest            : inner.isNewTransaction()=false
     * o.f.s.propagation.BasicTxTest            : 내부 트랜잭션 커밋
     * <p>
     * o.f.s.propagation.BasicTxTest            : 외부 트랜잭션 커밋 👈
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction commit
     * o.s.j.d.DataSourceTransactionManager     : Committing JDBC transaction on Connection [HikariProxyConnection@629230908 wrapping conn0: url=jdbc:h2:mem:6dc24db1-a471-4342-91d0-1fe3c1ff985e user=SA]
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@629230908 wrapping conn0: url=jdbc:h2:mem:6dc24db1-a471-4342-91d0-1fe3c1ff985e user=SA] after transaction
     */
    @Test
    void inner_commit() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute()); // 외부 트랜잭션을 하고 있는데 또 트랜잭션을 시작. 이것이 바로 내부 트랜잭션.
        log.info("inner.isNewTransaction()={}", inner.isNewTransaction());
        log.info("내부 트랜잭션 커밋");
        txManager.commit(inner);

        log.info("외부 트랜잭션 커밋");
        txManager.commit(outer);
    }

    /**
     * o.f.s.propagation.BasicTxTest            : 외부 트랜잭션 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@1247158141 wrapping conn0: url=jdbc:h2:mem:42e400d3-e6ee-414d-b2f9-1e78f23d8002 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@1247158141 wrapping conn0: url=jdbc:h2:mem:42e400d3-e6ee-414d-b2f9-1e78f23d8002 user=SA] to manual commit 👈 물리 트랜잭션 시작 = setAutoCommit(false)
     * o.f.s.propagation.BasicTxTest            : outer.isNewTransaction()=true
     * o.f.s.propagation.BasicTxTest            : 내부 트랜잭션 시작
     * o.s.j.d.DataSourceTransactionManager     : Participating in existing transaction
     * o.f.s.propagation.BasicTxTest            : inner.isNewTransaction()=false
     * o.f.s.propagation.BasicTxTest            : 내부 트랜잭션 커밋
     * o.f.s.propagation.BasicTxTest            : 외부 트랜잭션 롤백
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction rollback
     * o.s.j.d.DataSourceTransactionManager     : Rolling back JDBC transaction on Connection [HikariProxyConnection@1247158141 wrapping conn0: url=jdbc:h2:mem:42e400d3-e6ee-414d-b2f9-1e78f23d8002 user=SA]
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@1247158141 wrapping conn0: url=jdbc:h2:mem:42e400d3-e6ee-414d-b2f9-1e78f23d8002 user=SA] after transaction
     */
    @Test
    void outer_rollback() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("inner.isNewTransaction()={}", inner.isNewTransaction());
        log.info("내부 트랜잭션 커밋");
        txManager.commit(inner);

        log.info("외부 트랜잭션 롤백");
        txManager.rollback(outer);
    }

    /**
     * o.f.s.propagation.BasicTxTest            : 외부 트랜잭션 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@1247158141 wrapping conn0: url=jdbc:h2:mem:616152a3-8cda-4c86-aa3e-2c2bc523c913 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@1247158141 wrapping conn0: url=jdbc:h2:mem:616152a3-8cda-4c86-aa3e-2c2bc523c913 user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : outer.isNewTransaction()=true
     * <p>
     * o.f.s.propagation.BasicTxTest            : 내부 트랜잭션 시작
     * o.s.j.d.DataSourceTransactionManager     : Participating in existing transaction
     * o.f.s.propagation.BasicTxTest            : inner.isNewTransaction()=false
     * o.f.s.propagation.BasicTxTest            : 내부 트랜잭션 롤백
     * o.s.j.d.DataSourceTransactionManager     : Participating transaction failed - marking existing transaction as rollback-only 👈 내부 트랜잭션에서 참여 중인 트랜잭션에 rollback-only로 마킹한다.
     * o.s.j.d.DataSourceTransactionManager     : Setting JDBC transaction [HikariProxyConnection@1247158141 wrapping conn0: url=jdbc:h2:mem:616152a3-8cda-4c86-aa3e-2c2bc523c913 user=SA] rollback-only
     * <p>
     * o.f.s.propagation.BasicTxTest            : 외부 트랜잭션 커밋
     * o.s.j.d.DataSourceTransactionManager     : Global transaction is marked as rollback-only but transactional code requested commit
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction rollback 👈
     * o.s.j.d.DataSourceTransactionManager     : Rolling back JDBC transaction on Connection [HikariProxyConnection@1247158141 wrapping conn0: url=jdbc:h2:mem:616152a3-8cda-4c86-aa3e-2c2bc523c913 user=SA]
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@1247158141 wrapping conn0: url=jdbc:h2:mem:616152a3-8cda-4c86-aa3e-2c2bc523c913 user=SA] after transaction
     * <p>
     * org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only
     * <p>
     * at org.springframework.transaction.support.AbstractPlatformTransactionManager.processRollback(AbstractPlatformTransactionManager.java:938)
     * at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:754)
     * at org.festilog.springtransaction.propagation.BasicTxTest.inner_rollback(BasicTxTest.java:228)
     * at java.base/java.lang.reflect.Method.invoke(Method.java:568)
     * at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
     * at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
     */
    @Test
    void inner_rollback() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("inner.isNewTransaction()={}", inner.isNewTransaction());
        log.info("내부 트랜잭션 롤백");
        txManager.rollback(inner); // rollback-only로 어딘가에 표시.

        log.info("외부 트랜잭션 커밋");
        assertThatThrownBy(() -> txManager.commit(outer))
                .isInstanceOf(UnexpectedRollbackException.class);
    }

    /**
     * o.f.s.propagation.BasicTxTest            : 외부 트랜잭션 시작
     * o.s.j.d.DataSourceTransactionManager     : Creating new transaction with name [null]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@1676763984 wrapping conn0: url=jdbc:h2:mem:0d0f2d3f-66f0-43ec-954a-82e327823244 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@1676763984 wrapping conn0: url=jdbc:h2:mem:0d0f2d3f-66f0-43ec-954a-82e327823244 user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : outer.isNewTransaction()=true
     * <p>
     * o.f.s.propagation.BasicTxTest            : 내부 트랜잭션 시작
     * o.s.j.d.DataSourceTransactionManager     : Suspending current transaction, creating new transaction with name [null]
     * o.s.j.d.DataSourceTransactionManager     : Acquired Connection [HikariProxyConnection@1594513480 wrapping conn1: url=jdbc:h2:mem:0d0f2d3f-66f0-43ec-954a-82e327823244 user=SA] for JDBC transaction
     * o.s.j.d.DataSourceTransactionManager     : Switching JDBC Connection [HikariProxyConnection@1594513480 wrapping conn1: url=jdbc:h2:mem:0d0f2d3f-66f0-43ec-954a-82e327823244 user=SA] to manual commit
     * o.f.s.propagation.BasicTxTest            : inner.isNewTransaction()=true
     * <p>
     * o.f.s.propagation.BasicTxTest            : 내부 트랜잭션 롤백
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction rollback
     * o.s.j.d.DataSourceTransactionManager     : Rolling back JDBC transaction on Connection [HikariProxyConnection@1594513480 wrapping conn1: url=jdbc:h2:mem:0d0f2d3f-66f0-43ec-954a-82e327823244 user=SA]
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@1594513480 wrapping conn1: url=jdbc:h2:mem:0d0f2d3f-66f0-43ec-954a-82e327823244 user=SA] after transaction
     * o.s.j.d.DataSourceTransactionManager     : Resuming suspended transaction after completion of inner transaction
     * <p>
     * o.f.s.propagation.BasicTxTest            : 외부 트랜잭션 커밋
     * o.s.j.d.DataSourceTransactionManager     : Initiating transaction commit
     * o.s.j.d.DataSourceTransactionManager     : Committing JDBC transaction on Connection [HikariProxyConnection@1676763984 wrapping conn0: url=jdbc:h2:mem:0d0f2d3f-66f0-43ec-954a-82e327823244 user=SA]
     * o.s.j.d.DataSourceTransactionManager     : Releasing JDBC Connection [HikariProxyConnection@1676763984 wrapping conn0: url=jdbc:h2:mem:0d0f2d3f-66f0-43ec-954a-82e327823244 user=SA] after transaction
     */
    @Test
    void inner_rollback_requires_new() {
        // 외부 시작
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

        // 내부 시작
        log.info("내부 트랜잭션 시작");
        final DefaultTransactionAttribute definition = new DefaultTransactionAttribute();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // REQUIRES_NEW
        TransactionStatus inner = txManager.getTransaction(definition);
        log.info("inner.isNewTransaction()={}", inner.isNewTransaction());

        // 내부 롤백
        log.info("내부 트랜잭션 롤백");
        txManager.rollback(inner); // rollback-only로 어딘가에 표시.

        // 외부 커밋
        log.info("외부 트랜잭션 커밋");
        txManager.commit(outer);
    }
}
