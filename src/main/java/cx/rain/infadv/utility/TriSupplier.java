package cx.rain.infadv.utility;

@FunctionalInterface
public interface TriSupplier<T1, T2, T3> {
    void apply(T1 t1, T2 t2, T3 t3);
}
