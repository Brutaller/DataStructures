package AvlTree;

/**
 * Created by Azat Zaripov on 07.05.16.
 */
public final class MyInteger implements Comparable, Hashable {
    public MyInteger() {
        this( 0 );
    }

    public MyInteger( int x ) {
        value = x;
    }

    public int intValue( ) {
        return value;
    }

    public String toString( ) {
        return Integer.toString( value );
    }

    public int compareTo( Comparable rhs ) {
        return value < ((MyInteger)rhs).value ? -1 :
                value == ((MyInteger)rhs).value ? 0 : 1;
    }

    public boolean equals( Object rhs ) {
        return rhs != null && value == ((MyInteger)rhs).value;
    }

    public int hash( int tableSize ) {
        if( value < 0 )
            return -value % tableSize;
        else
            return value % tableSize;
    }

    private int value;

    @Override
    public int compareTo(Object o) {
        MyInteger i = (MyInteger) o;
        if (value < i.value){
            return -1;
        } else if (value > i.value){
            return 1;
        } else {
            return 0;
        }
    }
}