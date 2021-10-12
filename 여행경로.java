import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        String[][] tickets = { { "ICN", "SFO" }, { "SFO", "ICN" }, { "ICN", "SFO" }, { "SFO", "QRE" } };
        // 답["ICN", "B", "ICN", "A", "D", "A"]
        // {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}}
        // ICN A D A ICN B ICN
        // { { "ICN", "B" }, { "B", "ICN" }, { "ICN", "A" }, { "A", "D" }, { "D", "A" }
        // };
        // {{"ICN","AAA"},{"ICN","AAA"},{"ICN","AAA"},{"AAA","ICN"},{"AAA","ICN"}}
        // {{"ICN", "SFO"},{"ICN", "ATL"},{"SFO", "ATL"},{"ATL", "ICN"},{"ATL","SFO"}}
        // {{"ICN", "SFO"},{"SFO", "ICN"},{"ICN", "SFO"},{"SFO", "QRE"}}
        String[] result = solution(tickets);

        for (String s : result) {
            System.out.print(s + " / ");
        }

    }

    static public String[] solution(String[][] tickets) {

        TreeMap<String, ArrayList<String>> ts = new TreeMap<>();// 티켓들
        ArrayList<String[]> allCases = new ArrayList<>();// 결과 담기, combination으로 가능한 경우의 수를 모두 담는다.
        String[] oneCase = new String[tickets.length + 1];
        int depth = 0;
        oneCase[depth] = "ICN";// 첫 출발 티켓
        for (String[] t : tickets) {
            ts.put(t[0], new ArrayList<String>());
        }

        for (String[] t : tickets) {
            ts.get(t[0]).add(t[1]);
            Collections.sort(ts.get(t[0]));
        }

        combination(ts, allCases, oneCase, oneCase[depth], depth + 1);

        // for (String[] ss : allCases) {
        // for (String s : ss) {
        // System.out.print(s + "/");
        // }
        // System.out.println();
        // }
        return allCases.get(0);
    }

    static void combination(TreeMap<String, ArrayList<String>> ts, ArrayList<String[]> allCases, String[] oneCase,
            String from, int depth) {
        // 전체경우를 충족했을때 allCase에 저장
        if (depth == oneCase.length) {
            String[] complete = new String[depth];
            for (int index = 0; index < oneCase.length; index++) {
                complete[index] = oneCase[index];
            }
            ;
            allCases.add(complete);

            System.out.println("추가할 oneCase");
            for (String[] ss : allCases) {
                for (String s : ss) {
                    System.out.print(s + "/");
                }
                System.out.println();
            }
            return;
        }
        // 중간에 옮겨갈 티켓이 없을때
        if (ts.containsKey(from)) {
            if (ts.get(from).size() == 0 && depth < oneCase.length) {
                return;
            }

            int ways = ts.get(from).size();

            // 옮겨갈 수 있는 모든 경우를 가자
            for (int i = 0; i < ways; i++) {
                // System.out.println("depth값 :" + depth + " from 값 : " + from + "i값 : " + i +
                // "/ ts.get(from).size() : "
                // + ts.get(from).size());
                TreeMap<String, ArrayList<String>> newTs = new TreeMap<>();
                for (Map.Entry<String, ArrayList<String>> s : ts.entrySet()) {
                    ArrayList<String> v = new ArrayList<>(s.getValue());
                    newTs.put(s.getKey(), v);
                }
                oneCase[depth] = newTs.get(from).remove(i);
                combination(newTs, allCases, oneCase, oneCase[depth], depth + 1);
            }
        } else {
            return;
        }

    }
}
