package main.codetest.basic.architecture;

import java.util.Scanner;

public class Search extends ProgramMenu {

    public static final int[] QUESTIONS = {21};

    public Search(int[] qArr) {
        super(qArr);
    }

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("문제 번호를 입력하세요 : (종료하려면 0 입력)");
            int input = sc.nextInt();

            switch (input) {
                case 0:
                    break;
                case 21:
                    no021();
                    break;
                default:
                    wrongNumberMessage();
                    break;
            }

            if (input == 0) {
                sc.close();
                System.out.println("프로그램 종료 >>> ");
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ProgramMenu program = new Search(QUESTIONS);
        System.out.println("프로그램 시작 >>> ");
        program.menu();

    }

    private void no021() {
        System.out.println("no021 >>>");

    }
}
