public class GarageDoor {

    private DoorState currentState;
    private Motor myMotor;

    public GarageDoor() {
        currentState = new Closed();
        myMotor = new Motor();
    }


    public void openDoor() {
        myMotor.upwards();
        currentState.openDoor();
    }

    public void closeDoor() {
        myMotor.downwards();
        currentState.closeDoor();
    }

    public void stopper() {
        myMotor.stop();
        currentState.stopper();
    }

    public Motor getMotor() {
        return myMotor;
    }


    abstract class DoorState {
        public void openDoor() {
            throw new IllegalStateException("This method is not allowed in the current state!");
        }

        public void closeDoor() {
            throw new IllegalStateException("This method is not allowed in the current state!");
        }

        public void stopper() {
            throw new IllegalStateException("This method is not allowed in the current state!");
        }
    }

    class Closed extends DoorState {

        @Override
        public void openDoor() {
            currentState = new Opening();
        }
    }

    class Opening extends DoorState {
        @Override
        public void closeDoor() {
            currentState = new Closing();
        }

        @Override
        public void stopper() {
            currentState = new Open();
        }
    }

    class Open extends DoorState {
        @Override
        public void closeDoor() {
            currentState = new Closing();
        }
    }

    class Closing extends DoorState {

        @Override
        public void openDoor() {
            currentState = new Opening();
        }

        @Override
        public void stopper() {
            currentState = new Closed();
        }
    }
}