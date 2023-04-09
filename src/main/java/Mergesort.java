import java.util.ArrayList;
import java.util.List;

public class Mergesort {
    public static <T> List<T> mergesort(List<T> list, int inicio, int termina,Function<T> comparable){
        if (inicio >= termina){
            return list.subList(inicio,inicio+1);
        }
        int mitad = (inicio + termina)/2 ;
        var izq = mergesort(list,inicio,mitad, comparable);
        var der = mergesort(list,mitad + 1,termina,comparable);
        return merge(izq,der,comparable);
    }
    private static <T> List<T> merge(List<T> izq,List<T> der,Function<T> comparable){
        int i = 0;
        int j = 0;
        var devolver = new ArrayList<T>();
        while (i<izq.size() && j<der.size()) {
            if (comparable.apply(izq.get(i),der.get(j))<= 0){
                devolver.add(izq.get(i));
                i++;
            }else {
                devolver.add(der.get(j));
                j++;
            }
        }
        while (i<izq.size()){
            devolver.add(izq.get(i));
            i++;
        }
        while (j<der.size()){
            devolver.add(der.get(j));
            j++;
        }
        return devolver;
    }
}
