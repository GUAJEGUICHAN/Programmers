import java.util.HashMap;
public class Main {
    public static void main(String[] args) {
        String[] participant = { "mislav", "stanko", "mislav", "ana" };
        String[] completion = { "stanko", "ana", "mislav" };
        System.out.println(solution(participant, completion));
    }

    public static String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> players = new HashMap<>();
        for (String s : participant) {
            players.put(s, 0);
        }
        for (String s : participant) {
            players.put(s, players.get(s) + 1);
        }
        for (String s : completion) {
            players.put(s, players.get(s) - 1);

        }

        for (String s : participant) {
            if (0 < players.get(s))
                return s;
        }
        return "";
    }

}
