package cn.minuscu1e.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Builder<T> {

    private final Supplier<T> constructor;
    private final List<Consumer<T>> dInject = new ArrayList<>();

    public Builder(Supplier<T> constructor) {
        this.constructor = constructor;
    }

    public static <T> Builder<T> builder(Supplier<T> constructor) {
        return new Builder<>(constructor);
    }

    public <V> Builder<T> with(Builder.DInjectConsumer<T, V> consumer, V v) {
        Consumer<T> c = instance -> consumer.accept(instance, v);
        dInject.add(c);
        return this;
    }

    public T build() {
        T instance = constructor.get();
        dInject.forEach(tConsumer -> {
            tConsumer.accept(instance);
        });

        return instance;
    }


    @FunctionalInterface
    public interface DInjectConsumer<T, V> {
        void accept(T t, V v);
    }
}
