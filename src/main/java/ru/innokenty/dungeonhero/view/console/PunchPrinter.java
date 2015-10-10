package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.model.Punch;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class PunchPrinter implements Printer<Punch> {

    @Override
    public boolean accept(Object printable) {
        return printable instanceof Punch;
    }

    @Override
    public String print(Punch punch) {
        return new StringBuilder()
                .append("You punch the beast as hard as you can, you deal ")
                .append(punch.getHeroDamage())
                .append(" damage.\n")
                .append("The creature doesn't stand still though and hits you back dealing ")
                .append(punch.getMonsterDamage())
                .append(" damage!")
                .toString();
    }
}
