package nz.ac.vuw.ecs.swen225.gp22.fuzz;

class Input {
    InputStrategy inputStrategy;
    int prevKey;
    int key;

    /**
     * Constructor for Input
     * @param inputStrategy the input strategy to use
     */
    public Input( InputStrategy inputStrategy) {
        this.inputStrategy = inputStrategy;
        int prevKey = 0;
    }

    /**
     * This method is used to get the next input
     * @return the next KeyEvent
     */
    public int nextInput() {
        key = inputStrategy.nextInput(this);
        prevKey = key;
        return key;
    }

    /**
     * This method is used to check if the next input is a ctrl key
     * @return true if the next input is a ctrl key
     */
    public boolean isPressCtrl() {
        return inputStrategy.isPressCtrl(this);
    }
}
