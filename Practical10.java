public class Practical10 {

  public static String DisplayGrid (int size) {

        String[][] arr = new String[size][size];

        

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {
            
            
                arr[i][j] = String.format("%2d", (i + j) % size + 1);
                
                
            }
        }


        for (int i = 0; i < size * size / 2  ; i++) {

        int a = (int) ((System.nanoTime() / 47) % size);

        int b = (int) ((System.nanoTime() / 73 ) % size);
            
        if (!arr[a][b].equals("  ")) {

            arr[a][b] = "  ";

        } 
        
        else {

            i--;

        }
    }

        String s = "";
        
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                s += "-----";

            }

            s += "-\n";

            for (int j = 0; j < size; j++) {

                s += "| " + arr[i][j] + " ";

            }

            s += "|\n";
        }

        for (int j = 0; j < size; j++) {
            s += "-----";
        }
        s += "-\n";

        return s;
    }

    public static void main(String[] args) {

        int size = Integer.parseInt(args[0]) ; 
        
        
        System.out.print(DisplayGrid(size));
    }
}
