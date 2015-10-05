package ru.innokenty.dungeonhero.model;

import ru.innokenty.dungeonhero.view.Printable;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class Fight implements Printable {

    private final Fighter fighter1;
    private final Fighter fighter2;

    public Fight(Fighter fighter1, Fighter fighter2) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
    }

    public Fighter getFighter1() {
        return fighter1;
    }

    public Fighter getFighter2() {
        return fighter2;
    }
}
