public class BeadTracker {

    // Tracking the beads in each frame by find the minimum distance.
    public static void main(String[] args) {

        Stopwatch start = new Stopwatch();

        // Reading information from command lines
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);


        /* Main loop for calculating distance. We read all images using
        the args array and create Bead_finder objects to locate beads.
         */

        for (int i = 3; i < args.length - 1; i++) {

            // finding all the beads in two consecutive frames.
            String image = args[i];
            String image2 = args[i + 1];
            Picture picture1 = new Picture(image);
            Picture picture2 = new Picture(image2);
            BeadFinder bead1 = new BeadFinder(picture1, tau);
            BeadFinder bead2 = new BeadFinder(picture2, tau);
            Blob[] thing1 = bead1.getBeads(min);
            Blob[] thing2 = bead2.getBeads(min);

            /* Calculating the distance between beads in frame t + t_delta
            and frame t. Printing the minimum distance for each bead.
             */

            for (int j = 0; j < thing2.length; j++) {
                double mindistance = Double.POSITIVE_INFINITY;
                for (int k = 0; k < thing1.length; k++) {
                    double distance = thing2[j].distanceTo(thing1[k]);
                    if (distance < mindistance) {
                        mindistance = distance;
                    }
                }

                if (mindistance <= delta) {
                    StdOut.println(String.format("%.4f", mindistance));
                }
            }
        }
        StdOut.println("ellapsed time = " + start.elapsedTime());
    }
}



