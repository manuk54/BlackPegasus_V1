package classes;

import javafx.scene.text.Font;

import java.awt.*;

// class created to save instances and variables;
public class Model {
    //Settings
    private static final int WindowWidth = 1000;
    private static final int WindowHeight = 540;
    private static final Font defaultFont = Font.font("Bell MT",22);

    private boolean introFinished = false;
    private boolean randomPointsFinished = false;
    private String nickname = "def123";

    private String[] textCh1 = {"Chapter 1. The Prison","You wake up in a cell, captain. Of course, you don't remember what the hell happened yesterday, but the yellow cloth of the officers tells you that you are enprisoned by the Spanish fleet.\n" +
            "\n" +
            "You are in one of their fortifications in the Caribbean. God knows which of them...\n" +
            "\n" +
            "Trying to steady up your thoughts, you sit down and see that you're not alone in here. A dark sillhouette in the shades looks up at you from the corner of the room. It doesn't look like a human being until it stands up and walks towards you.\n",
            "- Hello, sir. I've heard much about you. And I've heard that you don't have a ship anymore.\n" +
                    "\n" +
                    "The sillhouette proves to be a huge man with scars and tattooes of different sea beasts.\n" +
                    "\n" +
                    "- I know how to help you out of this situation. I planned the escape out of this place and a few sailors of yours agreed overnight. I only want to be your right hand on your future new ship.\n" +
                    "\n" +
                    "Without saying a word, you give your new acquaintance a sign of agreement. However, you can't talk any longer, because another Spanish soldier came to you.\n",
            "As soon as you recover totally, your cellmate tells you what was on his mind. His plan is almost ready. He only needed a skillful captain to lead him and some more prisoners out of this hell.\n" +
                    "\n" +
                    "In a few days you get to know everyone who takes part in the escape plan. They seem to trust you more than William, your cellmate. That might be what he was talking about.\n" +
                    "\n" +
                    "In a few more nights, everything is ready for your escape. A riot at midnight gets the guards lost and you easily have your way to the small harboir at the fortress. The only way out is to take a ship and move away with all sails up.\n",
    "As soon as you get aboard, you have to take a riffle and shoot back to protect your people from enemy fire. Spanish soldiers are shooting at you, trying to weaken you as much as they can. Kill as many as you can in order to escape.\n" +
            "1) If less than 10 killed: You succeed in escaping but you can't go much too far. Another ship intercepts you and you cannot escape this time (GAME OVER).\n" +
            "2) If 10 to 19 killed: You succeed in escaping. Your crew is enough to maneuver and avoid enemy ships in the harbour or fight them back if necessary. But you will need to stop by a city to gather new sailors.\n" +
            "3) If more than 20 killed: You escape successfully. The ship has enough supplies for a whole month in open sea. You have just enough people to stand and face any problem and take any ship you meet in the Caribbean "};

    String[] textCh2 = {"Having torn off the flag of the Spanish empire, you arrive at an English city in order to gather some new crewmen in your team. Using all of the gold you got on your ship from her previous owners, you fullfill her needs by fortifying the body and gathering more skilled sailors, who though can't be trusted yet.\n" +
            "\n" +
            "After a day full of productive work, if you can say so, you are left in the middle of a small town under the light of the moon. There is only William besides you.\n" +
            "\n" +
            "- You are a good ol' dog, pal. I think I trust you enough to tell you a secret of mine.\n" +
            "\n" +
            "- What secret? - you ask.\n" +
            "\n" +
            "- Hm. - William thinks for a second longer. - Let's first get to the open sea. Have your time tonight. - and he leaves you alone, heading to your ship.\n"};

    String[] choiceCh2 = {"Go to tavern","Return to the ship and sleep"};

    String[] textCh2p1 = {"Chapter 2.1. In the tavern\n" +
            "\n" +
            "Entering the building you watch the things happening inside. A bunch of black musicians singing some hasty shuntee songs, a beautiful ample woman standing at the bar stand, tempting you to come get a drink and have a talk with her. In a corner there is an old man without \n" +
            "a leg telling stories to a bunch of drunk people who gathered around him. In another part of the room you see a few people trying their luck with the dice. You check through your pockets. You have 100 Reals.\n"};

    public static int getWindowWidth() { return WindowWidth; }

    public static int getWindowHeight() { return WindowHeight; }

    public static Font getDefaultFont() { return defaultFont;}

    public String[] getTextCh1(){return textCh1;}

    public String[] getTextCh2(){return textCh2;}

    public String[] getChoiceCh2() {return choiceCh2;}

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
