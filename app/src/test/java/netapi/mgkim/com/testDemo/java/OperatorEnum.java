package netapi.mgkim.com.testDemo.java;

public enum OperatorEnum {
    PLUS {
        @Override
        int cal(int a, int b) {
            return a + b;
        }
    },
    MINUS {
        @Override
        int cal(int a, int b) {
            return a - b;
        }
    },
    B_MINUS {
        @Override
        int cal(int a, int b) {
            return b - a;
        }
    },
    MUL {
        @Override
        int cal(int a, int b) {
            return a * b;
        }
    },
    DIV {
        @Override
        int cal(int a, int b) {
            if (b == 0) {
                return 0;
            }
            return a / b;
        }
    },
    B_DIV {
        @Override
        int cal(int a, int b) {
            if (a == 0) {
                return 0;
            }
            return b / a;
        }
    };

    abstract int cal(int a, int b);
}