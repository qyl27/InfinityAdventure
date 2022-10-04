package cx.rain.infadv.util;

public interface Self<T> {

    default T self() {
        return (T) this;
    }
}
