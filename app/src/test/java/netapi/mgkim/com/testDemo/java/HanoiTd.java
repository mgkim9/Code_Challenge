package netapi.mgkim.com.testDemo.java;

import org.junit.Test;

public class HanoiTd {
    @Test
    public void test01() {
        int i = 3;
//        hanoi(i, 1, 2, 3);
        System.out.printf("\n\n\nNon Recursive Hanoi \n");
        nr_hanoi(i, 10, 20, 30);
    }

    void move(int n, int from, int to){
        System.out.printf("\n%d from %d to %d", n, from, to);
    }

    // n개의 원반을 from 에서 by를 거쳐 to로 옮긴다.
    void hanoi(int n, int from, int by, int to){
        if (n == 1)
            move(n, from, to);
        else{
            hanoi(n - 1, from, to, by);    // 1번  N-1개의 원반을 기둥3을 거쳐 2로 옮긴다.
            move(n, from, to);                // 2번 기둥 1에서 1개의 원반을 기둥 3으롱 롬긴다.
            hanoi(n - 1, by, from, to);    // 3번 기둥 2에서 N-1개의 원반을 기둥 3으로 옮긴다.
        }
    }


    // 비재귀에 사용하기 위한 스택
    int MAX = 100;
    int[] stack = new int[MAX];        // 스택의 긴  통
    int top;            // 스택의 상단

    void init_stack(){
        top = -1;
    }

    int push(int t){

        if (top >= MAX - 1){
            System.out.printf("\n    Stack overflow.");
            return -1;
        }

        stack[++top] = t;
        return t;
    }

    int pop(){
        if (top < 0){
            System.out.printf("\n   Stack underflow.");
            return -1;
        }
        return stack[top--];
    }

    boolean is_stack_empty(){
        return (top > -1) ? false : true;
    }

    // 하노이의 탑 비재귀로 변환
    void nr_hanoi(int n, int from, int by, int to){

        init_stack();
        while (true){
            while (n > 1){
                push(to);    // 인자리스트 푸쉬
                push(by);
                push(from);
                push(n);
                n--;        // 인자리스트 변경 1
                push(to);    // to 와 by를 교환하기 위해 임시로 저장
                to = by;
                by = pop();
            }

            move(n, from, to);        // 재귀의 마지막 종료 처리

            if (!is_stack_empty()){
                n = pop();
                from = pop();
                by = pop();
                to = pop();

                move(n, from, to);        // 실제 이동 작업

                n--;        // 인자리스트 변경 2
                push(from);
                from = by;
                by = pop();
            }
            else{
                break;
            }
        }
    }

}
