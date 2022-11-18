package cn.minuscu1e.r2dbc;

import cn.minuscu1e.r2dbc.pojo.SystemUser;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Result;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

public class ConnectionTest {
    private ConnectionFactory connectionFactory;

    @Before
    public void init() {
        // 参数配置
        final ConnectionFactoryOptions options = builder()
                .option(DRIVER, "mysql")
                .option(HOST, "www.minuscu1e.cn")
                .option(PORT, 3306)
                .option(USER, "root")
                .option(PASSWORD, "Salin520.")
                .option(DATABASE, "minuscu1e").build();

        connectionFactory = ConnectionFactories.get(options);
    }

    @Test
    public void connectFactoryTest() throws InterruptedException {
        Mono.from(connectionFactory.create())
                .flatMapMany(connection ->
                        connection.createStatement("insert into system_user(id, username, password) values('SU001', 'test', 'test')")
                                .execute()
                ).flatMap(Result::getRowsUpdated).switchIfEmpty(Mono.just(0))
                .onErrorResume(throwable -> {
                    throwable.printStackTrace();
                    return Mono.empty();
                }).subscribe(System.out::println);
    }

    @Test
    public void select() throws InterruptedException {


        final Flux<String> username = Mono.from(connectionFactory.create())
                .flatMapMany(connection -> connection.createStatement("select * from system_user").execute())
                .flatMap(result -> result.map((row, rowMetadata) -> (String) row.get("username"))).
                onErrorResume(throwable -> {
                    throwable.printStackTrace();
                    return Mono.empty();
                });
        username
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(5);
    }

}
