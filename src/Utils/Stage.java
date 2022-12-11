package Utils;

public enum Stage{
    Fourth("4"),
    Third("3"),
    Second("2"),
    First("1");

    private String stage;

    Stage(String  stage) {
        this.stage = stage;
    }

    public String toString() {
        return stage;
    }
}
