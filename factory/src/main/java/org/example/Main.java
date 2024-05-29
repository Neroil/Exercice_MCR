package org.example;



public class Main {

    public static void main(String[] args) {
        exFactory(args);
    }

    public static void exFactory(String[] args) {
        List<Integer> l = new List<>();
        l.add(1);
        l.add(2);
        l.add(3);

        Array<String> a = new Array<>(3);
        a.add("one");
        a.add("two");
        a.add("three");

        for (Collection<?> collection : new Collection<?>[]{l, a}) {
            Iterator<?> it = collection.iterator(); // fabrication
            StringBuilder s = new StringBuilder("< ");
            while (it.hasNext()) {
                s.append(it.next()).append(" ");
            }
            System.out.println(s + ">");
        }
    }
}