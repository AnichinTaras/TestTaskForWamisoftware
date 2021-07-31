import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Does the job of parsing files. This class contains the main functionality of the program.
 */
public class Parser {
  public static void main(String[] args) throws IOException {
    parseFirstFile();
  }

  /**
   * This method works by piercing the tag_read_start.log file
   * Also, the functionality of selection by the first entry into the file is implemented here.
   */
  public static void parseFirstFile() {
    final String firstFile = "resourses/tag_read_start.log";
    try(Stream<String> stream = Files.lines(Paths.get(firstFile))) {
      final List<Logs> logs = findTagsAndTime(stream);
      secondFile(logs.stream ().collect(Collectors.toMap(Logs::getId, Function.identity (),(p, q) -> p)).values());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method implements similar functionality, only it supports fetching by the last occurrence.
   * This method also parses the tag_reads_finish.log file.
   * @param firstFileLog
   */
  private static void secondFile (Collection<Logs> firstFileLog) {
    final String secondFile = "resourses/tag_reads_finish.log";
    try (Stream<String> stream = Files.lines(Paths.get (secondFile))) {
      final List<Logs> logs = findTagsAndTime (stream);
      Collection<Logs> second = logs.stream().collect(Collectors.toMap(Logs::getId,Function.identity(),(p, q) -> q)).values();
      groupDateById(firstFileLog,second);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * We group by MAP ID and collect values for the list.
   * The purpose of the method is to provide the ability to store more than one date under one key.
   * Also, this method implements the display of information about the difference in date between the start and finish.
   * Demonstrated only for those who have start and finish parameters.
   * Also performs subtraction on dates. FinalData minus StartData. For further sampling.
   * @param firstFileLog
   * @param second
   */
  private static void groupDateById(Collection<Logs> firstFileLog,Collection<Logs> second) {
    System.out.println(" ID " + '\t' +  "      First Date  " + '\t' +  " Finish Date" + '\t' + "Difference");
    Stream <Collection <Logs>> firstFileLog1 = Stream.of(firstFileLog, second);
    firstFileLog1.flatMap (Collection::stream)
        .collect(Collectors.groupingBy(Logs::getId,
            Collectors.mapping(Logs::getTime,Collectors.toList())))
        .forEach ((id, list) -> {
          if (list.size () != 2)  {
          return;
        }
          long a = Long.parseLong (list.get (1));
          long b = Long.parseLong (list.get (0));
          long res = a - b;
          if (res < 5750) {
            System.out.println (id + "=" + list + ( ", result: " + GFG.findDifference (list.get (0), list.get (1)) ));
            System.out.print ("finish date minus start date = ");
            System.out.print (a - b);
            System.out.println ();
          }
        });
  }

  /**
   * This method implements the functionality of tag selection.
   * This allows us to immediately initialize the id and time values in the constructor.
   * @param stream
   * @return
   */
  private static List<Logs> findTagsAndTime(Stream <String> stream) {
    return stream.map(t -> new Logs(t.substring(4, 16), (t.substring (20, 32)))).collect (Collectors.toList());
  }

  /**
   * POJO for Logs. Contains various fields, getters and setters to work with.
   */
  static class Logs {
    private  String id;
    private  String time;
    public Logs(String id, String time) { this.id = id; this.time = time; }

    @Override
    public String toString() { return "{" + "id='" + id + '\'' + ", time=" + time + '}' + '\n'; }
    public String getId() { return id; }
    public String getTime() { return time; }
  }
}

