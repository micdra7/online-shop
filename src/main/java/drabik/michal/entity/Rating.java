package drabik.michal.entity;

public enum Rating {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6);

    private int value;

    private Rating(int value) {
        this.value = value;
    }
}
