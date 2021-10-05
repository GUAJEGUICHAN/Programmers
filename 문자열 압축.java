
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String s = "sdasdgsdg";
        System.out.println("\"" + s + "\"가장짧은 문자열 길이 : " + solution(s));

    }

    static public int solution(String s) {

        ArrayList<String> s_arr = new ArrayList<>();
        ArrayList<Integer> n_arr = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        int min = s.length(), temp = s.length();
        for (int n = 1; n <= s.length() / 2; n++) {// n개씩 나누겠다.
            for (int i = 0; i < s.length(); i = i + n) {// n개씩더한걸 큐에 넣겠다.
                // System.out.println("현재 n값 : " + n + "현재 i값" + i);
                String seg;
                // n씩 나뉘는 절편을 구한다.
                if (i + n <= s.length()) {
                    seg = s.substring(i, i + n);

                } else {
                    seg = s.substring(i, s.length());
                }

                // 나뉜 절편을 s_arr에 넣는다.
                if (s_arr.size() > 0) {// 두번재 이상 넣을때 비교한다.
                    if (s_arr.get(s_arr.size() - 1).equals(seg)) {// 만약 같은 절편이면 숫자만 올려준다.
                        n_arr.set(n_arr.size() - 1, n_arr.get(n_arr.size() - 1) + 1);
                    } else {// 새 절편이면 어레이에 각각 그 절편과 1을 넣는다.
                        s_arr.add(seg);
                        n_arr.add(1);
                    }
                } else {// 초기값이면 그냥 넣고
                    s_arr.add(seg);
                    n_arr.add(1);
                }
            }

            for (int i = 0; i < s_arr.size(); i++) {// sb에 모은 절편들을 모두 붙인다.

                if (n_arr.get(i) > 1) {
                    sb.append(n_arr.get(i));
                }
                sb.append(s_arr.get(i));
            }
            s_arr.clear();
            n_arr.clear();
            System.out.println(sb.toString());
            temp = sb.toString().length();
            sb = new StringBuilder("");
            min = Math.min(min, temp);

        }

        return min;

    }
}
