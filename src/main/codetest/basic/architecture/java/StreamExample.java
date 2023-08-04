package main.codetest.basic.architecture.java;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExample {

    public static void main(String[] args) throws IOException {
        List<Data> list = new ArrayList<>();
        list.add(new Data("tag1", "2023-08-01 00:01:01", (int) (Math.random() * 100)));
        list.add(new Data("tag2", "2023-08-01 00:01:02", (int) (Math.random() * 100)));
        list.add(new Data("tag3", "2023-08-01 00:01:03", (int) (Math.random() * 100)));
        list.add(new Data("tag4", "2023-08-01 00:01:04", (int) (Math.random() * 100)));
        list.add(new Data("tag5", "2023-08-01 00:02:01", (int) (Math.random() * 100)));
        list.add(new Data("tag6", "2023-08-01 00:02:02", (int) (Math.random() * 100)));
        list.add(new Data("tag7", "2023-08-01 00:02:03", (int) (Math.random() * 100)));
        list.add(new Data("tag8", "2023-08-01 00:02:04", (int) (Math.random() * 100)));
        list.add(new Data("tag9", "2023-08-01 00:03:05", (int) (Math.random() * 100)));
        list.add(new Data("tag10", "2023-08-02 00:03:05", (int) (Math.random() * 100)));
        list.add(new Data("tag11", "2023-08-03 00:03:05", (int) (Math.random() * 100)));
        list.add(new Data("tag12", "2023-08-04 00:03:05", (int) (Math.random() * 100)));
        list.add(new Data("tag13", "2023-08-05 00:03:05", (int) (Math.random() * 100)));
        list.add(new Data("tag14", "2023-08-06 00:03:05", (int) (Math.random() * 100)));
        list.add(new Data("tag15", "2023-08-07 00:03:05", (int) (Math.random() * 100)));
        list.add(new Data("tag16", "2023-08-08 00:03:05", (int) (Math.random() * 100)));
        list.add(new Data("tag17", "2023-08-09 00:03:05", (int) (Math.random() * 100)));
        list.add(new Data("tag18", "2023-08-10 00:03:05", (int) (Math.random() * 100)));
        list.add(new Data("tag19", "2023-08-11 00:03:05", (int) (Math.random() * 100)));
        list.add(new Data("tag20", "2023-08-12 00:03:05", (int) (Math.random() * 100)));
        list.add(new Data("tag21", "2023-08-13 00:03:05", (int) (Math.random() * 100)));
        list.add(new Data("tag22", "2023-08-14 00:03:05", (int) (Math.random() * 100)));


//        System.out.println("분까지 >>> ");
//        list.stream().map(Data::getAcqTime).map(s -> s.substring(0, 10)).forEach(System.out::println);
//        System.out.println();

        System.out.println("그룹핑 >>>  ");
        Map<String, List<Data>> result = list.stream().collect(groupingBy(o -> o.getAcqTime().substring(0, 10)));
        result.forEach((key, value) -> System.out.println("key : <" + key + "> \n" + value));
        System.out.println("===============================================================");

        result.forEach((key, value) -> {
            System.out.println("key : <" + key + ">");
            Map<String, List<Data>> result2 = value.stream().collect(groupingBy(o -> o.getAcqTime().substring(0, 16)));
            result2.forEach((key2, value2) -> System.out.println("\t=> key2 : <" +key2 + "> \n\t\t=> " + value2));
        });

//        System.out.println("평균값 >>> ");
//        result.forEach((key, value) -> {
//            System.out.println(value.stream().collect(averagingInt(Data::getVal)));
//        });
    }

    public static void sample1() {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring 으로 시작 하는 수업");
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("close 되지 않은 수업");
        springClasses.stream()
                .filter(oc -> !oc.isClosed())
//                .filter(Predicate.not(OnlineClass::isClosed))
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        keesunEvents.stream()
                .flatMap(Collection::stream)
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("자바 수업중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream()
                .anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        List<String> spring = springClasses.stream()
                .map(OnlineClass::getTitle)
                .filter(title -> title.contains("spring"))
                .collect(Collectors.toList());
        spring.forEach(System.out::println);
    }
}
