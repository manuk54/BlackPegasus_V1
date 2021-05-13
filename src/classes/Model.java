package classes;

// class created to save instances and variables;
public class Model {
    private boolean introFinished = false;
    private boolean randomPointsFinished = false;
    private String nickname = "def";

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
