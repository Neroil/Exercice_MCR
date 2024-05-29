package org.example;

import jdk.jshell.spi.ExecutionControl;

import java.util.LinkedList;
import java.util.List;

/**
 * In this exercise, you'll implement the Composite Design Pattern to represent
 * a hierarchical file system. The file system consists of directories and files.
 * Directories can contain both files and other directories.
 *
 * You need to define the following components:
 *
 * 1. A Component interface that declares the operations common to both files and directories.
 * 2. A File class that implements the Component interface and represents a file in the file system.
 * 3. A Directory class that implements the Component interface and represents a directory in the file system.
 *
 * The expected output when running the `main` method should be:
 *
 * /
 * ├── Documents
 * │   ├── file1.txt
 * │   └── file2.txt
 * ├── Music
 * │   ├── Album1
 * │   │   ├── song1.mp3
 * │   │   └── song2.mp3
 * │   └── Album2
 * │       ├── song3.mp3
 * │       └── song4.mp3
 * └── Pictures
 *     ├── photo1.jpg
 *     └── photo2.jpg
 */

abstract class Component{
    protected String name;

    Component(String name){
        this.name = name;
    }

    boolean checkCycle(Component c){
        return false;
    }

    void display(){
        System.out.print(name);
    }

    void display(String indent, boolean isLast){
        System.out.print(indent);
        if (!indent.isEmpty()) {
            System.out.print(isLast ? "└── " : "├── ");
        }
        System.out.println(name);
    }
}

class Directory extends Component{

    List<Component> subComponents = new LinkedList<>();

    Directory(String name){
        super(name);
    }

    void display(){
        display("", true);
    }

    void display(String indent, boolean isLast) {
        System.out.print(indent);
        if (!indent.isEmpty()) {
            System.out.print(isLast ? "└── " : "├── ");
        }
        System.out.println(name);

        String newIndent = indent + (isLast ? "    " : "│   ");
        int size = subComponents.size();
        int i = 0;
        for (Component component : subComponents) {
            boolean isLastComponent = (i == size - 1);
            component.display(newIndent, isLastComponent);
            ++i;
        }
    }

    void add(Component newComp){
        if(!subComponents.contains(newComp)){
            for(Component c : subComponents){
                if(c.checkCycle(newComp)){
                    throw new RuntimeException("Cycle detected");
                }
            }
            subComponents.add(newComp);
        }
    }

    @Override
    boolean checkCycle(Component c){
        if(c == this) return true;
        for(Component subComponent : subComponents){
            if(subComponent == c || subComponent.checkCycle(c)){
                return true;
            }
        }
        return false;
    }
}

class File extends Component{
    File(String name){
        super(name);
    }
}
class FileSystem {
    public static void main(String[] args) {
        // Build the file system hierarchy
        Directory root = new Directory("/");
        Directory documents = new Directory("Documents");
        Directory music = new Directory("Music");
        Directory pictures = new Directory("Pictures");

        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        Directory album1 = new Directory("Album1");
        Directory album2 = new Directory("Album2");
        File song1 = new File("song1.mp3");
        File song2 = new File("song2.mp3");
        File song3 = new File("song3.mp3");
        File song4 = new File("song4.mp3");
        File photo1 = new File("photo1.jpg");
        File photo2 = new File("photo2.jpg");

        // Add files and directories to their respective parents
        documents.add(file1);
        documents.add(file2);
        album1.add(song1);

        album1.add(song2);
        album2.add(song3);
        album2.add(song4);
        music.add(album1);
        music.add(album2);
        pictures.add(photo1);
        pictures.add(photo2);
        root.add(documents);
        root.add(music);
        root.add(pictures);

        try{
            album1.add(music);
        } catch(RuntimeException e){
            System.err.println(e);
        }

        // Print the file system hierarchy
        root.display();
    }
}