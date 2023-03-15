package main.codetest.basic.architecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Archi {

    public static void main(String[] args) throws Exception {
        Archi exe = new Archi();
        exe.no008();

    }


//    /**
//     * 문제 009 DNA 비밀번호 - 제한시간 2초, 난이도 실버5, 백준 12891번
//     */
//    private void no009() throws IOException {
//
//    }

    /**
     * 문제 008 좋은수 구하기 - 제한시간 2초, 난이도 골드4, 백준 1253번
     */
    private void no008() throws IOException {
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
        bf.close();
    }

    /**
     * 문제 007 주몽의 명령 - 제한 시간 2초, 난이도 실버4, 백준 1940번
     */
    private void no007() throws IOException {
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
        bf.close();
    }

}
