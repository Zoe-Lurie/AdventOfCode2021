package Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(setup(args[0]));
    }

    public static int setup(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        Map<String, List<String>> caveMap = input(scanner);
        Set<String> visited = new HashSet<>();
        visited.add("start");
        return countPaths(caveMap, visited, "start", false);
    }

    public static Map<String, List<String>> input(Scanner scanner){
        Map<String, List<String>> caveMap = new HashMap<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String cave1 = line.substring(0, line.indexOf('-'));
            String cave2 = line.substring(line.indexOf('-')+1);
            if(caveMap.containsKey(cave1)){
                caveMap.get(cave1).add(cave2);
            }
            else{
                List<String> list = new ArrayList<>();
                list.add(cave2);
                caveMap.put(cave1, list);
            }
            if(caveMap.containsKey(cave2)){
                caveMap.get(cave2).add(cave1);
            }
            else{
                List<String> list = new ArrayList<>();
                list.add(cave1);
                caveMap.put(cave2, list);
            }
        }
        return caveMap;
    }

    public static int countPaths(Map<String, List<String>> caveMap, Set<String> visited, String current, boolean smallCave){
        int totalPaths = 0;
        if(current.equals("end")) return 1;
        for(String neighbor : caveMap.get(current)){
            if(!visited.contains(neighbor)){
                Set<String> newVisited = new HashSet<>(visited);
                if(!(neighbor.charAt(0) - 'A' >= 0) || !(neighbor.charAt(0) - 'A' < 26)) {
                    newVisited.add(neighbor);
                }
                totalPaths += countPaths(caveMap, newVisited, neighbor, smallCave);
            }
            else if(!smallCave && !neighbor.equals("start")){
                Set<String> newVisited = new HashSet<>(visited);
                totalPaths += countPaths(caveMap, newVisited, neighbor, true);
            }
        }
        return totalPaths;
    }
}
