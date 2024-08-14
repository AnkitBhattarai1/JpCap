package org.jpcap.Core.Constants.NamedCodes;

public abstract class NamedCode<T extends Number, U extends NamedCode<T, ?>>
        implements Comparable<U> {
    private final T value;
    private final String name;

    protected NamedCode(T value, String name) {
        if (value == null || name == null)
            throw new IllegalArgumentException(value == null ? "value" : "name" + "cannot be null");

        this.value = value;
        this.name = name;
    }

    public T getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public abstract int compareTo(U o);
}
