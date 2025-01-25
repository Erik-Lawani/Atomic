public class Blob {

    private int nPixels; // Number of pixels.
    private double cx; // x_coordinate center of mass.
    private double cy; // y_coordinate center of mass.
    private double sx; // sum of x_coordinate.
    private double sy; // sum of y_coordinate;

    //  creates an empty blob by setting all parameters to zero.
    public Blob() {
        this.nPixels = 0;
        this.cx = 0;
        this.cy = 0;
        this.sx = 0;
        this.sy = 0;
    }

    //  adds pixel (x, y) to this blob
    public void add(int x, int y) {
        nPixels++;
        sx = sx + x;
        sy = sy + y;
        cx = sx / nPixels;
        cy = sy / nPixels;
    }

    //  return number of pixels added to this blob
    public int mass() {
        return nPixels;
    }

    //  Euclidean distance between the center of masses of the two blobs
    public double distanceTo(Blob that) {
        double sDistance = Math.pow(cx - that.cx, 2) + Math.pow(cy - that.cy, 2);
        return Math.sqrt(sDistance);
    }

    //  string representation of this blob (see below)
    public String toString() {
        return String.format("%2d (%8.4f, %8.4f)", nPixels, cx, cy);
    }

    //  tests this class by directly calling all instance methods
    public static void main(String[] args) {
        Blob blob = new Blob();
        Blob blob2 = new Blob();
        blob.add(3, 4);
        blob.add(2, 2);
        blob2.add(1, 1);
        double distance = blob.distanceTo(blob2);
        StdOut.println(distance);
        StdOut.println(blob.mass());
        StdOut.println(Integer.parseInt("2 + 3"));

    }
}
