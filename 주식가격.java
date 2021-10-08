import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 2, 3 };
        System.out.println(solution(arr));
    }

    static public int[] solution(int[] prices) {

        //몇 초 인지를  담는다.
        ArrayList<Integer> seconds = new ArrayList<>();

        //prices의 i번째와 j번째 값을 비교한다.
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                // System.out.println("prices[i] : " + prices[i] + " / prices[j] : " + prices[j]);

                //비교해서 i가 크면 값이 내려간거니까 지나친 인덱스(초)만큼 seconds[i] 에 추가한다.
                if (prices[i] > prices[j]) {
                    seconds.add(j - i);
                    break;

                //배열 마지막이면 그때 지나간 인덱스 만큼 seconds[i]에 추가한다.
                } else if (j == prices.length - 1) {
                    seconds.add(j - i);
                }

            }//i가 마지막까지 가면 0 넣고 마친다.
            if (i == prices.length - 1) {
                seconds.add(0);
            }
        }
        for (int i : seconds) {
            System.out.println(i + "/");
        }
        int[] arr = seconds.stream().mapToInt(i -> i).toArray();

        return arr;
    }
}
