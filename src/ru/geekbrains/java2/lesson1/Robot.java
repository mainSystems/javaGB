package ru.geekbrains.java2.lesson1;

public class Robot implements Run, Jump {
    private final String codeMame;
    private final Material material;
    private boolean pass;

    public Robot(String codeMame, Material material, boolean pass) {
        this.codeMame = codeMame;
        this.material = material;
        this.pass = pass;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    @Override
    public int jump() {
        return 0;
    }

    @Override
    public int run() {
        return 1000;
    }
}
