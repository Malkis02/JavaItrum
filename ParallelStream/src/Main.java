import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        Map<String,Double> avgGrades = students.parallelStream()
                .flatMap(s->s.getGrades().entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey,Collectors.averagingDouble(Map.Entry::getValue)));

        avgGrades.forEach((title,grade)-> System.out.println(title + " " + grade));











































//        List<Map<String,Integer>> grades = students
//                .parallelStream()
//                .map(Student::getGrades).toList();
//
//        List<Integer> values = grades
//                .parallelStream()
//                .mapToInt(map-> map.values().parallelStream().mapToInt(num->num).sum()).boxed().toList();
//
//        List<Set<String>> key = grades
//                .parallelStream()
//                .map(Map::keySet)
//                .collect(Collectors.toList());
//        Map<Object,Double> avgGrades = grades
//                .parallelStream()
//                .collect(Collectors.groupingBy(s->s,Collectors.averagingDouble(s-> s.values().size())));
////        System.out.println(grades);
////        System.out.println(avgGrades);
////        System.out.println(key);
//        System.out.println(values);
//        System.out.println(key);
    }
}