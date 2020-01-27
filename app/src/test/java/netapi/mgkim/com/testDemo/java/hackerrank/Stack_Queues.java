package netapi.mgkim.com.testDemo.java.hackerrank;


import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;

import netapi.mgkim.com.testDemo.java.programmers.DFSnBFSTest;

public class Stack_Queues {
    @Test
    public void test01() {
//        System.out.printf(isBalanced("{[()]}"));

        try {
//            Solution001.main(null);
//            System.out.println("");
//
//            Solution002.main(null);
//            System.out.println("");
//
//            Solution003.main(null);
//            System.out.println("");
//
//            Solution004.main(null);
//            System.out.println("");
//
//            Solution004_2.main(null);
//            System.out.println("");
//
//            Solution005.main(null);
//            System.out.println("");

            Solution006.main(null);
            System.out.println("");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //https://www.hackerrank.com/challenges/balanced-brackets/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
//Balanced Brackets
    public static class Solution001 {

        // Complete the isBalanced function below.
        static String isBalanced(String s) {
            Stack<Character> stack = new Stack();
            for (char c : s.toCharArray()) {
                switch(c) {
                    case '{':
                    case '[':
                    case '(':
                        stack.push(c);
                        break;

                    case ')':
                        if(stack.isEmpty() || stack.pop() != c-1) {
                            return "NO";
                        }
                        break;
                    default:
                        if(stack.isEmpty() || stack.pop() != c-2) {
                            return "NO";
                        }
                        break;
                }
            }

            return stack.isEmpty() ? "YES" : "NO";
        }

        private static final Scanner<Object> scanner = new Scanner(System.in);

        public static void main(String[] args) throws IOException {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            scanner.setData(1,"{[()]}");

            int t = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int tItr = 0; tItr < t; tItr++) {
                String s = scanner.nextLine();

                String result = isBalanced(s);
                System.out.printf("result " + result);

//                bufferedWriter.write(result);
//                bufferedWriter.newLine();
            }

//            bufferedWriter.close();

            scanner.close();
        }
    }

    //https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
//Queues: A Tale of Two Stacks
    public static class Solution002 {
        public static void main(String[] args) {

            Scanner<Integer> scan = new Scanner(System.in);
            scan.setData(10, 1, 42, 2, 1, 14, 3, 1, 28, 3, 1, 60, 1, 78, 2, 2);
            int n = scan.nextInt();
            MyQueue<Integer> queue = new MyQueue<Integer>(n);

            for (int i = 0; i < n; i++) {
                int operation = scan.nextInt();
                if (operation == 1) { // enqueue
                    queue.enqueue(scan.nextInt());
                } else if (operation == 2) { // dequeue
                    queue.dequeue();
                } else if (operation == 3) { // print/peek
                    System.out.println(queue.peek());
                }
            }
            scan.close();
        }

        static class MyQueue<T> implements IMyQueue<T> {
            private int maxSize;
            private int curIndex;
            private int lastIndex;
            private Object[] datas;

            public MyQueue(int maxSize) {
                this.maxSize = maxSize;
                curIndex = 0;
                lastIndex = -1;
                datas = new Object[maxSize];
            }

            @Override
            public void enqueue(T data) {
                if (isFull()) {
                    throw new IllegalStateException();
                }
                datas[++lastIndex] = data;
            }

            @Override
            public T dequeue() {
                if (isEmpty()) {
                    throw new IllegalStateException();
                }
                Object temp = datas[curIndex];
                datas[curIndex++] = null;
                return (T) temp;
            }

            @Override
            public T peek() {
                if (isEmpty()) {
                    return null;
                }
                return (T) datas[curIndex];
            }
            boolean isFull() {
                return lastIndex + 1 >= maxSize;
            }
            boolean isEmpty() {
                return lastIndex < curIndex;
            }
        }

        interface IMyQueue<T> {
            void enqueue(T data);
            T dequeue();
            T peek();
        }
    }


    //https://www.hackerrank.com/challenges/largest-rectangle/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
//Largest Rectangle
    public static class Solution003 {

        // Complete the largestRectangle function below.
        static long largestRectangle(int[] houses) {
            Stack<Integer> stack = new Stack();
            int maxSize = 0;
            for (int houseIndex = 0; houseIndex < houses.length; houseIndex++) {
                if (stack.isEmpty() || houses[stack.peek()] < houses[houseIndex]) {
                    stack.add(houseIndex);
                } else if (houses[stack.peek()] == houses[houseIndex]) {
                    stack.pop();
                    stack.add(houseIndex);
                } else {
                    do {
                        int curHight = houses[stack.pop()];
                        int prevIndex = stack.isEmpty() ? 0 : (stack.peek() + 1);
                        maxSize = Math.max(maxSize, (houseIndex - prevIndex) * curHight);
                    } while (!stack.isEmpty() && houses[stack.peek()] > houses[houseIndex]);
                    stack.add(houseIndex);
                }
            }

            while (!stack.isEmpty()) {
                int curHight = houses[stack.pop()];
                int prevIndex = stack.isEmpty() ? 0 : (stack.peek() + 1);
                maxSize = Math.max(maxSize, (houses.length - prevIndex) * curHight);
            }
            return maxSize;
        }

        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) throws IOException {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//            scanner.setData(6, 3, 11, 11, 10, 10, 10);    // 50
//            scanner.setData(5, 1,2,3,4,5);    // 9
//            scanner.setData(5, 1, 3, 5, 9, 11);   // 18
//            scanner.setData(5, 11, 11, 10, 10, 10);   // 50
//            scanner.setData(10, 8979, 4570, 6436, 5083, 7780, 3269, 5400, 7579, 2324, 2116); //26152
            scanner.setData(100, "6873 7005 1372 5438 1323 9238 9184 2396 4605 162 7170 9421 4012 5302 6277 2438 4409 3391 4956 4488 622 9365 5088 6926 2691 6909 1050 2824 3538 5801 8468 411 9158 9841 2201 481 5431 1385 2877 36 1547 48 5809 1911 1702 8439 4349 6111 1830 5657 6951 8804 5022 8391 2083 7713 5300 3133 6890 5190 5286 1710 1953 4445 7903 4154 4926 3335 5539 4156 9723 3438 556 1885 5349 2258 324 6050 4722 8506 1707 1673 7310 3081 65 9393 7147 1717 8878 389 6908 4165 2099 5213 8610 3 9368 3536 9690 1259"); //51060

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] h = new int[n];

            String[] hItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            System.out.printf("inputs " + Arrays.toString(hItems));

            for (int i = 0; i < n; i++) {
                int hItem = Integer.parseInt(hItems[i]);
                h[i] = hItem;
            }

            long result = largestRectangle(h);

            System.out.printf("result " + result);
//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
//
//            bufferedWriter.close();

            scanner.close();
        }
    }


    //https://www.hackerrank.com/challenges/min-max-riddle/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
//Min Max Riddle
    public static class Solution004 {

        // Complete the riddle function below.
        static long[] riddle(long[] arr) {
            // complete this function
            long[] results = new long[arr.length];
            long[] tempArray = new long[arr.length];//Arrays.copyOf(arr, arr.length);
            int step = 0;
            // step 0
            for (int i = 0; i < results.length; i++) {
                if(arr[i] > results[0]) {
                    results[0] = arr[i];
                }
            }

            // step 1
            for (int i = 0; i < arr.length - step; i++) {
                if(arr[i] > arr[i+step]) {
                    tempArray[i] = arr[i+step];
                } else {
                    tempArray[i] = arr[i];
                }
                if(tempArray[i] > results[step]) {
                    results[step] = tempArray[i];
                }
            }
            step++;

            // step else
            while (step < arr.length) {
                for (int i = 0; i < arr.length - step; i++) {
                    if(tempArray[i] > arr[i+step]) {
                        tempArray[i] = arr[i+step];
                    }
                    if(tempArray[i] > results[step]) {
                        results[step] = tempArray[i];
                    }
                }
                step++;
            }

            return results;
        }

        private static long getMax(long[] arr, int start, int end) {
            long max = 0;
            for (int i = start; i < end; i++) {
                if(arr[i] > max) {
                    max = arr[i];
                }
            }
            return max;
        }

        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) throws IOException {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            scanner.setData(6, "3 5 4 7 6 2");
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            long[] arr = new long[n];

            String[] arrItems = scanner.nextLine().split(" ");
            System.out.printf("inputs " + Arrays.toString(arrItems));
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                long arrItem = Long.parseLong(arrItems[i]);
                arr[i] = arrItem;
            }

            long[] res = riddle(arr);
            System.out.printf("result " + Arrays.toString(res));
//            for (int i = 0; i < res.length; i++) {
//                bufferedWriter.write(String.valueOf(res[i]));
//
//                if (i != res.length - 1) {
//                    bufferedWriter.write(" ");
//                }
//            }
//
//            bufferedWriter.newLine();
//
//            bufferedWriter.close();

            scanner.close();
        }
    }

    public static class Solution004_2 {
        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);
            sc.setData(6, 3, 5, 4, 7, 6, 2);
            int n = sc.nextInt();
            int[] arr = new int[n];

            for(int i=0; i<arr.length; i++){
                arr[i] = sc.nextInt();
            }

            int[] result = riddle(arr);

            for(int i=0; i<result.length; i++){
                if(i != 0){
                    System.out.print(" ");
                }
                System.out.print(result[i]);
            }
            sc.close();
        }

        /* Array에서 Window Size 1부터 n까지 증가할 때, 각 Size값의 최소값들 중 최대값을 구한다. */
        static int[] riddle(int[] arr) {
            int[] lefts = leftWinSize(arr);
            int[] rights = rightWinSize(arr);

            SortedMap<Integer, Integer> numberToWinSize = new TreeMap<>(Collections.reverseOrder());

            // 각 값에 대한 최대 length값을 구한다.
            for(int i=0; i<arr.length; i++){
                // 동일한 값이 있을 수 있기 때문에 기존값이 있다면 업데이트함.
                numberToWinSize.put(
                        arr[i],
                        Math.max(numberToWinSize.getOrDefault(arr[i], -1),lefts[i] + rights[i] + 1));
            }

            Iterator<Integer> iter = numberToWinSize.keySet().iterator();
            int number = iter.next();
            int[] result = new int[arr.length];

            for(int i=0; i<result.length; i++){
                // 해당 index의 값이 가질 수 있는 Window Size가 가질 수 있는 가장 큰 값을 구한다.
                while(numberToWinSize.get(number) <= i){
                    number = iter.next();
                }
                result[i] = number;
            }
            return result;
        }

        /* Array의 각 값에서 해당값이 최소값이 되는 좌측부터 연속한 Window Size를 구한다. */
        static int[] leftWinSize(int[] arr){
            int[] lefts = new int[arr.length];
            Stack<Integer> indices = new Stack<>();

            for(int i=0; i<arr.length; i++){
                while(!indices.isEmpty() && arr[i] <= arr[indices.peek()]){
                    indices.pop();
                }
                lefts[i] = i - (indices.isEmpty() ? 0 : indices.peek() + 1);
                indices.push(i);
            }

            return lefts;
        }

        /* Array의 각 값에서 해당값이 최소값이 되는 우측부터 연속한 Window Size를 구한다. */
        static int[] rightWinSize(int[] arr){
            int[] rights = new int[arr.length];
            Stack<Integer> indices = new Stack<>();

            for(int i=arr.length-1; i>=0; i--){
                while(!indices.isEmpty() && arr[i] <= arr[indices.peek()]){
                    indices.pop();
                }

                rights[i] = (indices.isEmpty() ? arr.length : indices.peek()) - i - 1;
                indices.push(i);
            }
            return rights;
        }
    }


    //https://www.hackerrank.com/challenges/simple-text-editor/problem
    //Simple Text Editor
    public static class Solution005 {

        static final char CMD_ADD = '1';
        static final char CMD_DEL = '2';
        static final char CMD_SHOW = '3';
        static final char CMD_UNDO = '4';

        public static void main(String[] args)  throws IOException {
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            int n = Integer.parseInt(br.readLine());
            //실재 Test에서는 Scanner가 아닌 BufferedReader를 사용해야 time out이 발생하지 않는다.!!
            Scanner sc = new Scanner(System.in);
            sc.setData(8,
                    "1 abc", "\n",
                    "3 3", "\n",
                    "2 3", "\n",
                    "1 xy", "\n",
                    "3 2", "\n",
                    "4", "\n",
                    "4", "\n",
                    "3 1");
            int n = sc.nextInt();

            CommandList commandList = new CommandList(n);
            for(int i=0;i<n;i++){
//                String inp = br.readLine();
                String inp = sc.nextLine();
                char t = inp.charAt(0);
                if(t == CMD_UNDO) {
                    commandList.addCmd(CMD_UNDO, null);
                } else {
                    commandList.addCmd(t, inp.substring(2));
                }
            }
        }

        static void solution(String[] cmds) {
            CommandList commandList = new CommandList(cmds.length);
            for (int i = 0; i < cmds.length; i++) {
                if (cmds[i].length() <= 2) {
                    if (cmds[i].charAt(0) != '4') {
                        // 입력 오류
                        System.out.println("입력 오류");
                        return;
                    }
                    commandList.addCmd(CMD_UNDO, null);
                } else if (cmds[i].charAt(1) == ' ') {
                    commandList.addCmd(cmds[i].charAt(0), cmds[i].substring(2));
                } else {
                    // 입력 오류
                    System.out.println("입력 오류");
                    return;
                }
            }
        }

        static class Stack<E> implements IStack<E> {
            private Object[] datas;
            private int maxSize;
            private int index;

            public Stack(int maxSize) {
                this.maxSize = maxSize;
                datas = new Object[maxSize];
                index = -1;
            }

            @Override
            public void push(E data) {
                if(isFull()) {
                    throw new IllegalStateException();
                }
                datas[++index] = data;
            }

            @Override
            public E pop() {
                if(isEmpty()) {
                    throw new IllegalStateException();
                }
                return (E) datas[index--];
            }

            private boolean isFull() {
                return index + 1 >= maxSize;
            }

            private boolean isEmpty() {
                return index < 0;
            }
        }
        interface IStack<E> {
            void push(E data);
            E pop();
        }

        static class CommandList {
            private StringBuilder datas;
            private Stack<Command> logs;

            public CommandList(int size) {
                datas = new StringBuilder();
                logs = new Stack(size);
            }

            void addCmd(char cmd, String value) {
                switch (cmd) {
                    case CMD_ADD:
                        add(value);
                        break;
                    case CMD_DEL:
                        String deleteStr = datas.substring(datas.length() - Integer.valueOf(value));
                        delete(deleteStr);
                        break;
                    case CMD_SHOW:
                        show(Integer.valueOf(value) - 1);
                        break;
                    case CMD_UNDO:
                        undo();
                        break;
                    default:
                        //error
                        return;
                }
            }

            void add(String value) {
                logs.push(new Command(CMD_ADD, value));
                datas.append(value);
            }

            void delete(String value) {
                logs.push(new Command(CMD_DEL, value));
                datas.delete(datas.length() - value.length(), datas.length());
            }

            void show(int index) {
                System.out.println(datas.charAt(index));
            }

            void undo() {
                Command cmd = logs.pop();
                if (cmd.cmd == CMD_ADD) {
                    datas.delete(datas.length() - cmd.value.length(), datas.length());
                } else {
                    datas.append(cmd.value);
                }
            }
        }

        static class Command {
            private char cmd;
            private String value;

            public Command(char cmd, String value) {
                this.cmd = cmd;
                this.value = value;
            }
        }
    }

//https://www.hackerrank.com/challenges/castle-on-the-grid/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
    //Castle on the Grid
    public static class Solution006 {
    static int test = 0;
    static int minimumMoves_BFS(String[] grid, int startY, int startX, int goalY, int goalX) {

        Queue<Pos> queue = new LinkedList<>();
        int[][] board = new int[grid.length][grid.length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if(grid[i].charAt(j) == 'X') {
                    board[i][j] = -2;
                } else {
                    board[i][j] = -1;
                }
            }
        }

        queue.add(new Pos(startX,startY, 0));
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Pos curPos = queue.poll();
            if(curPos.x == goalX && curPos.y == goalY) {
                System.out.println(curPos.step);
                for (int i = 0; i < board.length; i++) {
                    System.out.println(Arrays.toString(board[i]));
                }
                min = Math.min(min, curPos.step);
            }

            if(board[curPos.y][curPos.x] == -2 || (board[curPos.y][curPos.x] != -1 && board[curPos.y][curPos.x] < curPos.step)) {
                continue;
            }

            board[curPos.y][curPos.x] = curPos.step;
            for ( PosWay way : PosWay.values()) {
                if(isPosWay(board, curPos, way)) {
                    queue.add(curPos.newPos(way));
                }
            }
        }
        return min;
    }

    private static boolean isPosWay(int[][] board, Pos pos, PosWay way) {
        int newY = pos.y;
        int newX = pos.x;
        int nextStep = pos.step + (pos.lastWay == way ? 0 : 1);
        switch (way) {
            case POS_LEFT:
                newX -= 1;
                if (newX < 0) {
                    return false;
                }
                break;
            case POS_RIGHT:
                newX += 1;
                if (newX >= board[0].length) {
                    return false;
                }
                break;
            case POS_UP:
                newY -= 1;
                if (newY < 0) {
                    return false;
                }
                break;
            case POS_DOWN:
                newY += 1;
                if (newY >= board.length) {
                    return false;
                }
                break;
        }
        if (board[newY][newX] == -2/* || (board[newY][newX] != -1 && board[newY][newX] < nextStep)*/) {
            return false;
        }
        return true;
    }

    enum PosWay {
        POS_LEFT, POS_RIGHT, POS_UP, POS_DOWN
    }
    private static class Pos {
        private int x;
        private int y;
        private int step;
        private PosWay lastWay;
        public Pos(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }

        public Pos(int x, int y, int step, PosWay way) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.lastWay = way;
        }
        public Pos newPos(PosWay way) {
            int nextStep = step + (lastWay == way ? 0 : 1);
            switch (way) {
                case POS_LEFT:
                    return new Pos(x - 1, y,nextStep, PosWay.POS_LEFT);
                case POS_RIGHT:
                    return new Pos(x + 1, y,nextStep, PosWay.POS_RIGHT);
                case POS_UP:
                    return new Pos(x, y - 1,nextStep, PosWay.POS_UP);
                case POS_DOWN:
                    return new Pos(x, y + 1,nextStep, PosWay.POS_DOWN);
            }
            return null;
        }
    }

    // Complete the minimumMoves function below.
        static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {

            char[][] board = new char[grid.length][grid.length];
            boolean[][] visit = new boolean[grid.length][];
            for(int i = 0; i < grid.length; i++) {
                board[i] = grid[i].toCharArray();
                visit[i] = new boolean[grid.length];
            }

            doMove(board, visit, startY, startX, goalY, goalX, -1, 0);
            System.out.println(max_cnt);
            return max_cnt;

        }

        public static final int MOVE_LEFT = 1;
        public static final int MOVE_RIGHT = 2;
        public static final int MOVE_UP = 3;
        public static final int MOVE_DOWN = 4;
        public static int max_cnt = Integer.MAX_VALUE;

    private static void doMove(char[][] board, boolean[][] visit, int startX, int startY, int goalX, int goalY, int move, int cnt) {
        if(startX == goalX && startY == goalY) {
            System.out.println("cnt " + cnt + " max_cnt " + max_cnt);
            max_cnt = Math.min(cnt, max_cnt);
            return;
        }
        int temp = startX + 1;  // right

        if(temp < board.length && !visit[startY][temp] && board[startY][temp] != 'X') {
            visit[startY][temp] = true;
            doMove(board, visit, temp, startY, goalX, goalY, MOVE_RIGHT, move == MOVE_RIGHT ? cnt : cnt+1);
            visit[startY][temp] = false;
        }

        temp = startX - 1;  // left
        if(temp >= 0 && !visit[startY][temp] && board[startY][temp] != 'X') {
            visit[startY][temp] = true;
            doMove(board, visit, temp, startY, goalX, goalY, MOVE_LEFT, move == MOVE_LEFT ? cnt : cnt+1);
            visit[startY][temp] = false;
        }

        temp = startY - 1;  // up
        if(temp >= 0 && !visit[temp][startX] && board[temp][startX] != 'X') {
            visit[temp][startX] = true;
            doMove(board, visit, startX, temp, goalX, goalY, MOVE_UP, move == MOVE_UP ? cnt : cnt+1);
            visit[temp][startX] = false;
        }

        temp = startY + 1;  // down
        if(temp < board.length && !visit[temp][startX] && board[temp][startX] != 'X') {
            visit[temp][startX] = true;
            doMove(board, visit, startX, temp, goalX, goalY, MOVE_DOWN, move == MOVE_DOWN ? cnt : cnt+1);
            visit[temp][startX] = false;
        }
    }

        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) throws IOException {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

//            scanner.setData(3,
//                    ".X.", "\n",
//                    ".X.", "\n",
//                    "...", "\n",
//                    "0 0 0 2"
//            );

//            scanner.setData(4,
//                    ".X..", "\n",
//                    "....", "\n",
//                    ".X..", "\n",
//                    "....", "\n",
//                    "0 0 3 3"
//            );

            scanner.setData(40,
                    "...X......XX.X...........XX....X.XX.....", "\n",
                    ".X..............X...XX..X...X........X.X", "\n",
                    "......X....X....X.........X...........X.", "\n",
                    ".X.X.X..X........X.....X.X...X.....X..X.", "\n",
                    "....X.X.X...X..........X..........X.....", "\n",
                    "..X......X....X....X...X....X.X.X....XX.", "\n",
                    "...X....X.......X..XXX.......X.X.....X..", "\n",
                    "...X.X.........X.X....X...X.........X...", "\n",
                    "XXXX..X......X.XX......X.X......XX.X..XX", "\n",
                    ".X........X....X.X......X..X....XX....X.", "\n",
                    "...X.X..X.X.....X...X....X..X....X......", "\n",
                    "....XX.X.....X.XX.X...X.X.....X.X.......", "\n",
                    ".X.X.X..............X.....XX..X.........", "\n",
                    "..X...............X......X........XX...X", "\n",
                    ".X......X...X.XXXX.....XX...........X..X", "\n",
                    "...X....XX....X...XX.X..X..X..X.....X..X", "\n",
                    "...X...X.X.....X.....X.....XXXX.........", "\n",
                    "X.....XX..X.............X.XX.X.XXX......", "\n",
                    ".....X.X..X..........X.X..X...X.X......X", "\n",
                    "...X.....X..X.............X......X..X..X", "\n",
                    "........X.....................X....X.X..", "\n",
                    "..........X.....XXX...XX.X..............", "\n",
                    "........X..X..........X.XXXX..X.........", "\n",
                    "..X..X...X.......XX...X.....X...XXX..X..", "\n",
                    ".X.......X..............X....X...X....X.", "\n",
                    ".................X.....X......X.....X...", "\n",
                    ".......X.X.XX..X.XXX.X.....X..........X.", "\n",
                    "X..X......X..............X..X.X.......X.", "\n",
                    "X........X.....X.....X....XX.......XX...", "\n",
                    "X.....X.................X.....X..X...XXX", "\n",
                    "XXX..X..X.X.XX..X....X.....XXX..X......X", "\n",
                    "..........X.....X.....XX................", "\n",
                    "..X.........X..X.........X...X.....X....", "\n",
                    ".X.X....X...XX....X...............X.....", "\n",
                    ".X....X....XX.XX........X..X............", "\n",
                    "X...X.X................XX......X..X.....", "\n",
                    "..X.X.......X.X..X.....XX.........X..X..", "\n",
                    "........................X..X.XX..X......", "\n",
                    "........X..X.X.....X.....X......X.......", "\n",
                    ".X..X....X.X......XX....................", "\n",
                    "34 28 16 8");

//            scanner.setData(10,
//                    ".X..XX...X", "\n",
//                    "X.........", "\n",
//                    ".X.......X", "\n",
//                    "..........", "\n",
//                    "........X.", "\n",
//                    ".X...XXX..", "\n",
//                    ".....X..XX", "\n",
//                    ".....X.X..", "\n",
//                    "..........", "\n",
//                    ".....X..XX", "\n",
//                    "9 1 9 6");
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String[] grid = new String[n];

            for (int i = 0; i < n; i++) {
                String gridItem = scanner.nextLine();
                grid[i] = gridItem;
            }

            String[] startXStartY = scanner.nextLine().split(" ");

            int startX = Integer.parseInt(startXStartY[0]);

            int startY = Integer.parseInt(startXStartY[1]);

            int goalX = Integer.parseInt(startXStartY[2]);

            int goalY = Integer.parseInt(startXStartY[3]);

//            int result = minimumMoves(grid, startX, startY, goalX, goalY);
            int result = minimumMoves_BFS(grid, startX, startY, goalX, goalY);


//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
//
//            bufferedWriter.close();

            scanner.close();
        }
    }

}


