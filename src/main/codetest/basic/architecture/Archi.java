package main.codetest.basic.architecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Archi {
    static final int[] QUESTIONS = {7, 8, 9};

    public static void main(String[] args) throws Exception {
        Archi exe = new Archi();
        System.out.println("프로그램 시작 >>> ");

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("문제 번호를 입력하세요 : (종료하려면 0 입력)");
            int input = sc.nextInt();

            switch (input) {
                case 0:
                    break;
                case 7:
                    exe.no007();
                    break;
                case 8:
                    exe.no008();
                    break;
                case 9:
                    exe.no009();
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

    int[] checkArr; // 비밀번호 체크 배열
    int[] myArr; // 현재 상태 배열
    int checkSecret; // 몇 개의 문자와 관련된 개수를 충족했는지 판단하는 변수

    /**
     * 문제 009 DNA 비밀번호 - 제한시간 2초, 난이도 실버5, 백준 12891번
     */
    private void no009() throws IOException {
        System.out.println("문제 9 >>> : ");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int S = Integer.parseInt(st.nextToken()); // 문자열의 크기
        int P = Integer.parseInt(st.nextToken()); // 부분 문자열의 크기
        int Result = 0;
        char[] A;
        checkArr = new int[4];
        myArr = new int[4];
        checkSecret = 0;
        A = bf.readLine().toCharArray();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
            if (checkArr[i] == 0)
                checkSecret++;
        }
        // 처음에는 추가 부분만 존재
        for (int i = 0; i < P; i++) {
            Add(A[i]);
        }
        if (checkSecret == 4) {
            Result++;
        }
        // 오른쪽으로 옮기면서 추가와 제거를 반복
        for (int i = P; i < S; i++) {
            int j = i - P;
            // 오른쪽으로 한칸 옮긴 후
            Add(A[i]); // 마지막 인덱스에 새로운 문자 추가
            Remove(A[j]); // 제일 처음 인덱스에 해당하는 문자 제거
            if (checkSecret == 4) {
                Result++;
            }
        }
        System.out.println(Result);
        System.out.println("종료 >>>");
    }

    /*
        자바 14부터 향상된 switch 구문 추가됨. break 누락으로 인한 실수 제거.
    */
    private void Add(char c) {
        switch (c) {
            case 'A' -> {
                myArr[0]++;
                if (myArr[0] == checkArr[0]) {
                    checkSecret++;
                }
            }
            case 'C'-> {
                myArr[1]++;
                if (myArr[1] == checkArr[1]) {
                    checkSecret++;
                }
            }
            case 'G' -> {
                myArr[2]++;
                if (myArr[2] == checkArr[2]) {
                    checkSecret++;
                }
            }
            case 'T' -> {
                myArr[3]++;
                if (myArr[3] == checkArr[3]) {
                    checkSecret++;
                }
            }
        }
    }

    private void Remove(char c) {
        switch (c) {
            case 'A' -> {
                if (myArr[0] == checkArr[0]) {
                    checkSecret--;
                }
                myArr[0]--;
            }
            case 'C' -> {
                if (myArr[1] == checkArr[1]) {
                    checkSecret--;
                }
                myArr[1]--;
            }
            case 'G' -> {
                if (myArr[2] == checkArr[2]) {
                    checkSecret--;
                }
                myArr[2]--;
            }
            case 'T' -> {
                if (myArr[3] == checkArr[3]) {
                    checkSecret--;
                }
                myArr[3]--;
            }
        }
    }

    /**
     * 문제 008 좋은수 구하기 - 제한시간 2초, 난이도 골드4, 백준 1253번
     */
    private void no008() throws IOException {
        System.out.println("문제 8 >>> : ");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        long[] A = new long[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int Result = 0;
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(A);
        for (int k = 0; k < N; k++) {
            long find = A[k];
            int i = 0; // 작은 번호 index
            int j = N - 1; // 큰 번호 index

            while (i < j) {
                if (A[i] + A[j] == find) {
                    // find는 서로 다른 두 수의 합이어야 함
                    if (i != k && j != k) {
                        Result++;
                        break;
                    } else if (i == k) {
                        i++;
                    } else if (j == k) {
                        j--;
                    }
                } else if (A[i] + A[j] < find) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        System.out.println(Result);
        System.out.println("종료 >>> ");
    }

    /**
     * 문제 007 주몽의 명령 - 제한 시간 2초, 난이도 실버4, 백준 1940번
     */
    private void no007() throws IOException {
        System.out.println("문제 7 >>> : ");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int M = Integer.parseInt(bf.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        int count = 0;
        int i = 0; // 작은 번호 index
        int j = N - 1; // 큰 번호 index

        while (i < j) {
            if (A[i] + A[j] < M) {
                i++;
            } else if (A[i] + A[j] > M) {
                j--;
            } else {
                count++;
                i++;
                j--;
            }
        }
        System.out.println(count);
        System.out.println("종료 >>>");
    }

}
