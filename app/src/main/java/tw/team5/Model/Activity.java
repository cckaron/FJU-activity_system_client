package tw.team5.Model;

public class Activity {

    private String act_category;
    private String act_name;
    private String act_organizer;
    private String act_participant;
    private String act_speaker;

    public Activity(String act_category, String act_name, String act_organizer, String act_participant, String act_speaker) {
        this.act_category = act_category;
        this.act_name = act_name;
        this.act_organizer = act_organizer;
        this.act_participant = act_participant;
        this.act_speaker = act_speaker;
    }

    public String getAct_category() {
        return act_category;
    }

    public void setAct_category(String act_category) {
        this.act_category = act_category;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getAct_organizer() {
        return act_organizer;
    }

    public void setAct_organizer(String act_organizer) {
        this.act_organizer = act_organizer;
    }

    public String getAct_participant() {
        return act_participant;
    }

    public void setAct_participant(String act_participant) {
        this.act_participant = act_participant;
    }

    public String getAct_speaker() {
        return act_speaker;
    }

    public void setAct_speaker(String act_speaker) {
        this.act_speaker = act_speaker;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "act_category='" + act_category + '\'' +
                ", act_name='" + act_name + '\'' +
                ", act_organizer='" + act_organizer + '\'' +
                ", act_participant='" + act_participant + '\'' +
                ", act_speaker='" + act_speaker + '\'' +
                '}';
    }
}
