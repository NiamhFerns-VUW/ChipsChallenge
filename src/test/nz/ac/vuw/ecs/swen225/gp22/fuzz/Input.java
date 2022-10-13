package nz.ac.vuw.ecs.swen225.gp22.fuzz;

class Input {
    InputStrategy inputStrategy;
    int prevKey;
    int key;
    public Input( InputStrategy inputStrategy) {
        this.inputStrategy = inputStrategy;
        int prevKey = 0;
    }

    public int nextInput() {
        key = inputStrategy.nextInput(this);
        prevKey = key;
        return key;
    }

    public boolean isPressCtrl() {
        return inputStrategy.isPressCtrl(this);
    }
}
