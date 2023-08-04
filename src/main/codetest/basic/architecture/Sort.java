package main.codetest.basic.architecture;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 정렬
 */
public class Sort {
    public static final int[] QUESTIONS = {15, 16, 17, 18, 20, 21 ,22};
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Sort exe = new Sort();
        System.out.println("프로그램 시작 >>> ");

        while (true) {
            System.out.println("문제 번호를 입력하세요 : (종료하려면 0 입력)");
            int input = sc.nextInt();

            switch (input) {
                case 15:
                    exe.no015();
                    break;
                case 16:
                    break;
                case 17:
                    exe.no017();
                    break;
                case 18:
                    exe.no018();
                    break;
                case 19:
                    exe.no019();
                    break;
                case 20:
                    exe.no020();
                    break;
                case 21:
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

    /**
     *  문제 19 K번째 수 구하기
     */
    private void no019() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken()); // 숫자의 개수
        int K = Integer.parseInt(st.nextToken()); // 숫자 데이터 저장 배열
        st = new StringTokenizer(in.readLine());
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(A, 0, N-1, K-1);
        System.out.println(A[K - 1]);
    }

    // S : start, E : end
    private static void quickSort(int[] A, int S, int E, int K) {
        if (S < E) {
            int pivot = partition(A, S, E);

            // K번째 수가 pivot이면 더이상 구할 필요 없음
            if (pivot == K)
                return;
            // K가 pivot보다 작으면 왼쪽 그룹만 정렬 수행하기
            else if (K < pivot)
                quickSort(A, S, pivot - 1, K);
            // K가 pivot보다 크면 오른쪽 그룹만 정렬 수행하기
            else
                quickSort(A, pivot + 1, E, K);
        }
    }

    // pivot 구하기 함수
    public static int partition(int [] A, int S, int E) {
        if (S + 1 == E) {
            if(A[S] > A[E])
                swap(A, S, E);
            return E;
        }
        int M = (S + E) / 2;
        swap(A, S, M); // 중앙값을 첫번째 요소로 이동
        int pivot = A[S];
        int i = S + 1, j = E;
        while (i <= j) {
            // pivot 보다 작은 수가 나올 때까지 j--
            while (pivot < A[j] && j > 0) {
                j--;
            }
            // pivot 보다 큰 수가 나올 때까지 i++
            while (pivot > A[i] && i < A.length - 1) {
                i++;
            }
            if (i <= j) {
                swap(A, i++, j--);
            }
        }
        // i == j pivot의 값을 양쪽으로 분리한 가운데에 오도록 설정하기
        A[S] = A[j];
        A[j] = pivot;

        return j;
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /**
     *
     * 문제 20 수 정렬하기2 - 병합 정렬
     */
    private void no020() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        // 0번째 인덱스 사용안하려고 N+1까지 사용
        int[] A = new int[N+1];
        int[] tmp = new int[N + 1]; // 정렬할 때 사용하는 임시배열

        for (int i = 1; i < N + 1; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        // 병합 정렬 수행
        merget_sort(A, tmp,1, N);

        // 출력
        for (int i = 1; i <= N; i++) {
            bw.write(A[i] + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static  void merget_sort(int[] A, int[] tmp, int s, int e) {
        if (e - s < 1) return;

        int m = s + (e - s) / 2; // 중간점

        // 재귀 함수
        // 두 그룹으로 나눔
        merget_sort(A, tmp, s, m);
        merget_sort(A, tmp,m + 1, e);

        for (int i = s; i <= e; i++) {
            tmp[i] = A[i];
        }

        int k = s; // 정렬된 값을 저장할 때 사용하는 인덱스 값
        // 투 포인터
        int index1 = s; //  1그룹 시작점
        int index2 = m + 1; // 2그룹 시작점

        // 두 그룹을 병합하는 로직
        while (index1 <= m && index2 <= e) {
            if (tmp[index1] > tmp[index2]) {
                A[k] = tmp[index2];
                k++;
                index2++;
            } else {
                A[k] = tmp[index1];
                k++;
                index1++;
            }

            // 한 쪽 그룹이 모두 선택된 후 남아 있는 값 정리
            while (index1 <= e) {
                A[k] = tmp[index1];
                k++;
                index1++;
            }
            while (index2 <= e) {
                A[k] = tmp[index2];
                k++;
                index2++;
            }
        }
    }

}
