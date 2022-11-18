package cn.minuscu1e.r2dbc;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Result;
import org.junit.Test;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

public class ConnectionTest {

    @Test
    public void connectFactoryTest() throws InterruptedException {
        // 参数配置
        final ConnectionFactoryOptions options = builder()
                .option(DRIVER, "mysql")
                .option(HOST, "www.minuscu1e.cn")
                .option(PORT, 3306)
                .option(USER, "root")
                .option(PASSWORD, "Salin520.")
                .option(DATABASE, "minuscu1e").build();

        final ConnectionFactory connectionFactory = ConnectionFactories.get(options);

        Mono.from(connectionFactory.create())
                .flatMapMany(connection ->
                        connection.createStatement("insert into system_user(id, username, password) values('SU001', 'test', 'test')")
                                .execute()
                ).flatMap(Result::getRowsUpdated).switchIfEmpty(Mono.just(0))
                .onErrorResume(throwable -> {
                    throwable.printStackTrace();
                    return Mono.empty();
                }).subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(12);
    }
}
