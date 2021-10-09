import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        String[] test = { "XYZ", "XWY", "WXA" };
        int[] course = { 2, 3, 4 };
        System.out.println("안녕");
        for (String s : solution(test, course)) {
            System.out.print(" /" + s);
        }
    }

    static public String[] solution(String[] orders, int[] course) {
        System.out.println("시작");
        ArrayList<String> comb = new ArrayList<>();// 조합의 모든 경우의 수
        TreeSet<String> removeKeys = new TreeSet<>();// 제거할 키들만 모아둔 곳
        TreeMap<String, Integer> newMenuList = new TreeMap<>();// 새로운 메뉴들

        // 가능한 모든 조합을 orders에 몰아서 저장한다.
        for (String s : orders) {
            System.out.println(s + "의 가능한 조합들");
            String[] atoms = s.split(""); // 조합에 쓰일 원소들
            Arrays.sort(atoms);// 정렬해줘야 Case3 문제해결 가능
            int[] status = new int[atoms.length]; // 최대 depth는 원소 개수
            combination(atoms, -1, status, course, comb); // 조합 시작 여기서 모든 조합의 수를 구하고 Comb에 저장한다.
        }

        // for (String s : comb) { System.out.print(s + " "); }

        for (String s : comb) {// 해당 매뉴의 개수를 정산하는 해쉬맵
            if (newMenuList.containsKey(s)) {// 구면일 경우 1플러스
                newMenuList.put(s, newMenuList.get(s) + 1);
            } else {
                newMenuList.put(s, 1);// 초면일경우 1

            }
        }

        // System.out.println("자가 테스트 : " + newMenuList.get("AC"));
        // 문제 : 어떻게 n길이 문자열의 최대값에 해당되는 것을 뽑지?
        // 답 : max보다 높은 것을 max로 설정, 한바퀴 더 돌릴때 지울 key를 모아둔다.
        for (int n : course) {//문자열 n인 경우
            int max = 2;

            //최대 길이를 찾는다.
            for (String key : newMenuList.keySet()) {
                if (key.length() == n) {
                    if (newMenuList.get(key) >= max) {
                        max = newMenuList.get(key);
                    }
                }
            }

            //지울 key를 모아둔다.
            for (String key : newMenuList.keySet()) {
                if (key.length() == n) {
                    if (newMenuList.get(key) < max) {
                        removeKeys.add(key);
                    }
                }
            }

        }


        //제거할 key들을 여기서 제거시킨다.
        for (String key : removeKeys) {
            System.out.println("없앨 메뉴 : " + key);
            newMenuList.remove(key);
        }

        // for (String key : newMenuList.keySet()) {
        // System.out.print(key + " ");
        // }

        return newMenuList.keySet().toArray(new String[newMenuList.size()]);
    }

    static void combination(String[] atoms, int depth, int[] status, int[] course, ArrayList<String> comb) {
        // System.out.println(depth + "번째 depth입니다.");

        if (depth == atoms.length - 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < status.length; i++) {
                if (status[i] == 1) {
                    sb.append(atoms[i]);
                }
            }

            //
            if (IntStream.of(course).anyMatch(x -> x == sb.toString().length())) {
                comb.add(sb.toString());
            }
            return;
        }

        // 1인 경우
        status[depth + 1] = 1;
        combination(atoms, depth + 1, status, course, comb);
        // 0인경우
        status[depth + 1] = 0;
        combination(atoms, depth + 1, status, course, comb);

    }
}
