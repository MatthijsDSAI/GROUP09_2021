import titan.ODEFunctionInterface;
import titan.RateInterface;
import titan.StateInterface;
import titan.Vector3dInterface;

public class ODEFunction implements ODEFunctionInterface {

    private Vector3dInterface[] rates;
    private static final double G = 6.674 * Math.pow(10, -11);

    public ODEFunction() {

    }

    public Vector3dInterface[] calculateF(Vector3dInterface[] coordinates, double[] masses) {
        Vector3dInterface[] forces = new Vector3dInterface[coordinates.length];

        // Loops through all the bodies, where i is the body on which the total force is being calculated
        for(int i = 0; i < forces.length; i++) {

            Vector3dInterface totalF = new Vector3d(0,0,0);

            // Loops through all the other bodies and adds the force of each body on body i to the total force
            for(int j = 0; j < forces.length; j++) {
                if(i != j) {
                    double mm = (masses[i] * masses[j]);
                    double distance = coordinates[i].dist(coordinates[j]);
                    Vector3dInterface unitVector = (coordinates[i].sub(coordinates[j])).mul(1/distance);
                    totalF = totalF.add(unitVector.mul(-G * mm/Math.pow(distance, 2)));
                }
            }
            forces[i] = totalF;
        }
        return forces;
    }

    public Vector3dInterface[] calculateAccelerations(Vector3dInterface[] forces, double[] masses) {
        Vector3dInterface[] accelerations = new Vector3dInterface[forces.length];
        for(int i = 0; i < accelerations.length; i++) {
            accelerations[i] = new Vector3d(forces[i].getX()/masses[i], forces[i].getY()/masses[i], forces[i].getZ()/masses[i]);
        }
        return accelerations;
    }

    @Override
    public RateInterface call(double t, StateInterface y) {
        State state = (State) y;
        double step = t - state.getTime();
        rates = new Vector3dInterface[state.getCoordinates().length];

        Vector3dInterface[] forces = calculateF(state.getCoordinates(), state.masses);
        Vector3dInterface[] accelerations = calculateAccelerations(forces, state.masses);

        for(int i = 0; i < rates.length; i++) {
            rates[i] = accelerations[i].mul(step);
        }

        return new Rate(rates);
    }
}