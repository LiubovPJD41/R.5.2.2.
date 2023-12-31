import java.util.*;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long babies = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Number of people under the age of 18 " + babies);

        List<String> listNames = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() < 27)
                .map(Person::getFamily)
                .toList();
        System.out.println("List of men in the military " + listNames);


        Collection<Person> workingPeople = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> (person.getSex().equals(Sex.MAN) && person.getAge() <= 65) || (person.getSex().equals(Sex.WOMAN) && person.getAge() <= 60))
               .sorted((o1, o2) -> o1.getFamily().compareToIgnoreCase(o2.getFamily()))
              .toList();
        for(Person person : workingPeople){
           System.out.println("Work able population with higher education " + person);
        }
    }
}