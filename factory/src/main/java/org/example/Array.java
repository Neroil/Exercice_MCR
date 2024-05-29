package org.example;

public class Array<E> implements Collection<E>{

    protected E[] memory;
    protected ArrayIterator it;
    protected int index;

    public Array(int number){
        memory = (E[]) new Object[number];
        index = 0;
    }

    @Override
    public void add(E objet) {
        if(index == memory.length){
            throw new RuntimeException("Array is full !");
        }

        memory[index++] = objet;
    }

    @Override
    public Iterator<E> iterator() {
        if(it == null){
            it = new ArrayIterator();
        }
        return it;
    }

    private class ArrayIterator implements Iterator<E>{

        private int index = 0;

        @Override
        public E next() {
            if(!hasNext())
                throw new RuntimeException("Cannot go further than that !");
            return memory[index++];
        }

        @Override
        public boolean hasNext() {
            return index < memory.length;
        }
    }
}


