package classes;

// class created to save instances and variables;
public class Model {
    private static final int WindowWidth = 1000;
    private static final int WindowHeight = 540;

    private boolean introFinished = false;
    private boolean randomPointsFinished = false;
    private String nickname = "def";

    public static int getWindowWidth() { return WindowWidth; }

    public static int getWindowHeight() { return WindowHeight; }

    public boolean isIntroFinished() {
        return introFinished;
    }

    public void setRandomPointsFinished(boolean randomPointsFinished) {
        this.randomPointsFinished = randomPointsFinished;
    }

    public boolean isRandomPointsFinished() {
        return randomPointsFinished;
    }

    public void setIntroFinished(boolean introFinished) {
        this.introFinished = introFinished;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
