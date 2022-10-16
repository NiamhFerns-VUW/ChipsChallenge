package nz.ac.vuw.ecs.swen225.gp22.app;

public enum LevelTracker {
    NONE("Start Screen", "") {
        @Override
        public LevelTracker nextLevel() {
            return LevelTracker.LEVEL1;
        }
    },
    LEVEL1("Level One", "level1") {
        @Override
        public LevelTracker nextLevel() {
            return LevelTracker.LEVEL2;
        }
        @Override
        public long getTime() {
            return 60000;
        }
    },
    LEVEL2("Level Two", "level2") {
        @Override
        public LevelTracker nextLevel() {
            return LevelTracker.NONE;
        }
        @Override
        public long getTime() {
            return 90000;
        }
    },
    SAVED_LEVEL("Saved Level", "") {
        @Override
        public LevelTracker nextLevel() {
            return LevelTracker.NONE;
        }
    };

    private final Pair<String, String> currentLevel;

    protected void setCustomName(String name) {
        currentLevel.setKey(name);
    }
    protected void setCustomPath(String path) {
        currentLevel.setValue(path);
    }

    public long getTime() {
        return 0;
    }

    public String currentName(){
        return currentLevel.key();
    }
    public String currentPath(){
        return currentLevel.value();
    }

    public LevelTracker nextLevel() {
        return LevelTracker.NONE;
    }

    LevelTracker(String levelName, String levelPath) {
        currentLevel = new Pair<>(levelName, levelPath);
    }
}
