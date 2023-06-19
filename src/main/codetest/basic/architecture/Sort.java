package main.codetest.basic.architecture;

import java.io.BufferedReader;
import java.util.Scanner;

/*
 * 정렬
 */
public class Sort {
    public static final int[] QUESTIONS = {15, 16, 17, 18};
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Archi exe = new Archi();
        System.out.println("프로그램 시작 >>> ");

        while (true) {
            System.out.println("문제 번호를 입력하세요 : (종료하려면 0 입력)");
            int input = sc.nextInt();

            switch (input) {
                case 15:
                    break;
                default:
                    System.out.println("없는 문제 번호 입니다.");
                    System.out.print("문제 목록 : [");
                    for (int i = 0; i < QUESTIONS.length; i++) {
                        if (i == QUESTIONS.length - 1) {
                            System.out.print(QUESTIONS[i]);
                        } else {
                            System.out.print(QUESTIONS[i] + ", ");
                        }
                    }
                    System.out.println("]");
                    break;
            }

            if (input == 0) {
                sc.close();
                break;
            }

        }
        System.out.println("프로그램 종료 >>> ");
    }

    /*
     * 문제015 수 정렬하기 1
     */
    private void no015() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        for (int i = 0; i < (N - 1); i++) {
            for (int j = 0; j < (N - 1) - i; j++) {
                if (A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            System.out.println(A[i]);
        }
    }

    /*
    * 문제017 내림차순으로 자릿수 정렬하기
    */
    private void no017() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int size = str.length();
        int[] A = new int[size];
        for (int i = 0; i < size; i++) {
            A[i] = Integer.parseInt(str.substring(i, i + 1));
        }
        for (int i = 0; i < size; i++) {
            int max = i; // 인덱스 값
            // 현재 범위에서 Max값 찾기
            for (int j = i + 1; j < size; j++) {
                if (A[j] > A[max]) {
                    max = j; // 인덱스 값
                }
            }
            // swap
            if (A[i] < A[max]) {
                int temp = A[i];
                A[i] = A[max];
                A[max] = temp;
            }
        }
        for (int i = 0; i < size; i++) {
            System.out.println(A[i]);
        }
    }

    /*
     * 문제018 ATM 인출 시간 계산하기
     */
    private void no018() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 사람수
        int[] A = new int[N]; // 자릿수 별로 구분해 저장한 배열
        int[] S = new int[N]; // A 합 배열 : 각 사람이 인출을 완료하는 데 필요한 시간을 저장

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        // 삽입 정렬
        // 삽입 위치 찾기
        for (int i = 1; i < N; i++) {
            // 비교 대상 값 위치와 값 저장 : A[i]
            int insert_point = i; // 현재 대상 위치
            int insert_value = A[insert_point]; // 해당 위치 값

            for (int j = i - 1; j >= 0; j--) {
                // 오른쪽이 왼쪽보다 클 경우 그대로 유지
                if (A[j] < A[i]) {
                    insert_point = j + 1;
                    break;
                }
                // 처음 인덱스까지 왔을 경우 첫번째에 삽입
                if (j == 0) {
                    insert_point = 0;
                }
            }
            // 삽입을 위해 삽입 위치까지 데이터 Shift
            for (int z = i; z > insert_point; z--) {
                A[z] = A[z - 1];
            }

            A[insert_point] = insert_value;
        }
        S[0] = A[0];
        // 합 배열
        for (int i = 1; i < N; i++) {
            S[i] = S[i-1] + A[i];
        }
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum = sum + S[i];
        }
        System.out.println(sum);
    }

}
