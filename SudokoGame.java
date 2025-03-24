import java.util.Scanner;



class Sudoko {


    String[][] arr;

    String[][] ans;

    int[] rmvIndex;

    int size;

    int remove;

    Sudoko(int size) {

        this.size = size;

        arr = new String[size][size];

        ans = new String[size][size];


        getLayout();

        selectLvl();

        removeEle();

        grid();

        giveVal();

        if (checkSolution()) {

            String party = "\uD83C\uDF89";

            System.out.println("CONGRATS YO " + party);

        }

         else {

            String crying= "\uD83D\uDE22";

            System.out.println("Solution is Wrong " + crying);

        }

    }

    void getLayout() {


        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                int num = (int) (System.nanoTime() % size) + 1;

                while (!isValid(i, j, num)) {

                    num = (int) (System.nanoTime() % size) + 1;

                }

                arr[i][j] = String.valueOf(num);

            }
        }

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                ans[i][j] = arr[i][j];

            }
        }
    }
    

    boolean isValid(int row, int col, int num) {


        for (int i = 0; i < row; i++) {

            if (arr[i][col] != null && Integer.parseInt(arr[i][col]) == num) {

                return false;

            }

        }

        for (int j = 0; j < col; j++) {

            if (arr[row][j] != null && Integer.parseInt(arr[row][j]) == num) {

                return false;

            }

        }


        return true;

    }


    void selectLvl() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("SELECT THE LEVEL:\n1. EASY\n2. MEDIUM\n3. HARD");


        while (true) {

            String level = scanner.nextLine();

            if (level.equals("1")) {

                remove = (size * size) / 3;
                break;

            } 
            else if (level.equals("2")) {
                remove = (size * size) / 2;
                break;

            } 
            
            else if (level.equals("3")) {
                remove = (size * size * 3) / 4;
                break;
            }
            
            else {
                System.out.println("INVALID OPTION ");
            }
        }
    }

   

    void grid() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                s.append("----");
            }

            s.append("-\n");

            for (int j = 0; j < size; j++) {

                s.append("| ").append(arr[i][j] != null ? arr[i][j] : " ").append(" ");
            }


            s.append("|\n");


        }


        for (int j = 0; j < size; j++) {

            s.append("----");
        }

        s.append("-\n");


        System.out.print(s.toString());

    }

    void removeEle() {

        rmvIndex = new int[2 * remove];

        int count = 0;


        while (count < remove) {

            int i = (int) (System.nanoTime() % size);

            int j = (int) (System.nanoTime() % size);

            if (arr[i][j] != null) {

                arr[i][j] = " ";

                rmvIndex[2 * count] = i;

                rmvIndex[2 * count + 1] = j;

                count++;
            }
        }
    }

    
    

    public static void main(String[] args) {


        if (args.length == 0) {


            System.out.println("ENTER THE SIZE OF THE GRID AT COMMAND-LINE ");


            return;
        }

        int size = Integer.parseInt(args[0]);

        if (size < 2) {
            System.out.println("SIZE SHOULD BE AT LEAST 2 ");

            return;

        }

        new Sudoko(size);
    }


    boolean checkSolution() {

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                if (arr[i][j] == null || !arr[i][j].equals(ans[i][j])) {

                    return false;


                }
            }
        }
        return true;
    }

    void giveVal() {
        Scanner scanner = new Scanner(System.in);


        int i = 0;


        while (i < remove) {


            int row = rmvIndex[2 * i];

            int col = rmvIndex[2 * i + 1];


            System.out.println("Enter value for index (" + (row + 1) + "," + (col + 1) + ") or 'Z' to undo:");

            String input = scanner.nextLine();


            if (input.equalsIgnoreCase("Z") && i > 0) {

                int prevRow = rmvIndex[2 * (i - 1)];

                int prevCol = rmvIndex[2 * (i - 1) + 1];

                arr[prevRow][prevCol] = " ";

                i -= 2;

                grid();

                continue;
            }

            try {
                int num = Integer.parseInt(input);


                if (num < 1 || num > size) {

                    System.out.println("Invalid input. Enter a number between 1 and " + size);

                    i--;

                    continue;

                }
                arr[row][col] = input;

                grid();

            } 
            
            catch (NumberFormatException e) {

                System.out.println("Invalid input. Enter a number.");

                i--;

            }

            i++;

        }
    }

}
