import java.awt.Color;

public class BeadFinder {
    private Stack<Blob> bag; // creating a stack to populate the blobs inside.

    //  finds all blobs in the specified picture using luminance threshold tau
    public BeadFinder(Picture picture, double tau) {
        int width = picture.width();
        int height = picture.height();

        StdOut.println(width * height);

        // array to go through pixels and finding blobs.
        boolean[][] array = new boolean[height][width];

        /* Nested loop to find the luminance of each pixel.
        If the luminance is less than tau, ignore the pixel by marking false in
        the boolean array.
         */
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = picture.get(col, row);
                double luminance = Luminance.intensity(color);
                if (luminance >= tau) array[row][col] = true;
                if (luminance < tau) array[row][col] = false;
            }
        }
        this.bag = new Stack<>();
        for (int col = 0; col < array[0].length; col++) {
            for (int row = 0; row < array.length; row++) {
                if (array[row][col]) {
                    Blob blob = new Blob();
                    dfs(array, blob, row, col);
                    bag.push(blob);
                }
            }
        }
    }

    /* finding all the pixels that form the blob
    dfs makes sure we go through all pixel north, south, east or west. After
    visiting a pixel and adding it to a blob, we mark it false to indicate we
    already visited it, and it is already part to a blob.
     */
    private void dfs(boolean[][] array, Blob blob, int a, int b) {
        int n = array.length;
        int m = array[0].length;

        if (a < 0 || a >= n) return;
        if (b < 0 || b >= m) return;
        if (!array[a][b]) return;
        blob.add(b, a);
        array[a][b] = false;

        /* Recursive method to ensure the motion north, south, east, west of the
        boolean array.
         */

        dfs(array, blob, a + 1, b);  // South.
        dfs(array, blob, a, b + 1);  // Est.
        dfs(array, blob, a - 1, b);  // North.
        dfs(array, blob, a, b - 1);  // West.
    }


    //  returns all beads (blobs with >= min pixels)
    public Blob[] getBeads(int min) {

        /* Using an intermediate stack to store the beads. This will allow us to
        find the length of the blob array
         */
        Stack<Blob> intermediate = new Stack<>();
        for (Blob b : bag) {
            if (b.mass() >= min) intermediate.push(b);
        }

        int n = intermediate.size();

        // Copying back the beads in intermediate in the blob array.
        Blob[] bBlob = new Blob[n];
        int index = 0;
        for (Blob b : intermediate) {
            bBlob[index] = b;
            index++;
        }
        return bBlob;
    }

    //  Using the main to test the functions created above.
    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        String image = args[2];
        Picture picture = new Picture(image);
        BeadFinder bead = new BeadFinder(picture, tau);
        Blob[] get = bead.getBeads(min);

        // Printing the beads using the string format in blob.
        for (int i = 0; i < get.length; i++) {
            StdOut.println(get[i].toString());
        }
    }

}

