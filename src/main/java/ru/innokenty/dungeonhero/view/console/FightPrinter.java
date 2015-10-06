package ru.innokenty.dungeonhero.view.console;

import ru.innokenty.dungeonhero.model.Fight;
import ru.innokenty.dungeonhero.model.Fighter;

/**
 * @author Innokenty Shuvalov innokenty@yandex-team.ru
 */
public class FightPrinter implements Printer<Fight> {

    private static final FightPrinter instance = new FightPrinter();

    public static FightPrinter getInstance() {
        return instance;
    }

    private FightPrinter() {
    }

    @Override
    public String stringify(Fight fight) {
        StringBuilder builder = new StringBuilder();
        appendFighter(builder, fight.getHero());
        builder.append("\n=========== VS ===========\n");
        appendFighter(builder, fight.getMonster());
        return builder.toString();
    }

    private void appendFighter(StringBuilder builder, Fighter fighter) {
        builder.append(fighter.getName()).append("\n")
               .append("HP => ").append(fighter.getHealth()).append("/").append(fighter.getHealthTotal()).append("\n")
               .append("damage => ").append(fighter.getMinDamage()).append("-").append(fighter.getMaxDamage());
    }
}
