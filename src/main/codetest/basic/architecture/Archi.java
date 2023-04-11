package main.codetest.basic.architecture;

import java.io.*;
import java.util.*;

/*
* 자료구조
*/
public class Archi {
    public static final int[] QUESTIONS = {7, 8, 9, 10, 11, 12};
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Archi exe = new Archi();
        System.out.println("프로그램 시작 >>> ");

        while (true) {
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
                case 10:
                    exe.no010();
                    break;
                case 11:
                    exe.no011();
                    break;
                case 12:
                    exe.no012();
                    break;
                case 13:
                    exe.no013();
                    break;
                case 14:
                    exe.no014();
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
    * 문제 014 절댓값 힙 구현하기
    */
    private void no014() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> myQueue = new PriorityQueue<>((o1, o2) -> {
            int first_abs = Math.abs(o1);
            int second_abs = Math.abs(o2);
            if (first_abs == second_abs) {
                return o1 > o2 ? 1 : -1; // 절댓값이 같으면 음수 우선 정렬
            } else {
                return first_abs - second_abs; // 절댓값 기준으로 정렬
            }
        });

        for (int i = 0; i < N; i++) {
            int request = Integer.parseInt(br.readLine());
            if (request == 0) {
                if (myQueue.isEmpty()) {
                    System.out.println("0");
                } else {
                    System.out.println(myQueue.poll());
                }
            } else {
                myQueue.add(request);
            }
        }
    }

    /*
    * 문제 013 카드 게임
    */
    private void no013() {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> myQueue = new LinkedList<>();
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            myQueue.add(i);
        }
        while (myQueue.size() > 1) {
            myQueue.poll();
            myQueue.add(myQueue.poll());
        }
        System.out.println(myQueue.poll());
    }

    /*
    * 문제 012 오큰수 구하기 - 제한시간 1초, 난이도 골드4, 백준 1729번
    */
    private void no012() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] A = new int[n];   // 수열 배열 생성
        int[] ans = new int[n]; // 정답 배열 생성
        String[] str = bf.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(str[i]);
        }
        Stack<Integer> myStack = new Stack<>();
        myStack.push(0);    // 처음에는 항상 스택이 비어 있으므로 최초 값 push해서 초기화

        for (int i = 1; i < n; i++) {
            // 스택이 비어 있지 않고 현재 수열이 스택 top 인덱스가 가리키는 수열보다 클 경우
            while (!myStack.isEmpty() && A[myStack.peek()] < A[i]) {
                ans[myStack.pop()] = A[i];
            }
            myStack.push(i);
        }
        // 반복문을 다 돌고 나왔는데 스택이 비어 있지 않다면 빌때까지 반복
        while (!myStack.isEmpty()) {
            // 오큰수가 없으면 -1
            ans[myStack.pop()] = -1;
        }
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter((System.out))));
        // ans[] 출력
        for (int i = 0; i < n; i++) {
            bw.write(ans[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }


    /**
     * 문제 011 스택으로 오름차순 수열 만들기 - 제한시간 2초, 난이도 실버3, 백준 1874번
     */
    private void no011() {
        System.out.println("문제 11 >>> : ");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 수열 개수
        int[] A = new int[N]; // 수열 배열
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        StringBuffer bf = new StringBuffer();
        int num = 1;
        boolean result = true;
        for (int i = 0; i < N; i++) {
            // 현재 수열 값 >= 오름차순 자연수 : 값이 같아질 때까지 push() 수행
            if (A[i] >= num) {
                while (A[i] >= num) {
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
            }
            // 현재 수열 값 < 오름차순 자연수 : pop()을 수행해 수열 원소를 꺼냄
            else {
                int n = stack.pop();
                // 스택의 가장 위의 수가 만들어야 하는 수열의 수보다 크면 수열을 출력할 수 없음
                if (n > A[i]) {
                    System.out.println("NO");
                    result = false;
                    break;
                } else {
                    bf.append("-\n");
                }
            }
        }
        if (result) {
            System.out.println(bf);
        }
        System.out.println("종료 >>>");
    }

    // 노드 클래스
    public static class Node {
        public int value;
        public int index;

        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    /**
     * 문제 010 최솟값 찾기 - 제한시간 2.4초 난이도 플래티넘, 백준 11003번
     */
    private void no010() throws IOException {
        System.out.println("문제 10 >>> : ");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken()); // 숫자의 개수
        int L = Integer.parseInt(st.nextToken()); // 슬라이딩 윈도우의 크기
        st = new StringTokenizer(bf.readLine());
        Deque<Node> mydeque = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());
            // 새로운 값이 들어올 때마다 정렬 대신 현재 수보다 큰 값을 덱에서 제거해 시간 복잡도를 줄임.

            while (!mydeque.isEmpty() && mydeque.getLast().value > now) {
                mydeque.removeLast();
            }
            mydeque.addLast(new Node(now, i));
            // 범위에서 벗어난 값은 덱에서 제거
            if (mydeque.getFirst().index <= i - L) {
                mydeque.removeFirst();
            }
            bw.write(mydeque.getFirst().value + " ");
        }
        bw.flush();
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
                    } else { // else if (j == k)
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
