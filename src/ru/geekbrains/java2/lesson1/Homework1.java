package ru.geekbrains.java2.lesson1;

public class Homework1 {
    public static void main(String[] args) {
        int units = 3;
        int lets = 2;

        Object[] unit = new Object[units];
        Object[] let = new Object[lets];

        Treadmill road = new Treadmill(88);
        Wall wall = new Wall(1);

        Human human = new Human("Dn", true);
        Cat cat = new Cat("Kuzya", Color.BLACK, true);
        Robot robot = new Robot("Robot", Material.COMPOSITE, true);


        unit[0] = human;
        unit[1] = cat;
        unit[2] = robot;

        let[0] = road;
        let[1] = wall;

        obstacleCourse(let, unit);
    }

    private static void obstacleCourse(Object[] obstacles, Object[] units) {
        for (Object unit : units) {
            String unitName = unit.getClass().getSimpleName();
            for (Object let : obstacles) {
                if (let instanceof Treadmill && getResult(unit)) {
                    if (tryRun(let, unit)) {
                        System.out.println(unitName + " is tryRun and did it");
//                        System.out.println(getResult((Object)let,"run"));
                    } else {
                        System.out.println(unitName + " couldn't run AND REMOVED FROM RACE!");
                    }
                } else if (let instanceof Wall && getResult(unit)) {
                    if (tryJump(let, unit)) {
                        System.out.println(unitName + " is tryJump and did it");
                    } else {
                        System.out.println(unitName + " couldn't run AND REMOVED FROM RACE!");
                    }
                }
            }
            System.out.println("-----------------------------------------");
        }
    }


    private static boolean tryRun(Object let, Object unit) {
        if (unit instanceof Run) {
            boolean tryRun = ((Treadmill) let).canRun((Run) unit);
            setResult(unit, tryRun);
            return tryRun;
        }
        return false;
    }

    private static boolean tryJump(Object let, Object unit) {
        if (unit instanceof Jump) {
            boolean tryJump = ((Wall) let).canJump((Jump) unit);
            setResult(unit, tryJump);
            return tryJump;
        }
        return false;
    }

    private static void setResult(Object unit, boolean attempts) {
        if (unit instanceof Human) {
            ((Human) unit).setPass(attempts);
        } else if (unit instanceof Cat) {
            ((Cat) unit).setPass(attempts);
        } else if (unit instanceof Robot) {
            ((Robot) unit).setPass(attempts);
        }
    }

    private static boolean getResult(Object unit) {
        if (unit instanceof Human) {
            return ((Human) unit).isPass();
        } else if (unit instanceof Cat) {
            return ((Cat) unit).isPass();
        } else if (unit instanceof Robot) {
            return ((Robot) unit).isPass();
        }
        return false;
    }

}
