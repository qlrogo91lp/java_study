package main.codetest.basic.architecture;

public abstract class ProgramMenu {

    private final int[] QUESTIONS;

    public ProgramMenu(int[] qArr) {
        this.QUESTIONS = qArr;
    }

    /**
     * 메뉴 구현
     */
    public abstract void menu();

    /**
     * 없는 번호 입력했을 때 메시지
     */
    public void wrongNumberMessage() {
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
    }
}
