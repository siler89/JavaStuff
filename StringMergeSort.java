/**
 *
 * @author sileR
 */
public class StringMergeSort {
    public static void main(String [] args){
        //String de prueba
        String [] caca = {"que", "me"};
        
        //Imprimimos por pantalla el Array String sin ordenar
        System.out.println("String sin ordenar: ");
        for(int i=0;i<caca.length;i++){
            System.out.println(caca[i]);
        }
        
        System.out.println();
        
        //Aplicamos la ordenacion por 'mergeSort'
        mergeSort(caca);
        
        //Imprimimos por pantalla el Array String ordenado
        System.out.println("String ordenado: ");
        for(int j=0;j<caca.length;j++){
            System.out.println(caca[j]);
        }
        
        
    }
    
    public static void mergeSort(String[] nombres) {
        /*Si tiene mas de 1 elemento se procede ha ordenar, si no, el Array 
        de un solo elemento ya esta ordenado*/
        if (nombres.length > 1) {
            String[] izq = new String[nombres.length / 2];
            String[] dcha = new String[nombres.length - nombres.length / 2];

            //Copiamos el Array 'nombres' al Array 'izq' hasta izq.length
            for(int i = 0; i < izq.length; i++) {
                izq[i] = nombres[i];
            }
            //Copiamos el Array 'nombres' al Array 'dcha' hasta dcha.length
            for (int i = 0; i < dcha.length; i++) {
                dcha[i] = nombres[i + nombres.length / 2];
            }

            mergeSort(izq);
            mergeSort(dcha);
            merge(nombres, izq, dcha);
        }
        
    }

    public static void merge(String[] nombres, String[] izq, String[] dcha) {
        //Indices iniciales de los Arrays: 'izq' y 'dcha'
        int a = 0;
        int b = 0;
        
        /*Recorremos todo el Array inicial, combinando y ordenando las partes segmentadas 
        por el metodo 'mergeSort'*/
        for (int i = 0; i < nombres.length; i++) {
            if (b >= dcha.length || (a < izq.length /* Primero la parte 'izq' y luego la parte 'dcha' */
                    && izq[a].compareToIgnoreCase(dcha[b]) < 0) /* Comparamos si el elemento de la 'izq' es mayor 
                                                                   que el de la 'dcha' */) 
            {
                nombres[i] = izq[a];
                a++;
            } else {
                nombres[i] = dcha[b];
                b++;
            }
        }
    }
}
