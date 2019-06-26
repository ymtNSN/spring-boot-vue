package com.ymt.manage.demo.lambda;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author yangmingtian
 * @Date 2019/6/25
 */
public class StreamDemo {
    public static void main(String[] args) {
        allMatch();
    }

    public static void flatMap() {
        List<String> flatList = new ArrayList<>();
        flatList.add("唱,跳");
        flatList.add("rape,篮球,music");
        //flatMap(T -> Stream<R>)
        flatList = flatList.stream().map(s -> s.split(",")).flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println(flatList);
    }

    public static void allMatch() {
        List<User> list = Arrays.asList(
                new User("钢铁侠", 40, 0, "华盛顿"),
                new User("钢铁侠", 40, 0, "华盛顿"),
                new User("蜘蛛侠", 20, 0, "华盛顿"),
                new User("赵丽颖", 30, 1, "湖北武汉市"),
                new User("詹姆斯", 35, 0, "洛杉矶"),
                new User("李世民", 60, 0, "山西省太原市"),
                new User("蔡徐坤", 15, 1, "陕西西安市"),
                new User("葫芦娃的爷爷", 70, 0, "山西省太原市"));
        boolean b = list.stream().allMatch(user -> user.getAge() >= 18);
        boolean sex = list.stream().anyMatch(user -> user.getSex() == 1);
        boolean none = list.stream().noneMatch(user -> user.getAddress().contains("巴黎"));
        Optional<User> first = list.stream().findFirst();
        Optional<User> any = list.stream().findAny();
        Optional<User> any2 = list.parallelStream().findAny();
//        long count = list.stream().collect(Collectors.counting());
        long count = list.stream().count();
        Optional<User> maxAge = list.stream().collect(Collectors.maxBy(Comparator.comparing(User::getAge)));
        Optional<User> minAge = list.stream().collect(Collectors.minBy(Comparator.comparing(User::getAge)));
       int totalAge = list.stream().collect(Collectors.summingInt(User::getAge));
//        BigDecimal totalMoney = list.stream().map(User::getMoney).reduce(BigDecimal.ZERO,BigDecimal::add);
        double avgAge = list.stream().collect(Collectors.averagingDouble(User::getAge));
        IntSummaryStatistics statistics = list.stream().collect(Collectors.summarizingInt(User::getAge));
        String names = list.stream().map(User::getName).collect(Collectors.joining(","));
        Map<String, List<User>> cityMap = list.stream().collect(Collectors.groupingBy(User::getAddress));
        Map<String, Map<Integer, List<User>>> group = list.stream().collect(Collectors.groupingBy(User::getAddress,Collectors.groupingBy(User::getSex)));
        Map<String, Long> cityCountMap = list.stream().collect(Collectors.groupingBy(User::getAddress, Collectors.counting()));
        Map<String, Long> filterCityCountMap = list.stream().filter(user -> user.getAge() <= 30).collect(Collectors.groupingBy(User::getAddress, Collectors.counting()));
//        Map<String, Long> filterCityCountMap = list.stream().collect(Collectors.partitioningBy(user -> user.getAge() <= 30);
        System.out.println(filterCityCountMap);
    }
}
