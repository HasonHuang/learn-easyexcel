package com.ihason.learn.easyexcel;

import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Setter
class Person {
    private String name;
    private Person[] children;

    public Person(String name, Person[] children) {
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public Person[] getChildren() {
        return children;
    }
}

public class TreeTraversal {
    public static void breadthFirstSearch(Person root) {
        if (root == null) {
            return;
        }

        Queue<Person> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Person currentPerson = queue.poll();
            System.out.println(currentPerson.getName());

            Person[] children = currentPerson.getChildren();
            if (children != null) {
                for (Person child : children) {
                    queue.add(child);
                }
            }
        }
    }

    public static void main(String[] args) {
        // 构建一个树形结构
        Person child1 = new Person("Child 1", null);
        Person child2 = new Person("Child 2", null);
        Person child3 = new Person("Child 3", null);
        Person child4 = new Person("Child 4", null);

        Person grandchild1 = new Person("Grandchild 1", null);
        Person grandchild2 = new Person("Grandchild 2", null);

        Person[] grandchildren = {grandchild1, grandchild2};
        Person[] children = {child1, child2, child3, child4};

        Person root = new Person("Root", children);
        child1.setChildren(grandchildren);
        child3.setChildren(new Person[]{new Person("Grandchild 3", null)});

        // 使用广度优先算法遍历树形结构
        breadthFirstSearch(root);
    }
}
