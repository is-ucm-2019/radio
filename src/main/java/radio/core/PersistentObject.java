package radio.core;

import java.io.Serializable;

abstract class PersistentObject<T> implements Serializable {
    abstract T getKey();
}
