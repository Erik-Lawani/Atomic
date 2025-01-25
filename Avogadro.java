public class Avogadro {

    // Finding the Boltzmann and avogadro constants
    public static void main(String[] args) {
        double t = 297.0; // Absolute temperature.
        double radius = 0.5e-6; // radius of the bead.
        double v = 9.135e-4; // viscosity of water at room temperature.
        double r = 8.31446; // Universal gas constant.
        double ot = 0.5; // time elapsed between two frames.
        double factor = 0.175e-6; // converting factor from pixel to meters.

        double sdisplacement = 0.0;
        double count = 0.0;

        // Reading from StdInput and computing the mean square displacement.
        while (!StdIn.isEmpty()) {
            double radials = StdIn.readDouble();
            sdisplacement += Math.pow(radials * factor, 2);
            count++;
        }
        double mdisplacement = sdisplacement / (2.0 * count);

        // Computing the boltzmann and avogadro constants.
        double boltzman = (6.0 * Math.PI * v * radius * mdisplacement) /
                (2.0 * t * ot);
        double avogadro = r / boltzman;
        StdOut.println("Boltzmann = " + String.format("%.4e", boltzman));
        StdOut.println("Avogadro = " + String.format("%.4e", avogadro));
    }
}
