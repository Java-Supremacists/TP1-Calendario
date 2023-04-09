@FunctionalInterface
public interface Function <T> {
    int apply(T a1,T a2);
    //if a1 > a2 return mayor o igual a 1
    //if a1 == a2 return 0
    //if a1 < a2 return menor o igual a -1
}
