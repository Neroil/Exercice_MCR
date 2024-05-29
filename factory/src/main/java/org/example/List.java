package org.example;

import javax.swing.text.html.HTMLDocument;

public class List<E> implements Collection<E>{

    private int size;
    private Element head;
    private ListIterator it;

    private class Element{
        private final E element;
        Element next;

        Element(E object, Element next){
            element = object;
            this.next = next;
        }
    }

    @Override
    public void add(E objet) {
        head = new Element(objet, head);
        ++size;
    }

    @Override
    public Iterator<E> iterator() {
        if(it == null){
            it = new ListIterator(head);
        }
        return it;
    }

    private class ListIterator implements Iterator<E> {

        Element current;

        ListIterator(Element e){
            current = e;
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new RuntimeException("Cannot go further than that !");
            E res = current.element;
            current = current.next;

            return res;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }
    }
}
