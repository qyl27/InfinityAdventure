package cx.rain.infadv.utility;

public interface Self<T> {

    default T self() {
        return (T) this;
    }
}
