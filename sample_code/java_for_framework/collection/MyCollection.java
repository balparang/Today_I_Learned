package sample_code.java_for_framework.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyCollection<T> {
    private List<T> list;

    public MyCollection(List<T> list) {
        this.list = list;
    }

    public <U> MyCollection<U> map(Function<T, U> function) {
        List<U> newList = new ArrayList<>();
        forEach(data -> newList.add(function.apply(data)));
        return new MyCollection<>(newList);
    }

    public MyCollection<T> filter(Predicate<T> predicate) {
        List<T> newList = new ArrayList<>();
        forEach(data -> {
            if (predicate.test(data)) newList.add(data);
        });
        return new MyCollection<>(newList);
    }

    public void forEach(Consumer<T> consumer) {
        for (T each : list) {
            consumer.accept(each);
        }
    }

    public int size() {
        return list.size();
    }
}
