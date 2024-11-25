package com.ll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExam {
    public static void main(String[] args) {

        String[] massages = Arrays.stream(new int[]{1, 2, 3})
                //.filter(e -> e % 2 != 0)
                .mapToObj(e -> e + "번") //기본형 타입에서 레퍼런스 타입으로 바뀔 때(애초에 배열이 Integer 였으면 안써도 됐음)
                .toArray(String[]::new);
        System.out.println(Arrays.toString(massages));

        int[] numbers = Arrays.stream(massages)
                .mapToInt(e -> Integer.parseInt(e.replace("번", "")))
                .filter(e -> e % 2 != 0)
                .toArray();
        int[] numbers2 = Arrays.stream(massages)
                .map(massage -> massage.substring(0, massage.length() - 1))
                .mapToInt(Integer::parseInt)
                .filter(number -> number % 2 != 0)
                .toArray();
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(numbers2));

        List<Integer> list1 = Arrays
                .stream(new int[]{10, 20, 30})
                .mapToObj(e -> e) //int => Integer
                .toList();

        List<Integer> list2 = Arrays
                .stream(new int[]{10, 20, 30})
                .boxed() //int => Integer
                .toList();

        List<Integer> list3 = Arrays
                .stream(new Integer[]{10, 20, 30}) //애초에 Integer 배열인 경우에는 래핑(boxed)할 필요가 없다.
                .toList();

        List<Person> people = new ArrayList<Person>();
        people.add(new Person(1, "Alice", 20, 'F'));
        people.add(new Person(2, "Bob", 25, 'M'));
        people.add(new Person(3, "David", 35, 'M'));

        //문제 : 나이가 25세 이상인 사람들의 이름들을 출력해주세요
        //출력 : Bob, David

        String result = people
                .stream()
                .filter(person -> person.getAge() >= 25)
                .map(Person::getName)
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }
}

class Person {
    private int id;
    private String name;
    private int age;
    private char gender;

    public Person(int id, String name, int age, char gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }
}
