
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int[] people = { 50, 70, 80 };
        int s = Solution(people, 100);
        System.out.println("원하는 값" + s);
    }

    static public int Solution(int[] people, int limit) {
        int answer = 0;
        int left_index = 0;
        int limit_index = people.length - 1;
        Arrays.sort(people);
        for (int i = people.length - 1; 0 <= i; i--) {
            if (left_index == limit_index) {
                System.out.println("양쪽 인덱스가 같습니다.");
                answer++;
                break;
            }
            if (left_index < limit_index) {// 인덱스가 서로 엇갈리면 끝냅니다.
                if (limit < (people[limit_index] + people[left_index])) {
                    System.out.println("합이 리밋보다 작으므로 오른쪽 인덱스를 하나 내립니다.");
                    limit_index--;
                    answer++;
                } else {
                    System.out.println("합이 리밋보다 크므로 양쪽 인덱스를 가운데로 한칸씩 움직입니다..");
                    limit_index--;
                    left_index++;
                    answer++;
                }
            } else {
                break;
            }
        }
        return answer;
    }
}
